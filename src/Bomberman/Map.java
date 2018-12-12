package Bomberman;

import Bomberman.EntityManager.Entity;

import java.awt.Point;
import java.util.List;

public class Map {
    static final int MAP_SIZE_X = 800; // Modify values if needed
    static final int MAP_SIZE_Y = 800;

    public static int HEIGHT_TILE;
    public static int WIDTH_TILE;

    private Tile[][] map;
    private List<Entity> entitiesList;

    public void init()
    {

    }

    public boolean isMapGenerated()
    {
        return true;
    }

    public Tile getTile(Point point)
    {
        return map[(int)point.getX()][(int)point.getY()];
    }

    public boolean isInsideMap(Point point)
    {
        return point.getX()>=0&&point.getY()>0 && point.getX()<WIDTH_TILE && point.getY()<HEIGHT_TILE;
    }

    public List<Entity> getEntitiesList() {
        return entitiesList;
    }
}
