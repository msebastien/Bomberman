package Bomberman.EntityManager;

import Bomberman.Direction;
import Bomberman.Game;
import Bomberman.Randomator;


import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Enemy extends MovingEntity
{
    @Override
    public boolean collisionWith(Entity entity) {
        return true;
    }

    @Override
    protected void changeDirection() {
        List<Direction> listPossibleExit=new LinkedList<>();

        Point testPoint=new Point();
        for(int i=0;i< Game.map.getDirectionList().size();i++)
        {
            testPoint.setLocation(Game.map.getDirectionList().get(i).getDirection());
            testPoint.translate((int)posInArrayMap.getX(),(int)posInArrayMap.getY());

            if(Game.map.isInsideMap(testPoint) && Game.map.getTile(testPoint).isFree())
            {
                listPossibleExit.add(Game.map.getDirectionList().get(i));
            }
        }

        if(listPossibleExit.size()>0)
            directionMovement= Randomator.getRandomElementIn(listPossibleExit).getDirection();
        else
            //like that the entity will not move next time and wait for an exit
            directionMovement=Direction.IDLE.getDirection();

    }
}
