import MainProject.Action;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy.Monster;
import Tiles.Units.Players.Rogue;
import Tiles.Units.Players.Warrior;
import Tiles.Units.Position;
import Tiles.Units.Unit;
import Tiles.Units.Players.Player;
import Tiles.Wall;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class EnemyTests  extends MainTests{

    @Test
    public void acceptEnemies() {
        enemy1.accept(enemy2);
        Assert.assertEquals(0, enemy1.getPosition().compareTo(pos1));
    }

    @Test
    public void swapCheck(){
        empty1.accept(enemy1);
        Assert.assertEquals(0, enemy1.getPosition().compareTo(pos4));
    }
    @Test
    public void swapCheck2(){
        empty1.accept(enemy1);
        Assert.assertEquals(0, empty1.getPosition().compareTo(pos1));
    }
    @Test
    public void acceptWall() {
        wall1.accept(enemy1);
        Assert.assertEquals(0, enemy1.getPosition().compareTo(pos1));
    }

    @Test
    public void enemyChasePlayer(){
        Action cur = enemy1.enemyTurn(player1);
        Assert.assertEquals(Action.UP, cur);
    }

    @Test
    public void bossSpecialAbility(){
        int healthBeforeTest = player1.getHealth().getAmount();
        boss1.AbilityCast(player1);
        Assert.assertEquals(true, player1.getHealth().getAmount()<=healthBeforeTest);
    }

    @Test
    public void trapVisibility(){
        trap1.enemyTurn(player1);
        trap1.enemyTurn(player1);
        Assert.assertEquals(true,trap1.isVisible());
    }
    @Test
    public void trapInvisibility(){
        trap1.enemyTurn(player1);
        trap1.enemyTurn(player1);
        trap1.enemyTurn(player1);
        trap1.enemyTurn(player1);

        Assert.assertEquals(false,trap1.isVisible());

    }




}
