package Bomberman.EntityManager;


import Bomberman.Animation;
import Bomberman.IssueGame;
import Bomberman.Main;

public class Exit extends Entity {

    public Exit() {
        super();
        entityType=EntityType.EXIT;
        container.init(Animation.DURATION_ANIMATION_EXIT,Animation.EXIT,entityType);
    }

    @Override
    public void actionOnCollision(Entity entity) {

        if(entity.getClass()==Player.class)
        {
            Main.game.end(IssueGame.VICTORY);
        }

        //we don't care because this is not a movingentity ,so we never save this returned value
    }

}
