package Tiles.Units.Players;

import Tiles.Units.Player;

public class Hunter extends Player {
    private Integer range;
    private Integer arrowsCount;
    private Integer ticksCount;
    public Hunter(Resource resource, Integer experience, Integer level) {
        super(resource, experience, level);
    }

    public void LevelUp(){
        super(LevelUp());
        // adding the specific level
    }
}
