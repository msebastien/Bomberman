package Bomberman;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.awt.image.BufferedImage;

import static java.time.Instant.now;
import static java.util.Map.entry;

public class Container {
    static Map<Animation, BufferedImage[]> globalEntityAnimations;

    static {
        try {
            globalEntityAnimations = (Map<Animation, BufferedImage[]>) Map.ofEntries(
                    entry(Animation.PLAYER_IDLE, new BufferedImage[]{
                            ImageIO.read( new File("resources/player/Player_Idle_000.png") )}),
                    entry(Animation.PLAYER_MOVE_LEFT, new BufferedImage[]{
                            ImageIO.read( new File("resources/player/move_left/Player_MoveLeft_000.png") ),
                            ImageIO.read( new File("resources/player/move_left/Player_MoveLeft_001.png") ),
                            ImageIO.read( new File("resources/player/move_left/Player_MoveLeft_002.png") ),
                            ImageIO.read( new File("resources/player/move_left/Player_MoveLeft_003.png") ),
                            ImageIO.read( new File("resources/player/move_left/Player_MoveLeft_004.png") ),
                            ImageIO.read( new File("resources/player/move_left/Player_MoveLeft_005.png") ),
                            ImageIO.read( new File("resources/player/move_left/Player_MoveLeft_006.png") ),
                            ImageIO.read( new File("resources/player/move_left/Player_MoveLeft_007.png") )}),
                    entry(Animation.PLAYER_MOVE_RIGHT, new BufferedImage[]{
                            ImageIO.read( new File("resources/player/move_right/Player_MoveRight_000.png") ),
                            ImageIO.read( new File("resources/player/move_right/Player_MoveRight_001.png") ),
                            ImageIO.read( new File("resources/player/move_right/Player_MoveRight_002.png") ),
                            ImageIO.read( new File("resources/player/move_right/Player_MoveRight_003.png") ),
                            ImageIO.read( new File("resources/player/move_right/Player_MoveRight_004.png") ),
                            ImageIO.read( new File("resources/player/move_right/Player_MoveRight_005.png") ),
                            ImageIO.read( new File("resources/player/move_right/Player_MoveRight_006.png") ),
                            ImageIO.read( new File("resources/player/move_right/Player_MoveRight_007.png") )})
            );
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }

    private int timeToNextImage; // Number of ms to go the next image
    private long lastAnimationTime;
    private BufferedImage animation[];
    boolean continueAnim;
    private int index;


    public Container(){
        index = 0;
        lastAnimationTime = 0;
        continueAnim = false;
    }

    // Set the duration for displaying one image of the animation
    public void setDuration(int moveDurationMs){
        timeToNextImage = (int)Math.rint((double)moveDurationMs / (double)(animation.length*10));
    }

    public int getDuration(){
        return timeToNextImage;
    }

    // Select the animation to apply to the entity's container
    public void setAnimation(Animation animType){
        animation = globalEntityAnimations.get(animType);

    }

    // Get the next image of the animation
    public BufferedImage getNextImage()
    {
        long now = now().toEpochMilli();

        if(index >= animation.length-1){ // Checks if we reach the end of the array/animation
            index = 0;
            continueAnim = false;
        }
        else if(now-lastAnimationTime >=  (long)timeToNextImage && continueAnim){ // Increment index every specific period of time
            index++;
            lastAnimationTime = now().toEpochMilli();
        }else if(index == 0){ // Checks if we are at the beginning of the animation
            continueAnim = true;
        }

        return animation[index];
    }


}