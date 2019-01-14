package Bomberman.EntityManager;

import Bomberman.*;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player extends MovingEntity implements KeyListener
{
    private final static String[] keySchema=new String[]{"o","+","x"};
    private final static Map<String,List<Point>> DifferentTypeExplosion= new HashMap<>();
    static {
        DifferentTypeExplosion.put("o", Arrays.asList(new Point(2,1),new Point(1,2),new Point(-2,1),new Point(1,-2),new Point(-2,-1)
                ,new Point(-1,-2),new Point(2,-1),new Point(-1,2)));

        DifferentTypeExplosion.put("+",Arrays.asList(new Point(0,0),new Point(0,-1),new Point(-1,0),new Point(0,1),new Point(1,0)));
        DifferentTypeExplosion.put("x",Arrays.asList(new Point(0,0),new Point(-1,-1),new Point(-1,1),new Point(1,-1),new Point(1,1)));
    }
    /*Arrays.asList(
            Arrays.asList(new Point(0,0),new Point(-1,-1),new Point(-1,1),new Point(1,-1),new Point(1,1)),
            Arrays.asList(new Point(0,0),new Point(0,-1),new Point(-1,0),new Point(0,1),new Point(1,0)),
            Arrays.asList(new Point(2,1),new Point(1,2),new Point(-2,1),new Point(1,-2),new Point(-2,-1)
                    ,new Point(-1,-2),new Point(2,-1),new Point(-1,2)));*/


    //contain the future direction (chose with a key pressed)
    private AtomicBoolean isThrowingBomb;
    private int bombReserve;
    private Point futurePosBomb;
    private int indexExplosion;
    private int range;
    private int timeInvicible;

    private boolean AutorizationNextLevel;

    public Player(Vitesse vitesse){
        super(vitesse.getDurationMs(),Game.lifePLayer,Game.PLAYER_DAMAGE_LEVEL_0,EntityType.PLAYER);
        futurePosBomb=new Point();
        isThrowingBomb=new AtomicBoolean(false);
        indexExplosion=0;
        initStat();
    }

    public void init(Point posInArrayMap) {
        super.init(posInArrayMap);
        AutorizationNextLevel =true;
        isThrowingBomb.set(false);
        directionMovement= Direction.IDLE.getDirection();
        direction =Direction.IDLE;
        container.init(moveDuration,Animation.IDLE,entityType);
        timeInvicible=0;
        initStat();
        nbrPv=Game.lifePLayer;
        bombReserve=Game.nbrBombInitReserve;
    }

    @Override
    public boolean isMoveBegin() {
        boolean res= super.isMoveBegin();

        //on decremente le temps d'invincibilite si invincible
        if(timeInvicible>0)
            timeInvicible-=Game.THREAD_SLEEP;

        if(res)
        {
            directionMovement=direction.getDirection();
            Animation animation=translateDirectionToAnimation();
            container.init(moveDuration,animation,entityType);
        }
        return res ;
    }


    @Override
    public void changeDirection() {
        directionMovement= Direction.IDLE.getDirection();
        container.stopAnimation();
    }


    private void placeBomb()
    {
        //the tile where we will put the bomb
        Point directionExplosion=new Point(futurePosBomb);
        directionExplosion.translate(posInArrayMap.x,posInArrayMap.y);

        if(Main.game!=null && Main.game.getMap().isInsideMap(directionExplosion))
        {
            Tile tile=Main.game.getMap().getTile(directionExplosion);
            if(tile.isFree())
            {
                Bomb bomb=new Bomb(directionExplosion,Game.COUNT_DOWN_BOMB, getCurrentSchema(),damage,range);
                tile.setEntity(bomb);
                Main.game.getMap().getEntitiesList().add(bomb);
                bombReserve--;
            }
        }
    }



    @Override
    public boolean hurt(int damage) {

        //a chaque fois qu'on est frappé on perd ses bonus

        boolean dead=false;
        if(timeInvicible<=0) {
            initStat();
            timeInvicible = Game.COUNT_DOWN_INVINCIBLE_TIME;

            /*nbrPv -= damage;
            if (nbrPv <= 0) {
                Main.game.getMap().deleteFromAll(this);
                Main.game.getMap().addToEntityListOnly(new TemporaryEntity
                        (posInArrayMap, Animation.DURATION_ANIMATION_HURT_PLAYER, Animation.DIE, EntityType.DIE) {
                    @Override
                    public void actionOnDisappearance() {
                        super.actionOnDisappearance();
                        Main.game.end(IssueGame.DEFEAT);
                    }
                });
            } else {
                Main.game.getMap().addToEntityListOnly(new TemporaryEntity
                        (posInArrayMap, Animation.DURATION_ANIMATION_HURT, Animation.DIE, EntityType.DIE));
            }*/
            if(!super.hurt(damage))
            {
                Main.game.getMap().addToEntityListOnly(new TemporaryEntity
                        (posInArrayMap, Animation.DURATION_ANIMATION_HURT, Animation.DIE, EntityType.DIE));
            }
            else dead=true;
        }

        return dead;
    }

    @Override
    public void actionOnDisappearance() {
        super.actionOnDisappearance();

        Main.game.getMap().addToEntityListOnly(new TemporaryEntity
                (posInArrayMap, Animation.DURATION_ANIMATION_HURT_PLAYER, Animation.DIE, EntityType.DIE) {
            @Override
            public void actionOnDisappearance() {
                super.actionOnDisappearance();
                Main.game.end(IssueGame.DEFEAT);
            }
        });
    }

    private void initStat()
    {
        damage=Game.PLAYER_DAMAGE_LEVEL_0;
        range=Game.PLAYER_RANGE_LEVEL_0;
        initSpeed(Game.PLAYER_SPEED.getDurationMs());
    }

    private void nextSchemaExplosion()
    {
        indexExplosion++;
        if(indexExplosion>=keySchema.length)indexExplosion=0;
    }

    private List<Point> getCurrentSchema()
    {
        return DifferentTypeExplosion.get(keySchema[indexExplosion]);
    }

    public String getCurrentSchemaKey()
    {
        return keySchema[indexExplosion];
    }

    @Override
    public void action() {
        super.action();

        if(isThrowingBomb.get()&&bombReserve>0)
        {
            placeBomb();
        }
        isThrowingBomb.set(false);
    }

    public int getBombReserve() {
        return bombReserve;
    }

    public int getRange() {
        return range;
    }

    public void addBombToReserve()
    {
        bombReserve++;
    }
    public void addBombToReserve(int add)
    {
        bombReserve+=add;
    }

    public void addRange()
    {
        range++;
    }

    public void addLife()
    {
        nbrPv++;
    }


    public void addDamage()
    {
        damage++;
    }

    public boolean isAutorizationNextLevel() {
        return AutorizationNextLevel;
    }

    public void setAutorizationNextLevel(boolean autorizationNextLevel) {
        AutorizationNextLevel = autorizationNextLevel;
    }

    @Override
    public void actionOnCollision(Entity entity) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                direction =Direction.WEST;
                break;
            case KeyEvent.VK_RIGHT:
                direction =Direction.EAST;
                break;
            case KeyEvent.VK_UP:
                direction =Direction.NORTH;
                break;
            case KeyEvent.VK_DOWN:
                direction =Direction.SOUTH;
                break;

            case KeyEvent.VK_SPACE:
                nextSchemaExplosion();
                break;

                //gestion bombe
            case KeyEvent.VK_Q:
                futurePosBomb=Direction.WEST.getDirection();
                isThrowingBomb.set(true);
                break;
            case KeyEvent.VK_D:
                isThrowingBomb.set(true);
                futurePosBomb=Direction.EAST.getDirection();
                break;
            case KeyEvent.VK_Z:
                isThrowingBomb.set(true);
                futurePosBomb=Direction.NORTH.getDirection();
                break;
            case KeyEvent.VK_S:
                isThrowingBomb.set(true);
                futurePosBomb=Direction.SOUTH.getDirection();

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                direction =Direction.IDLE;
        }
    }
}
