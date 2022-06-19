package Tiles;

import Tiles.Units.Unit;

public class Wall extends Tile {

    public Wall(Position p){
        super('#');
        initialize(p);
    }

    @Override
    public void accept(Unit unit) {
        // do nothing becuase its a wall
    }
}
