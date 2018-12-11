package Bomberman;

import java.awt.*;

public class Map {
    static final int MAP_SIZE_X = 800; // Modify values if needed
    static final int MAP_SIZE_Y = 600;

    static int HEIGHT_TILE;
    static int WIDTH_TILE;
    enum DIRECTION {
        NORTH(1), SOUTH(-1), EAST(-2), WEST(2);
    }

    Tile[][] map;
    List entitiesList;

    public Map()
    {

    }

    public void init()
    {

    }

    public Boolean isMapGenerated()
    {

    }

}
