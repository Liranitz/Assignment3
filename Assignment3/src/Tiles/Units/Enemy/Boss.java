package Tiles.Units.Enemy;

import MainProject.Action;
import Tiles.Units.Enemy.Monster;
import Tiles.Units.HeroicUnit.HeroicUnit;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;

public class Boss extends Monster implements HeroicUnit {
    protected int abilityFrequency;
    private int combatTicks;

    public Boss(char tile, String name, int healthPool, int attack, int defense,int experienceValue,int visionRange,int abilityFrequency) {
        super(tile, name, healthPool, attack, defense ,experienceValue,visionRange);
        this.abilityFrequency=abilityFrequency;
        this.combatTicks=0;
    }

    @Override
    public Action enemyTurn(Player player) {
        double distance = this.getPosition().Range(player.getPosition());
        Action act = super.enemyTurn(player);
        if (distance < visionRange) {
            if (combatTicks == abilityFrequency) {
                combatTicks = 0;
                AbilityCast(player);
                return Action.NOTHING;
            }
            else
                combatTicks++;
        }
        else
            combatTicks=0;
        return act;
    }

    @Override
    public void AbilityCast(Unit u) {
        messageCallback.send(String.format("%s activated special ability on %s.",name,u.getName()));
        int damageDone = Math.max(attackPoints - u.defend(),0);
        u.getHealth().reduceAmount(damageDone);
        messageCallback.send(String.format("%s dealt %d damage to %s.",name,damageDone,u.getName()));

    }
}
