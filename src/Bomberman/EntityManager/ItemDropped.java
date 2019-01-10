package Bomberman.EntityManager;

import Bomberman.Animation;
import Bomberman.Main;

import java.awt.*;

public class ItemDropped extends Entity{

    public ItemDropped(Point posInArrayMap) {
        super(posInArrayMap);
        entityType=EntityType.ITEM_DROPPED;
        container.init(Animation.DURATION_ANIMATION_ITEM,Animation.ITEM_BOMB,entityType);
    }

    @Override
    protected void actionOnCollision(Entity entity) {
        if(entity.getClass()==Player.class)
        {
            ((Player)entity).addBombToReserve();
            Main.game.getMap().deleteFromAll(this);
        }
    }
}
