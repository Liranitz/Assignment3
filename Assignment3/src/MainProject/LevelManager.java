package MainProject;

import Tiles.Units.Enemy;

import java.util.LinkedList;

public class LevelManager {
    private LinkedList<GameBoard> boards;

    public LevelManager(LinkedList<GameBoard> boards){
        this.boards = boards;
    }
    public void run(){
        while(boards.IsActive()){
            player.play;
            for(Enemy e in enemies){
                e.play;
            }
        }

    }
    public void playerTurn(){
    }

    public void enemyTurn(){

    }
    public void UpdateLevel(){

    }
    public void EndGame(){

    }
}
