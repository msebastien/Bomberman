package Bomberman;

import Bomberman.EntityManager.Entity;
import Bomberman.EntityManager.EntityType;

public class Tile {
    private Entity entity;
    private TileType type;



    public Tile(Animation type, EntityType biome) // Constructor
    {
        this.type=new TileType(type,biome);
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

    public void setTileType(Animation typeBackground,EntityType biome) {
        this.type = new TileType(typeBackground,biome);
    }

    public Boolean isFree(){
        return type.getTypeBackground()==Animation.GRASS && entity==null;
    }

    public boolean isWay(){
        return type.getTypeBackground()==Animation.GRASS;
    }

    public boolean hasEntity(){
        return entity!=null;
    }
}
