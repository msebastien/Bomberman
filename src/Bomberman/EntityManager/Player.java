package Bomberman.EntityManager;

import Bomberman.Animation;
import Bomberman.Direction;
import Bomberman.Main;
import Bomberman.Randomator;
import Bomberman.Tile;
import Bomberman.Game;

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

        futurePosBomb=new Point();
        isThrowingBomb=new AtomicBoolean(false);

        // init container for animations
        container.setAnimation(Animation.PLAYER_IDLE);
        container.setDuration(moveDuration); // Set duration of the animation based on the movement of the entity
    }

    @Override
    public void init(Point posInArrayMap) {
        super.init(posInArrayMap);
        isThrowingBomb.set(false);
        bombReserve=Game.nbrBombInitReserve;
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

        //System.out.println(this+"\n\t>"+directionMovement.toString());
        if(isThrowingBomb.get()&&bombReserve>0)
        {
            isThrowingBomb.set(false);
            placeBomb();
            bombReserve--;
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
                futureDirection=Direction.WEST.getDirection();
                container.setAnimation(Animation.PLAYER_MOVE_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                futureDirection=Direction.EAST.getDirection();
                container.setAnimation(Animation.PLAYER_MOVE_RIGHT);
                break;
            case KeyEvent.VK_UP:
                futureDirection=Direction.NORTH.getDirection();
                break;
            case KeyEvent.VK_DOWN:
                futureDirection=Direction.SOUTH.getDirection();
                break;

                //Bomb management
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
                futureDirection=Direction.IDLE.getDirection();
                container.setAnimation(Animation.PLAYER_IDLE);
        }
    }
}
