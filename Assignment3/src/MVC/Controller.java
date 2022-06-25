package MVC;

import MainProject.GameInitializer;
import Tiles.Units.Players.Player;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Controller {
    private String path ;
    private Player player;
    public Controller(String path) {
        this.path=path;
        player=null;
    }

    public void play() throws IOException {
        for (Path p : Files.list(Paths.get(path)).sorted().collect(Collectors.toList())) {
            List<String> list = readAllLines(p.toString());
            char[][] board = listToChar(list);
            GameInitializer init = new GameInitializer(board);
            if (player == null) {
                player = init.choosePlayer();
            }
            init.initialize(player);
        }
    }

    public List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        }
        catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +  e.getStackTrace());
        }
        return lines;
    }

    public char[][] listToChar(List<String> lines) {
        int rowCounter = 0;
        char[][] charArr = new char[lines.size()][lines.get(0).length()];
        for(String s : lines){
            for (int i = 0; i <s.length() ; i++) {
                charArr[rowCounter][i] = s.charAt(i);
            }
            rowCounter++;
        }
        return charArr;
    }
}

