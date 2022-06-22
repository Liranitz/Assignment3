package Tiles.Units.Enemy;

import MainProject.Action;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;

import java.util.Random;

public class Monster extends Enemy {

    protected int visionRange;

    public Monster(char tile, String name, int healthPool, int attackPoints, int defensePoints, int experinceValue, int visionRange) {
        super(tile, name, healthPool, attackPoints, defensePoints, experinceValue);
        this.visionRange = visionRange;
    }

    public Action EnemyTurn(Player player) {
        double distance = this.getPosition().Range(player.getPosition());
        if (distance < visionRange) {
            int dx = getPosition().getXCoordinate() - player.getPosition().getXCoordinate();
            int dy = getPosition().getYCoordinate() - player.getPosition().getYCoordinate();
            if (Math.abs(dx) > Math.abs(dy)) { //monster sees player
                if (dx > 0)
                    return Action.LEFT;
                else
                    return Action.RIGHT;
            } else {
                if (dy > 0)
                    return Action.UP;
                else
                    return Action.DOWN;
            }
        }
        else  // Random move
            RandomMovement();
        return Action.NOTHING;
    }

    public Action RandomMovement() {
        Random random = new Random();
        int num = random.nextInt(5);
        switch (num) {
            case 0:
                return Action.NOTHING;
            case 1:
                return Action.DOWN;
            case 2:
                return Action.UP;
            case 3:
                return Action.RIGHT;
            case 4:
                return Action.LEFT;
        }
        return Action.NOTHING;
    }
}
