package Bomberman;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(JPanel pan) throws HeadlessException {
        this.setTitle("Bomberman");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        this.setContentPane(pan);
    }
}
