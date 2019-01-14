package Bomberman.EntityManager;

import Bomberman.Animation;
import Bomberman.Game;
import Bomberman.Main;


public class Chest extends Entity {
    public Chest() {
        super(EntityType.CHEST);
        container.init(Animation.DURATION_ANIMATION_CHEST,Animation.CHEST_OPEN,entityType);
    }

    @Override
    protected void actionOnCollision(Entity entity) {
        if(entity.getClass()==Player.class&&((Player)entity).getBombReserve()==0)
        {
            ((Player)entity).addBombToReserve(Main.game.getLevel()/ Game.FRQCY_BOSS+1);
        }
    }
}
