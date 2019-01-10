package Bomberman.EntityManager;

import Bomberman.*;

import java.awt.*;
import java.util.List;

public class Bomb extends TemporaryEntity {



            /*Arrays.asList(new Point(0,0),new Point(1,0),new Point(2,0),new Point(3,0),new Point(4,0),new Point(-1,0),
            new Point(-2,0),new Point(-3,0),new Point(-4,0)),

            Arrays.asList(new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3),new Point(0,4),new Point(0,-1),
                    new Point(0,-2),new Point(0,-3),new Point(0,-4))
            );*/

    private List<Point> schemaExplosion;
    private int damage;

    public Bomb(Point posInArrayMap,int countDown,List<Point> schemaExplosion) {
        super(posInArrayMap,countDown,Animation.BOMB,EntityType.BOMB);
        damage=1;
        this.schemaExplosion=schemaExplosion;


    }

    @Override
    public void actionOnDisappearance()
    {
        // we search all the tile around the bomb

        Map map=Main.game.getMap();

        //we calculate the coordinate of all points with an explosion
        Point impactPoint=new Point();
        schemaExplosion.forEach(point->{
            impactPoint.setLocation(posInArrayMap);
            impactPoint.translate(point.x,point.y);

            if(map.isInsideMap(impactPoint))
            {
                TemporaryEntity explosion=new TemporaryEntity(new Point(impactPoint), Animation.COUNT_DOWN_EXPLOSION,
                        Animation.EXPLOSION,EntityType.EXPLOSION) {
                };
                map.getEntitiesList().add(explosion);

                Tile tile=map.getTile(impactPoint);
                if(tile.hasEntity())
                {

                    if(tile.getEntity() instanceof Enemy)
                    {
                        Enemy entity=(Enemy)tile.getEntity();

                        Main.game.getMap().addToMap(new TemporaryEntity(
                                entity.getPosInArrayMap(),
                                Animation.DURATION_ANIMATION_HURT,
                                Animation.HURT,
                                EntityType.HURT
                                ));
                        //do this after put the tile.entity to null because we save the item in this tile
                        if(entity.hurt(damage))
                        {
                            Main.game.getMap().deleteFromAll(entity);
                            entity.actionOnDisappearance();
                        }
                    }else if(tile.getEntity().getClass()==Player.class)
                    {
                        Main.game.end(IssueGame.DEFEAT);
                    }
                }else if(tile.getTileType().equals(TileType.OBSTACLE))
                {
                    tile.setTileType(TileType.GRASS);
                }

                //on met pas les explosion dans la carte , seulement dans la liste d'entités
            }
        });

        Main.game.getMap().deleteFromAll(this);



    }
}
