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

    static {
        try {

            entityAnimations.put(EntityType.ICE,new HashMap<>());
            entityAnimations.get(EntityType.ICE).put(Animation.GRASS,new BufferedImage[]
            {
                ImageIO.read( new File("resources/background/ice/grass.png") )
            });
            entityAnimations.get(EntityType.ICE).put(Animation.OBSTACLE,new BufferedImage[]
            {
                    ImageIO.read( new File("resources/background/ice/obstacle.png") ),
                    ImageIO.read( new File("resources/background/ice/obstacle0.png") ),
                    ImageIO.read( new File("resources/background/ice/obstacle1.png") ),
                    ImageIO.read( new File("resources/background/ice/obstacle2.png") )
            });
            entityAnimations.get(EntityType.ICE).put(Animation.BACKGROUND,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/hud/ice.png") )
                    });

            entityAnimations.put(EntityType.VOLCAN,new HashMap<>());
            entityAnimations.get(EntityType.VOLCAN).put(Animation.GRASS,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/background/volcan/grass.png") )
                    });
            entityAnimations.get(EntityType.VOLCAN).put(Animation.OBSTACLE,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/background/volcan/obstacle.png") ),
                            ImageIO.read( new File("resources/background/volcan/obstacle0.png") ),
                            ImageIO.read( new File("resources/background/volcan/obstacle1.png") )
                    });
            entityAnimations.get(EntityType.VOLCAN).put(Animation.BACKGROUND,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/hud/volcan.png") )
                    });

            entityAnimations.put(EntityType.DUNGEON,new HashMap<>());
            entityAnimations.get(EntityType.DUNGEON).put(Animation.GRASS,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/background/dungeon/grass.png") )
                    });
            entityAnimations.get(EntityType.DUNGEON).put(Animation.OBSTACLE,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/background/dungeon/obstacle.png") ),
                            ImageIO.read( new File("resources/background/dungeon/obstacle0.png") ),
                            ImageIO.read( new File("resources/background/dungeon/obstacle1.png") )
                    });
            entityAnimations.get(EntityType.DUNGEON).put(Animation.BACKGROUND,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/hud/dungeon.png") )
                    });

            entityAnimations.put(EntityType.DESERT,new HashMap<>());
            entityAnimations.get(EntityType.DESERT).put(Animation.GRASS,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/background/desert/grass.png") )
                    });
            entityAnimations.get(EntityType.DESERT).put(Animation.OBSTACLE,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/background/desert/obstacle.png") ),
                            ImageIO.read( new File("resources/background/desert/obstacle0.png") ),
                            ImageIO.read( new File("resources/background/desert/obstacle1.png") )
                    });
            entityAnimations.get(EntityType.DESERT).put(Animation.BACKGROUND,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/hud/desert.png") )
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

            entityAnimations.put(EntityType.EVIL,new HashMap<>());
            entityAnimations.get(EntityType.EVIL).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/evil/east/evil0.png") ),
                    ImageIO.read( new File("resources/evil/east/evil1.png") ),
                    ImageIO.read( new File("resources/evil/east/evil2.png") ),
                    ImageIO.read( new File("resources/evil/east/evil3.png") )});
            entityAnimations.get(EntityType.EVIL).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/evil/west/evil0.png") ),
                    ImageIO.read( new File("resources/evil/west/evil1.png") ),
                    ImageIO.read( new File("resources/evil/west/evil2.png") ),
                    ImageIO.read( new File("resources/evil/west/evil3.png") )});
            entityAnimations.get(EntityType.EVIL).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/evil/north/evil0.png") ),
                    ImageIO.read( new File("resources/evil/north/evil1.png") ),
                    ImageIO.read( new File("resources/evil/north/evil2.png") ),
                    ImageIO.read( new File("resources/evil/north/evil3.png") )});
            entityAnimations.get(EntityType.EVIL).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/evil/south/evil0.png") ),
                    ImageIO.read( new File("resources/evil/south/evil1.png") ),
                    ImageIO.read( new File("resources/evil/south/evil2.png") ),
                    ImageIO.read( new File("resources/evil/south/evil3.png") )});
            entityAnimations.get(EntityType.EVIL).put(Animation.IDLE, new BufferedImage[]{
                    ImageIO.read( new File("resources/evil/evil_Idle.png") )});

            entityAnimations.put(EntityType.GROUDON,new HashMap<>());
            entityAnimations.get(EntityType.GROUDON).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/groudon/east/groudon0.png") ),
                    ImageIO.read( new File("resources/boss/groudon/east/groudon1.png") ),
                    ImageIO.read( new File("resources/boss/groudon/east/groudon2.png") ),
                    ImageIO.read( new File("resources/boss/groudon/east/groudon3.png") )});
            entityAnimations.get(EntityType.GROUDON).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/groudon/west/groudon0.png") ),
                    ImageIO.read( new File("resources/boss/groudon/west/groudon1.png") ),
                    ImageIO.read( new File("resources/boss/groudon/west/groudon2.png") ),
                    ImageIO.read( new File("resources/boss/groudon/west/groudon3.png") )});
            entityAnimations.get(EntityType.GROUDON).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/groudon/north/groudon0.png") ),
                    ImageIO.read( new File("resources/boss/groudon/north/groudon1.png") ),
                    ImageIO.read( new File("resources/boss/groudon/north/groudon2.png") ),
                    ImageIO.read( new File("resources/boss/groudon/north/groudon3.png") )});
            entityAnimations.get(EntityType.GROUDON).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/groudon/south/groudon0.png") ),
                    ImageIO.read( new File("resources/boss/groudon/south/groudon1.png") ),
                    ImageIO.read( new File("resources/boss/groudon/south/groudon2.png") ),
                    ImageIO.read( new File("resources/boss/groudon/south/groudon3.png") )});
            entityAnimations.get(EntityType.GROUDON).put(Animation.IDLE, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/groudon/groudon_Idle.png") )});

            entityAnimations.put(EntityType.LEVIATHAN,new HashMap<>());
            entityAnimations.get(EntityType.LEVIATHAN).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/leviathan/east/leviathan0.png") ),
                    ImageIO.read( new File("resources/leviathan/east/leviathan1.png") ),
                    ImageIO.read( new File("resources/leviathan/east/leviathan2.png") ),
                    ImageIO.read( new File("resources/leviathan/east/leviathan3.png") )});
            entityAnimations.get(EntityType.LEVIATHAN).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/leviathan/west/leviathan0.png") ),
                    ImageIO.read( new File("resources/leviathan/west/leviathan1.png") ),
                    ImageIO.read( new File("resources/leviathan/west/leviathan2.png") ),
                    ImageIO.read( new File("resources/leviathan/west/leviathan3.png") )});
            entityAnimations.get(EntityType.LEVIATHAN).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/leviathan/north/leviathan0.png") ),
                    ImageIO.read( new File("resources/leviathan/north/leviathan1.png") ),
                    ImageIO.read( new File("resources/leviathan/north/leviathan2.png") ),
                    ImageIO.read( new File("resources/leviathan/north/leviathan3.png") )});
            entityAnimations.get(EntityType.LEVIATHAN).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/leviathan/south/leviathan0.png") ),
                    ImageIO.read( new File("resources/leviathan/south/leviathan1.png") ),
                    ImageIO.read( new File("resources/leviathan/south/leviathan2.png") ),
                    ImageIO.read( new File("resources/leviathan/south/leviathan3.png") )});
            entityAnimations.get(EntityType.LEVIATHAN).put(Animation.IDLE, new BufferedImage[]{
                    ImageIO.read( new File("resources/leviathan/leviathan_Idle.png") )});


            entityAnimations.put(EntityType.DARK_VADOR,new HashMap<>());
            entityAnimations.get(EntityType.DARK_VADOR).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/darkVador/east/darkVador0.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/east/darkVador1.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/east/darkVador2.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/east/darkVador3.png") )});
            entityAnimations.get(EntityType.DARK_VADOR).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/darkVador/west/darkVador0.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/west/darkVador1.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/west/darkVador2.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/west/darkVador3.png") )});
            entityAnimations.get(EntityType.DARK_VADOR).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/darkVador/north/darkVador0.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/north/darkVador1.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/north/darkVador2.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/north/darkVador3.png") )});
            entityAnimations.get(EntityType.DARK_VADOR).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/darkVador/south/darkVador0.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/south/darkVador1.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/south/darkVador2.png") ),
                    ImageIO.read( new File("resources/boss/darkVador/south/darkVador3.png") )});
            entityAnimations.get(EntityType.DARK_VADOR).put(Animation.IDLE, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/darkVador/darkVador_Idle.png") )});

            entityAnimations.put(EntityType.HELL_DOG,new HashMap<>());
            entityAnimations.get(EntityType.HELL_DOG).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/hellDog/east/hellDog0.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/east/hellDog1.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/east/hellDog2.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/east/hellDog3.png") )});
            entityAnimations.get(EntityType.HELL_DOG).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/hellDog/west/hellDog0.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/west/hellDog1.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/west/hellDog2.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/west/hellDog3.png") )});
            entityAnimations.get(EntityType.HELL_DOG).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/hellDog/north/hellDog0.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/north/hellDog1.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/north/hellDog2.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/north/hellDog3.png") )});
            entityAnimations.get(EntityType.HELL_DOG).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/hellDog/south/hellDog0.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/south/hellDog1.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/south/hellDog2.png") ),
                    ImageIO.read( new File("resources/boss/hellDog/south/hellDog3.png") )});
            entityAnimations.get(EntityType.HELL_DOG).put(Animation.IDLE, new BufferedImage[]{
                    ImageIO.read( new File("resources/boss/hellDog/hellDog_Idle.png") )});

            entityAnimations.put(EntityType.SABRE_LASER,new HashMap<>());
            entityAnimations.get(EntityType.SABRE_LASER).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/projectile/sabreLaser/east/saberLaser0.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/east/saberLaser1.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/east/saberLaser2.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/east/saberLaser3.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/east/saberLaser4.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/east/saberLaser5.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/east/saberLaser6.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/east/saberLaser7.png") )});
            entityAnimations.get(EntityType.SABRE_LASER).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/projectile/sabreLaser/west/saberLaser0.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/west/saberLaser1.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/west/saberLaser2.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/west/saberLaser3.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/west/saberLaser4.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/west/saberLaser5.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/west/saberLaser6.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/west/saberLaser7.png") )});
            entityAnimations.get(EntityType.SABRE_LASER).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/projectile/sabreLaser/north/saberLaser0.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/north/saberLaser1.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/north/saberLaser2.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/north/saberLaser3.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/north/saberLaser4.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/north/saberLaser5.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/north/saberLaser6.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/north/saberLaser7.png") )});
            entityAnimations.get(EntityType.SABRE_LASER).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/projectile/sabreLaser/south/saberLaser0.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/south/saberLaser1.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/south/saberLaser2.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/south/saberLaser3.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/south/saberLaser4.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/south/saberLaser5.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/south/saberLaser6.png") ),
                    ImageIO.read( new File("resources/projectile/sabreLaser/south/saberLaser7.png") )});



            entityAnimations.put(EntityType.FIRE_BALL,new HashMap<>());
            entityAnimations.get(EntityType.FIRE_BALL).put(Animation.MOVE_EAST, new BufferedImage[]{
                    ImageIO.read( new File("resources/projectile/fireBall/east/fireBall0.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/east/fireBall1.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/east/fireBall2.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/east/fireBall3.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/east/fireBall4.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/east/fireBall5.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/east/fireBall6.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/east/fireBall7.png") )});
            entityAnimations.get(EntityType.FIRE_BALL).put(Animation.MOVE_WEST, new BufferedImage[]{
                    ImageIO.read( new File("resources/projectile/fireBall/west/fireBall0.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/west/fireBall1.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/west/fireBall2.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/west/fireBall3.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/west/fireBall4.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/west/fireBall5.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/west/fireBall6.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/west/fireBall7.png") )});
            entityAnimations.get(EntityType.FIRE_BALL).put(Animation.MOVE_NORTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/projectile/fireBall/north/fireBall0.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/north/fireBall1.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/north/fireBall2.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/north/fireBall3.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/north/fireBall4.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/north/fireBall5.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/north/fireBall6.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/north/fireBall7.png") )});
            entityAnimations.get(EntityType.FIRE_BALL).put(Animation.MOVE_SOUTH, new BufferedImage[]{
                    ImageIO.read( new File("resources/projectile/fireBall/south/fireBall0.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/south/fireBall1.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/south/fireBall2.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/south/fireBall3.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/south/fireBall4.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/south/fireBall5.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/south/fireBall6.png") ),
                    ImageIO.read( new File("resources/projectile/fireBall/south/fireBall7.png") )});




            entityAnimations.put(EntityType.EXIT,new HashMap<>());
            entityAnimations.get(EntityType.EXIT).put(Animation.EXIT,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/exit/exit0.png") ),
                    ImageIO.read( new File("resources/object/exit/exit1.png") ),
                    ImageIO.read( new File("resources/object/exit/exit2.png") ),
                    ImageIO.read( new File("resources/object/exit/exit3.png") ),
                    ImageIO.read( new File("resources/object/exit/exit4.png") ),
                    ImageIO.read( new File("resources/object/exit/exit5.png") )
            });

            entityAnimations.put(EntityType.DIE,new HashMap<>());
            entityAnimations.get(EntityType.DIE).put(Animation.DIE,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/die/die0.png") ),
                    ImageIO.read( new File("resources/object/die/die1.png") ),
                    ImageIO.read( new File("resources/object/die/die2.png") ),
                    ImageIO.read( new File("resources/object/die/die3.png") ),
                    ImageIO.read( new File("resources/object/die/die4.png") ),
                    ImageIO.read( new File("resources/object/die/die5.png") ),
                    ImageIO.read( new File("resources/object/die/die6.png") ),
                    ImageIO.read( new File("resources/object/die/die7.png") ),
                    ImageIO.read( new File("resources/object/die/die8.png") ),
                    ImageIO.read( new File("resources/object/die/die9.png") ),
                    ImageIO.read( new File("resources/object/die/die10.png") )
            });

            entityAnimations.put(EntityType.HURT,new HashMap<>());
            entityAnimations.get(EntityType.HURT).put(Animation.HURT,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/hurt/hurt0.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt1.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt2.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt3.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt4.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt5.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt6.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt7.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt8.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt9.png") ),
                    ImageIO.read( new File("resources/object/hurt/hurt10.png") )
            });

            entityAnimations.put(EntityType.CHEST,new HashMap<>());
            entityAnimations.get(EntityType.CHEST).put(Animation.CHEST_OPEN,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/chest/chest0.png") ),
                    ImageIO.read( new File("resources/object/chest/chest1.png") ),
                    ImageIO.read( new File("resources/object/chest/chest2.png") ),
                    ImageIO.read( new File("resources/object/chest/chest3.png") )
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
            entityAnimations.get(EntityType.EXPLOSION).put(Animation.IMPACT,new BufferedImage[]{
                    ImageIO.read( new File("resources/projectile/destruction/destruction0.png") ),
                    ImageIO.read( new File("resources/projectile/destruction/destruction1.png") ),
                    ImageIO.read( new File("resources/projectile/destruction/destruction2.png") ),
                    ImageIO.read( new File("resources/projectile/destruction/destruction3.png") ),
                    ImageIO.read( new File("resources/projectile/destruction/destruction4.png") ),
                    ImageIO.read( new File("resources/projectile/destruction/destruction5.png") )
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

            entityAnimations.get(EntityType.ITEM_DROPPED).put(Animation.ITEM_SPEED,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/itemSpeed/itemSpeed0.png") ),
                    ImageIO.read( new File("resources/object/itemSpeed/itemSpeed1.png") ),
                    ImageIO.read( new File("resources/object/itemSpeed/itemSpeed2.png") ),
                    ImageIO.read( new File("resources/object/itemSpeed/itemSpeed3.png") ),
                    ImageIO.read( new File("resources/object/itemSpeed/itemSpeed4.png") ),
                    ImageIO.read( new File("resources/object/itemSpeed/itemSpeed5.png") ),
                    ImageIO.read( new File("resources/object/itemSpeed/itemSpeed6.png") ),
                    ImageIO.read( new File("resources/object/itemSpeed/itemSpeed7.png") )
            });

            entityAnimations.get(EntityType.ITEM_DROPPED).put(Animation.ITEM_KEY,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/itemKey/itemKey0.png") ),
                    ImageIO.read( new File("resources/object/itemKey/itemKey1.png") ),
                    ImageIO.read( new File("resources/object/itemKey/itemKey2.png") ),
                    ImageIO.read( new File("resources/object/itemKey/itemKey3.png") ),
                    ImageIO.read( new File("resources/object/itemKey/itemKey4.png") ),
                    ImageIO.read( new File("resources/object/itemKey/itemKey5.png") ),
                    ImageIO.read( new File("resources/object/itemKey/itemKey6.png") ),
                    ImageIO.read( new File("resources/object/itemKey/itemKey7.png") )
            });

            entityAnimations.get(EntityType.ITEM_DROPPED).put(Animation.ITEM_PV,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/itemPv/itemPv0.png") ),
                    ImageIO.read( new File("resources/object/itemPv/itemPv1.png") ),
                    ImageIO.read( new File("resources/object/itemPv/itemPv2.png") ),
                    ImageIO.read( new File("resources/object/itemPv/itemPv3.png") ),
                    ImageIO.read( new File("resources/object/itemPv/itemPv4.png") ),
                    ImageIO.read( new File("resources/object/itemPv/itemPv5.png") ),
                    ImageIO.read( new File("resources/object/itemPv/itemPv6.png") ),
                    ImageIO.read( new File("resources/object/itemPv/itemPv7.png") )
            });

            entityAnimations.get(EntityType.ITEM_DROPPED).put(Animation.ITEM_RANGE,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/itemRange/itemRange0.png") ),
                    ImageIO.read( new File("resources/object/itemRange/itemRange1.png") ),
                    ImageIO.read( new File("resources/object/itemRange/itemRange2.png") ),
                    ImageIO.read( new File("resources/object/itemRange/itemRange3.png") ),
                    ImageIO.read( new File("resources/object/itemRange/itemRange4.png") ),
                    ImageIO.read( new File("resources/object/itemRange/itemRange5.png") ),
                    ImageIO.read( new File("resources/object/itemRange/itemRange6.png") ),
                    ImageIO.read( new File("resources/object/itemRange/itemRange7.png") ),
                    ImageIO.read( new File("resources/object/itemRange/itemRange8.png") ),
                    ImageIO.read( new File("resources/object/itemRange/itemRange9.png") )
            });
            entityAnimations.get(EntityType.ITEM_DROPPED).put(Animation.ITEM_DAMAGE,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage0.png") ),
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage1.png") ),
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage2.png") ),
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage3.png") ),
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage4.png") ),
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage5.png") ),
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage6.png") ),
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage7.png") ),
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage8.png") ),
                    ImageIO.read( new File("resources/object/itemDamage/itemDamage9.png") )
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

            entityAnimations.put(EntityType.APPARITION,new HashMap<>());
            entityAnimations.get(EntityType.APPARITION).put(Animation.APPARITION,new BufferedImage[]{
                    ImageIO.read( new File("resources/object/apparition/apparition0.png") ),
                    ImageIO.read( new File("resources/object/apparition/apparition1.png") ),
                    ImageIO.read( new File("resources/object/apparition/apparition2.png") ),
                    ImageIO.read( new File("resources/object/apparition/apparition3.png") ),
                    ImageIO.read( new File("resources/object/apparition/apparition4.png") ),
                    ImageIO.read( new File("resources/object/apparition/apparition5.png") ),
                    ImageIO.read( new File("resources/object/apparition/apparition6.png") )
            });


            entityAnimations.put(EntityType.ACCUEIL,new HashMap<>());
            entityAnimations.get(EntityType.ACCUEIL).put(Animation.BACKGROUND,new BufferedImage[]
                    {
                            ImageIO.read( new File("resources/background/accueil/back1.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back2.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back3.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back4.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back5.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back6.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back7.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back8.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back9.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back10.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back11.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back12.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back13.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back14.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back15.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back16.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back17.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back18.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back19.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back20.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back21.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back22.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back23.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back24.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back25.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back26.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back27.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back28.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back29.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back30.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back31.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back32.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back33.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back34.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back35.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back36.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back37.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back38.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back39.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back40.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back41.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back42.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back43.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back44.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back45.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back46.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back47.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back48.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back49.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back50.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back51.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back52.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back53.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back54.jpg") ),
                            ImageIO.read( new File("resources/background/accueil/back55.jpg") ),
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
        if(index<animation.length-1 && continueAnimation)
        {
            index++;
        }
        else
        {
            index=0;
        }
    }

    public boolean isEmpty()
    {
        return animation==null;
    }

    public BufferedImage getCurrentImage()
    {
        return animation[index];
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
