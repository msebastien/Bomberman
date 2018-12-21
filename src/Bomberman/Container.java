package Bomberman;

import Bomberman.EntityManager.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.awt.image.BufferedImage;
import static java.util.Map.entry;

public class Container {
    static Map<Animation, BufferedImage[]> globalEntityAnimations;

    static {
        try {
            globalEntityAnimations = (Map<Animation, BufferedImage[]>) Map.ofEntries(
                    entry(Animation.PLAYER_IDLE, new BufferedImage[]{
                            ImageIO.read( new File("resources/player/Player_Idle_000.png") ) }),
                    entry(Animation.PLAYER_MOVE_LEFT, new BufferedImage[]{
                            ImageIO.read( new File("resources/player/move_left/Player_MoveLeft_000.png") ) })
            );
        }catch(IOException e){

        }
    }

    private BufferedImage animation[];
    private int index;


    public Container(Animation animType){
        setAnimation(animType);
    }

    // Select the animation to apply to the entity's container
    public void setAnimation(Animation animType){
        index = -1;
        animation = globalEntityAnimations.get(animType).clone();
    }

    // Get the next image of the animation
    public BufferedImage getNextImage()
    {
        if(index < animation.length-1) index++;
        return animation[index];
    }


}
