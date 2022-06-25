package Tiles;

import Tiles.Units.Position;
import Tiles.Units.Unit;

public class Wall extends Tile {

    public Wall(Position p){
        super('#');
        initialize(p);
    }
    @Override
    public void accept(Unit unit) {
        return;

    }
}
