package topcoder.srm211.grafixMask;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        //test = test(test, new String[] { "0 292 399 307" }, new int[] { 116800,
        //        116800 });
        
         /* The masking layer is depicted below in a 1:4 scale diagram.
         */ 
         //1)
          
        //test = test(test, new String[] {"48 192 351 207", "48 392 351 407", "120 52 135 547",
        //"260 52 275 547"}, new int[]{ 22816, 192608 });
        
          
         //2)
          
//        test = test(test, new String[] {"0 192 399 207", "0 392 399 407", "120 0 135 599", "260 0 275 599"},
//        new int[]{ 22080, 22816, 22816, 23040, 23040, 23808, 23808, 23808,
//        23808 });
         
         //3)
          
//        test = test(test, new String[] {"50 300 199 300", "201 300 350 300", "200 50 200 299",
//        "200 301 200 550"},new int[]{ 1, 239199 });
        
         //4)
        
         
        test = test(test, new String[] {"0 20 399 20", "0 44 399 44", "0 68 399 68", "0 92 399 92",
        "0 116 399 116", "0 140 399 140", "0 164 399 164", "0 188 399 188",
        "0 212 399 212", "0 236 399 236", "0 260 399 260", "0 284 399 284",
        "0 308 399 308", "0 332 399 332", "0 356 399 356", "0 380 399 380",
        "0 404 399 404", "0 428 399 428", "0 452 399 452", "0 476 399 476",
        "0 500 399 500", "0 524 399 524", "0 548 399 548", "0 572 399 572",
        "0 596 399 596", "5 0 5 599", "21 0 21 599", "37 0 37 599",
        "53 0 53 599", "69 0 69 599", "85 0 85 599", "101 0 101 599",
        "117 0 117 599", "133 0 133 599", "149 0 149 599", "165 0 165 599",
        "181 0 181 599", "197 0 197 599", "213 0 213 599", "229 0 229 599",
        "245 0 245 599", "261 0 261 599", "277 0 277 599", "293 0 293 599",
        "309 0 309 599", "325 0 325 599", "341 0 341 599", "357 0 357 599",
        "373 0 373 599", "389 0 389 599"}, new int[] { 15, 30, 45, 45, 45, 45,
         45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45,
         45, 45, 45, 100, 115, 115, 115, 115, 115, 115, 115, 115, 115, 115,
         115, 115, 115, 115, 115, 115, 115, 115, 115, 115, 115, 115, 115, 115,
         200, 230, 230, 230, 230, 230, 230, 230, 230, 230, 230, 230, 230, 230,
         230, 230, 230, 230, 230, 230, 230, 230, 230, 230, 230, 300, 300, 300,
         300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300,
         300, 300, 300, 300, 300, 300, 300, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345, 345,
         345, 345, 345, 345, 345, 345, 345, 345, 345 });
         
    }

    private static int test(int test, String[] rectangles, int[] expected) {
        System.out.println("Test" + (test++));
        System.out.println("input: " + print(rectangles));
        System.out.println("Expected: " + printInts(expected));
        List<Integer> actual = solve(rectangles);
        System.out.println("Actual: \n" + actual);
        if (areEqual(expected, actual)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static boolean areEqual(int[] expected, List<Integer> actual) {
        if (expected == null) {
            return actual == null;
        }
        if (actual == null) {
            return false;
        }
        if (actual.size() != expected.length) {
            return false;
        }
        for (int i = 0; i < expected.length; i++) {
            int e = expected[i];
            if (e != actual.get(i)) {
                return false;
            }
        }

        return true;
    }

    private static int vertIndex = 0;
    private static int horizIndex = 1;
    private static int maxX = 599;
    private static int maxY = 399;

    private static List<Integer> solve(String[] rectangles) {

        List<List<Border>> borders = convertToBorders(rectangles);
        List<Integer> areas = new ArrayList<Integer>();

        Point start = new Point(0, 0);

        Map<String, Boolean> backTrack = new HashMap<String, Boolean>();
        
        while (true) {
            int fillArea = 1;
            List<Point> q = new ArrayList<Point>();
            q.add(start);
            addToVisited(backTrack, start);
            System.out.println("Start: " + getId(start));
            Point next = null;
            while (!q.isEmpty()) {
                next = q.remove(0);
                //System.out.println("Next: " + getId(next));
                List<Point> neighborList = findSuccessors(next, backTrack);
                for (Point neighbor : neighborList) {
                   // System.out.print("Neighbor: " + getId(neighbor));
                    // if (!visited(backTrack, neighbor)) {
                 //   System.out.print(" (Unvisited)");
                    if (!isBorder(borders, neighbor)) {
                        //System.out.println();
                        fillArea++;
                        q.add(neighbor);
                        addToVisited(backTrack, neighbor);
                    } else {
                   //     System.out.println("(Border)");
                    }
                    /*
                     * } else{ System.out.println("(Visited)"); }
                     */
                }
                //System.out.println("---------------------------------");
            }
            System.out.println("Last: " + getId(next));
            System.out.println("Fill area: " + fillArea);
            System.out.println();
            areas.add(fillArea);
            start = null;
            if (next != null) {
                
                
                
                List<Point> q2 = new ArrayList<Point>();
                Map<String, Boolean> bTrack = new HashMap<String, Boolean>();

                /*Point borderPoint = findBorder(next, borders, backTrack);
                if (borderPoint != null) {
                    //break;
                    q2.add(borderPoint);
                }*/
                for(Border vB:borders.get(vertIndex)){
                    q2.add(vB.topLeft);
                    q2.add(vB.bottomRight);
                }
                for(Border hB:borders.get(horizIndex)){
                    q2.add(hB.topLeft);
                    q2.add(hB.bottomRight);
                }
                while (!q2.isEmpty()) {
                    next = q2.remove(0);
                    List<Point> neighborList = findSuccessors(next,bTrack);
                    for (Point neighbor : neighborList) {
                        if (!visited(backTrack, neighbor)) {
                            if (!isBorder(borders, neighbor)) {
                                start = neighbor;
                                break;
                            } else {
                                q2.add(neighbor);
                                addToVisited(bTrack, neighbor);
                            }
                        }
                    }
                    if(start!=null){
                        break;
                    }
                }
            }

            if (start == null) {
                break;
            }
        }

        Collections.sort(areas);

        return areas;
    }

    private static void addToVisited(Map<String, Boolean> backTrack,
            Point neighbor) {
        String key = getId(neighbor);
        backTrack.put(key, true);
    }

    private static Point findBorder(Point start, List<List<Border>> borders,
            Map<String, Boolean> backTrack) {
        List<Point> q = new ArrayList<Point>();
        q.add(start);
        while (!q.isEmpty()) {
            Point next = q.remove(0);
            List<Point> neighborList = findSuccessors(next,backTrack);
            for (Point neighbor : neighborList) {
                //if (!visited(backTrack, neighbor)) {
                    if (isBorder(borders, neighbor)) {
                        return neighbor;
                    } else {
                        q.add(neighbor);
                        addToVisited(backTrack, neighbor);
                    }
                //}
            }
        }
        return null;
    }

    private static boolean isBorder(List<List<Border>> borders, Point p) {
        List<Border> vertList = borders.get(vertIndex);
        for (Border vB : vertList) {
            if (vB.contains(p)) {
                return true;
            } else if (vB.lessThan(p)) {
                break;
            }
        }

        List<Border> horizList = borders.get(horizIndex);
        for (Border hB : horizList) {
            if (hB.contains(p)) {
                return true;
            } else if (hB.lessThan(p)) {
                break;
            }
        }

        return false;
    }

    private static boolean visited(Map<String, Boolean> backTrack, Point next) {
        String key = getId(next);
        return backTrack.containsKey(key);
    }

    private static String getId(Point p) {
        return "X" + p.x + "_Y" + p.y;
    }

    private static List<Point> findSuccessors(Point next,
            Map<String, Boolean> backTrack) {
        int prevX = next.x - 1;
        int nextX = next.x + 1;
        int prevY = next.y - 1;
        int nextY = next.y + 1;
        int x = next.x;
        int y = next.y;

        List<Point> result = new ArrayList<Point>();
        if (withinBounds(prevX, y)) {
            Point newCell = new Point(prevX, y);
            if (!visited(backTrack, newCell)) {
                result.add(newCell);
            }
        }

        if (withinBounds(nextX, y)) {
            Point newCell = new Point(nextX, y);
            if (!visited(backTrack, newCell)) {
                result.add(newCell);
            }
        }

        if (withinBounds(x, prevY)) {
            Point newCell = new Point(x, prevY);
            if (!visited(backTrack, newCell)) {
                result.add(newCell);
            }
        }

        if (withinBounds(x, nextY)) {
            Point newCell = new Point(x, nextY);
            if (!visited(backTrack, newCell)) {
                result.add(newCell);
            }
        }
        return result;
    }

    private static boolean withinBounds(int x, int y) {
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;
    }

    private static List<List<Border>> convertToBorders(String[] rectangles) {
        List<List<Border>> result = new ArrayList<List<Border>>();
        result.add(new ArrayList<Border>());
        result.add(new ArrayList<Border>());
        for (String r : rectangles) {
            String[] tokens = r.split("\\s");
            int i = 0;
            int topY = Integer.parseInt(tokens[i++]);
            int topX = Integer.parseInt(tokens[i++]);
            int bottomY = Integer.parseInt(tokens[i++]);
            int bottomX = Integer.parseInt(tokens[i++]);
            Point topLeft = new Point(topX, topY);
            Point bottomRight = new Point(bottomX, bottomY);
            Border b = new Border(topLeft, bottomRight);

            List<Border> list;
            if (b.isVertical) {
                list = result.get(vertIndex);
            } else {
                list = result.get(horizIndex);
            }

            list.add(b);
        }
        Collections.sort(result.get(vertIndex));
        Collections.sort(result.get(horizIndex));
        return result;
    }

    private static String printInts(int[] expected) {
        String result = "\n[";
        boolean first = true;
        for (int e : expected) {
            if(first){
                first = false;
            }
            else{
                result+=" ";
            }
            result += e;
        }
        result+="]\n";
        return result;
    }

    private static String print(String[] rectangles) {
        String result = "\n";
        for (String r : rectangles) {
            result += r + "\n";
        }
        return result;
    }

}
