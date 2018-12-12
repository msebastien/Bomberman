package Bomberman;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Randomator {
    
    //return a int in [min; max-1]
    public static int rand(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max );
    }

    public static <T> T getRandomElementIn(List<T> list)
    {
        return list.get(rand(0,list.size()));
    }
}
