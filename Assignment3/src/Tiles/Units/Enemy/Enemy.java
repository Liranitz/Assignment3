package Tiles.Units.Enemy;

import CallBacks.EnemyDeathCallback;
import CallBacks.MessageCallback;
import MainProject.Action;
import Tiles.Empty;
import Tiles.Position;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;

public abstract class Enemy extends Unit  {

    protected int experienceValue;
    protected EnemyDeathCallback edCallback;
    public Enemy(char tile,String name,int healthPool,int attackPoints,int defensePoints,int experienceValue){
        super(tile,name,healthPool,attackPoints,defensePoints);
        this.experienceValue =experienceValue;
    }

    @Override
    public void initialize(Position position) {
        super.initialize(position);
    }
    public int getExperienceValue(){ return experienceValue;}
    public void SetEnemyDeathCallback(EnemyDeathCallback ed ){
       this.edCallback=ed;
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    public abstract Action EnemyTurn(Player player) ;

    public void onDeath(){
        edCallback.call();
    }

    @Override
    public void visit(Player p)
    {
        battle(p);
        if(!p.IsAlive())
            p.onDeath();
    }

    @Override
    public void visit(Enemy e) {}

}
