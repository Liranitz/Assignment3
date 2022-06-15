public class GameInitializer {

    public void RunGame(){
        GameManager m = new GameManager();

        Enemy e = new Enemy();
        e.SetDeathCallback(()-> m.RemoveEnemy(e));

    }



}
