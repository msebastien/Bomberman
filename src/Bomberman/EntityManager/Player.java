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

    public Player(int moveDuration){
        super(moveDuration);
        bombReserve=15;
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
        if(Main.game!=null )
        {
            Tile tile=Main.game.getMap().getTile(oldPosInArrayMap);
            if(!tile.hasEntity())
            {
                Bomb bomb=new Bomb(oldPosInArrayMap,4000, Randomator.getRandomElementIn(Bomb.DifferentTypeExplosion));
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
            case KeyEvent.VK_SPACE:
                isThrowingBomb.set(true);
                //placeBomb();
                break;
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
