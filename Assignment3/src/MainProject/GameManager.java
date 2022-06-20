package MainProject;

import Tiles.Empty;
import Tiles.Position;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Players.Player;

import java.util.LinkedList;

public class GameManager {
    private LinkedList<Enemy> enemies;
    private Player player;
    private  boolean isActive;
    private GameBoard board;
    //private InputProvider input;

    public void Initializer(LinkedList<Enemy> enemies, Player player, GameBoard board){
        this.board = board;
        this.enemies=enemies;
        this.player=player;
        isActive=true;
    }


    public GameManager(){

        //input = new InputProvider();
    }
    public void runGame(){

        while(isActive){
            board.toString();
            playerTurn(player.GetInput());
            //layer.in getInput()
            for(Enemy e : enemies){
                enemyTurn(e);
            }
        }
    }
    public void playerTurn(Action input){
        Tile tile;
        switch(input) {
            case UP:
                tile = board.get(player.getPosition().getXCoordinate(),player.getPosition().getYCoordinate()+1);
                tile.accept(player);
            case LEFT:
                tile = board.get(player.getPosition().getXCoordinate()-1,player.getPosition().getYCoordinate());
                tile.accept(player);
            case RIGHT:
                tile = board.get(player.getPosition().getXCoordinate()+1,player.getPosition().getYCoordinate());
                tile.accept(player);
            case DOWN:
                tile = board.get(player.getPosition().getXCoordinate(),player.getPosition().getYCoordinate()-1);
                tile.accept(player);
            case SPECIALABILITY:
              player.AbilityCast();
            case NOTHING:
        }
    }

    public void enemyTurn(Enemy enemy){
        double distance = enemy.getPosition().Range(player.getPosition());
        Tile tile;
        switch (enemy.EnemyTurn(player)){
            case LEFT:
                tile = board.get(enemy.getPosition().getXCoordinate()-1,enemy.getPosition().getYCoordinate());
                tile.accept(enemy);
            case RIGHT:
                tile = board.get(enemy.getPosition().getXCoordinate()+1,enemy.getPosition().getYCoordinate());
                tile.accept(enemy);
            case UP:
                tile = board.get(enemy.getPosition().getXCoordinate(),enemy.getPosition().getYCoordinate()+1);
                tile.accept(enemy);
            case DOWN:
                tile = board.get(enemy.getPosition().getXCoordinate(),enemy.getPosition().getYCoordinate()-1);
                tile.accept(enemy);
            case NOTHING:
        }
    }
    public void UpdateLevel(){

    }
    public void EndGame(){

    }

    public void RemoveEnemy(Enemy enemy) {
        Position p = enemy.getPosition();
        board.remove(enemy);
        Empty emptyTile = new Empty(p);
        board.add(emptyTile);
    }
}
