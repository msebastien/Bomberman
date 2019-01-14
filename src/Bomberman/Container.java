package Bomberman;

import Bomberman.EntityManager.Entity;
import Bomberman.EntityManager.EntityType;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;

public class Container {
    static Map<EntityType,Map<Animation, BufferedImage[]>> entityAnimations =new HashMap<>();
    static Map<Animation,BufferedImage[]> backgroundAnimation=new HashMap<>();

    static {
        try {

            backgroundAnimation.put(Animation.OBSTACLE,new BufferedImage[]{
                    ImageIO.read( new File("resources/background/obstacle.png") )
            });
            backgroundAnimation.put(Animation.GRASS,new BufferedImage[]{
                    ImageIO.read( new File("resources/background/grass.png") )
            });

            entityAnimations.put(EntityType.PLAYER,new HashMap<>());
            entityAnimations.get(EntityType.PLAYER).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/player/move_right/player0.png") ),
                    ImageIO.read( new File("resources/player/move_right/player1.png") ),
                    ImageIO.read( new File("resources/player/move_right/player2.png") ),
                    ImageIO.read( new File("resources/player/move_right/player3.png") )});
            entityAnimations.get(EntityType.PLAYER).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/player/move_left/player0.png") ),
                    ImageIO.read( new File("resources/player/move_left/player1.png") ),
                    ImageIO.read( new File("resources/player/move_left/player2.png") ),
                    ImageIO.read( new File("resources/player/move_left/player3.png") )});
            entityAnimations.get(EntityType.PLAYER).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/player/move_forward/player0.png") ),
                    ImageIO.read( new File("resources/player/move_forward/player1.png") ),
                    ImageIO.read( new File("resources/player/move_forward/player2.png") ),
                    ImageIO.read( new File("resources/player/move_forward/player3.png") )});
            entityAnimations.get(EntityType.PLAYER).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/player/move_backward/player1.png") ),
                    ImageIO.read( new File("resources/player/move_backward/player1.png") ),
                    ImageIO.read( new File("resources/player/move_backward/player2.png") ),
                    ImageIO.read( new File("resources/player/move_backward/player3.png") )});
            entityAnimations.get(EntityType.PLAYER).put(Animation.IDLE, new BufferedImage[]{
                    ImageIO.read( new File("resources/player/player_Idle.png") )});

            entityAnimations.put(EntityType.ENEMY,new HashMap<>());
            entityAnimations.get(EntityType.ENEMY).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/enemy/move_right/enemy0.png") ),
                    ImageIO.read( new File("resources/enemy/move_right/enemy1.png") ),
                    ImageIO.read( new File("resources/enemy/move_right/enemy2.png") ),
                    ImageIO.read( new File("resources/enemy/move_right/enemy3.png") )});
            entityAnimations.get(EntityType.ENEMY).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/enemy/move_left/enemy0.png") ),
                    ImageIO.read( new File("resources/enemy/move_left/enemy1.png") ),
                    ImageIO.read( new File("resources/enemy/move_left/enemy2.png") ),
                    ImageIO.read( new File("resources/enemy/move_left/enemy3.png") )});
            entityAnimations.get(EntityType.ENEMY).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/enemy/move_forward/enemy0.png") ),
                    ImageIO.read( new File("resources/enemy/move_forward/enemy1.png") ),
                    ImageIO.read( new File("resources/enemy/move_forward/enemy2.png") ),
                    ImageIO.read( new File("resources/enemy/move_forward/enemy3.png") )});
            entityAnimations.get(EntityType.ENEMY).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/enemy/move_backward/enemy0.png") ),
                    ImageIO.read( new File("resources/enemy/move_backward/enemy1.png") ),
                    ImageIO.read( new File("resources/enemy/move_backward/enemy2.png") ),
                    ImageIO.read( new File("resources/enemy/move_backward/enemy3.png") )});
            entityAnimations.get(EntityType.ENEMY).put(Animation.IDLE, new BufferedImage[]{
                    ImageIO.read( new File("resources/enemy/enemy_Idle.png") )});

            entityAnimations.put(EntityType.KNIGHT,new HashMap<>());
            entityAnimations.get(EntityType.KNIGHT).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/knight/east/knight0.png") ),
                    ImageIO.read( new File("resources/knight/east/knight1.png") ),
                    ImageIO.read( new File("resources/knight/east/knight2.png") ),
                    ImageIO.read( new File("resources/knight/east/knight3.png") )});
            entityAnimations.get(EntityType.KNIGHT).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/knight/west/knight0.png") ),
                    ImageIO.read( new File("resources/knight/west/knight1.png") ),
                    ImageIO.read( new File("resources/knight/west/knight2.png") ),
                    ImageIO.read( new File("resources/knight/west/knight3.png") )});
            entityAnimations.get(EntityType.KNIGHT).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/knight/north/knight0.png") ),
                    ImageIO.read( new File("resources/knight/north/knight1.png") ),
                    ImageIO.read( new File("resources/knight/north/knight2.png") ),
                    ImageIO.read( new File("resources/knight/north/knight3.png") )});
            entityAnimations.get(EntityType.KNIGHT).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/knight/south/knight0.png") ),
                    ImageIO.read( new File("resources/knight/south/knight1.png") ),
                    ImageIO.read( new File("resources/knight/south/knight2.png") ),
                    ImageIO.read( new File("resources/knight/south/knight3.png") )});
            entityAnimations.get(EntityType.KNIGHT).put(Animation.IDLE, new BufferedImage[]{
                    ImageIO.read( new File("resources/knight/knight_Ilde.png") )});



            entityAnimations.put(EntityType.EXIT,new HashMap<>());
            entityAnimations.get(EntityType.EXIT).put(Animation.EXIT,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/exit/exit0.png") ),
                    ImageIO.read( new File("resources/object/exit/exit1.png") ),
                    ImageIO.read( new File("resources/object/exit/exit2.png") ),
                    ImageIO.read( new File("resources/object/exit/exit3.png") ),
                    ImageIO.read( new File("resources/object/exit/exit4.png") ),
                    ImageIO.read( new File("resources/object/exit/exit5.png") )
            });

            entityAnimations.put(EntityType.HURT,new HashMap<>());
            entityAnimations.get(EntityType.HURT).put(Animation.HURT,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/hurt/hurt0.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt1.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt2.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt3.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt4.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt5.png") )
            });

            entityAnimations.put(EntityType.EXPLOSION,new HashMap<>());
            entityAnimations.get(EntityType.EXPLOSION).put(Animation.EXPLOSION,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/explosion/flam0.png") ),
                    ImageIO.read( new File("resources/object/explosion/flam1.png") ),
                    ImageIO.read( new File("resources/object/explosion/flam2.png") ),
                    ImageIO.read( new File("resources/object/explosion/flam3.png") ),
                    ImageIO.read( new File("resources/object/explosion/flam4.png") ),
                    ImageIO.read( new File("resources/object/explosion/flam5.png") )
            });

            entityAnimations.put(EntityType.ITEM_DROPPED,new HashMap<>());
            entityAnimations.get(EntityType.ITEM_DROPPED).put(Animation.ITEM_BOMB,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb0.png") ),
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb1.png") ),
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb2.png") ),
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb3.png") ),
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb4.png") ),
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb5.png") ),
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb6.png") ),
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb7.png") ),
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb8.png") ),
                    ImageIO.read( new File("resources/object/itemBomb/itemBomb9.png") )
            });

            entityAnimations.put(EntityType.BOMB,new HashMap<>());
            entityAnimations.get(EntityType.BOMB).put(Animation.BOMB,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/bomb/bomb0.png") ),
                    ImageIO.read( new File("resources/object/bomb/bomb1.png") ),
                    ImageIO.read( new File("resources/object/bomb/bomb2.png") ),
                    ImageIO.read( new File("resources/object/bomb/bomb3.png") ),
                    ImageIO.read( new File("resources/object/bomb/bomb4.png") ),
                    ImageIO.read( new File("resources/object/bomb/bomb5.png") )
            });




        }catch(IOException e){
            System.out.println(e.toString());
        }
    }

    private int timeToNextImage; // Number of ms to go the next image
    private long lastAnimationTime;
    private BufferedImage[] animation;
    private int index;
    private boolean continueAnimation;


    public Container(){
        index = 0;
        lastAnimationTime = 0;
        continueAnimation=true;

        //set the time of all the animation
        //thanks to that we can set the time before next image
    }

    public void init(int animationDuration,Animation animationName,EntityType entityType)
    {
        continueAnimation=true;
        setAnimation(animationName,entityType);
        setDuration(animationDuration);
    }

    // Set the duration for displaying one image of the animation
    private void setDuration(int animationDuration){
        if(animation.length > 0)
        {
            timeToNextImage = (int)Math.rint((double)animationDuration / (double)(animation.length));
        }else
            timeToNextImage=0;

    }

    // Select the animation to apply to the entity's container
    private void setAnimation(Animation animType, EntityType entityType){
        index=0;
        animation = entityAnimations.get(entityType).get(animType);
    }

    // Get the next image of the animation
    public void nextImage()
    {
        //each turn we call this function
        lastAnimationTime+=Game.THREAD_SLEEP;

        if(lastAnimationTime>=timeToNextImage) {
            indexUp();
            lastAnimationTime = 0;
        }
    }

    private void indexUp()
    {
        if(animation!=null&& index<animation.length-1 && continueAnimation)
        {
            index++;
        }
        else
        {
            index=0;
        }
    }

    public BufferedImage getCurrentImage()
    {
        if(animation!=null)
            return animation[index];
        else return null;
    }

    public void play()
    {
        continueAnimation=true;
    }

    public void stopAnimation()
    {
        continueAnimation=false;
    }

    public int getIndex() {
        return index;
    }

    public boolean isContinueAnimation() {
        return continueAnimation;
    }
}
