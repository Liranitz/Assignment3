import MainProject.Action;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy.Boss;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Enemy.Monster;
import Tiles.Units.Enemy.Trap;
import Tiles.Units.Players.Player;
import Tiles.Units.Players.Warrior;
import Tiles.Units.Position;
import Tiles.Wall;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

public class MainTests {
    public Tile wall1;
    public Tile empty1;
    public Player player1;
    public Monster enemy1;
    public Monster enemy2;
    public Trap trap1;
    public Boss boss1;
    public Position pos1;
    public Position pos2;
    public Position pos3;
    public Position pos4;
    public Position pos5;
    public LinkedList<Enemy> enemies;

    @Before
    public void beforeAny() {
        player1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        enemy1 = new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3);
        enemy2 = new Monster('w', "White Walker", 2000, 150, 50, 1000, 6);
        boss1 = new Boss('M', "The Mountain", 1000, 60, 25, 500, 6, 5);
        trap1 = new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 7);
        pos1 = new Position(5, 4);
        pos2 = new Position(4, 4);
        pos3 = new Position(7, 4);
        pos4 = new Position(6, 4);
        pos5 = new Position(5, 3);
        player1.setPosition(pos5);
        wall1 = new Wall(pos3);
        empty1 = new Empty(pos4);
        enemy1.setPosition(pos1);
        enemy2.setPosition(pos2);
        trap1.setPosition(pos2);
        boss1.setPosition(pos1);
        player1.setMessageCallback(s -> System.out.println(s));
        enemy1.setMessageCallback(s -> System.out.println(s));
        trap1.setMessageCallback(s-> System.out.println(s));
        boss1.setMessageCallback(s-> System.out.println(s));
        enemies = new LinkedList<Enemy>();
        enemies.add(enemy1);
    }

    //--------- Enemy Tests ------- //
    @Test
    public void acceptEnemies() {
        enemy1.accept(enemy2);
        Assert.assertEquals(0, enemy1.getPosition().compareTo(pos1));
    }

    @Test
    public void swapCheck1() {
        empty1.accept(enemy1);
        Assert.assertEquals(0, enemy1.getPosition().compareTo(pos4));
    }

    @Test
    public void swapCheck2() {
        empty1.accept(enemy1);
        Assert.assertEquals(0, empty1.getPosition().compareTo(pos1));
    }

    @Test
    public void acceptWall1() {
        wall1.accept(enemy1);
        Assert.assertEquals(0, enemy1.getPosition().compareTo(pos1));
    }

    @Test
    public void enemyChasePlayer() {
        Action cur = enemy1.enemyTurn(player1);
        Assert.assertEquals(Action.UP, cur);
    }

    @Test
    public void EnemyAttackPlayer() {
        int preAmount = (int) player1.getHealth().getAmount();
        enemy1.battle(player1);
        int newAmount = (int) enemies.get(0).getHealth().getAmount();
        Assert.assertEquals(true, newAmount <= preAmount && newAmount >= newAmount - enemy1.getAttackPoints());
    }

    //--------- Player Tests ------- //
    @Test
    public void swapCheck3() {
        empty1.accept(player1);
        Assert.assertEquals(0, player1.getPosition().compareTo(pos4));
    }

    @Test
    public void swapCheck4() {
        empty1.accept(player1);
        Assert.assertEquals(0, empty1.getPosition().compareTo(pos5));
    }

    @Test
    public void acceptWall() {
        wall1.accept(player1);
        Assert.assertEquals(0, player1.getPosition().compareTo(pos5));
    }

    @Test
    public void specialAb() {
        player1.abilityCast(enemies);
        // need to figure out how to do it properly , enemy has 80 , demage is 30
        int newAmount = enemies.get(0).getHealth().getAmount();
        Assert.assertEquals(true, 50 <= newAmount && newAmount < 80);
    }

    @Test
    public void PlayerAttackEnemy() {
        int preAmount = enemies.get(0).getHealth().getAmount();
        player1.battle(enemy1);
        int newAmount = enemies.get(0).getHealth().getAmount();
        Assert.assertEquals(true, newAmount <= preAmount);
    }

    @Test
    public void levelUpHealthCheck() {
        int prePool = player1.getHealth().getPool();
        player1.levelUp();
        int newPool = player1.getHealth().getPool();
        Assert.assertEquals(prePool + 30, newPool);
    }

    @Test
    public void levelUpAttackCheck() {
        int expectedAttack = player1.getAttackPoints() + 4 * (player1.getLevel() + 1) + 2 * (player1.getLevel() + 1);
        player1.levelUp();
        int newAttack = player1.getAttackPoints();
        Assert.assertEquals(expectedAttack, newAttack);
    }
}