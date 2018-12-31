package Bomberman.EntityManager;

import Bomberman.Container;
import Bomberman.Map;

import java.awt.Point;

public abstract class Entity {

    protected Point posInArrayMap;
    protected Point posInPixelMap;
    Container container; // contains images for animation


    public Entity(Point posInArrayMap)
    {
        init(posInArrayMap);

    }

    //just used to call super from moving entity
    public Entity() {
        container = new Container();
    }

    public void init(Point posInArrayMap)
    {
        this.posInArrayMap=new Point(posInArrayMap);
        this.posInPixelMap=new Point(posInArrayMap.x* Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);

    }

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

    public Container getContainer(){
        return container;
    }
}
