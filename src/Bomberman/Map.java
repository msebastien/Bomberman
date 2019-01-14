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
    //a reference to player;
    private Player player;
    private Boss boss;

    private boolean isMapGenerated=false;
    private final int PROBA_GENERATION_MAP=77;
    private EntityType biome;

    private final int MIN_DISTANCE_FROM_PLAYER_SPAWN=5;

    public Map(Scene scene) {

        WIDTH_TILE=scene.getWidth()/MAP_SIZE_X;
        HEIGHT_TILE=scene.getHeight()/MAP_SIZE_Y;
        if(WIDTH_TILE<HEIGHT_TILE) HEIGHT_TILE=WIDTH_TILE;
        else WIDTH_TILE=HEIGHT_TILE;

        map =new Tile[MAP_SIZE_X][MAP_SIZE_Y];
        entitiesList=new Vector<>(0);

        player=new Player(Game.PLAYER_SPEED);

        scene.setFocusable(true);
        scene.requestFocus();
        scene.addKeyListener(player);

    }




    public void init(int level)
    {
        /*WIDTH_TILE=scene.getWidth()/MAP_SIZE_X;
        HEIGHT_TILE=scene.getHeight()/MAP_SIZE_Y;
        if(WIDTH_TILE<HEIGHT_TILE) HEIGHT_TILE=WIDTH_TILE;
        else WIDTH_TILE=HEIGHT_TILE;*/

        boss=null;
        isMapGenerated=false;
        initBiome();

        //on initialise a null notre map
        for(int i=0;i<MAP_SIZE_X;i++)
        {
            Arrays.fill(map[i],null);
        }


        //on vide notre list de entites list (si on recommence une partie)
        entitiesList.clear();

        //on genere d'abord la case du milieu
        generateMap(new Point(MAP_SIZE_X/2,MAP_SIZE_Y/2),Animation.GRASS);

        //but after generation some tile are still null
        //we have to instantiate them with TileType.OBSTACLE
        List<Point> listOfFreeTile=new LinkedList<>();
        for(int i=0;i<MAP_SIZE_X;i++)
        {
            for (int j=0;j<MAP_SIZE_Y;j++)
            {
                if(map[i][j]==null)map[i][j]=new Tile(Animation.OBSTACLE,biome);
                else if(map[i][j].isFree())listOfFreeTile.add(new Point(i,j));
                //in same time we register all the free tile, in order to generate the enemy location
            }
        }

        //insert player
        //player=new Player(800);
        insertEntityInMap(player,listOfFreeTile);
        //we filter the list to prevent from spawn near an enemy

        generateApparitionPlayer(listOfFreeTile.stream().filter(elt->
                elt.distance(player.getPosInArrayMap())<=MIN_DISTANCE_FROM_PLAYER_SPAWN).collect(Collectors.toList()));

        listOfFreeTile=listOfFreeTile.stream().filter(elt->
                elt.distance(player.getPosInArrayMap())>MIN_DISTANCE_FROM_PLAYER_SPAWN).collect(Collectors.toList());

        //si la generation est degeneree on relance la generation
        if(listOfFreeTile.isEmpty())this.init(level);

        //insert exit
        /*Exit exit=new Exit();
        insertEntityInMap(exit,listOfFreeTile);*/

        generateBoss(listOfFreeTile,level);

        //insert the chest to assure bpmb reserve
        Chest chest=new Chest();
        insertEntityInMap(chest,listOfFreeTile);

        //now we generate the enemy
        int i=0;
        //insert enemy
        Enemy enemy;
        Enemy copyEnemy;

        int nbrEnemy=0;
        if(!listOfFreeTile.isEmpty())nbrEnemy=(int)(Game.nbrEnemy *listOfFreeTile.size());

        while(i<nbrEnemy && !listOfFreeTile.isEmpty())
        {
            copyEnemy=Randomator.getRandomElementIn(Enemy.ListEnemy);
            enemy=new Enemy(copyEnemy);
            insertEntityInMap(enemy,listOfFreeTile);
            i++;
        }


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

    private void generateMap(Point positionTileToGenerate,Animation typeOfTile)
    {
        //we instantiate this tile with specified parameter
        map[positionTileToGenerate.x][positionTileToGenerate.y]=new Tile(typeOfTile,biome);


        //we are going to instantiate the case near to this one
        //if this one is of type Grass
        if(typeOfTile.equals(Animation.GRASS))
        {
            Point futureTile=new Point();

            Direction.directionList.forEach(direction->
            {
                //we search coordinate of the future tile with this direction
                futureTile.setLocation(positionTileToGenerate);
                futureTile.translate(direction.getDirection().x,direction.getDirection().y);

                Animation futureType=Randomator.probaOfSuccess(PROBA_GENERATION_MAP)?Animation.GRASS:Animation.OBSTACLE;

                if(isInsideMap(futureTile) && getTile(futureTile)==null)
                {
                    generateMap(futureTile,futureType);
                }
            });
        }
    }

    private void initBiome()
    {
        biome=Randomator.getRandomElementIn(EntityType.listBiome);
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

    public Player getPlayer() {
        return player;
    }

    public Boss getBoss() {
        return boss;
    }

    public EntityType getBiome() {
        return biome;
    }

    public boolean isInsideMap(Point point)
    {
        return point.x>=0&&point.y>=0 && point.x<MAP_SIZE_X && point.y<MAP_SIZE_Y;
    }


    public synchronized List<Entity> getEntitiesList() {
        return entitiesList;
    }


    public void deleteFromAll(Entity entity)
    {
        if(entity!=null)
        {
            entitiesList.remove(entity);
            map[entity.getPosInArrayMap().x][entity.getPosInArrayMap().y].setEntity(null);
        }
    }

    public void deleteFromListEntitites(Entity entity)
    {
        entitiesList.remove(entity);
    }

    public void deleteFromMapArray(Entity entity)
    {
        map[entity.getPosInArrayMap().x][entity.getPosInArrayMap().y].setEntity(null);
    }

    private void generateBoss(List<Point> listOfFreeTile,int level)
    {
        //si on est à un niveau multiple de 10
        if(level%Game.FRQCY_BOSS==0 && level>0)
        {
            //there is a boss so he can't go further without killing him
            player.setAutorizationNextLevel(false);
            boss=new Boss(Boss.getBossFrom(level));
            insertEntityInMap(boss,listOfFreeTile);
        }
        else//si il y a pas de boss on genere la cle
        {
            ItemDropped key=new ItemDropped(ItemDropped.KeyEscape);
            insertEntityInMap(key,listOfFreeTile);
        }
    }

    private void generateApparitionPlayer(List<Point> listTileToAnimate)
    {
        listTileToAnimate.forEach(point -> {
            int timeApparition=Math.abs(Animation.DURATION_ANIMATION_ITEM-
                    10*Game.THREAD_SLEEP*(int)Math.round(point.distance(player.getPosInArrayMap())));

            addToEntityListOnly(new TemporaryEntity(point,timeApparition,Animation.APPARITION,
                    EntityType.APPARITION));
        });
    }

    public void addToMap(Entity entity)
    {
        if(entity!=null)
        {
            entitiesList.add(entity);
            map[entity.getPosInArrayMap().x][entity.getPosInArrayMap().y].setEntity(entity);
        }
    }

    public void addToEntityListOnly(Entity entity)
    {
        entitiesList.add(entity);
    }
}
