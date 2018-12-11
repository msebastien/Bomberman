package Bomberman.EntityManager;

import java.awt.Point;

public abstract class Entity {

    protected Point posInArrayMap;
    protected Point posInPixelMap;

    public abstract void action();
}
