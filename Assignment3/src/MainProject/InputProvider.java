package MainProject;

import java.util.Locale;
import java.util.Scanner;

public class InputProvider implements InputQuery {
    Scanner scanner;

    public InputProvider() {
        scanner = new Scanner(System.in);
    }


    public char getInput() {
        String input="";
        String valid = "wasdqe";
        boolean loop = true;
        while (loop) {
            input = scanner.nextLine();
            input = input.trim();
            if (input.length() == 1 || input.indexOf(valid) != -1) {
                loop = false;
            }
            else {
                System.out.println("invalid input - Please enter a valid input");
            }
        }
        return input.charAt(0);
    }
}