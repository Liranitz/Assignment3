package Tiles.Units.Players;

import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;

import java.util.List;

public class Rogue extends Player {
    private Integer cost;
    private Integer currentEnergy;
    public Rogue(String name , Integer pool , Integer defence, Integer attack ,Integer cost) {
        super(pool , name , attack , defence);
        this.cost = cost;
        this.currentEnergy = 100;
    }

    public void LevelUp(){
        super.LevelUp();
        currentEnergy = 100;
        attackPoints = attackPoints + 3;
    }
    public void GameTick(Tile e){
        int tempEnergy = currentEnergy + 10;
        if(tempEnergy < 100)
            currentEnergy = tempEnergy;
        else{
            currentEnergy = 100;
        }
        e.accept(this);
    }
    public void AbilityCast(List<Enemy> enemyList){
        currentEnergy = currentEnergy - cost;
        //...
    }
}