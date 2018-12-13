package Bomberman;

import Bomberman.EntityManager.Enemy;
import Bomberman.EntityManager.Entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Map {
    static final int MAP_SIZE_X = 10; // Modify values if needed
    static final int MAP_SIZE_Y = 10;

    public static int HEIGHT_TILE;
    public static int WIDTH_TILE;

    private Tile[][] map;
    private List<Entity> entitiesList;
    private boolean isMapGenerated=false;

    private final int NBR_ENTITY=5;

    public Map() {
        map =new Tile[MAP_SIZE_X][MAP_SIZE_Y];
        entitiesList=new ArrayList<>(0);
    }

    public void init(Scene scene)
    {
        WIDTH_TILE=scene.getWidth()/MAP_SIZE_X;
        HEIGHT_TILE=scene.getHeight()/MAP_SIZE_Y;
        isMapGenerated=false;

        //on initialise a null notre map
        for(int i=0;i<MAP_SIZE_X;i++)
        {
            Arrays.fill(map[i],null);
        }


        //on vide notre list de entites list (si on recommence une partie)
        entitiesList.clear();

        //on genere d'abord la case du milieu
        generateMap(new Point(MAP_SIZE_X/2,MAP_SIZE_Y/2),TileType.GRASS);

        //but after generation some tile are still null
        //we have to instantiate them with TileType.OBSTACLE
        List<Point> listOfFreeTile=new LinkedList<>();
        for(int i=0;i<MAP_SIZE_X;i++)
        {
            for (int j=0;j<MAP_SIZE_Y;j++)
            {
                if(map[i][j]==null)map[i][j]=new Tile(TileType.OBSTACLE);
                else if(map[i][j].isFree())listOfFreeTile.add(new Point(i,j));
                //in same time we register all the free tile, in order to generate the entity location
            }
        }


        //now we generate the entity
        int i=0;
        Point tileCoord;
        Enemy enemy;
        while(i<NBR_ENTITY && !listOfFreeTile.isEmpty())
        {
            tileCoord=Randomator.getRandomElementIn(listOfFreeTile);
            enemy=new Enemy(tileCoord,1000);

            map[tileCoord.x][tileCoord.y].setEntity(enemy);
            entitiesList.add(enemy);

            listOfFreeTile.remove(tileCoord);
            i++;
        }


        isMapGenerated=true;
    }

    private void generateMap(Point positionTileToGenerate,TileType typeOfTile)
    {
        //we instantiate this tile with specified parameter
        map[positionTileToGenerate.x][positionTileToGenerate.y]=new Tile(typeOfTile);


        //we are going to instantiate the case near to this one
        //if this one is of type Grass
        if(typeOfTile.equals(TileType.GRASS))
        {
            Point futureTile=new Point();

            Direction.directionList.forEach(direction->
            {
                //we search coordinate of the future tile with this direction
                futureTile.setLocation(positionTileToGenerate);
                futureTile.translate(direction.getDirection().x,direction.getDirection().y);

                TileType futureType=Randomator.probaOfSuccess(70)?TileType.GRASS:TileType.OBSTACLE;

                if(isInsideMap(futureTile) && getTile(futureTile)==null)
                {
                    generateMap(futureTile,futureType);
                }
            });
        }
    }

    public boolean isMapGenerated()
    {
        return isMapGenerated;
    }

    public Tile getTile(Point point)
    {
        return map[point.x][point.y];
    }

    public Tile getTile(int x,int y)
    {
        return map[x][y];
    }

    public boolean isInsideMap(Point point)
    {
        return point.x>=0&&point.y>=0 && point.x<MAP_SIZE_X && point.y<MAP_SIZE_Y;
    }

    public List<Entity> getEntitiesList() {
        return entitiesList;
    }

    public void afficher()
    {
        for (int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[i].length;j++)
            {

                if(map[i][j]!=null)
                System.out.print("|"+(map[i][j].getEntity()!=null));
                else
                {
                    System.out.print("|0");
                }
            }
            System.out.print("|\n");

        }
    }
}
