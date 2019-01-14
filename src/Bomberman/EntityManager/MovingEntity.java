package Bomberman.EntityManager;

import Bomberman.*;

import java.awt.Point;
import java.util.HashMap;

public abstract class MovingEntity extends Entity implements ActionOnDisappearance
{

    protected int damage;
    protected Point directionMovement;
    protected Point constPixelMovement;
    protected Direction direction;
    protected int moveDuration;
    protected int nbrPv;


    //just used to a create a player without parameter

    public MovingEntity(int moveDurationMs,int Pv,int damage,EntityType entityType) {
        super(entityType);
        initSpeed(moveDurationMs);
        nbrPv=Pv;
        this.damage=damage;
    }

    public MovingEntity(MovingEntity movingEntity,Point posInArrayMap) {
        super(posInArrayMap,movingEntity.entityType);
        initSpeed(movingEntity.moveDuration);
        nbrPv=movingEntity.nbrPv;
        this.damage=movingEntity.damage;
    }

    public void initSpeed(int moveDuration)
    {
        this.moveDuration=moveDuration;
        int pixelWidth= (int) Math.rint(((float)Map.WIDTH_TILE)/((float)moveDuration/(float)Game.THREAD_SLEEP));
        int pixelHeight=(int) Math.rint(((float)Map.WIDTH_TILE)/((float)moveDuration/(float)Game.THREAD_SLEEP));
        constPixelMovement=new Point(pixelWidth,pixelHeight);
    }



    /**
     * this function solve the effect of a collision with an other entity, win the game if we enter in the exit
     * @param caseCollision the case we go inside
    * @return true if the tile get an entity
     */
    public final boolean collisionWith(Point caseCollision)
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
        return Main.game.getMap().getTile(caseCollision).hasEntity();
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
        super.action();

        move();
    }

    private void move()
    {
        if(isMoveBegin())
        {
            posInPixelMap.setLocation(posInArrayMap.x* Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);

            //we have to check his future Tile
            Point futureTile=new Point(posInArrayMap);
            //we set his future tile
            futureTile.translate(directionMovement.x,directionMovement.y);

            //if it's not inside map OR it's not free


            boolean changeDirectionCollision=true;
            if(Main.game.getMap().isInsideMap(futureTile)&&
                    Main.game.getMap().getTile(futureTile).isWay())
            {
                if(Main.game.getMap().getTile(futureTile).hasEntity())
                {
                    changeDirectionCollision=this.collisionWith(futureTile);
                }else
                {
                    changeDirectionCollision=false;
                }
            }

            if(changeDirectionCollision)
            {
                changeDirection();

                futureTile.setLocation(posInArrayMap);
                futureTile.translate(directionMovement.x,directionMovement.y);
            }


            if(!posInArrayMap.equals(futureTile))
            {
                Main.game.getMap().getTile(posInArrayMap).setEntity(null);
                Main.game.getMap().getTile(futureTile).setEntity(this);
            }

            //we save our old position
            //oldPosInArrayMap.setLocation(posInArrayMap);
            posInArrayMap.setLocation(futureTile);
        }

        //he translates himself forward with his direction
        translatePixelEntity();
    }

    public boolean hurt(int damage)
    {
        nbrPv-=damage;
        if(nbrPv<=0)
        {
            actionOnDisappearance();
            return true;
        }

        return false;

    }

    @Override
    public void actionOnDisappearance() {
        Main.game.getMap().deleteFromAll(this);
    }

    @Override
    public void init(Point posInArrayMap) {
        super.init(posInArrayMap);
        //this.oldPosInArrayMap=new Point(posInArrayMap);
    }


    public int getDamage() {
        return damage;
    }

    public abstract void changeDirection();



    protected Animation translateDirectionToAnimation()
    {
        Animation animation=null;
        switch(direction)
        {
            case NORTH:
                animation=Animation.MOVE_NORTH;
                break;
            case WEST:
                animation=Animation.MOVE_WEST;
                break;
            case EAST:
                animation=Animation.MOVE_EAST;
                break;
            case SOUTH:
                animation=Animation.MOVE_SOUTH;
                break;
            case IDLE:
                animation=Animation.IDLE;
                break;
        }

        return animation;
    }

    public void setDirection(Direction direction)
    {
        this.direction=direction;
        directionMovement=direction.getDirection();
    }

    public int getNbrPv() {
        return nbrPv;
    }
}
