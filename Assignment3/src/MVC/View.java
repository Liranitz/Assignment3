package MVC;

import MVC.Controller;

import java.io.*;


public class View {

    public static void main(String[] args) throws IOException {
        String path = args[0];
        Controller control = new Controller(path);
        control.play();
    }
}