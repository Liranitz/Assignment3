import MainProject.GameInitializer;
import MainProject.GameManager;
import Tiles.Units.Players.Player;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;


public class View {

    public static void main(String[] args) throws IOException {
        String path = args[0];
        boolean isFirstLevel = true;
        Player player = null;

        for(Path p : Files.list(Paths.get(path)).sorted().collect(Collectors.toList())){
            List<String> list = readAllLines(path);
            char[][] board = listToChar(list);
            GameInitializer init = new GameInitializer(board);
            if(player == null) {
                player = init.ChoosePlayer();
            }
            init.Initialize(player);
        }
    }
    public static List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        }
        catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +  e.getStackTrace());
        }
        return lines;
    }

    public static char[][] listToChar(List<String> lines) {
        int rowCounter = 0;
        char[][] charArr = new char[lines.size()][];
        for(String s : lines){
            for (int i = 0; i <s.length() ; i++) {
                charArr[rowCounter][i] = s.charAt(i);
            }
            rowCounter++;
        }
        return charArr;
    }
}
