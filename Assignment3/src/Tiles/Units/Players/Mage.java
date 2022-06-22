package Tiles.Units.Players;

import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Mage extends Player {
    private Integer manaPool;
    private Integer currentMana;
    private Integer manaCost;
    private Integer spellPower;
    private Integer hitsCount;
    private Integer abilityRange;

    public Mage(String name , Integer HealthPool , Integer attack, Integer defence, Integer ManaPool ,Integer ManaCost,Integer SpellPower , Integer HitCount , Integer AbilityRange) {
        super(HealthPool, name,attack, defence);
        this.manaPool = ManaPool;
        this.currentMana = manaPool / 4;
        this.spellPower = SpellPower;
        this.hitsCount = HitCount;
        this.abilityRange = AbilityRange;
        this.manaCost = ManaCost;
    }

    public void LevelUp(){
        super.LevelUp();
        manaPool = manaPool + 25*level;
        int curMana1 = currentMana + manaPool / 4;
        if(curMana1 > manaPool)
            currentMana = manaPool;
        else{
            currentMana = curMana1;
        }
        spellPower = spellPower + 10*level;
    }
    public void GameTick(Tile e) {
        int curMana1 = currentMana + level;
        if (curMana1 > manaPool) {
            currentMana = manaPool;
        } else {
            currentMana = curMana1;
        }
        e.accept(this);
    }

    public void AbilityCast(List<Enemy> enemyList) {
        if(currentMana <= manaCost) {
            currentMana = currentMana - manaCost;
            int hits = 0;
            while(hits < hitsCount){
                List<Enemy> enemies = enemyList.stream().filter(x -> this.position.Range(x.getPosition()) < abilityRange ).collect(Collectors.toList());
                if(enemies.size() > 0) {
                    Random r = new Random();
                    int i = r.nextInt(enemies.size());
                    Enemy e = enemies.get(i);
                    e.ReduceAmount(this.spellPower - enemies.get(i).defend());
                    if(!e.IsAlive()){
                        this.onKill(e);
                    }
                }
                hits++;
            }
        }
        else{
            messageCallback.send("not enough mana");
        }
    }
}
