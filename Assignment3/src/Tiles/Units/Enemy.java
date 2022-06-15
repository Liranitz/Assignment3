public abstract class Enemy extends Unit  {

    protected int experienceValue;
    protected EnemyDeathCallback edCallback;
    public Enemy(){
       experienceValue =0;
    }

    public void SetDeathCallback(EnemyDeathCallback edCallback){
        this.edCallback=edCallback;
    }

    @Override
    public void call() {

    }

    @Override
    public void accept(Unit unit) {

    }
    public abstract void processStep() ;

    @Override
    public void onDeath() {
        edCallback.call();
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }
}
