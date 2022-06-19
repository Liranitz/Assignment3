package Tiles.Units.HeroicUnit;

import Tiles.Units.Enemy.Monster;

public class Boss extends Monster implements HeroicUnit {
    protected int abilityFrequency;

    public Boss(char tile, String name, int healthPool, int attack, int defense,int experienceValue,int visionRange,int abilityFrequency) {
        super(tile, name, healthPool, attack, defense ,experienceValue,visionRange);
        this.abilityFrequency=abilityFrequency;
    }

    @Override
    public void CastAbility() {

    }
}
