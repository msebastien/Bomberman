package Bomberman.EntityManager;

import Bomberman.*;


import java.awt.Point;
import java.util.*;
import java.util.Map;


public class Enemy extends MovingEntity implements ActionOnDisappearance
{

    public static final List<Enemy> ListEnemy= Arrays.asList(
            new Enemy(Game.speedEnemy,EntityType.KNIGHT,2),
            new Enemy(Game.speedEnemy,EntityType.ENEMY,1));

    private int nbrPv;

    private Enemy(int moveDurationMs,EntityType entityType, int nbrPv) {
        super(moveDurationMs);
        this.entityType=entityType;
        this.nbrPv = nbrPv;
    }

    public Enemy(Enemy enemyCopy) {
        super(enemyCopy.moveDuration);
        nbrPv=enemyCopy.nbrPv;
        direction=Randomator.getRandomElementIn(Direction.directionList);
        directionMovement= direction.getDirection();
        this.entityType=enemyCopy.entityType;

        Animation animation=translateDirectionToAnimation();
        container.init(moveDuration,animation,entityType);
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

        if(listPossibleExit.size()>0) {
            direction=Randomator.getRandomElementIn(listPossibleExit);
        }
        else
            //like that the entity will not move next time and wait for an exit
        {
            direction=Direction.IDLE;
        }
        Animation animation=translateDirectionToAnimation();
        container.init(moveDuration,animation,entityType);
        directionMovement=direction.getDirection();
    }

    public boolean hurt(int damage)
    {
        nbrPv-=damage;
        return(nbrPv<=0);
    }



    @Override
    public void actionOnDisappearance() {
        Main.game.getMap().addToMap(new ItemDropped(new Point(this.posInArrayMap)));
    }
}
