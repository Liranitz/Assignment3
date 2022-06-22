package Tiles.Units.Players;

import CallBacks.EnemyDeathCallback;
import CallBacks.MessageCallback;
import CallBacks.PlayerDeathCallback;
import MainProject.Action;
import MainProject.InputProvider;
import Tiles.Position;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Unit;

import java.util.List;

public abstract class Player extends Unit{
    Integer experience;
    Integer level;
    private PlayerDeathCallback pdCallback;
    private InputProvider inputProvider;

    public Player (Integer pool,  String name , Integer attack, Integer defence) {
        super('@',name , pool , attack, defence);
        this.experience = 0;
        this.level = 1;
        inputProvider = new InputProvider();
    }

    public abstract void GameTick(Tile t);

    public void initialize(Position position){
        super.initialize(position);

    }

    public void SetPlayerDeathCallback(PlayerDeathCallback pdCallback){
        this.pdCallback=pdCallback;
    }

    public void LevelUp(){
        health.AddPool(10 * level);
        level++;
        experience = experience - (50 * level);
        health.AddPool(health.getPool() + (10 * level));
        health.UpdateAmount(health.getPool());
        attackPoints = attackPoints + (4 * level);
        defensePoints = defensePoints + (level);
    }

    public abstract void AbilityCast(List<Enemy> enemyList);


    public Action GetInput() {
        return inputProvider.getInput();
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void onDeath() {
        pdCallback.call();
    }

    public void AddExp(Integer exp){
        this.experience += exp;
    }

    public void onKill(Enemy enemy) {
        this.experience += enemy.getExperienceValue();
        this.swapPosition(enemy);
        if(experience>=50*level)
            LevelUp();
    }

    @Override
    public void visit(Player p) {}

    @Override
    public void visit(Enemy e) {
        battle(e);
        if (!e.IsAlive())
            onKill(e);
    }

}
