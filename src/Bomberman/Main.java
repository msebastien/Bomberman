package Bomberman;

import java.awt.*;

public class Main {

    public static Game game;
    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.opengl", "true"); // Enable hardware (GPU) acceleration to avoid stuttering
        Scene scene=new Scene();
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        //pour pas remplir l'écran dans sa totalité on met * 4/5
        scene.setPreferredSize(new Dimension(screenSize.height*5/6,screenSize.height*5/6));

        Window window=new Window(scene);
        game=new Game(scene,window);

    }
}
