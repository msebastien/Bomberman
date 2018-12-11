package Bomberman.EntityManager;

import Bomberman.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends MovingEntity implements KeyListener
{

    @Override
    public boolean collisionWith(Entity entity) {
        return true;
    }

    @Override
    protected void changeDirection() {
        directionMovement= Direction.IDLE.getDirection();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
