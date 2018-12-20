package Bomberman.EntityManager;

import Bomberman.Main;

import java.awt.*;

public class ItemDropped extends Entity{

    public ItemDropped(Point posInArrayMap) {
        super(posInArrayMap);
    }

    @Override
    public void action() {

    }

    @Override
    protected void actionOnCollision(Entity entity) {
        if(entity.getClass()==Player.class)
        {
            ((Player)entity).addBombToReserve();
            Main.game.getMap().deleteFromMap(this);
        }
    }
}
