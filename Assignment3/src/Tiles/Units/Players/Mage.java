package Tiles.Units.Players;

import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Resource;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Mage extends Player {
    private Resource mana;
    private Integer manaCost;
    private Integer spellPower;
    private Integer hitsCount;
    private Integer abilityRange;

    public Mage(String name , Integer HealthPool , Integer attack, Integer defence, Integer manaPool ,Integer ManaCost,Integer SpellPower , Integer HitCount , Integer AbilityRange) {
        super(HealthPool, name,attack, defence);
        this.mana = new Resource(manaPool);
        this.mana.updateAmount(manaPool/4);
        this.spellPower = SpellPower;
        this.hitsCount = HitCount;
        this.abilityRange = AbilityRange;
        this.manaCost = ManaCost;
    }

    public void levelUp(){
        super.levelUp();
        this.mana.updatePool(this.mana.getPool() + 25*level);
        this.mana.updateAmount(Math.min(mana.getPool(),mana.getAmount() + mana.getPool() / 4));
        spellPower = spellPower + 10*level;
    }
    public void gameTick(Tile e) {
        this.mana.updateAmount(Math.min(mana.getPool(),mana.getAmount() + level));
        e.accept(this);
    }

    public void abilityCast(List<Enemy> enemyList) {
        if(mana.getAmount() <= manaCost) {
            mana.updateAmount(mana.getAmount() - manaCost);
            int hits = 0;
            while(hits < hitsCount){
                List<Enemy> enemies = enemyList.stream().filter(x -> this.position.Range(x.getPosition()) < abilityRange ).collect(Collectors.toList());
                if(enemies.size() > 0) {
                    Random r = new Random();
                    int i = r.nextInt(enemies.size());
                    Enemy e = enemies.get(i);
                    messageCallback.send(String.format("%s cast Blizzard on : %s.",name,e.getName()));
                    int eDef = e.defend();
                    int damage = this.spellPower - eDef;
                    e.getHealth().reduceAmount(damage);
                    messageCallback.send(String.format("%s dealt %d damage to %s.",name,damage,e.getName()));
                    if(!e.IsAlive()){
                        this.onKill(e);
                    }
                }
                hits++;
            }
        }
        else{
            messageCallback.send(String.format("Player %s does not have enough mana to cast special abilty ",name));
        }
    }
    @Override
    public String describe() {
        String describe = super.describe();
        return describe+String.format("\n\t Mana: %d/%d\t Special Ability cost: %d\t" +
                " Spell Power : %d\t Hits Count : %d\t Ability Range : %d"
                , mana.getAmount(), mana.getPool(), manaCost , spellPower , hitsCount , abilityRange);
    }
}
