package Bomberman;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0,-1), SOUTH(0,1), EAST(1,0), WEST(-1, 0), IDLE(0,0);
    //contains all of the possible directions
    public static final List<Direction> directionList= Arrays.asList(NORTH, EAST, SOUTH, WEST);

    protected Point direction;
    Direction(int x, int y) {
        this.direction = new Point(x, y);
    }
    public Point getDirection() {
        return this.direction;
    }
}
