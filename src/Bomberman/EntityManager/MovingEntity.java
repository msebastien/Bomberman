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

    public MovingEntity(Point posInArrayMap, int moveDurationMs) {
        super(posInArrayMap);
        directionMovement= Randomator.getRandomElementIn(Direction.directionList).getDirection();

        constPixelMovement=new Point(Map.WIDTH_TILE/(moveDurationMs/Game.THREAD_SLEEP),
                Map.HEIGHT_TILE/(moveDurationMs/Game.THREAD_SLEEP));

    }

    //indicate and solve if there is a collision with an other entity
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
    public void action() {

        //if the ennemy is at the beginning of a tile
        if(isMoveBegin())
        {

            posInPixelMap.setLocation(posInArrayMap.x*Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);

            //we have to check his future Tile
            Point futureTile=new Point(posInArrayMap);
            //we set his future tile
            futureTile.translate(directionMovement.x,directionMovement.y);

            //if it's not inside map OR it's not free
            if( !Game.map.isInsideMap(futureTile) || !Game.map.getTile(futureTile).isFree())
            {
                changeDirection();

                futureTile.setLocation(posInArrayMap);
                futureTile.translate(directionMovement.x,directionMovement.y);
            }

            Game.map.getTile(posInArrayMap).setEntity(null);
            Game.map.getTile(futureTile).setEntity(this);
            posInArrayMap.setLocation(futureTile);
        }

        //he translates himself forward with his direction
        translatePixelEntity();
    }

    //what to do if entity is blocked
    protected abstract void changeDirection();

}
