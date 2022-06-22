package Tiles.Units.Players;

import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        currentEnergy = Math.min(tempEnergy, 100);
        e.accept(this);
    }
    public void AbilityCast(List<Enemy> enemyList) {
        if (currentEnergy >= cost) {
            currentEnergy = currentEnergy - cost;
            List<Enemy> enemies = enemyList.stream().filter(x -> this.position.Range(x.getPosition()) < 2).collect(Collectors.toList());
            for (Enemy e : enemies) {
                e.ReduceAmount(attackPoints - e.defend());
                if (!e.IsAlive())
                    this.onKill(e);
            }
        }
        else {
            messageCallback.send(" not enough energy");
        }
    }
}