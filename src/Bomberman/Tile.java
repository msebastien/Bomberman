package Bomberman;

import Bomberman.EntityManager.Entity;

public class Tile {
    private Entity entity;
    private TileType type;



    public Tile() // Constructor
    {

    }

    public Entity getEntity()
    {
        return entity;
    }

    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }

    public TileType getTileType()
    {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public Boolean isFree(){
        return type==TileType.GRASS && entity==null;
    }
}
