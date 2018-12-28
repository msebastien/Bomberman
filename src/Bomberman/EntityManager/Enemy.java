package Bomberman.EntityManager;

import Bomberman.*;


import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class Enemy extends MovingEntity implements ActionOnDisappearance
{



    public Enemy(int moveDuration) {
        super(moveDuration);
        directionMovement= Randomator.getRandomElementIn(Direction.directionList).getDirection();
    }


    @Override
    public void actionOnCollision(Entity entity) {

        //if we enter in a player
        if(entity.getClass()==Player.class)
        {
            //this is the end of the game
            Main.game.end(IssueGame.DEFEAT);
        }

    }

    public void changeDirection() {
        List<Direction> listPossibleExit=new LinkedList<>();

        Point testPoint=new Point();
        for(int i=0;i< Direction.directionList.size();i++)
        {
            testPoint.setLocation(Direction.directionList.get(i).getDirection());
            testPoint.translate(posInArrayMap.x,posInArrayMap.y);

            if(Main.game.getMap().isInsideMap(testPoint) && Main.game.getMap().getTile(testPoint).isFree())
            {
                listPossibleExit.add(Direction.directionList.get(i));
            }
        }

        if(listPossibleExit.size()>0)
            directionMovement= Randomator.getRandomElementIn(listPossibleExit).getDirection();
        else
            //like that the entity will not move next time and wait for an exit
            directionMovement=Direction.IDLE.getDirection();

    }

    @Override
    public void actionOnDisappearance() {
        Main.game.getMap().addToMap(new ItemDropped(new Point(this.posInArrayMap)));
    }
}
