package Tiles;

import Tiles.Units.Unit;

public class Empty extends Tile {
    public Empty(Position p) {
        super('.');
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
