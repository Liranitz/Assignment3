package MainProject;
import CallBacks.EndGameCallback;
import Tiles.Empty;
import Tiles.Units.Position;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Players.Player;

import java.util.LinkedList;

public class GameManager {
    private LinkedList<Enemy> enemies;
    private Player player;
    private  boolean isActive;
    private GameBoard board;
     private EndGameCallback endGameCallback;

   public void initialize(LinkedList<Enemy> enemies, Player player, GameBoard board){
        this.board = board;
        this.enemies=enemies;
        this.player=player;
        isActive=true;
    }

    public void SetEndGameCallback(EndGameCallback endGameCallback){
        this.endGameCallback = endGameCallback;
    }

    public GameManager(){

    }
    public void runGame(){
        System.out.println(board.toString());
        while(isActive){
            PlayerTurn(player.getInput());
            if(enemies.size() == 0)
                isActive = false;
            else {
                for (Enemy e : enemies) {
                    EnemyTurn(e);
                }
            }
            System.out.println(board.toString());
            System.out.println(player.describe()+"\n");
        }
    }
    public void PlayerTurn(Action input){
        Tile tile;
        switch(input) {
            case UP:
                tile = board.get(player.getPosition().getXCoordinate()-1,player.getPosition().getYCoordinate());
                player.gameTick(tile);
                break;
            case LEFT:
                tile = board.get(player.getPosition().getXCoordinate(),player.getPosition().getYCoordinate()-1);
                player.gameTick(tile);
                break;
            case RIGHT:
                tile = board.get(player.getPosition().getXCoordinate(),player.getPosition().getYCoordinate()+1);
                player.gameTick(tile);
                break;
            case DOWN:
                tile = board.get(player.getPosition().getXCoordinate()+1,player.getPosition().getYCoordinate());
                player.gameTick(tile);
                break;
            case SPECIALABILITY:
                if(enemies.size()>0)
                     player.abilityCast(enemies);
                break;
            case NOTHING:
                break;
        }
    }

    public void EnemyTurn(Enemy enemy){
        double distance = enemy.getPosition().Range(player.getPosition());
        Tile tile;
        switch (enemy.enemyTurn(player)){
            case LEFT:
                tile = board.get(enemy.getPosition().getXCoordinate()-1,enemy.getPosition().getYCoordinate());
                tile.accept(enemy);
                break;
            case RIGHT:
                tile = board.get(enemy.getPosition().getXCoordinate()+1,enemy.getPosition().getYCoordinate());
                tile.accept(enemy);
                break;
            case UP:
                tile = board.get(enemy.getPosition().getXCoordinate(),enemy.getPosition().getYCoordinate()-1);
                tile.accept(enemy);
                break;
            case DOWN:
                tile = board.get(enemy.getPosition().getXCoordinate(),enemy.getPosition().getYCoordinate()+1);
                tile.accept(enemy);
                break;
            case NOTHING:
                break;

        }
    }
    public void RemoveEnemy(Enemy enemy) {
        Position p = enemy.getPosition();
        board.remove(enemy);
        enemies.remove(enemy);
        board.add(new Empty(p));

    }
    public void EndGame(){
        System.out.println("Player died - Game is Over");
        System.exit(0);
    }
}
