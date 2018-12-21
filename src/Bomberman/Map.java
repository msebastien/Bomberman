package Bomberman;

import Bomberman.EntityManager.*;

import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;

public class Map {
    static final int MAP_SIZE_X = 20; // Modify values if needed
    static final int MAP_SIZE_Y = 20;

    public static int HEIGHT_TILE;
    public static int WIDTH_TILE;

    private Tile[][] map;
    //contain all the current entities
    private Vector<Entity> entitiesList;



    private boolean isMapGenerated=false;
    private final int PROBA_GENERATION_MAP=75;
    private final int NBR_ENTITY=20;
    private final int MIN_DISTANCE_FROM_PLAYER_SPAWN=5;

    public Map() {
        map =new Tile[MAP_SIZE_X][MAP_SIZE_Y];
        entitiesList=new Vector<>(0);
    }


    public void init(Scene scene)
    {
        WIDTH_TILE=scene.getWidth()/MAP_SIZE_X;
        HEIGHT_TILE=scene.getHeight()/MAP_SIZE_Y;
        if(WIDTH_TILE<HEIGHT_TILE) HEIGHT_TILE=WIDTH_TILE;
        else WIDTH_TILE=HEIGHT_TILE;

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
                //in same time we register all the free tile, in order to generate the enemy location
            }
        }

        //insert player
        Player player=new Player(800);
        insertEntityInMap(player,listOfFreeTile);
        //we filter the list to prevent from spawn near an enemy
        listOfFreeTile=listOfFreeTile.stream().filter(elt->
                elt.distance(player.getPosInArrayMap())>MIN_DISTANCE_FROM_PLAYER_SPAWN).collect(Collectors.toList());

        //now we generate the enemy
        int i=0;

        //insert enemy
        Enemy enemy;
        while(i<NBR_ENTITY && !listOfFreeTile.isEmpty())
        {
            enemy=new Enemy(1000);
            insertEntityInMap(enemy,listOfFreeTile);
            i++;
        }


        //insert exit
        Exit exit=new Exit();
        insertEntityInMap(exit,listOfFreeTile);

        scene.setFocusable(true);
        scene.requestFocus();
        scene.addKeyListener(player);


        isMapGenerated=true;
    }

    private void insertEntityInMap(Entity entity,List<Point> listOfFreeTile)
    {

        if(!listOfFreeTile.isEmpty())
        {
            Point tileCoord=Randomator.getRandomElementIn(listOfFreeTile);

            map[tileCoord.x][tileCoord.y].setEntity(entity);
            entitiesList.add(entity);
            entity.init(tileCoord);

            listOfFreeTile.remove(tileCoord);
        }
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

                TileType futureType=Randomator.probaOfSuccess(PROBA_GENERATION_MAP)?TileType.GRASS:TileType.OBSTACLE;

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

    public boolean isInsideMap(int x,int y)
    {
        return x>=0&&y>=0 && x<MAP_SIZE_X && y<MAP_SIZE_Y;
    }

    public synchronized List<Entity> getEntitiesList() {
        return entitiesList;
    }


    public void deleteFromMap(Entity entity)
    {
        if(entity!=null)
        {
            //entity.destroy();
            entitiesList.remove(entity);
            map[entity.getPosInArrayMap().x][entity.getPosInArrayMap().y].setEntity(null);
        }
    }

    public void addToMap(Entity entity)
    {
        if(entity!=null)
        {
            entitiesList.add(entity);
            map[entity.getPosInArrayMap().x][entity.getPosInArrayMap().y].setEntity(entity);
        }
    }

    public void afficher()
    {
        for(int j=0;j<map[0].length;j++)
        {
            for (int i=0;i<map.length;i++)
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
