package Bomberman.EntityManager;

import Bomberman.Direction;
import Bomberman.Game;
import Bomberman.Map;
import Bomberman.Randomator;

import java.awt.Point;

public abstract class MovingEntity extends Bomberman.EntityManager.Entity
{
    protected Point directionMovement;
    protected Point constPixelMovement;

    /*public MovingEntity(Point posInArrayMap, int moveDurationMs) {
        //super(posInArrayMap);

        init(posInArrayMap,moveDurationMs);

    }*/

    //just used to a create a player without parameter
    public MovingEntity(int moveDurationMs) {
        super();
        constPixelMovement=new Point(Map.WIDTH_TILE/(moveDurationMs/Game.THREAD_SLEEP),
                Map.HEIGHT_TILE/(moveDurationMs/Game.THREAD_SLEEP));
    }



    //TODO
    /**
     * this function solve the effect of a collision with an other entity, win the game if we enter in the exit
     * @param caseCollision the case we go inside
     * @return true if we can't go inside the case so we have to choose an other direction
     */
    public abstract boolean collisionWith(Point caseCollision);

    //update the coordinate in pixel of the entity after a move
    public void translatePixelEntity()
    {
        Point transitional=new Point(directionMovement);
        transitional.setLocation(transitional.getX()* constPixelMovement.getX(),transitional.getY()* constPixelMovement.getY());
        posInPixelMap.translate(transitional.x,transitional.y);
    }

    public boolean isMoveBegin()
    {
        return Math.abs(posInPixelMap.getX()-Map.WIDTH_TILE*posInArrayMap.getX())<=(constPixelMovement.getX()/2) &&
            Math.abs(posInPixelMap.getY()-Map.HEIGHT_TILE*posInArrayMap.getY())<=(constPixelMovement.getY()/2);
    }

    @Override
    public abstract void action();

}
