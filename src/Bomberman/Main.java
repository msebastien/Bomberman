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
        scene.setPreferredSize(new Dimension(screenSize.height*4/5,screenSize.height*4/5));

        Window window=new Window(scene);
        game=new Game(scene,window);

    }
}
