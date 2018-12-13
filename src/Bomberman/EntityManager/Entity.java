package Bomberman.EntityManager;

import Bomberman.Map;

import java.awt.Point;

public abstract class Entity {

    protected Point posInArrayMap;
    protected Point posInPixelMap;

    public Entity(Point posInArrayMap)
    {
        this.posInArrayMap=new Point(posInArrayMap);
        this.posInPixelMap=new Point(posInArrayMap.x* Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);
    }

    public abstract void action();

    public Point getPosInPixelMap() {
        return posInPixelMap;
    }
}
