package Tiles.Units.Players;

import Tiles.Units.Enemy.Enemy;

import java.util.List;
import java.util.Random;

public class Hunter extends Player {
    private Integer range;
    private Integer arrowsCount;
    private Integer ticksCount;
    private Integer cost;
    private Integer currentEnergy;
    public Hunter(String name , Integer pool , Integer attack, Integer defence ,Integer range) {
        super(pool , name , attack , defence);
        this.range = range;
        this.currentEnergy = 100;
        arrowsCount = 10*level;
        ticksCount = 0;
    }

    public void LevelUp(){
        super.Levelup();
        attackPoints = attackPoints + 2*level;
        defensePoints = defensePoints +level;
    }
    public void GameTick(){
        if(ticksCount == 10){
            arrowsCount = arrowsCount + level;
            ticksCount = 0;
        }
        else{
            ticksCount++;
        }
    }
    public void AbilityCast(List<Enemy> enemyList){
        if(!enemyList.isEmpty()){
            int numberOfEnemies = enemyList.size();
            for (Enemy e : enemyList) {
                Random r = new Random();
                int result = r.nextInt(numberOfEnemies);
            }
        }
    }
}
