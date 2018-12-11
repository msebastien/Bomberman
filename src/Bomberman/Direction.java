package Bomberman;

import java.awt.Point;

public enum Direction {
    NORTH(0,-1), SOUTH(0,1), EAST(1,0), WEST(-1, 0);

    protected Point direction;
    Direction(int x, int y) {
        this.direction = new Point(x, y);
    }
    public Point getDirection() {
        return this.direction;
    }
}
