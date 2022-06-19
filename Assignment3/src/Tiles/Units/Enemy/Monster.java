package Tiles.Units.Enemy;

import MainProject.Action;
import Tiles.Units.Players.Player;

public class Monster extends Enemy {

    protected int visionRange;

    public Monster(char tile, String name, int healthPool, int attackPoints, int defensePoints,int experinceValue, int visionRange) {
        super(tile, name, healthPool, attackPoints, defensePoints,experinceValue);
        this.visionRange=visionRange;
    }

    public void Move() {

    }

    @Override
    public void processStep(Player player) {

    }

    public Action EnemyTurn(Player player) {
        double distance = this.getPosition().Range(player.getPosition());
         if(distance<visionRange) {
            int dx = getPosition().getXCoordinate()-player.getPosition().getXCoordinate();
            int dy = getPosition().getYCoordinate()-player.getPosition().getYCoordinate();
            if(Math.abs(dx)>Math.abs(dy))
                if(dx>0)
                    return Action.DOWN;


        }


        }
    }
    public bool
}
