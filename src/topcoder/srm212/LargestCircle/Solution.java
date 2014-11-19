package topcoder.srm212.LargestCircle;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws Exception {
        // 0)
        int test = 0;
        //test = test(test, new String[] { "####", "#..#", "#..#", "####" }, 1);
        // Only one circle fits in this grid -- a circle of radius 1, in the
        // center of the grid.
        
         //1)
  /*        
        test = test(test, new String[] { "############", "###......###", "##.######.##", "#.########.#",
          "#.##..####.#", "#.##..####.#", "#.########.#", "#.########.#",
          "#.########.#", "##.######.##", "###......###", "############" },
          5);*/
         /*This is the example from the problem statement. */
        
         //2)
        //test = test(test, new String[] { ".........." },0);
        
        //The grid must be at least two squares
         //* wide and tall in order for any circles to fit. 3)
         
        //test = test(test, new String[] { "#######", "#######", "#######", "#######", "#######" },0);
         // 4)
         /*
        test = test(test, new String[] { "#####.......", "#####.......", "#####.......", "............",
          "............", "............", "............", "............",
          "............", "............" } , 4);*/
          /*A circle of radius 4 fits
         * in this grid, as shown here:
         */
        
         //5)
         /*
        test = test(test, new String[] { "#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.",
          "...#...#...#...#...#...#...#...#...#...#...#...#..",
          "#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.",
          ".#...#...#...#...#...#...#...#...#...#...#...#...#",
          "#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.",
          "...#...#...#...#...#...#...#...#...#...#...#...#..",
          "#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.",
          ".#...#...#...#...#...#...#...#...#...#...#...#...#",
          "#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.",
          "...#...#...#...#...#...#...#...#...#...#...#...#.#",
          "#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#." } , 0);*/
        //6)
          
        test = test(test, new String[] { ".........................#........................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          "..................................................",
          ".................................................." } , 24);
         

    }

    private static int test(int test, String[] grid, int expected)
            throws Exception {
        System.out.println("Test" + (test++));
        System.out.println("input: " + print(grid));
        System.out.println("Expected: " + expected);
        int actual = solve(grid);
        System.out.println("Actual: " + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static int solve(String[] grid) throws Exception {
        int maxRadius = Integer.MIN_VALUE;
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length(); j++) {
                //char c = grid[i].charAt(j);
                //if (c == '.') {
                    Point center = new Point(j, i);
                    int radius = buildCircle(grid, center);
                    if (radius > maxRadius) {
                        maxRadius = radius;
                    }
                //}
            }
        }
        if(maxRadius==Integer.MIN_VALUE){
            return 0;
        }
        return maxRadius;
    }

    private static int buildCircle(String[] grid, Point center)
            throws Exception {
        int dimension = Math.min(center.y,Math.min(center.x,Math.min(grid.length - center.y, grid[0].length()
                - center.x)));
        int maxRadius = Integer.MIN_VALUE;
        for (int r = 1; r <= dimension; r++) {
            // (x-center.x)^2+(y-center.y)^2=r^2
            // x=-r...0...r
            // Calc: y = sqrt(r^2 - (x-center.x)^2) + center.y
            double rSquared = Math.pow(r, 2);
            boolean goodCircle = true;
            for (double x = (center.x - r); x <= (center.x + r); x+=0.25) {
                if (x >= 0 && x <= grid[0].length()) {
                    double xSquared = Math.pow(x - center.x, 2);
                    double deltay = Math.sqrt(rSquared - xSquared);
                    double y1 = center.y + deltay;
                    double y2 = center.y - deltay;
                    for (double y : Arrays.asList(y1, y2)) {
                        if (y >= 0 && y <= grid.length) {
                            List<Point> list = getIntersectingSquares(center,
                                    x, y);
                            for (Point p : list) {
                                char c = grid[p.y].charAt(p.x);
                                if (c == '#') {
                                    goodCircle = false;
                                    break;
                                }
                            }

                        }
                    }

                } else {
                    throw new Exception("X is out of bounds!");
                }
            }
            if (goodCircle) {
                if (r > maxRadius) {
                    maxRadius = r;
                }
            }
        }

        return maxRadius;
    }

    private static List<Point> getIntersectingSquares(Point origin, double x,
            double y) {
        /*
        if (Math.ceil(y) == y) {
            if (x == origin.x) {
                // Top or Bottom of circle
                return Arrays.asList(new Point(x - 1, (int) y), new Point(x,
                        (int) y));
            } else {
                // Left or Right most point of circle
                return Arrays.asList(new Point(x, (int) y - 1), new Point(x,
                        (int) y));

            }
        } else {

        }
        return null;
        */
        if (Math.ceil(y) == y) { return new ArrayList<Point>();}
        int col = (int)Math.floor(x);
        int row = (int)Math.floor(y);
        return Arrays.asList(new Point(col,row));               
    }

    private static String print(String[] grid) {
        String result = "\n";
        for (String r : grid) {
            result += r + "\n";
        }
        return result;
    }

}
