package MainProject;

import Tiles.Empty;
import Tiles.Position;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Enemy.Monster;
import Tiles.Units.Enemy.Trap;
import Tiles.Units.HeroicUnit.Boss;
import Tiles.Units.Players.*;
import Tiles.Wall;
import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.function.Supplier;

public class GameInitializer {

    private List<Supplier<Player>> playerPool;
    private List<Supplier<Enemy>> enemiesMap;
    private Player selected;

    public GameInitializer(char[][]board) {
        playerPool = initPlayers();
        enemiesMap = initEnemies();
    }
    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Warrior("Jon Snow", 300, 30, 4, 3),
                () -> new Warrior("The Hound", 400, 20, 6, 5),
                () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6),
                () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
                () -> new Rogue("Arya Stark", 150, 40, 2, 20),
                () -> new Rogue("Bronn", 250, 35, 3, 50),
                () -> new Hunter("Ygritte", 220, 30, 2, 6)
        );
    }

    private List<Supplier<Enemy>> initEnemies() {
        return Arrays.asList(
                () -> new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3),
                () -> new Monster('k', "Lannister Knight", 200, 14, 8, 50, 4),
                () -> new Monster('q', "Queen's Guard", 400, 20, 15, 100, 5),
                () -> new Monster('z', "Wright", 600, 30, 15, 100, 3),
                () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 250, 4),
                () -> new Monster('g', "Giant-Wright", 1500, 100, 40, 500, 5),
                () -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6),
                () -> new Boss('M', "The Mountain", 1000, 60, 25, 500, 6, 5),
                () -> new Boss('C', "Queen Cersei", 100, 10, 10, 1000, 1, 8),
                () -> new Boss('K', "Night's King", 5000, 300, 150, 5000, 8, 3),
                () -> new Trap('B', "Bonus Tiles.Units.Enemy.Trap", 1, 1, 1, 250, 1, 10),
                () -> new Trap('Q', "Queen's Tiles.Units.Enemy.Trap", 250, 50, 10, 100, 3, 10),
                () -> new Trap('D', "Death Tiles.Units.Enemy.Trap", 500, 100, 20, 250, 1, 10)
        );
    }
    public void Initialize(char[][] charArr, int playerInput){
        LinkedList<Tile> tileList = new LinkedList<>();
        LinkedList<Enemy> enemies = new LinkedList<>();
        Player player = null;
        GameManager m = new GameManager();
        GameBoard board = new GameBoard();

        for (int i = 0; i <charArr.length ; i++) { // init tileList
            for (int j = 0; j <charArr[i].length ; j++) {
                Position p = new Position(i,j);
                if(charArr[i][j]=='#') // wall case
                    tileList.add(produceWall(p));
                if(charArr[i][j]=='@') {// player case
                    player = producePlayer(playerInput,p);
                    player.SetDeathCallback(()->m.EndGame());
                    tileList.add(player);
                }
                if(charArr[i][j]=='.')// empty case
                    tileList.add(produceEmpty(p));
                else {// enemy case
                    Enemy enemy = produceEnemy(charArr[i][j], p);
                    enemy.SetDeathCallback(()-> m.RemoveEnemy(enemy));
                    enemies.add(enemy);
                    tileList.add(enemy);
                }
            }
        }
        m.Initializer(enemies,player,board);
        board.Initialize(tileList);
        m.runGame();
    }

        public Enemy produceEnemy(char tile, Position position) {
            Random rand = new Random();
            int num = rand.nextInt(13);
            //set monster position
             return enemiesMap.get(num).get();
        }

        public Player producePlayer(int idx , Position p) {
            return playerPool.get(idx).get();
            //set player postion
        }

        public Empty produceEmpty(Position position) {
            return new Empty(position);
        }

        public Wall produceWall(Position position) {
            return new Wall(position);
        }


}
