package Tiles.Units.Players;

public class Rogue extends Player {
    private Integer cost;
    private Integer currentEnergy;
    public Rogue(Resource resource, Integer experience, Integer level) {
        super(resource, experience, level);
    }

    public void LevelUp(){
        super(LevelUp());
        // adding the specific level
    }
}
