package MainProject;

import Tiles.Empty;
import Tiles.Units.Position;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Enemy.Monster;
import Tiles.Units.Enemy.Trap;
import Tiles.Units.Enemy.Boss;
import Tiles.Units.Players.*;
import Tiles.Wall;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GameInitializer {

    private List<Supplier<Player>> playerPool;
    private Map<Character, Supplier<Enemy>> enemiesMap;
    private Player player;
    private char[][] boardChar;

    public GameInitializer(char[][]boardChar) {
        //this.playerSelection=playerSelection;
        playerPool = initPlayers();
        enemiesMap = initEnemies();
        this.player=null;
        this.boardChar=boardChar;
    }

    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Warrior("Jon Snow", 300, 30, 4, 3),
                () -> new Warrior("The Hound", 400, 20, 6, 5),
                () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6),
                () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
                () -> new Rogue("Arya Stark", 150, 2, 40, 20),
                () -> new Rogue("Bronn", 250, 3, 35, 50),
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
                () -> new Trap('B', "Bonus Trap", 1, 1, 1, 250, 1, 5),
                () -> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 7),
                () -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10)
        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().getTile(), Function.identity()));
    }
    public void initialize(Player player){
        LinkedList<Tile> tileList = new LinkedList<>();
        LinkedList<Enemy> enemies = new LinkedList<>();
        GameManager gameManager = new GameManager();

        for (int i = 0; i <boardChar.length ; i++) { // init tileList 19X49
            for (int j = 0; j <boardChar[0].length ; j++) {
                Position pos = new Position(i,j);
                if(boardChar[i][j]=='#') // wall case
                    tileList.add(produceWall(pos));
               else if(boardChar[i][j]=='@') {// player case
                    player.initialize(pos);
                    player.setPlayerDeathCallback(()-> gameManager.EndGame());
                    player.setMessageCallback(s-> System.out.println(s));
                    tileList.add(player);
                }
                else if(boardChar[i][j]=='.')// empty case
                    tileList.add(produceEmpty(pos));
                else {// enemy case
                    Enemy enemy = produceEnemy(boardChar[i][j], pos);
                    enemy.setEnemyDeathCallback(()->gameManager.RemoveEnemy(enemy));
                    enemy.setMessageCallback(s-> System.out.println(s));
                    enemies.add(enemy);
                    tileList.add(enemy);
                }
            }
        }
        GameBoard gameboard = new GameBoard(tileList);
        gameManager.initialize(enemies,player,gameboard);
        gameManager.runGame();
    }

        public Enemy produceEnemy(char tile, Position position) {
            Enemy enemy = enemiesMap.get(tile).get();
            enemy.initialize(position);
            return  enemy;
            }

        public Player producePlayer(int idx) {
            Player player =  playerPool.get(idx).get();
            return player;
        }

        public Empty produceEmpty(Position position) {
            Empty empty = new Empty(position);
            return empty;
        }

        public Wall produceWall(Position position) {
            Wall wall = new Wall(position);
            return wall;
        }

        public Player choosePlayer() {
            String res = "Choose a Player" + "\n";
            int counter = 1;
            for (Supplier<Player> s : playerPool) {
                res += counter + ") " + s.get().describe() + "\n";
                counter++;
            }
            System.out.println(res);
            List<Integer> validCommands = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

            boolean started = false;
            while (!started) {
                Scanner scan = new Scanner(System.in);
                try {
                    int idx = scan.nextInt();
                    /*if (!validCommands.contains(idx)) {
                        System.out.println("Invalid command");
                        System.out.println(res);*/
                    //}
                //else {
                        this.player = producePlayer(idx - 1);
                        System.out.println(String.format("You chose the player : \n %s", this.player.getName()));
                        started = true;
                  //  }
                }
                catch (Exception ex){

                }
            }
            return player;
        }
}
