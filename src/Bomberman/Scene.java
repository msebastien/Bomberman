package Bomberman;


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

                g.drawImage(entity.getContainer().getCurrentImage(), entity.getPosInPixelMap().x, entity.getPosInPixelMap().y, Map.WIDTH_TILE, Map.HEIGHT_TILE, null);

                //}

            });


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




}
