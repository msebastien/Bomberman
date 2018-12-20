package Bomberman.EntityManager;

import Bomberman.Game;

import java.awt.*;

public abstract class TemporaryEntity extends Entity{

    protected int countDown;

    /**
     * @param posInArrayMap
     * @param countDown time beofre the entity disappear
     */
    public TemporaryEntity(Point posInArrayMap, int countDown) {
        super(posInArrayMap);
        this.countDown = countDown;
    }

    @Override
    public void action() {
        countDown-= Game.THREAD_SLEEP;
        if(countDown<=0)
        {
            this.destroy();
        }

    }

    @Override
    protected void actionOnCollision(Entity entity) {

    }
}
