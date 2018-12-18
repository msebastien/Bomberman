package Bomberman.EntityManager;

import Bomberman.*;

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
        int pixelWidth= (int) Math.rint(((float)Map.WIDTH_TILE)/((float)moveDurationMs/(float)Game.THREAD_SLEEP));
        int pixelHeight=(int) Math.rint(((float)Map.WIDTH_TILE)/((float)moveDurationMs/(float)Game.THREAD_SLEEP));

        constPixelMovement=new Point(pixelWidth,pixelHeight);
    }



    /**
     * this function solve the effect of a collision with an other entity, win the game if we enter in the exit
     * @param caseCollision the case we go inside
     */
    public final void collisionWith(Point caseCollision)
    {

        Entity entityMet= Main.game.getMap().getTile(caseCollision).getEntity();

        //if there is no entity to meet , there is no collision
        if(entityMet!=null)
        {
            //if there is an entity to strike
            //we solve the effect for the both entity : this entity and the entity met
            entityMet.actionOnCollision(this);

            //here we save the returned value of actionOnCollision because it indicates if we can go inside
            //the caseCollision after handle the collision
            this.actionOnCollision(entityMet);

            //System.out.println(this.getClass().getSimpleName()+":"+entityMet.getClass().getSimpleName()+"="+isThereCollision);
        }
        //System.out.println(this.getClass().getSimpleName()+"="+isThereCollision);

    }

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
        if(isMoveBegin())
        {

            posInPixelMap.setLocation(posInArrayMap.x* Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);

            //we have to check his future Tile
            Point futureTile=new Point(posInArrayMap);
            //we set his future tile
            futureTile.translate(directionMovement.x,directionMovement.y);

            //if it's not inside map OR it's not free


            if( !Main.game.getMap().isInsideMap(futureTile)
                    || !Main.game.getMap().getTile(futureTile).isFree())
            {

                if(Main.game.getMap().isInsideMap(futureTile)&&Main.game.getMap().getTile(futureTile).hasItem())
                    this.collisionWith(futureTile);

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

    public abstract void changeDirection();
}
