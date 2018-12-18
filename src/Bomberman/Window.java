package Bomberman;

import javax.swing.*;
import java.awt.*;


public class Window extends JFrame  {

    //pour avoir la bonne taille de JPanel
    private final int heightBorder=40;
    private final int widthBorder=6;


    public Window(JPanel pan) throws HeadlessException {


        this.setTitle("Bomberman");
        this.setSize(1000+widthBorder, 1000+heightBorder);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.setResizable(false);

        this.setContentPane(pan);
    }


}
