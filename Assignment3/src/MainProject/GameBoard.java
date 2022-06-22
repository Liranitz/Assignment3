package MainProject;

import CallBacks.MessageCallback;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;
import Tiles.Position;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;

    public GameBoard(List<Tile> tiles){
        this.tiles = tiles;
    }


    public Tile get(int x, int y) {
        Tile tile = null;
        for(Tile t : tiles){
            if (t.getPosition().getXCoordinate() == x && t.getPosition().getYCoordinate() == y ){
                tile=t;
            }
        }
        return  tile;
    }

    public void remove(Tile t) {
        tiles.remove(t);
    }

    public void add(Tile t) {
        tiles.add(t);

    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        String res="";
        int rowCounter=0;
        for(Tile tile : tiles){
            if(tile.getPosition().getXCoordinate() > rowCounter) {
                res += "\n";
                rowCounter++;
            }
            res+=tile.getTile();
        }
        // TODO: Implement me
        // sort all the tiles in the list, print char's tile
        return res;
    }

}