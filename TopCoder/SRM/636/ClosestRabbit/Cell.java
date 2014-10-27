package topcoder.srm636.ClosestRabbit;

import java.awt.Point;

public class Cell {

    public Point location;
    public String label;

    public Cell(Point loc, String label) {
        this.location = loc;
        this.label = label;
    }
}
