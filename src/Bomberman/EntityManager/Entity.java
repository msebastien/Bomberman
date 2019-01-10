package Bomberman.EntityManager;

import Bomberman.Animation;
import Bomberman.Container;
import Bomberman.Map;

import java.awt.Point;

public abstract class Entity {

    protected Point posInArrayMap;
    protected Point posInPixelMap;
    Container container;
    protected EntityType entityType;

    //check if we can delete the entity from the entity list
    //protected boolean isAlive;

    public Entity(Point posInArrayMap)
    {
        container = new Container();
        init(posInArrayMap);
    }

    //just used to call super from moving entity
    public Entity() {
        container = new Container();
    }

    //function always called when instantiate an entity
    public void init(Point posInArrayMap)
    {
        this.posInArrayMap=new Point(posInArrayMap);
        this.posInPixelMap=new Point(posInArrayMap.x* Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);
    }

    public void action(){
        container.nextImage();
    }

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

    public Container getContainer() {
        return container;
    }
}
