package Tiles.Units.Players;

import CallBacks.PlayerDeathCallback;
import MainProject.Action;
import MainProject.InputProvider;
import Tiles.Units.Position;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Resource;
import Tiles.Units.Unit;

import java.util.List;

public abstract class Player extends Unit  {
    private Resource experience;
    protected Integer level;
    private PlayerDeathCallback pdCallback;
    private InputProvider inputProvider;

    public Player (Integer pool,  String name , Integer attack, Integer defence) {
        super('@',name , pool , attack, defence);
        this.level = 1;
        experience = new Resource(50*level);
        experience.updateAmount(0);
        inputProvider = new InputProvider();
    }
    public void levelUp(){
        health.updatePool(10 * level);
        experience.updateAmount(experience.getAmount()-(50*level));
        level++;
        experience.updatePool(50*level);
        health.updatePool(health.getPool() + (10 * level));
        health.updateAmount(health.getPool());
        attackPoints = attackPoints + (4 * level);
        defensePoints = defensePoints + (level);
        messageCallback.send(String.format("Player %s reached level %d +%d Health  +%d Attack  +%d Defence",name,level,10 * level,4*level,level));
    }

    public abstract void gameTick(Tile t);

    public void initialize(Position position){
        super.initialize(position);
    }

    public void setPlayerDeathCallback(PlayerDeathCallback pdCallback){
        this.pdCallback=pdCallback;
    }



    public abstract void abilityCast(List<Enemy> enemyList);

    public Action getInput() {
        return inputProvider.getInput();
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void onDeath() {
        tile='X';
        pdCallback.call();
    }

    public void onKill(Enemy enemy) {
        messageCallback.send(String.format("Enemy %s killed by %s\n%s has gained +%d experience",enemy.getName(),name,name,enemy.getExperienceValue()));
        experience.updateAmount(experience.getAmount()+enemy.getExperienceValue());
        enemy.onDeath();
            while(experience.getAmount()>=50*level)
                levelUp();

    }

    @Override
    public void visit(Player p) {}

    @Override
    public void visit(Enemy e) {
        battle(e);
        if (!e.IsAlive()) {
            swapPosition(e);
            onKill(e);

        }
    }
    public int getLevel(){
        return level;
    }
    public int getExperience(){
        return experience.getAmount();
    }
    @Override
    public String describe() {
        String describe = super.describe();
        return describe+String.format(" Level: %d\t\t Experience: %d/%d", level, experience.getAmount(), experience.getPool());
    }
}
