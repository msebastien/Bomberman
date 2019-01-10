package Bomberman;



public class Game {

    /**
     * Rules for the game level 1
     * the rule for a level-up
     * value for current level
     */
    private int level;

    private final float NBR_ENEMY_LEVEL_0 =0.02f;
    private final float INC_ENEMY=0.01f;//each level we add this prct of enemy
    public static float nbrEnemy;


    private final int SPEED_ENEMY_LEVEL_0 =700;
    private final int INC_SPPED_ENEMY=10;//each level we add this amount of milliseconds for an enemy move
    public static int speedEnemy;

    private final int LIFE_ENEMY_LEVEL_0=1;

    private final int NUMBER_BOMB_LEVEL_0 =3;
    private final int INC_NUMBER_BOMB=1;//each level we add this amount of bomb to the player's stack
    public static int nbrBombInitReserve;

    public final static int COUNT_DOWN_BOMB=3000;

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
        level=0;
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
        nbrEnemy=level*INC_ENEMY+ NBR_ENEMY_LEVEL_0;
        nbrBombInitReserve=level*INC_NUMBER_BOMB+ NUMBER_BOMB_LEVEL_0;
        speedEnemy =-(level*INC_SPPED_ENEMY)+ SPEED_ENEMY_LEVEL_0;//on met un signe moins car il faut diminuer la duree
        if(speedEnemy<=0)speedEnemy=5;//on s'assure que les ennemis se déplacent dans tous les cas
        //du mouvement pour qu'ils aillent plus vite


        isRunning=true;
        map.init();
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
