package Bomberman;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Randomator {
    
    public static int rand(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1); // add 1 to include the max. nextInt is normally exclusive
    }

    public static <T> T getRandomElementIn(List<T> list)
    {
        return list.get(rand(0,list.size()));
    }
}
