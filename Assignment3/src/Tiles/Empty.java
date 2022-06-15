package Tiles;

import Tiles.Units.Unit;

public class Empty extends Tile {
    protected Empty(char tile) {
        super('.');
    }

    @Override
    public void accept(Unit unit) {

    }
}
