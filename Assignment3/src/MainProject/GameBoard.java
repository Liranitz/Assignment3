package MainProject;

import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Players.Player;
import Tiles.Units.Unit;
import Tiles.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;

    public GameBoard(){

    }

    public void Initialize(List<Tile> tiles){
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
        // TODO: Implement me
        // sort all the tiles in the list, print char's tile
        return null;
    }
}