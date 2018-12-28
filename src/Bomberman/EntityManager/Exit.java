package Bomberman.EntityManager;


import Bomberman.IssueGame;
import Bomberman.Main;

public class Exit extends Entity {


    @Override
    public void actionOnCollision(Entity entity) {

        if(entity.getClass()==Player.class)
        {
            Main.game.end(IssueGame.VICTORY);
        }

        //we don't care because this is not a movingentity ,so we never save this returned value
    }

    @Override
    //TODO Don't know what to do with the exit each turn , maybe increment something to decrement the point or maybe the
    //time of the game ...if the time is short , you win more point
    public void action() {

    }
}
