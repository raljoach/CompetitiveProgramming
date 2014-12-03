package topcoder.srm236.Parking;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Car implements Comparable<Car>{
    public int id;
    public List<Point> spots = new ArrayList<Point>();
    public List<Integer> spotIndex = new ArrayList<Integer>();
    public Car(int id){ this.id=id;}
    @Override
    public int compareTo(Car other) {
        return new Integer(spots.size()).compareTo(new Integer(other.spots.size()));
    }
}
