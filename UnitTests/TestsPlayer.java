import CallBacks.MessageCallback;
import MainProject.Action;
import Tiles.Empty;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Enemy.Monster;
import Tiles.Units.Players.Player;
import Tiles.Units.Players.Rogue;
import Tiles.Units.Players.Warrior;
import Tiles.Units.Position;
import Tiles.Tile;
import Tiles.Units.Unit;
import Tiles.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

//import org.testing.annotations.Test;


public class TestsPlayer extends MainTests{

    @Test
    public void AttackEnemy() {
        //player1
        //player1.battle(enemy1);
        //Assert.assertEquals(0, player1.getPosition().compareTo(pos1));
    }

    @Test
    public void swapCheck() {
        empty1.accept(player1);
        Assert.assertEquals(0, player1.getPosition().compareTo(pos4));
    }

    @Test
    public void swapCheck2() {
        empty1.accept(player1);
        Assert.assertEquals(0, empty1.getPosition().compareTo(pos5));
    }

    @Test
    public void acceptWall() {
        wall1.accept(player1);
        Assert.assertEquals(0, player1.getPosition().compareTo(pos5));
    }
    @Test
    public void specialAb(){
        player1.abilityCast(enemies);

    }

}

