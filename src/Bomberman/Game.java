package Bomberman;

public class Game {

    public static Map map;
    public final static int THREAD_SLEEP=10;//in ms

    public Game() {
        map=new Map();
        Thread thread=new Thread(new GameThread());
        //thread.start();
    }

    class GameThread implements Runnable{
        @Override

        //TODO
        public void run() {
        }
    }

}
