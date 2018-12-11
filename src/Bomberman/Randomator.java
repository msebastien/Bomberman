package Bomberman;

import java.util.List;

public class Randomator {

    //TODO return an int between inf and supp-1
    public static int alea(int inf,int supp)
    {
        return 0;
    }

    public static <T> T getElementAleaIn(List<T> list)
    {
        return list.get(alea(0,list.size()));
    }
}
