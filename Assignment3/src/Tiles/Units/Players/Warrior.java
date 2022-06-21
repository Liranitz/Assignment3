package Tiles.Units.Players;

import Tiles.Units.Enemy.Enemy;

import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    private  Integer abilityCooldown;
    private  Integer remainingCooldown;
    public Warrior(String name , Integer pool , Integer defence, Integer attack ,Integer abilityCooldown ) {
        super(pool, name , attack, defence);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }

    public void GameTick(){
        remainingCooldown = remainingCooldown - 1;
    }

    @Override
    public void AbilityCast(List<Enemy> enemyList) {

    }

    public void LevelUp(){
        super.Levelup();
        remainingCooldown = 0;
        super.health.AddPool(5*level);
        super.attackPoints += 1*level;
    }
    public void specialAbility(){
        remainingCooldown = abilityCooldown;
        int cur1 = health.getAmount() + 10*defensePoints;
        int cur2 = health.getPool();
        if(cur1 > cur2)
            health.UpdateAmount(cur2);
        else{
            health.UpdateAmount(cur1);
        }
        Random r = new Random();
        int result =  r.nextInt();
        //....
    }

}
