package Bomberman;

import Bomberman.EntityManager.Entity;


public class Game {

    private Map map;
    public final static int THREAD_SLEEP=100;//in ms

    private Scene scene;

    private boolean isRunning;

    public Game(Scene scene) {
        isRunning=true;
        map=new Map();

        this.scene=scene;


        Thread thread=new Thread(new GameThread());

        map.init(scene);

        //map.afficher();
        thread.start();
    }

    public Map getMap() {
        return map;
    }

    public void end(String str)
    {
        scene.setStringEndGame(str);
        isRunning=false;
    }

    class GameThread implements Runnable{

        @Override
        public void run() {

            while(true)
            {
                if(isRunning)
                {
                    //all of the entity do their action
                    map.getEntitiesList().forEach(Entity::action);
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
