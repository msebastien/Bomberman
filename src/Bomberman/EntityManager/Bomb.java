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
    private int range;

    public Bomb(Point posInArrayMap,int countDown,List<Point> schemaExplosion,int damage,int range) {
        super(posInArrayMap,countDown,Animation.BOMB,EntityType.BOMB);
        this.damage=damage;
        this.range=range;
        this.schemaExplosion=schemaExplosion;
    }

    @Override
    public void actionOnDisappearance()
    {
        // we search all the tile around the bomb

        Map map=Main.game.getMap();

        //we calculate the coordinate of all points with an explosion
        Point impactPoint=new Point();

        //for each range
        for(int distance=1;distance<=range;distance++)
        {
            //for each point of the schema
            for (int i=0;i<schemaExplosion.size();i++) {

                //si on est sur un range superieur à 1 et qu'on place le point zero on le fait pas
                //parcequ'il y a deja l'impact du range precedent
                if(range>1&&schemaExplosion.get(i).x==0&&schemaExplosion.get(i).y==0)continue;

                //we calculate the imact point position
                impactPoint.setLocation(posInArrayMap);
                impactPoint.translate(schemaExplosion.get(i).x * distance, schemaExplosion.get(i).y*distance);

                if (map.isInsideMap(impactPoint)) {
                    map.addToEntityListOnly(new TemporaryEntity(new Point(impactPoint), Animation.ANIMATION_EXPLOSION,
                            Animation.EXPLOSION, EntityType.EXPLOSION));


                    Tile tile = map.getTile(impactPoint);
                    if (tile.hasEntity() && tile.getEntity() instanceof MovingEntity) {
                        ((MovingEntity) tile.getEntity()).hurt(damage);

                    } else if (tile.getTileType().getTypeBackground().equals(Animation.OBSTACLE)) {
                        tile.setTileType(Animation.GRASS, Main.game.getMap().getBiome());
                    }

                    //on met pas les explosion dans la carte , seulement dans la liste d'entités
                }
            }
            //schemaExplosion.forEach(point->{
            //});
        }

        Main.game.getMap().deleteFromAll(this);



    }
}
