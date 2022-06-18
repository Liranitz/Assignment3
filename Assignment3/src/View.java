import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.*;
import java.io.*;


public class View {
    public static void main(String[] args) {
        String path = args[0];
        List<String> list = readAllLines(path);

        //System.out.println(list.);

    }
    public static List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +  e.getStackTrace());
        }
        return lines;
    }


}
