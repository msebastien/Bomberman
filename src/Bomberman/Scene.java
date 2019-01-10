package Bomberman;

import Bomberman.EntityManager.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Scene extends JPanel  {


    @Override
    protected void paintComponent(Graphics g) {


        //we check if we have the data to print the map
        if(Main.game!=null && Main.game.getMap()!=null && Main.game.getMap().isMapGenerated())
        {
            //if we are on the first print, we have to print all the map first

            printAllMap(g);


            /*//now we print all the backgrounf of the current entities in the map
            Main.game.getMap().getEntitiesList().forEach(entity-> {

                //we paint the background of used tile
                //if this is a subclass of Moving entity
                if(entity instanceof MovingEntity)
                {
                    paintTile(g,((MovingEntity) entity).getOldPosInArrayMap());
                }

                paintTile(g,entity.getPosInArrayMap());

            });*/

            Main.game.getMap().getEntitiesList().forEach(entity-> {
                //we paint the entity itself

                //if the entity is dead , we can't paint it because we want to hide it one last time with the previous
                //operation when we printed the background
                //if(entity.isAlive()) {





                g.drawImage(entity.getContainer().getCurrentImage(), entity.getPosInPixelMap().x, entity.getPosInPixelMap().y, Map.WIDTH_TILE, Map.HEIGHT_TILE, null);

                //}

            });

            //now we painted one last time the dead entities we can delete them
            //Main.game.getMap().deleteDeadEntities();

        }
    }


    private void printAllMap(Graphics g)
    {
        //we watch each tile and print them
        Map map=Main.game.getMap();
        for(int i=0;i<Map.MAP_SIZE_X;i++)
        {
            for (int j=0;j<Map.MAP_SIZE_Y;j++)
            {
                g.drawImage(map.getTile(i,j).getTileType().getImage(),i*Map.WIDTH_TILE, j*Map.HEIGHT_TILE, Map.WIDTH_TILE, Map.HEIGHT_TILE, null);
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


}
