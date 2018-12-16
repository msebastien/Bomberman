package Bomberman.EntityManager;

import Bomberman.*;


import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class Enemy extends MovingEntity
{

    /*public Enemy(Point posInArrayMap, int moveDurationMs) {
        super(posInArrayMap, moveDurationMs);
        directionMovement= Randomator.getRandomElementIn(Direction.directionList).getDirection();
    }*/

    public Enemy(int moveDuration) {
        super(moveDuration);
        directionMovement= Randomator.getRandomElementIn(Direction.directionList).getDirection();
    }



    /*public void init(Point posInArrayMap, int moveDurationMs)
    {
        super.init(posInArrayMap,moveDurationMs);
        directionMovement= Randomator.getRandomElementIn(Direction.directionList).getDirection();
    }*/


    @Override
    public boolean collisionWith(Point caseCollision) {
        return true;
    }

    @Override
    public void action() {

        //if the ennemy is at the beginning of a tile
        if(isMoveBegin())
        {

            posInPixelMap.setLocation(posInArrayMap.x* Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);

            //we have to check his future Tile
            Point futureTile=new Point(posInArrayMap);
            //we set his future tile
            futureTile.translate(directionMovement.x,directionMovement.y);

            //if it's not inside map OR it's not free
            if( !Main.game.getMap().isInsideMap(futureTile) || !Main.game.getMap().getTile(futureTile).isFree())
            {
                changeDirection();

                futureTile.setLocation(posInArrayMap);
                futureTile.translate(directionMovement.x,directionMovement.y);
            }

            Main.game.getMap().getTile(posInArrayMap).setEntity(null);
            Main.game.getMap().getTile(futureTile).setEntity(this);

            posInArrayMap.setLocation(futureTile);
        }

        //he translates himself forward with his direction
        translatePixelEntity();
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
}
