package Bomberman;

import java.awt.image.BufferedImage;

public enum TileType {
    OBSTACLE(Animation.OBSTACLE),
    GRASS(Animation.GRASS);

    private BufferedImage image;

    TileType(Animation animationName) {
        image=Container.backgroundAnimation.get(animationName)[0];
    }

    public BufferedImage getImage() {
        return image;
    }
}
