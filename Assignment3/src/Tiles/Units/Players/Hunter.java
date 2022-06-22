package Tiles.Units.Players;

import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        super.LevelUp();
        attackPoints = attackPoints + 2*level;
        defensePoints = defensePoints +level;
    }
    public void GameTick(Tile e){
        if(ticksCount == 10){
            arrowsCount = arrowsCount + level;
            ticksCount = 0;
        }
        else{
            ticksCount++;
        }
        e.accept(this);
    }
    public void AbilityCast(List<Enemy> enemyList){
        arrowsCount = arrowsCount - 1;
        if(!enemyList.isEmpty()){
            List<Enemy> enemies = enemyList.stream().sorted((x,y) -> (int) Math.min(this.position.Range(x.getPosition()),(this.position.Range(y.getPosition())))).collect(Collectors.toList());
            int numberOfEnemies = enemyList.size();
            Enemy e = enemies.get(0);
            e.ReduceAmount(this.attackPoints - e.defend());
            if (!e.IsAlive())
                this.AddExp(e.getExperienceValue());
        }
    }
}