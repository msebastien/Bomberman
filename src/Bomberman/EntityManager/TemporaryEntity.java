package Bomberman.EntityManager;

import Bomberman.Animation;
import Bomberman.Game;
import Bomberman.Main;

import java.awt.*;

public class TemporaryEntity extends Entity implements ActionOnDisappearance{

    protected int countDown;

    /**
     * @param posInArrayMap
     * @param countDown time beofre the entity disappear
     */
    public TemporaryEntity(Point posInArrayMap, int countDown,Animation animation,EntityType entityType) {
        super(posInArrayMap,entityType);
        this.countDown = countDown;
        container.init(countDown, animation,entityType);
    }

    @Override
    public void action() {
        super.action();
        countDown-= Game.THREAD_SLEEP;
        if(countDown<=0)
        {
            actionOnDisappearance();
            //Main.game.getMap().deleteFromAll(this);
        }

    }

    @Override
    protected void actionOnCollision(Entity entity) {

    }

    @Override
    public void actionOnDisappearance() {
        Main.game.getMap().getEntitiesList().remove(this);

    }
}
