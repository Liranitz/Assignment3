package Tiles.Units.Players;

public class Warrior extends Player {
    private  Integer abilityCooldown;
    private  Integer remainingCooldown;
    public Warrior(Resource resource, Integer experience, Integer level) {
        super(resource, experience, level);
    }

    public void LevelUp(){
        super(LevelUp());
        // adding the specific level
    }

}
