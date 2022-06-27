package Tiles.Units.Players;

import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Resource;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Warrior extends Player {
    private Resource cooldown;

    public Warrior(String name , Integer pool , Integer attack, Integer defence ,Integer abilityCooldown) {
        super(pool, name , attack, defence);
        cooldown = new Resource(abilityCooldown);
        cooldown.updateAmount(0);
    }

    public void gameTick(Tile t){
        if(cooldown.getAmount()>0)
            cooldown.updateAmount(cooldown.getAmount()-1);
        t.accept(this);
    }

    @Override
    public void abilityCast(List<Enemy> enemyList) {
        if(cooldown.getAmount() == 0) {
            cooldown.updateAmount(cooldown.getPool());
            health.updateAmount(Math.min(health.getAmount() + 10 * defensePoints, health.getPool()));
            List<Enemy> enemies = enemyList.stream().filter(x -> this.position.Range(x.getPosition()) < 3 ).collect(Collectors.toList());
            if(enemies.size() > 0) {
                Random r = new Random();
                int i = r.nextInt(enemies.size());
                Enemy e = enemies.get(i);
                int eDef = e.defend();
                int damage = (int) (this.health.getPool()*(0.1)) - eDef;
                messageCallback.send(String.format("%s cast Avenger’s Shield on : %s.",name,e.getName()));
                e.getHealth().reduceAmount(damage);
                messageCallback.send(String.format("%s dealt %d damage to %s.",name,damage,e.getName()));
                if(!e.IsAlive())
                    this.onKill(e);
                this.health.updateAmount(this.health.getAmount() + 10 * defensePoints);
            }
        }
        else{
            messageCallback.send("Cooldown has not reached to the required amount");
        }
    }

    public void levelUp(){
        super.levelUp();
        super.health.updatePool(health.getPool() + 5*level);
        super.attackPoints += level*2;
        super.defensePoints += level;
        cooldown.updateAmount(0);
    }

    public String describe() {
        String describe = super.describe();
        return describe+String.format("\tAbility Cooldown : %d/%d" ,  cooldown.getAmount(), cooldown.getPool());
    }

}
