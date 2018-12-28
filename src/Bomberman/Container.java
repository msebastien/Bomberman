package Bomberman;

import java.util.List;
import java.util.Map;
import java.awt.image.BufferedImage;

public class Container {
    static Map<Animation, List<BufferedImage>> entityAnimations;
    private List<BufferedImage> tileImages;
    private int index;




    public Container() {
        this.tileImages = tileImages;
        this.index = 0;
    }

    // Tiles
    public List<BufferedImage> getTileImages() {
        return tileImages;
    }

    public void setTileImages(List<BufferedImage> tileImages) {
        this.tileImages = tileImages;
    }


    // Animations
    public BufferedImage getNextImage(Animation animationType)
    {
        index++;
        return entityAnimations.get(animationType).get(index);
    }

    // Set 1 animation
    public void setAnimation(Animation animationType, List<BufferedImage> animation){
        for(int i=0; i<animation.size(); i++){
            entityAnimations.get(animationType).add(animation.get(i));
        }
    }

    /*public void setAnimations(List<Animation> animationTypes, List<BufferedImage> animation){
        for(int i=0; i<animation.size(); i++){
            entityAnimations.get(animationTypes.get(i)).set(i, animation.get(i));
        }
    }*/





}
