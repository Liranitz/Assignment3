package Tiles.Units;

import CallBacks.MessageCallback;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Players.Player;
import Tiles.Wall;

import java.util.Random;

public abstract class Unit extends Tile {
	protected String name;
    protected Resource health;
    protected int attackPoints;
    protected int defensePoints;
    protected MessageCallback messageCallback;

    protected Unit(char tile, String name, int healthPool, int attack, int defense) {
        super(tile);
        this.name = name;
        this.health = new Resource(healthPool);
        this.attackPoints = attack;
        this.defensePoints = defense;
    }

    public void initialize(Position position){
        super.initialize(position);
    }
	
    public int attack(){
        Random r = new Random();
		int result =  r.nextInt(attackPoints);
        messageCallback.send(String.format("%s rolled %d attack points.",name,result));
        return result;
    }

    public int defend(){
        Random r = new Random();
        int result =  r.nextInt(defensePoints);
        messageCallback.send(String.format("%s rolled %d defense points.",name,result));
        return result;
    }

    public void setMessageCallback(MessageCallback messageCallback){
        this.messageCallback = messageCallback;
    }


    public void swapPositions(Tile tile){
        Position p = tile.getPosition();
        tile.setPosition(this.getPosition());
        this.setPosition(p);
    }
    public boolean IsAlive(){
        return health.getAmount()>0;
    }

    public void visit(Wall w){}
    public void visit(Empty e){
        swapPositions(e);
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);


	// Combat against another unit.
    public void battle(Unit u){
            messageCallback.send(String.format("%s engaged in combat with %s.\n%s\n%s",name,u.name,describe(),u.describe()));
            int damageDone = Math.max(attack() - u.defend(),0);
            u.health.reduceAmount(damageDone);
            messageCallback.send(String.format("%s dealt %d damage to %s.",name,damageDone,u.name));
    }
    public String describe() {
        return String.format("%s\t\tHealth: %d/%d\t\tAttack: %d\t\tDefense: %d\t\t", name, health.getAmount(),health.getPool(), attackPoints, defensePoints);
    }
    public String getName(){
        return name;
    }
    public Resource getHealth(){
        return health;
    }
    public int getAttackPoints(){ return attackPoints;}
    public int getDefensePoints(){ return defensePoints;}
}
