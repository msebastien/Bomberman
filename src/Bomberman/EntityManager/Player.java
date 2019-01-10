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

    public Player(int moveDuration){
        super(moveDuration);
        futurePosBomb=new Point();
        isThrowingBomb=new AtomicBoolean(false);
        entityType=EntityType.PLAYER;
        indexExplosion=0;
    }

    public void init(Point posInArrayMap) {
        super.init(posInArrayMap);
        isThrowingBomb.set(false);
        bombReserve=Game.nbrBombInitReserve;
        directionMovement= Direction.IDLE.getDirection();
        direction =Direction.IDLE;
        container.init(moveDuration,Animation.IDLE,entityType);

    }

    @Override
    public boolean isMoveBegin() {
        boolean res= super.isMoveBegin();
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
                Bomb bomb=new Bomb(directionExplosion,Game.COUNT_DOWN_BOMB, getCurrentSchema());
                tile.setEntity(bomb);
                Main.game.getMap().getEntitiesList().add(bomb);
                bombReserve--;
            }
        }
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
            isThrowingBomb.set(false);
            placeBomb();
        }
    }

    public int getBombReserve() {
        return bombReserve;
    }

    public void addBombToReserve()
    {
        bombReserve++;
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
