package UnitTests;

import Tiles.Units.Enemy.Monster;
import Tiles.Units.Players.Player;
import Tiles.Units.Players.Warrior;
import Tiles.Units.Unit;
import org.testng.annotations.Test;

public class EnemyTests {


    @Test
    void battleCheck() {
        Unit u = new Warrior("yagil", 100, 5, 20, 5);
        Unit e = new Monster('s', "mons", 40, 5, 2, 25, 4);
    }
}
