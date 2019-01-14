package Bomberman;

import Bomberman.EntityManager.EntityType;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AccueilPanel extends JPanel {

    private Container containerFond;

    public AccueilPanel() {
        super();

        containerFond=new Container();
        containerFond.init(100,Animation.BACKGROUND, EntityType.ACCUEIL);

        Thread t=new Thread(() -> {
            while(true)
            {
                if(isShowing())
                {
                    containerFond.nextImage();
                    repaint();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage img=containerFond.getCurrentImage();



        img=img.getSubimage(0,0,1296,1080);
        /*if(img.getHeight()<height)height=img.getHeight();
        if(img.getWidth()<width)width=img.getWidth();
        */
        //img=img.getSubimage(0,0,width,height);

        g.drawImage(img,0,0,getWidth(),getHeight(),null);
    }
}
