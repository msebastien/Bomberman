package Bomberman;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {

        if(Game.map!=null && Game.map.isMapGenerated())
        {
            //we watch each tile and print them
            for(int i=0;i<Map.MAP_SIZE_X;i++)
            {
                for (int j=0;j<Map.MAP_SIZE_Y;j++)
                {
                    if(Game.map.getTile(i,j).getTileType().equals(TileType.GRASS))
                    {
                        g.setColor(Color.GREEN);
                    }
                    else{
                        g.setColor(Color.GRAY);
                    }
                    g.fillRect(i*Map.WIDTH_TILE,j*Map.HEIGHT_TILE,Map.WIDTH_TILE,Map.HEIGHT_TILE);
                }
            }

            //now we print all of the entity in the map
            g.setColor(Color.RED);
            Game.map.getEntitiesList().forEach(entity->
                    g.fillRect(entity.getPosInPixelMap().x,entity.getPosInPixelMap().y,Map.WIDTH_TILE,Map.HEIGHT_TILE));
        }
    }




}
