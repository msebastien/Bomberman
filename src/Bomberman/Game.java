package Bomberman;

import Bomberman.EntityManager.Entity;

public class Game {

    public static Map map;
    public final static int THREAD_SLEEP=100;//in ms

    private Scene scene;

    public Game(Scene scene) {
        map=new Map();
        this.scene=scene;
        Thread thread=new Thread(new GameThread());
        map.init(scene);

        //map.afficher();
        thread.start();
    }

    class GameThread implements Runnable{

        @Override
        public void run() {

            //map.init(scene);
            while(true)
            {
                //all of the entity do their action
                map.getEntitiesList().forEach(Entity::action);
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
