package MainProject;

import Tiles.Units.Enemy.Enemy;

public class GameInitializer {

    public void RunGame(){
        LevelManager m = new LevelManager();

        Enemy e = new Enemy();
        e.SetDeathCallback(()-> m.RemoveEnemy(e));

    }



}
