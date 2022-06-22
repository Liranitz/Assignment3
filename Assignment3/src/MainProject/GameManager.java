package MainProject;
import CallBacks.EndGameCallback;
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
    private EndGameCallback endGameCallback;

    public GameManager(){

    }
    public void Initializer(LinkedList<Enemy> enemies, Player player, GameBoard board){
        this.board = board;
        this.enemies=enemies;
        this.player=player;
        isActive=true;
    }

    public void SetEndGameCallback(EndGameCallback endGameCallback){
        this.endGameCallback = endGameCallback;
    }

    public void runGame(){
        while(isActive){
            System.out.println(board.toString());
            PlayerTurn(player.GetInput());
            for(Enemy e : enemies){
                EnemyTurn(e);
            }
        }
    }
    public void PlayerTurn(Action input){
        Tile tile;
        switch(input) {
            case UP:
                tile = board.get(player.getPosition().getXCoordinate(),player.getPosition().getYCoordinate()+1);
                player.GameTick(tile);
            case LEFT:
                tile = board.get(player.getPosition().getXCoordinate()-1,player.getPosition().getYCoordinate());
                player.GameTick(tile);
            case RIGHT:
                tile = board.get(player.getPosition().getXCoordinate()+1,player.getPosition().getYCoordinate());
                player.GameTick(tile);
            case DOWN:
                tile = board.get(player.getPosition().getXCoordinate(),player.getPosition().getYCoordinate()-1);
                player.GameTick(tile);
            case SPECIALABILITY:
              player.AbilityCast(enemies);
            case NOTHING:
        }
    }

    public void EnemyTurn(Enemy enemy){
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
        System.out.println("Game is Over");
        System.exit(0);
    }

    public void RemoveEnemy(Enemy enemy) {
        Position p = enemy.getPosition();
        board.remove(enemy);
        Empty emptyTile = new Empty(p);
        board.add(emptyTile);
    }
}
