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

   public void Initializer(LinkedList<Enemy> enemies, Player player, GameBoard board){
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
            PlayerTurn(player.GetInput());
            //layer.in getInput()
            if(enemies.size() == 0)
                isActive = false;
            else {
                for (Enemy e : enemies) {
                    EnemyTurn(e);
                }
            }
            System.out.println(board.toString());
        }
    }
    public void PlayerTurn(Action input){
        Tile tile;
        switch(input) {
            case UP:
                tile = board.get(player.getPosition().getXCoordinate()-1,player.getPosition().getYCoordinate());
                player.GameTick(tile);
                break;
            case LEFT:
                tile = board.get(player.getPosition().getXCoordinate(),player.getPosition().getYCoordinate()-1);
                player.GameTick(tile);
                break;
            case RIGHT:
                tile = board.get(player.getPosition().getXCoordinate(),player.getPosition().getYCoordinate()+1);
                player.GameTick(tile);
                break;
            case DOWN:
                tile = board.get(player.getPosition().getXCoordinate()+1,player.getPosition().getYCoordinate());
                player.GameTick(tile);
                break;
            case SPECIALABILITY:
              player.AbilityCast(enemies);
                break;
            case NOTHING:
                break;
        }
    }

    public void EnemyTurn(Enemy enemy){
        double distance = enemy.getPosition().Range(player.getPosition());
        Tile tile;
        switch (enemy.EnemyTurn(player)){
            case LEFT:
                tile = board.get(enemy.getPosition().getXCoordinate(),enemy.getPosition().getYCoordinate()+1);
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
                tile = board.get(enemy.getPosition().getXCoordinate()-1,enemy.getPosition().getYCoordinate());
                tile.accept(enemy);
                break;
            case NOTHING:
                break;

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
        board.remove(enemy , new Empty(p));
        enemies.remove(enemy);
    //Empty emptyTile = new Empty(p);
        //board.add(emptyTile);
    }
}
