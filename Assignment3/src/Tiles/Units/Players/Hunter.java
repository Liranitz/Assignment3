package Tiles.Units.Players;

import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Resource;

import java.util.List;
import java.util.stream.Collectors;

public class Hunter extends Player {
    private Integer range;
    private Resource arrows;
    private Integer ticksCount;
    private Integer cost;

    public Hunter(String name , Integer pool , Integer attack, Integer defence ,Integer range) {
        super(pool , name , attack , defence);
        this.range = range;
        arrows=new Resource(Integer.MAX_VALUE);
        arrows.updateAmount(10*level);
        ticksCount = 0;
    }

    public void levelUp(){
        super.levelUp();
        attackPoints = attackPoints + 2*level;
        defensePoints = defensePoints +level;
        arrows.updateAmount(arrows.getAmount()+(10*level));
    }
    public void gameTick(Tile e){
        if(ticksCount == 10){
            arrows.updateAmount(arrows.getAmount()+level);
            ticksCount = 0;
        }
        else{
            ticksCount++;
        }
        e.accept(this);
    }
    public void abilityCast(List<Enemy> enemyList){
        if(arrows.getAmount()>0) {
                enemyList = enemyList.stream().filter(x -> this.position.Range(x.getPosition()) < range ).collect(Collectors.toList());
            if (!enemyList.isEmpty()) {
                enemyList = enemyList.stream().sorted((x, y) -> (int) Math.min(this.position.Range(x.getPosition()), (this.position.Range(y.getPosition())))).collect(Collectors.toList());
                Enemy e = enemyList.get(0);
                messageCallback.send(String.format("%s cast Shoot on : %s.", name, e.getName()));
                int eDef = e.defend();
                int damage = attackPoints - eDef;
                e.getHealth().reduceAmount(damage);
                messageCallback.send(String.format("%s dealt %d damage to %s.", name, damage, e.getName()));
                arrows.updateAmount(arrows.getAmount()-1);
                if (!e.IsAlive())
                    onKill(e);
            }
        }
    }
    public String describe() {
        String describe = super.describe();
        return describe+String.format("\t\tArrows : %d\t\t TicksCount :%d\t Range: %d\t" ,  arrows.getAmount(), ticksCount , range);
    }
}