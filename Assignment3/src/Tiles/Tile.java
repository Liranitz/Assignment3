package Tiles;

import CallBacks.MessageCallback;
import Tiles.Units.Unit;


import java.util.Random;

public abstract class Tile implements Comparable<Tile> {
    protected char tile;
    protected Position position;

    protected Tile(char tile){
        this.tile = tile;
    }

    public void initialize(Position position){
        this.position = position;
    }

    public char getTile() {
        return tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void accept(Unit unit);

    @Override
    public int compareTo(Tile tile) {
        return this.position.compareTo(tile.getPosition());
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }


    public void visit(Empty e){
        swapPosition(e);
    }

    public void swapPosition(Tile t) {
        Position p = t.getPosition();
        this.setPosition(t.getPosition());
        t.setPosition(p);
    }

    public void Visit(Wall w){
        return;
    }



}
