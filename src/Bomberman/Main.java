package Bomberman;

import java.awt.*;

public class Main {

    public static Game game;
    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.opengl", "true"); // Enable hardware (GPU) acceleration to avoid stuttering
        Scene scene=new Scene();
        scene.setPreferredSize(new Dimension(1000,1000));

        Window window=new Window(scene);
        game=new Game(scene,window);

    }
}
