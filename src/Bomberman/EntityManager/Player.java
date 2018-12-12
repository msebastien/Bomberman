package Bomberman.EntityManager;

import Bomberman.Direction;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends MovingEntity implements KeyListener
{


    public Player(Point posInArrayMap, int moveDurationMs) {
        super(posInArrayMap, moveDurationMs);
    }

    //TODO
    @Override
    public boolean collisionWith(Point caseCollision) {
        return true;
    }

    @Override
    protected void changeDirection() {
        directionMovement= Direction.IDLE.getDirection();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    //TODO
    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
