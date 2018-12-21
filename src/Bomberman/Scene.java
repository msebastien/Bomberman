package Bomberman;

import Bomberman.EntityManager.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Scene extends JPanel implements ComponentListener {

    private String stringEndGame;
    private boolean isFirstPrint=true;

    @Override
    protected void paintComponent(Graphics g) {

        //we check if we have the data to print the map
        if(Main.game!=null && Main.game.getMap()!=null && Main.game.getMap().isMapGenerated())
        {
            //if we are on the first print, we have to print all the map first
            if(isFirstPrint)
            {
                printAllMap(g);
                isFirstPrint=false;
            }

            //now we print all the background of the current entities in the map
            Main.game.getMap().getEntitiesList().forEach(entity-> {

                //we paint the background of used tile
                //if this is a subclass of Moving entity
                if(entity instanceof MovingEntity)
                {
                    paintTile(g,((MovingEntity) entity).getOldPosInArrayMap());
                }

                paintTile(g,entity.getPosInArrayMap());

            });

            //we paint the entity itself
            //if the entity is dead , we can't paint it because we want to hide it one last time with the previous
            //operation when we printed the background
            //g.fillRect(entity.getPosInPixelMap().x, entity.getPosInPixelMap().y, Map.WIDTH_TILE, Map.HEIGHT_TILE);
            Main.game.getMap().getEntitiesList().stream().filter(Entity::isAlive).forEach(entity -> {
                if (entity.getClass() == Enemy.class) g.setColor(Color.RED);
                else if (entity.getClass() == Exit.class) g.setColor(Color.MAGENTA);
                else if (entity.getClass() == Bomb.class) g.setColor(Color.CYAN);
                else if (entity.getClass() == Explosion.class) g.setColor(Color.ORANGE);
                else if (entity.getClass() == ItemDropped.class) g.setColor(Color.WHITE);

                else
                g.drawImage(entity.getContainer().getNextImage(), entity.getPosInPixelMap().x, entity.getPosInPixelMap().y, Map.WIDTH_TILE, Map.HEIGHT_TILE, null);

            });

            //now we painted one last time the dead entities we can delete them
            Main.game.getMap().deleteDeadEntities();

            if(stringEndGame!=null)
            {
                g.setFont(new Font("TimesRoman",Font.BOLD,getWidth()/10));
                g.setColor(Color.MAGENTA);
                g.drawString(stringEndGame,getWidth()/2,getHeight()/2);
                return;
            }
        }
    }

    public void setStringEndGame(String stringEndGame) {
        this.stringEndGame = stringEndGame;
    }

    private void printAllMap(Graphics g)
    {
        //we watch each tile and print them
        for(int i=0;i<Map.MAP_SIZE_X;i++)
        {
            for (int j=0;j<Map.MAP_SIZE_Y;j++)
            {
                if(Main.game.getMap().getTile(i,j).getTileType().equals(TileType.GRASS))
                {
                    g.setColor(Color.GREEN);
                }
                else{
                    g.setColor(Color.GRAY);
                }
                g.fillRect(i*Map.WIDTH_TILE,j*Map.HEIGHT_TILE,Map.WIDTH_TILE,Map.HEIGHT_TILE);
            }
        }
    }

    private void paintTile(Graphics g,Point tileToPaint)
    {
        switch (Main.game.getMap().getTile(tileToPaint).getTileType())
        {
            case GRASS:
                g.setColor(Color.GREEN);
                break;
            case OBSTACLE:
                g.setColor(Color.GRAY);
                break;
        }

        g.fillRect(tileToPaint.x*Map.WIDTH_TILE,tileToPaint.y*Map.HEIGHT_TILE,Map.WIDTH_TILE,Map.HEIGHT_TILE);
    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        isFirstPrint=true;
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
