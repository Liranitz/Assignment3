public class Enemy extends Unit implements EnemyDeathCallback {

    private int experienceValue;
    public Enemy(){
       experienceValue =0;
    }

    @Override
    public void call(Enemy e) {
        // calls to board.remove
    }

    @Override
    public void accept(Unit unit) {

    }

    @Override // where to implement this? bith monster and trap has different implementation of this method
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }
}
