package Bomberman;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Randomator {
    
    //return a int in [min; max-1]
    public static int rand(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static <T> T getRandomElementIn(List<T> list)
    {
        return list.get(rand(0,list.size()));
    }

    /**
     * @param proba a number in [0,100]
     * @return true with proba% of chance
     */
    public static boolean probaOfSuccess(int proba)
    {
        return rand(0,100+1)<=proba;
    }
}
