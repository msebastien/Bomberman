package Bomberman.EntityManager;

import Bomberman.Map;

import java.awt.*;

public class Exit extends Entity {


    /*public Exit(Point posInArrayMap) {
        super(posInArrayMap);
    }*/

    /*public void init(Point posInArrayMap)
    {
        this.posInArrayMap=new Point(posInArrayMap);
        this.posInPixelMap=new Point(posInArrayMap.x* Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);
    }*/

    @Override
    //TODO Don't know what to do with the exit each turn , maybe increment something to decrement the point or maybe the
    //time of the game ...if the time is short , you win more point
    public void action() {

    }
}
