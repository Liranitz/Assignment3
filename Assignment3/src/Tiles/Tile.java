package Tiles;

import Tiles.Units.Unit;

import Tiles.Units.Player;

import java.util.Random;

public abstract class Tile implements Comparable<Tile> {
    protected char tile;
    protected Position position;
    private messageCallback messageCallback;

    protected Tile(char tile){
        this.tile = tile;
    }

    protected void initialize(Position position){
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
        return getPosition().compareTo(tile.getPosition());
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }

    public abstract void onDeath();
    public void visit(Empty e){
        swapPosition(e);
    }

    public void Visit(Wall w){
        return;
    }



}
