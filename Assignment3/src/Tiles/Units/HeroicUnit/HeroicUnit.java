package Tiles.Units.HeroicUnit;

import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;

public class HeroicUnit extends Unit {
    protected HeroicUnit(char tile, String name, int healthPool, int attack, int defense) {
        super(tile, name, healthPool, attack, defense);
    }

    @Override
    public void accept(Unit unit) {

    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }
}
