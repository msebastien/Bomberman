package Bomberman;

import java.awt.*;

public class Main {

    public static Game game;
    public static void main(String[] args)
    {

        Scene scene=new Scene();
        scene.setPreferredSize(new Dimension(1000,1000));

        Window window=new Window(scene);
        game=new Game(scene);

    }
}
