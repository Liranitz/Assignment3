package MainProject;

import Tiles.Tile;

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

    public void remove(Tile tileToRemove) {
        Tile tileToGet = get(tileToRemove.getPosition().getXCoordinate(),tileToRemove.getPosition().getYCoordinate());
        tiles.remove((tileToGet));
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
            res+=tile.toString();
        }
        // TODO: Implement me
        // sort all the tiles in the list, print char's tile
        return res;
    }

}