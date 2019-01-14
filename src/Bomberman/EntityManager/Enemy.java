package Bomberman.EntityManager;

import Bomberman.*;


import java.awt.Point;
import java.util.*;
import java.util.Map;


public class Enemy extends MovingEntity
{

    public static final List<Enemy> ListEnemy= Arrays.asList(
            new Enemy(Vitesse.LENT,EntityType.KNIGHT,2,2),
            new Enemy(Vitesse.RAPIDE,EntityType.LEVIATHAN,4,1),
            new Enemy(Vitesse.MOYEN,EntityType.EVIL,2,1),
            new Enemy(Vitesse.MOYEN,EntityType.ENEMY,1,1));




    protected Enemy(Vitesse vitesse,EntityType entityType,int damage, int nbrPv) {
        super(vitesse.getDurationMs(),nbrPv,damage,entityType);
    }

    public Enemy(Enemy enemyCopy) {
        super(enemyCopy.moveDuration,enemyCopy.nbrPv,enemyCopy.damage,enemyCopy.entityType);
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
            //Main.game.end(IssueGame.DEFEAT);
            ((Player)entity).hurt(damage);
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

    @Override
    public boolean hurt(int damage)
    {
        /*nbrPv-=damage;
        if(nbrPv<=0)
        {
            Main.game.getMap().deleteFromAll(this);
            actionOnDisappearance();
        }*/

        Main.game.getMap().addToEntityListOnly(new TemporaryEntity(
                this.getPosInArrayMap(),
                Animation.DURATION_ANIMATION_HURT,
                Animation.HURT,
                EntityType.HURT));

        return super.hurt(damage);
    }



    @Override
    public void actionOnDisappearance() {
        super.actionOnDisappearance();

        Main.game.getMap().addToMap(itemToDrop());
    }

    protected ItemDropped itemToDrop()
    {
        return new ItemDropped(Randomator.getRandomElementIn(ItemDropped.listItem),posInArrayMap);

    }


}
