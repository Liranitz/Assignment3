package Tiles.Units;

import CallBacks.MessageCallback;
import Tiles.Empty;
import Tiles.Position;
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

    protected void initialize(Position position, MessageCallback messageCallback){
        super.initialize(position);
        this.messageCallback = messageCallback;
    }
	
    protected int attack(){
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

    public void AddAmount(int amount){
        health.amount = health.amount+amount;
    }
    public void ReduceAmount(int amount){
        health.amount = health.amount-amount;
    }


	// Should be automatically called once the unit finishes its turn
    public abstract void processStep();
	
	// What happens when the unit dies
    public abstract void onDeath();

	// This unit attempts to interact with another tile.
    public void interact(Tile tile){
        tile.accept(this);
    }

    public void visit(Empty e){
        SwapPositions(e);
    }

    public  void SwapPositions(Tile tile){
        Position p =tile.getPosition();
        tile.setPosition(this.getPosition());
        this.setPosition(p);
    }
    public boolean IsAlive(){
        return health.amount>0;
    }

    public void visit(Wall e){
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);


	// Combat against another unit.
    protected void battle(Unit u){
        messageCallback.send(String.format("%s engaged in combat with %s.\n%s\n%s",name,u.name,describe(),u.describe()));
        int damageDone = Math.max(attack() - u.defend(),0);
        u.health.ReduceAmount(damageDone);
        messageCallback.send(String.format("%s dealt %d damage to %s.",name,damageDone,u.name));
    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", name, health.getAmount(), attackPoints, defensePoints);
    }
}
