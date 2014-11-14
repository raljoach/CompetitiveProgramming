package topcoder.srm233.RevolvingDoors;

import java.util.HashMap;

@SuppressWarnings("serial")
public class MyMap<T,E> extends HashMap<T,E> {

    public void copy(MyMap<T,E> other)
    {
        for(T key:this.keySet())
        {
            other.put(key, this.get(key));
        }
    }
}
