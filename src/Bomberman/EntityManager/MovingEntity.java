package Bomberman.EntityManager;

import Bomberman.Game;
import Bomberman.Map;

import java.awt.Point;

public abstract class MovingEntity extends Bomberman.EntityManager.Entity
{
    protected Point directionMovement;
    protected Point constPixelMovement;

    //indicate and solve if there is a collision with an other entity
    public abstract boolean collisionWith(Bomberman.EntityManager.Entity entity);

    //update the coordinate in pixel of the entity after a move
    public void translatePixelEntity()
    {
        Point transitional=new Point(directionMovement);
        transitional.setLocation(transitional.getX()* constPixelMovement.getX(),transitional.getY()* constPixelMovement.getY());
        posInPixelMap.translate((int)transitional.getX(),(int)transitional.getY());
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

            //we have to check his future Tile
            Point futureTile=new Point(posInArrayMap);
            //we set his future tile
            futureTile.translate((int)directionMovement.getX(),(int)directionMovement.getY());

            //if it's not inside map OR it's not free
            if( !Game.map.isInsideMap(futureTile) || !Game.map.getTile(futureTile).isFree())
            {
                changeDirection();

                futureTile.setLocation(posInArrayMap);
                futureTile.translate((int)directionMovement.getX(),(int)directionMovement.getY());
            }

            Game.map.getTile(futureTile).setEntity(this);
            Game.map.getTile(posInArrayMap).setEntity(null);
            posInArrayMap.setLocation(futureTile);
        }

        //he translates himself forward with his direction
        translatePixelEntity();
    }

    //what to do if entity is blocked
    protected abstract void changeDirection();

}
