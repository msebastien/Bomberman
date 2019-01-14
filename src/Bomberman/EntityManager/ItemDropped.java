package Bomberman.EntityManager;

import Bomberman.Animation;
import Bomberman.Main;

import java.awt.*;

public class ItemDropped extends Entity{

    public static ItemDropped[] listItem=new ItemDropped[]{
            new ItemDropped(Animation.ITEM_RANGE,new RangeDropped()),
            new ItemDropped(Animation.ITEM_BOMB,new BombDropped()),
            new ItemDropped(Animation.ITEM_DAMAGE,new DamageDropped()),
            new ItemDropped(Animation.ITEM_SPEED,new SpeedDropped()),
            new ItemDropped(Animation.ITEM_PV,new PvDropped())
    };

    public static ItemDropped KeyEscape=new ItemDropped(Animation.ITEM_KEY,new KeyDropped());

    private Animation animation;
    private ItemAction actionOnPlayerMeeting;

    protected ItemDropped(Animation animation,ItemAction item) {
        super(EntityType.ITEM_DROPPED);
        actionOnPlayerMeeting=item;
        this.animation=animation;
    }

    public ItemDropped(ItemDropped itemCopy, Point posInArrayMap)
    {
        super(posInArrayMap,itemCopy.entityType);
        actionOnPlayerMeeting=itemCopy.actionOnPlayerMeeting;
        animation=itemCopy.animation;
        container.init(Animation.DURATION_ANIMATION_ITEM,animation,entityType);
    }

    public ItemDropped(ItemDropped itemCopy)
    {
        super(itemCopy.entityType);
        actionOnPlayerMeeting=itemCopy.actionOnPlayerMeeting;
        animation=itemCopy.animation;
        container.init(Animation.DURATION_ANIMATION_ITEM,animation,entityType);
    }

    @Override
    protected void actionOnCollision(Entity entity) {
        if(entity.getClass()==Player.class)
        {
            Main.game.getMap().deleteFromAll(this);
            actionOnPlayerMeeting.meetPlayer((Player)entity);
        }
    }
}
