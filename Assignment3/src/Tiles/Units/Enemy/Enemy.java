package Tiles.Units.Enemy;

import CallBacks.EnemyDeathCallback;
import MainProject.Action;
import MainProject.Direction;
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
       edCallback.call();
    }

    @Override
    public void accept(Unit unit) {

    }
    public abstract void processStep(Player player) ;

    public abstract Action EnemyTurn(Player player) ;

    @Override
    public void onDeath() {
        edCallback.call();
    }

    @Override
    public void visit(Player p) {battle(p);}

    @Override
    public void visit(Enemy e) {}
}
