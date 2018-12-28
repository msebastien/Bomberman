package Bomberman;

import Bomberman.EntityManager.*;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel  {


    @Override
    protected void paintComponent(Graphics g) {

        //we check if we have the data to print the map
        if(Main.game!=null && Main.game.getMap()!=null && Main.game.getMap().isMapGenerated())
        {
            //if we are on the first print, we have to print all the map first
            printAllMap(g);

            Main.game.getMap().getEntitiesList().forEach(entity-> {
                //we paint the entity itself

                    if (entity.getClass() == Enemy.class) g.setColor(Color.RED);
                    else if (entity.getClass() == Exit.class) g.setColor(Color.MAGENTA);
                    else if (entity.getClass() == Bomb.class) g.setColor(Color.CYAN);
                    else if(entity.getClass()== Explosion.class) g.setColor(Color.ORANGE);
                    else if(entity.getClass()== ItemDropped.class) g.setColor(Color.WHITE);

                    if(entity.getClass() == Player.class){
                        g.drawImage(entity.getContainer().getNextImage(), entity.getPosInPixelMap().x, entity.getPosInPixelMap().y, Map.WIDTH_TILE, Map.HEIGHT_TILE, null);
                    } else {
                        g.fillRect(entity.getPosInPixelMap().x, entity.getPosInPixelMap().y, Map.WIDTH_TILE, Map.HEIGHT_TILE);
                    }


            });

        }
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


}
