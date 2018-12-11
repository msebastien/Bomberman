package Bomberman;

import java.awt.Point;
import java.util.List;

public class Map {
    static final int MAP_SIZE_X = 800; // Modify values if needed
    static final int MAP_SIZE_Y = 600;

    static int HEIGHT_TILE;
    static int WIDTH_TILE;

    enum Direction {
        NORTH(0,1), SOUTH(0,-1), EAST(1,0), WEST(-1, 0);

        protected Point direction;
        Direction(int x, int y) {
            this.direction = new Point(x, y);
        }
        public Point getDirection() {
            return this.direction;
        }
    }

    private Tile[][] map;
    private List<Entity> entitiesList;

    public Map(Tile[][] map)
    {
        this.map = map;
    }

    public void init()
    {

    }

    public Boolean isMapGenerated()
    {

    }

}
