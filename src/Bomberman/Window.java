package Bomberman;

import javax.swing.*;
import java.awt.*;


public class Window extends JFrame  {

    //pour avoir la bonne taille de JPanel



    public Window(Scene scene) throws HeadlessException {


        this.setTitle("Bomberman");
        //this.setSize(1000, 1000);
        //this.setLocationRelativeTo(null);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        //this.addComponentListener(scene);
        this.setContentPane(scene);
        this.pack();
    }


}
