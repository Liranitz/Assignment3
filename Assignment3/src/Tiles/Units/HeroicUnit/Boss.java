package Tiles.Units.HeroicUnit;

import MainProject.Action;
import Tiles.Units.Enemy.Monster;
import Tiles.Units.Players.Player;

public class Boss extends Monster implements HeroicUnit {
    protected int abilityFrequency;
    private int combatTicks;

    public Boss(char tile, String name, int healthPool, int attack, int defense,int experienceValue,int visionRange,int abilityFrequency) {
        super(tile, name, healthPool, attack, defense ,experienceValue,visionRange);
        this.abilityFrequency=abilityFrequency;
        this.combatTicks=0;
    }

    @Override
    public Action EnemyTurn(Player player) {
        double distance = this.getPosition().Range(player.getPosition());
        Action act = super.EnemyTurn(player);
        if (distance < visionRange) {
            if (combatTicks == abilityFrequency) {
                combatTicks = 0;
                AbilityCast(player);
            }
            else
                combatTicks++;
        }
        else
            combatTicks=0;
        return act;
    }

    @Override
    public void AbilityCast(Player player) {
        player.accept(this);
    }
}
