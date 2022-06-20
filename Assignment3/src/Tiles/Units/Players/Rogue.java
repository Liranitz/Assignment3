package Tiles.Units.Players;

public class Rogue extends Player {
    private Integer cost;
    private Integer currentEnergy;
    public Rogue(String name , Integer pool , Integer defence, Integer attack ,Integer cost) {
        super(pool , name , attack , defence);
        this.cost = cost;
        this.currentEnergy = 100;
    }

    public void LevelUp(){
        super.Levelup();
        currentEnergy = 100;
        attackPoints = attackPoints + 3;
    }
    public void GameTick(){
        int tempEnergy = currentEnergy + 10;
        if(tempEnergy < 100)
            currentEnergy = tempEnergy;
        else{
            currentEnergy = 100;
        }
    }
    public void AbilityCast(){
        currentEnergy = currentEnergy - cost;
        //...
    }
}
