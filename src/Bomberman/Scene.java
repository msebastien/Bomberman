package Bomberman;

import Bomberman.EntityManager.Enemy;
import Bomberman.EntityManager.Exit;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {

    private String stringEndGame;

    @Override
    protected void paintComponent(Graphics g) {

        if(Main.game!=null && Main.game.getMap()!=null && Main.game.getMap().isMapGenerated())
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

            //now we print all of the entity in the map
            Main.game.getMap().getEntitiesList().forEach(entity-> {
                if(entity.getClass()== Enemy.class)g.setColor(Color.RED);
                else if(entity.getClass()== Exit.class)g.setColor(Color.ORANGE);
                else g.setColor(Color.BLUE);
                g.fillRect(entity.getPosInPixelMap().x, entity.getPosInPixelMap().y, Map.WIDTH_TILE, Map.HEIGHT_TILE);
            });

            //TODO very bad implement , change after for interface pattern
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
}
