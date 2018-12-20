package Bomberman.EntityManager;

import Bomberman.Main;
import Bomberman.Map;
import Bomberman.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bomb extends TemporaryEntity implements ActionOnDisappearance{

    public final static List<List<Point>> DifferentTypeExplosion= Arrays.asList(
            Arrays.asList(new Point(-1,-1),new Point(-1,1),new Point(1,-1),new Point(1,1)),
            Arrays.asList(new Point(0,-1),new Point(-1,0),new Point(0,1),new Point(1,0)),
            Arrays.asList(new Point(1,0),new Point(2,0),new Point(0,1),new Point(0,2)),
            Arrays.asList(new Point(-1,0),new Point(-2,0),new Point(0,-1),new Point(0,-2)),

            Arrays.asList(new Point(1,0),new Point(2,0),new Point(3,0),new Point(4,0),new Point(-1,0),
            new Point(-2,0),new Point(-3,0),new Point(-4,0)),

            Arrays.asList(new Point(0,1),new Point(0,2),new Point(0,3),new Point(0,4),new Point(0,-1),
                    new Point(0,-2),new Point(0,-3),new Point(0,-4))
            );

    private List<Point> schemaExplosion;

    public Bomb(Point posInArrayMap,int countDown,List<Point> schemaExplosion) {
        super(posInArrayMap,countDown);
        this.schemaExplosion=schemaExplosion;

    }

    @Override
    public void action() {
        super.action();
        if(countDown<=0)actionOnDisappearance();
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
                Explosion explosion=new Explosion(new Point(impactPoint),1000);
                map.getEntitiesList().add(explosion);
            }
        });
        map.getTile(posInArrayMap).setEntity(null);

        /*Tile tile;
        for(int i=posInArrayMap.x-1;i<=posInArrayMap.x+1;i++)
        {
            for(int j=posInArrayMap.y-1;j<=posInArrayMap.y+1;j++)
            {
                //we destroy the entity concerned
                if(map.isInsideMap(i,j) )
                {
                    Explosion explosion=new Explosion(new Point(i,j),1000);
                    map.getEntitiesList().add(explosion);
                }
            }
        }*/

    }
}
