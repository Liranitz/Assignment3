package MainProject;

import java.util.Scanner;

public class InputProvider implements InputQuery {
    Scanner scanner;

    public InputProvider() {
        scanner = new Scanner(System.in);
    }


    public Action getInput() {
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
        switch(input.charAt(0)) {
            case 'w':
                return Action.UP;

            case 'a':
                return Action.LEFT;

            case 'd':
                return Action.RIGHT;

            case 's':
                return Action.DOWN;

            case 'e':
                return Action.SPECIALABILITY;

            case 'q':
                return Action.NOTHING;
        }
        return Action.NOTHING;
    }
}