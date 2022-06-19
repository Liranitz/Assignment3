package Tiles.Units.Enemy;

import CallBacks.EnemyDeathCallback;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;

public abstract class Enemy extends Unit  {

    protected int experienceValue;
    protected EnemyDeathCallback edCallback;
    public Enemy(char tile,String name,int healthPool,int attackPoints,int defensePoints,int experienceValue){
        super(tile,name,healthPool,attackPoints,defensePoints);
        this.experienceValue =experienceValue;
    }

    public void SetDeathCallback(EnemyDeathCallback edCallback){
        this.edCallback=edCallback;
    }

    public void call() {

    }

    @Override
    public void accept(Unit unit) {

    }
    public abstract void processStep() ;

    @Override
    public void onDeath() {
        edCallback.call();
    }

    @Override
    public void visit(Player p) {
    battle(p);
    }

    @Override
    public void visit(Enemy e) {

    }
}
