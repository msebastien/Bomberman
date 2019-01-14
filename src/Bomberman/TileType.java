package Bomberman;

import Bomberman.EntityManager.Entity;
import Bomberman.EntityManager.EntityType;

import java.awt.image.BufferedImage;

public class TileType {

    private Animation typeBackground;
    private BufferedImage image;


    public TileType(Animation typeBackground, EntityType entityType)
    {
        image=Randomator.getRandomElementIn(Container.entityAnimations.
                get(entityType).
                get(typeBackground));
        this.typeBackground=typeBackground;

    }


    public Animation getTypeBackground() {
        return typeBackground;
    }

    public BufferedImage getImage() {
        return image;
    }
}
