package Bomberman.EntityManager;

import Bomberman.Game;
import Bomberman.Main;
import Bomberman.Map;
import Bomberman.Tile;

import java.awt.*;

public class Bomb extends Entity{

    //counting down when it go below zero , it explodes
    private int countDown;//time in ms

    public Bomb(Point posInArrayMap) {
        super(posInArrayMap);
        countDown=5000;
    }

    private void explode()
    {
        // we search all the tile around the bomb

        Map map=Main.game.getMap();
        Tile tile;
        for(int i=posInArrayMap.x-1;i<=posInArrayMap.x+1;i++)
        {
            for(int j=posInArrayMap.y-1;j<=posInArrayMap.y+1;j++)
            {
                //we destroy the entity concerned
                if(map.isInsideMap(i,j) && !(i==posInArrayMap.x&&j==posInArrayMap.y))
                {
                    tile=map.getTile(i,j);

                    if(tile.hasItem()&&tile.getEntity() instanceof Enemy)
                    {
                        tile.getEntity().destroy();
                        tile.setEntity(null);
                    }
                }
            }
        }

        this.destroy();
        map.getTile(posInArrayMap).setEntity(null);
    }

    @Override
    public void action() {

        //we decrement the countdown
        countDown-= Game.THREAD_SLEEP;
        if(countDown<=0)
        {
            explode();
            //we explode this bomb
        }
    }

    @Override
    protected void actionOnCollision(Entity entity) {
    }
}
