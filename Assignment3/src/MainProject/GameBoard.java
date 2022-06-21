package MainProject;

import CallBacks.MessageCallback;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;
import Tiles.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;
    private MessageCallback messageCallback;
    public GameBoard(){

    }

    public void Initialize(List<Tile> tiles, MessageCallback messageCallback){
        this.tiles = tiles;
    }


    public Tile get(int x, int y) {
        for(Tile t : tiles){
            if (t.getPosition().getXCoordinate() == x && t.getPosition().getYCoordinate() == y ){
                return t;
            }
        }
        throw new RuntimeException("no such tile");
        // Throw an exception if no such tile.
    }

    public void remove(Tile t) {
        Position pos = t.getPosition();
        tiles.remove(t);
    }

    public void add(Tile t) {
       //Tile t =  get(u.getPosition().,u.y)

    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        String res="";
        int rowCounter=0;
        for(Tile tile : tiles){
            if(tile.getPosition().getYCoordinate() > rowCounter) {
                res += "\n";
                rowCounter++;
            }
            res+=tile.getTile();
        }
        // TODO: Implement me
        // sort all the tiles in the list, print char's tile
        return null;
    }

}