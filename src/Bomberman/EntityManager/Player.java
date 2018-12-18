package Bomberman.EntityManager;

import Bomberman.Direction;
import Bomberman.Game;
import Bomberman.Main;
import Bomberman.Map;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends MovingEntity implements KeyListener
{

    //contain the future direction (chose with a key pressed)
    private Point futureDirection;

    public Player(int moveDuration){
        super(moveDuration);
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

    //I think this method is too similar to the method in Enemy maybe we can do something to concatenate the both
    /*@Override
    public void action() {

        if(isMoveBegin())
        {
            //we search the next move
            directionMovement=futureDirection;

            posInPixelMap.setLocation(posInArrayMap.x* Map.WIDTH_TILE,posInArrayMap.y*Map.HEIGHT_TILE);

            //we have to check his future Tile
            Point futureTile=new Point(posInArrayMap);
            //we set his future tile
            futureTile.translate(directionMovement.x,directionMovement.y);

            //if it's not inside map OR it's not free
            if( !Main.game.getMap().isInsideMap(futureTile) || !Main.game.getMap().getTile(futureTile).isFree())
            {
                directionMovement= Direction.IDLE.getDirection();
            }
            else {
                Main.game.getMap().getTile(posInArrayMap).setEntity(null);
                Main.game.getMap().getTile(futureTile).setEntity(this);
                posInArrayMap.setLocation(futureTile);
            }
        }

        //he translates himself forward with his direction
        translatePixelEntity();
    }*/


    @Override
    public void actionOnCollision(Entity entity) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    //TODO
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
