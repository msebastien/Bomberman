package Bomberman;

import Bomberman.EntityManager.Boss;
import Bomberman.EntityManager.EntityType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

public class Hud extends JPanel {

    Container listImageBackground;
    Container lifeBoss;

    List<Container> listIcon;
    //Container level,bomb,schema,vie,damage,range;



    public Hud() {
        super();
        lifeBoss=new Container();
        lifeBoss.init(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_PV,EntityType.ITEM_DROPPED);

        listImageBackground =new Container();

        listIcon=new ArrayList<>(10);

        addIcon(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_KEY,EntityType.ITEM_DROPPED);
        addIcon(Game.COUNT_DOWN_BOMB,Animation.BOMB,EntityType.BOMB);
        addIcon(Animation.ANIMATION_EXPLOSION,Animation.EXPLOSION,EntityType.EXPLOSION);
        addIcon(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_PV,EntityType.ITEM_DROPPED);
        addIcon(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_DAMAGE,EntityType.ITEM_DROPPED);
        addIcon(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_RANGE,EntityType.ITEM_DROPPED);

        /*level.init(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_KEY,EntityType.ITEM_DROPPED);
        bomb.init(Game.COUNT_DOWN_BOMB,Animation.BOMB,EntityType.BOMB);
        schema.init(Animation.ANIMATION_EXPLOSION,Animation.EXPLOSION,EntityType.EXPLOSION);
        vie.init(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_PV,EntityType.ITEM_DROPPED);
        damage.init(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_DAMAGE,EntityType.ITEM_DROPPED);
        range.init(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_RANGE,EntityType.ITEM_DROPPED);*/
    }

    public void initBackground(EntityType entityType)
    {
        listImageBackground.init(0,Animation.BACKGROUND,entityType);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(listImageBackground.isEmpty())
        {
            return;
        }

        int width= listImageBackground.getCurrentImage().getWidth();
        int height= listImageBackground.getCurrentImage().getHeight();
        if(getWidth()<width)width=getWidth();
        if(getHeight()<height)height=getHeight();

        BufferedImage img= listImageBackground.getCurrentImage().getSubimage(0,0,width,height);
        g.drawImage(img,0,0,getWidth(),getHeight(),null);

        //on draw le boss si il y en a un
        if(Main.game!=null&&Main.game.getMap().isMapGenerated()&&Main.game.getMap().getBoss()!=null)
        {
            drawBoss(g);
        }

        drawIcon(g);
    }

    private void drawIcon(Graphics g)
    {
        int posy=10;
        for(int i=0;i<listIcon.size();i++)
        {
            listIcon.get(i).nextImage();//on calcule l'image

            g.drawImage(listIcon.get(i).getCurrentImage(),0,posy,30,30,null);

            posy+=45;
        }
    }

    private void drawBoss(Graphics g)
    {
        //on dessine le boss
        Boss boss=Main.game.getMap().getBoss();

        BufferedImage img=Main.game.getMap().getBoss().getContainer().getCurrentImage();
        g.drawImage(img,0,getHeight()-getWidth(),getWidth(),getWidth(),null);

        //on calcule l'animation
        lifeBoss.nextImage();

        int sizeImg=getWidth()/10;

        int posy=getHeight()-sizeImg;
        for(int i=0;i<boss.getNbrPv();i++)
        {
            g.drawImage(lifeBoss.getCurrentImage(),0,posy,sizeImg,sizeImg,null);
            posy-=sizeImg;
        }
    }

    private void addIcon(int animationDuration,Animation animation,EntityType entityType)
    {
        Container container=new Container();
        container.init(animationDuration,animation,entityType);

        listIcon.add(container);
    }
}
