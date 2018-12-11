package Bomberman;

public class Tile {
    private Entity entity;
    private tileType type;
    private Boolean free;

    public enum tileType{
        OBSTACLE, GRASS;
    }

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

    public tileType getTileType()
    {
        return type;
    }

    public void setType(tileType type) {
        this.type = type;
    }

    public Boolean isFree(){
        return free;
    }
}
