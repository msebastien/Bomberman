package Bomberman;



public class Game {

    private Map map;
    public final static int THREAD_SLEEP=15;//in ms

    private Scene scene;

    private boolean isRunning;

    public Game(Scene scene) {
        isRunning=true;
        map=new Map();

        this.scene=scene;

        Thread thread=new Thread(new GameThread());

        map.init(scene);

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
                    //for loop necessary to go through concurrentModification
                    for(int i=0;i<map.getEntitiesList().size();i++)
                    {
                        if(map.getEntitiesList().get(i).isAlive())
                        {
                            map.getEntitiesList().get(i).action();
                        }
                    }

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
