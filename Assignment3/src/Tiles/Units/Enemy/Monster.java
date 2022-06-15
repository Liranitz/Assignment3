package Tiles.Units.Enemy;

public class Monster extends Enemy {

    protected int visionRange;

    public Monster(char tile, String name, int healthPool, int attackPoints, int defensePoints,int visionRange) {
        super(tile, name, healthPool, attackPoints, defensePoints);
        this.visionRange=visionRange;

    }

    @Override
    public void processStep() {

    }
}
