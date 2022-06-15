import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private List<Tile> tiles;
    private List<Player> playerList;
    private List<Enemy> enemyList;

    public GameBoard(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line : board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    public Tile get(int x, int y) {
        for(Tile t : tiles){
            if (t.getPosition().equals(Position.at(x, y))){
                return t;
            }
        }
        // Throw an exception if no such tile.
    }

    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        // TODO: Implement me
        // sort all the tiles in the list, print char's tile
    }
}