package Tiles.Units.Players;

import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Warrior extends Player {
    private  Integer abilityCooldown;
    private  Integer remainingCooldown;
    public Warrior(String name , Integer pool , Integer defence, Integer attack ,Integer abilityCooldown) {
        super(pool, name , attack, defence);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }

    public void GameTick(Tile t){
        if(remainingCooldown>0)
             remainingCooldown = remainingCooldown - 1;
        t.accept(this);
    }

    @Override
    public void AbilityCast(List<Enemy> enemyList) {
        if(remainingCooldown == 0) {
            remainingCooldown = abilityCooldown;
            health.UpdateAmount(Math.min(health.getAmount() + 10 * defensePoints, health.getPool()));
            List<Enemy> enemies = enemyList.stream().filter(x -> this.position.Range(x.getPosition()) < 3 ).collect(Collectors.toList());
            if(enemies.size() > 0) {
                Random r = new Random();
                int i = r.nextInt(enemies.size());
                enemies.get(i).ReduceAmount(this.health.getPool());
                this.health.UpdateAmount(this.health.getAmount() + 10 * defensePoints);
            }
        }
        else{
            messageCallback.send("Cooldown has not reached to the required amount");
        }
    }

    public void LevelUp(){
        super.LevelUp();
        remainingCooldown = 0;
        super.health.AddPool(5*level);
        super.attackPoints += 1*level;
    }
}
