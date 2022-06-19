package MainProject;

import Tiles.Tile;
import Tiles.Units.Enemy;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Players.Player;

import java.util.LinkedList;

public class GameManager {
    private LinkedList<Enemy> enemies;
    private Player player;
    private  boolean isActive;
    private GameBoard board;
    private InputProvider input;


    public GameManager(LinkedList<Enemy> enemies, Player player, GameBoard board){
        this.board = board;
        this.enemies=enemies;
        this.player=player;
        isActive=true;
        input = new InputProvider();
    }
    public void runGame(){

        while(isActive){
            playerTurn(player.GetInput());
            player.in getInput()
            for(Enemy e in enemies){
                e.play;
            }
        }

    }
    public void playerTurn(char input){
        switch(input) {
            Tile tile;
            case 'w':
                tile = board.get(player.getPosition().getXCoordinate(),player.getPosition().getXCoordinate()+1);
                tile.accept(player);
                break;
            case 'a':
                // code block
                break;
            default:
                // code block
        }
    }

    public void enemyTurn(){

    }
    public void UpdateLevel(){

    }
    public void EndGame(){

    }

    public void RemoveEnemy(Enemy enemy) {
    }
}
