package Bomberman;


import Bomberman.EntityManager.Vitesse;

public class Game {

    /**
     * Rules for the game level 1
     * the rule for a level-up
     * value for current level
     */
    private int level;

    private final float NBR_ENEMY_LEVEL_0 =0.02f;
    private final float INC_ENEMY=0.0005f;//each level we add this prct of enemy
    public static float nbrEnemy;

    private final int LIFE_PLAYER_LEVEL_0=1;
    public static int lifePLayer;

    private final int NUMBER_BOMB_LEVEL_0 =3;
    private final int INC_NUMBER_BOMB=1;//each level we add this amount of bomb to the player's stack
    public static int nbrBombInitReserve;

    //un boss tous les combiens de niveaux
    public final static int FRQCY_BOSS=10;

    public final static int PLAYER_RANGE_LEVEL_0=1;
    public final static int PLAYER_DAMAGE_LEVEL_0=1;
    public final static Vitesse PLAYER_SPEED=Vitesse.MOYEN;
    public final static int COUNT_DOWN_BOMB=2500;
    public final static int COUNT_DOWN_INVINCIBLE_TIME=1000;

    /**************/


    //number between 0 and 1 -> 1= all free case with enemy 0= without enemy
    private Map map;
    public final static int THREAD_SLEEP=50;//in ms

    private Scene scene;
    private Window window;

    private boolean isRunning;

    //implement onKey listener to skip to the next level when isRunnig=false
    public Game(Scene scene,Window window)
    {
        level=30;
        this.window=window;
        this.scene=scene;

        map=new Map(scene);

        init();
        Thread thread=new Thread(new GameThread());


        thread.start();
    }

    //method called when we go to next level
    public void init()
    {
        /*
        Create the param for the actual level
         */
        lifePLayer=level/5+LIFE_PLAYER_LEVEL_0;

        nbrEnemy=level*INC_ENEMY+ NBR_ENEMY_LEVEL_0;
        nbrBombInitReserve=level*INC_NUMBER_BOMB+NUMBER_BOMB_LEVEL_0;


        isRunning=true;
        map.init(level);
        window.getHud().initBackground(map.getBiome());
        scene.setFocusable(true);
        scene.requestFocus();
    }

    public Map getMap() {
        return map;
    }

    public int getLevel() {
        return level;
    }

    public void end(IssueGame issueGame)
    {


        isRunning=false;
        window.showMenuEndGame(issueGame);

        if(issueGame.equals(IssueGame.VICTORY))level++;
    }




    class GameThread implements Runnable{

        @Override
        public void run() {

            while(true)
            {
                if(isRunning)
                {
                    //all of the entity do their action
                    //for loop necessary to go through concurrentModification
                    for(int i=0;i<map.getEntitiesList().size();i++)
                    {
                        //if(map.getEntitiesList().get(i).isAlive())
                        //{
                        map.getEntitiesList().get(i).action();
                        //}
                    }
                    window.updateHUD(map.getPlayer());
                }

                scene.repaint();



                try {
                    Thread.sleep(THREAD_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
