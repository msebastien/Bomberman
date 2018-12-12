package Bomberman.EntityManager;

import Bomberman.Direction;
import Bomberman.Game;
import Bomberman.Randomator;


import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class Enemy extends MovingEntity
{

    public Enemy(Point posInArrayMap, int moveDurationMs) {
        super(posInArrayMap, moveDurationMs);
    }

    //TODO
    @Override
    public boolean collisionWith(Point caseCollision) {
        return true;
    }

    @Override
    protected void changeDirection() {
        List<Direction> listPossibleExit=new LinkedList<>();

        Point testPoint=new Point();
        for(int i=0;i< Direction.directionList.size();i++)
        {
            testPoint.setLocation(Direction.directionList.get(i).getDirection());
            testPoint.translate(posInArrayMap.x,posInArrayMap.y);

            if(Game.map.isInsideMap(testPoint) && Game.map.getTile(testPoint).isFree())
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
}
