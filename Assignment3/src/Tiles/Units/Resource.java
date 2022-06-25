package Tiles.Units;

public class Resource {
    private Integer pool;
    private Integer amount;

    public  Resource(int pool)
    {
        this.pool=pool ;
        amount = pool;
    }
    public void updatePool(Integer pool){
         this.pool = pool;
    }
    public void updateAmount(Integer amount){
         this.amount = amount;
    }
    public void reduceAmount(int damageDone) {
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
