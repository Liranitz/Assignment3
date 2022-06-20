package Tiles.Units;

public class Resource {
    Integer pool;
    Integer amount;

    public  Resource(int pool)
    {
        this.pool=pool ;
        amount = pool;
    }
    public Integer AddPool(Integer pool){
        return this.pool + pool;
    }
    public Integer UpdateAmount(Integer amount){
        return this.amount = amount;
    }

    public Integer UpdateMana(){
        return 0;
    }

    public void ReduceAmount(int damageDone) {
        amount = amount-damageDone;
        if(amount<0)
            amount=0;
    }
    public Integer getAmount() {
        return amount;
    }

    public Integer getPool() {
        return pool;
    }
}
