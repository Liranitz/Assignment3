package Tiles.Units.Players;

public class Mage extends Player {
    private Integer manaPool;
    private Integer currentMana;
    private Integer manaCost;
    private Integer spellPower;
    private Integer hitsCount;
    private Integer abilityRange;
    public Mage(String name , Integer HealthPool , Integer attack, Integer defence, Integer ManaPool ,Integer SpellPower , Integer HitCount , Integer AbilityRange) {
        super(HealthPool, name,attack, defence);
        this.manaPool = ManaPool;
        this.currentMana = manaPool / 4;
        this.spellPower = SpellPower;
        this.hitsCount = HitCount;
        this.abilityRange = AbilityRange;
    }

    public void LevelUp(){
        super.Levelup();
        manaPool = manaPool + 25*level;
        int curMana1 = currentMana + manaPool / 4;
        if(curMana1 > manaPool)
            currentMana = manaPool;
        else{
            currentMana = curMana1;
        }
        spellPower = spellPower + 10*level;
    }
    public void GameTick(){
        int curMana1 = currentMana + level;
        if(curMana1 > manaPool){
            currentMana = manaPool;
        }
        else{
            currentMana = curMana1;
        }
    }

    public void AbilityCast(){
        currentMana = currentMana - manaCost;
        int hits = 0;
        while(hits < hitsCount){

            hits++;
        }
    }
}
