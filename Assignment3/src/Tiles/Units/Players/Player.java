package Tiles.Units.Players;

import MainProject.InputProvider;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Unit;

import java.util.List;

public abstract class Player extends Unit{
    Integer experience;
    Integer level;
    private InputProvider inputProvider;

    public Player (Integer pool,  String name , Integer attack, Integer defence) {
        super('@',name , pool , attack, defence);
        this.experience = 0;
        this.level = level;
        inputProvider = new InputProvider();
    }
    public void Levelup(){
        health.AddPool(10 * level);
        level++;
        experience = experience - (50 * level);
        health.AddPool(health.getPool() + (10 * level));
        health.UpdateAmount(health.getPool());
        attackPoints = attackPoints + (4 * level);
        defensePoints = defensePoints + (1 * level);
    }
    public abstract void GameTick();
    public abstract void AbilityCast(List<Enemy> enemyList);


    public char GetInput() {
        return inputProvider.getInput();
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {
        messageCallback.send("Game have neem finished.");
    }

    @Override
    public void visit(Player p) {
        return;
    }

    @Override
    public void visit(Enemy e) {
    battle(e);
    }
}
