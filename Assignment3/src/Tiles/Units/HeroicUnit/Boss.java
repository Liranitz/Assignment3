package Tiles.Units.HeroicUnit;

import MainProject.Action;
import Tiles.Units.Enemy.Monster;
import Tiles.Units.Players.Player;

public class Boss extends Monster implements HeroicUnit {
    protected int abilityFrequency;

    public Boss(char tile, String name, int healthPool, int attack, int defense,int experienceValue,int visionRange,int abilityFrequency) {
        super(tile, name, healthPool, attack, defense ,experienceValue,visionRange);
        this.abilityFrequency=abilityFrequency;
    }

    @Override
    public Action EnemyTurn(Player player) {
        Action act = super.EnemyTurn(player);
        //if red conditoins true
        // then castAbilty();
        return  act;


    }


    @Override
    public void CastAbility() {

    }
}
