package topcoder.srm233.RevolvingDoors;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class MyPath<T> extends ArrayList<T>
{
    public void copy(ArrayList<T> other)
    {
        for(T v:this)
        {
            other.add(v);
        }
    }
}
