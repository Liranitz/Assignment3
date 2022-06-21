package MainProject;

import CallBacks.EnemyDeathCallback;
import CallBacks.MessageCallback;
import CallBacks.PlayerDeathCallback;
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
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GameInitializer {

    private List<Supplier<Player>> playerPool;
    private Map<Character, Supplier<Enemy>> enemiesMap;
    private int playerSelection;
    private GameManager gameManager;
    private char[][] boardChar;



    public GameInitializer(char[][]boardChar) {
        //this.playerSelection=playerSelection;
        playerPool = initPlayers();
        enemiesMap = initEnemies();
        this.boardChar=boardChar;
        this.gameManager =  new GameManager();
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

    private Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
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

        return enemies.stream().collect(Collectors.toMap(s -> s.get().getTile(), Function.identity()));
    }
    public void Initialize(){
        LinkedList<Tile> tileList = new LinkedList<>();
        LinkedList<Enemy> enemies = new LinkedList<>();
        Player player = null;
        GameBoard board = new GameBoard();

        MessageCallback m = (s) -> System.out.println(s);
        PlayerDeathCallback d = () -> gameManager.EndGame();

        for (int i = 0; i <boardChar.length ; i++) { // init tileList
            for (int j = 0; j <boardChar[i].length ; j++) {
                Position p = new Position(i,j);
                if(boardChar[i][j]=='#') // wall case
                    tileList.add(produceWall(p));
                if(boardChar[i][j]=='@') {// player case
                    player = producePlayer(playerSelection,p,m, d);
                    tileList.add(player);
                }
                if(boardChar[i][j]=='.')// empty case
                    tileList.add(produceEmpty(p));
                else {// enemy case
                    Enemy enemy = produceEnemy(boardChar[i][j], p,m);
                    enemies.add(enemy);
                    tileList.add(enemy);
                }
            }
        }
        gameManager.Initializer(enemies,player,board);
        board.Initialize(tileList,m);
        gameManager.runGame();
    }

        public Enemy produceEnemy(char tile, Position position , MessageCallback messageCallback) {
            Enemy enemy = enemiesMap.get(tile).get();
            EnemyDeathCallback e = () -> gameManager.RemoveEnemy(enemy);
            enemy.initialize(position,messageCallback);
            return  enemy;
            }


        public Player producePlayer(int idx , Position p, MessageCallback m, PlayerDeathCallback d) {
            Player player =  playerPool.get(idx).get();
            player.initialize(p, m, d);
//            player.setPosition(p);
            return player;
        }

        public Empty produceEmpty(Position position) {
            return new Empty(position);
        }

        public Wall produceWall(Position position) {
            return new Wall(position);
        }

        public void PresentPlayers() {
            String res ="Choose a Player"+"\n";
            for (Supplier<Player> s : playerPool) {
                   res+=s.get().toString()+"\n";
            }
            System.out.println(res);
            Scanner scan = new Scanner(System.in);
            this.playerSelection = scan.nextInt();
        }

}
