package Bomberman.EntityManager;

import Bomberman.Map;

import java.awt.Point;

public abstract class Entity {

    protected Point posInArrayMap;
    protected Point posInPixelMap;

    //check if we can delete the entity from the entity list
    //protected boolean isAlive;

    public Entity(Point posInArrayMap)
    {
        init(posInArrayMap);
    }

    //just used to call super frommoving entity
    public Entity() {}

    public void init(Point posInArrayMap)
    {
        //isAlive=true;
        this.posInArrayMap=new Point(posInArrayMap);
        this.posInPixelMap=new Point(posInArrayMap.x* Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);
    }

    /*public boolean isAlive() {
        return isAlive;
    }

    public void destroy() {
        isAlive = false;
    }*/

    public abstract void action();

    /**
     * Something to do when you enter in an other entity
     * What to do to the other entity
     * @param entity
     */
    protected abstract void actionOnCollision(Entity entity);

    public Point getPosInPixelMap() {
        return posInPixelMap;
    }

    public Point getPosInArrayMap() {
        return posInArrayMap;
    }
}
