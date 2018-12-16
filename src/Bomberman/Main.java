package Bomberman;

public class Main {

    public static Game game;
    public static void main(String[] args)
    {

        Scene scene=new Scene();
        Window window=new Window(scene);
        game=new Game(scene);

    }
}
