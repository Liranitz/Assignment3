package Tiles.Units.Players;

import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Resource;

import java.util.List;
import java.util.stream.Collectors;

public class Rogue extends Player {
    private Integer cost;
    private Resource energy;
    public Rogue(String name , Integer HealthPool , Integer defence, Integer attack ,Integer cost) {
        super(HealthPool , name , attack , defence);
        this.cost = cost;
        energy = new Resource(100);
    }

    public void levelUp(){
        super.levelUp();
        energy.updateAmount(100);
        attackPoints = attackPoints + 3*getLevel();
    }
    public void gameTick(Tile e){
        int tempEnergy = energy.getAmount() + 10;
        energy.updateAmount(Math.min(tempEnergy, 100));
        e.accept(this);
    }
    public void abilityCast(List<Enemy> enemyList) {
        int currentEnergy = energy.getAmount();
        if (currentEnergy >= cost) {
            energy.updateAmount(currentEnergy - cost);
            List<Enemy> enemies = enemyList.stream().filter(x -> this.position.Range(x.getPosition()) < 2).collect(Collectors.toList());
            for (Enemy e : enemies) {
                messageCallback.send(String.format("%s cast Fan of Knives on : %s.",name,e.getName()));
                int eDef = e.defend();
                int damage = attackPoints - eDef;
                e.getHealth().reduceAmount(damage);
                messageCallback.send(String.format("%s dealt %d damage to %s.",name,damage,e.getName()));
                if (!e.IsAlive())
                   onKill(e);
            }
        }
        else {
            messageCallback.send(" not enough energy");
        }
    }
    public String describe() {
        String describe = super.describe();
        return describe+String.format("\n\t Energy: %d/%d\t Special Ability cost: %d", energy.getAmount(), energy.getPool(), cost);
    }
}