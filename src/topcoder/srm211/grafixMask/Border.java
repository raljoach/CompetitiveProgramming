package topcoder.srm211.grafixMask;

import java.awt.Point;

public class Border implements Comparable<Border> {
    public Point topLeft;
    public Point bottomRight;
    public boolean isVertical = false;

    public Border(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;

        this.isVertical = Math.abs(bottomRight.x - topLeft.x) < Math
                .abs(bottomRight.y - topLeft.y);
    }

    @Override
    public int compareTo(Border other) {
        if (this.isVertical) {
            if (other.isVertical) {
                if (this.topLeft.x == other.topLeft.x) {
                    if (this.bottomRight.x == other.bottomRight.x) {
                        return 0;
                    }
                    else if(this.bottomRight.x<other.bottomRight.x){
                        return -1;
                    }
                    /*else{
                        return 1;
                    }*/
                } else if (this.topLeft.x < other.topLeft.x) {
                    return -1;
                } else {
                    return 1;
                }
            } /*else {
                throw new Exception(
                        "Error: This is vertical, other is Horizontal");
            }*/
        }
        else{
            if(!other.isVertical){
                if (this.topLeft.y == other.topLeft.y) {
                    if (this.bottomRight.y == other.bottomRight.y) {
                        return 0;
                    }
                    else if(this.bottomRight.y<other.bottomRight.y){
                        return -1;
                    }
                    /*else{
                        return 1;
                    }*/
                } else if (this.topLeft.y < other.topLeft.y) {
                    return -1;
                } else {
                    return 1;
                }
            }
            /*else {
                throw new Exception(
                        "Error: This is horizontal, other is Vertical");
            }*/
        }
        return 1;
    }

    public boolean contains(Point p) {
        return p.x>=topLeft.x && p.x<=bottomRight.x &&
               p.y>=topLeft.y && p.y<=bottomRight.y;
    }

    public boolean lessThan(Point p) {
        if(isVertical){
            return p.x<topLeft.x;
        }
        else{
            return p.y <topLeft.y;
        }
    }
}
