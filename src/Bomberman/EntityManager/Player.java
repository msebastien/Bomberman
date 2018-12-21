package Bomberman.EntityManager;

import Bomberman.*;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player extends MovingEntity implements KeyListener
{

    //contain the future direction (chose with a key pressed)
    private Point futureDirection;
    private AtomicBoolean isThrowingBomb;
    private int bombReserve;
    private Point futurePosBomb;

    public Player(int moveDuration){
        super(moveDuration);
        bombReserve=15;
        futurePosBomb=new Point();
        isThrowingBomb=new AtomicBoolean(false);
        directionMovement= Direction.IDLE.getDirection();
        futureDirection=Direction.IDLE.getDirection();
    }


    @Override
    public boolean isMoveBegin() {
        boolean res= super.isMoveBegin();
        if(res)
        {
            directionMovement=futureDirection;
        }
        return res ;
    }


    @Override
    public void changeDirection() {
        directionMovement= Direction.IDLE.getDirection();
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
                Bomb bomb=new Bomb(directionExplosion,4000, Randomator.getRandomElementIn(Bomb.DifferentTypeExplosion));
                tile.setEntity(bomb);
                Main.game.getMap().getEntitiesList().add(bomb);
            }
        }
    }

    @Override
    public void action() {
        super.action();
        if(isThrowingBomb.get()&&bombReserve>0)
        {
            isThrowingBomb.set(false);
            placeBomb();
            bombReserve--;
        }
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
                futureDirection=Direction.WEST.getDirection();
                break;
            case KeyEvent.VK_RIGHT:
                futureDirection=Direction.EAST.getDirection();
                break;
            case KeyEvent.VK_UP:
                futureDirection=Direction.NORTH.getDirection();
                break;
            case KeyEvent.VK_DOWN:
                futureDirection=Direction.SOUTH.getDirection();
                break;

                //gestion bombe
            case KeyEvent.VK_SPACE:
                isThrowingBomb.set(true);
                break;
                //direction Bombe
            case KeyEvent.VK_Q:
                futurePosBomb=Direction.WEST.getDirection();
                break;
            case KeyEvent.VK_D:
                futurePosBomb=Direction.EAST.getDirection();
                break;
            case KeyEvent.VK_Z:
                futurePosBomb=Direction.NORTH.getDirection();
                break;
            case KeyEvent.VK_S:
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
                futureDirection=Direction.IDLE.getDirection();
        }
    }
}
