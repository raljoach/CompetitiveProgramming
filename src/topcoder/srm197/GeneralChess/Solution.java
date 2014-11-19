package topcoder.srm197.GeneralChess;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        //test = test(test, new String[] { "0,0" }, new String[] { "-2,-1",
        //        "-2,1", "-1,-2", "-1,2", "1,-2", "1,2", "2,-1", "2,1" });
        
        // This location is threatened from eight different places.
        
         //1)
         
         //test = test(test, new String[] {"2,1", "-1,-2"}, new String[] { "0,0", "1,-1" } );
         
         /*A knight may be in two
         * places such that both pieces are threatened. In chess, placing your
         * pieces in such positions is usually undesirable when your opponent
         * has a knight. */
        
         //2)
         
         //test = test(test, new String[] {"0,0", "2,1"}, new String[] { });
         //3)
         
         
         test = test(test, new String[] {"-1000,1000", "-999,999", "-999,997"}, new String[]{ "-1001,998" });
         /*No
         * three pieces can ever be threatened by a knight from more than one
         * position.
         */
    }

    private static int test(int test, String[] pieces, String[] expected) {
        System.out.println("Test" + (test++));
        System.out.println("input: " + print(pieces));
        System.out.println("Expected: " + print(expected));
        List<String> actual = solve(pieces);
        System.out.println("Actual: \n" + actual);
        if (areEqual(expected, actual)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static boolean areEqual(String[] expected, List<String> actual) {
        if (expected == null) {
            return actual == null;
        }
        if (actual == null) {
            return false;
        }
        if (actual.size() != expected.length) {
            System.out.println("Expected length ("+expected.length + ") and Actual length (" + actual.size() + ") don't match");
            return false;
        }
        for (int i = 0; i < expected.length; i++) {
            String e = expected[i];
            boolean found = false;
            for (String a : actual) {
                if (e.equals(a)) {
                    found = true;
                    break;
                }
            }
            if(!found){
                System.out.println("Error: Did not find " + e);
                return false;
            }
        }

        return true;
    }

    private static List<String> solve(String[] pieces) {
        List<Point> currentList = new ArrayList<Point>();
        for (String pieceStr : pieces) {
            String[] tokens = pieceStr.split(",");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            Point target = new Point(x, y);
            List<Point> newList = startForKnight(target);
            if (currentList.isEmpty()) {
                currentList = newList;
            } else {
                currentList = intersection(currentList, newList);
            }
        }
        /*if (currentList.isEmpty()) {
            return null;
        }*/
        List<String> result = new ArrayList<String>();
        for (Point p : currentList) {
            result.add(p.x + "," + p.y);
        }
        return result;
    }

    private static List<Point> intersection(List<Point> currentList,
            List<Point> newList) {
        List<Point> intersect = new ArrayList<Point>();
        for (Point p : newList) {
            boolean found = false;
            for (Point p2 : currentList) {
                if (p.x == p2.x && p.y == p2.y) {
                    intersect.add(p);
                }
            }
        }
        return intersect;
    }

    private static List<Point> startForKnight(Point target) {
        List<Point> jumps = Arrays.asList(new Point(-1, 2), new Point(-1, -2),
                new Point(1, 2),  new Point(1, -2), new Point(-2, -1),
                new Point(-2, 1), new Point(2, -1), new Point(2, 1));

        List<Point> result = new ArrayList<Point>();
        for (Point j : jumps) {
            result.add(new Point(target.x + j.x, target.y + j.y));
        }
        return result;
    }

    private static String print(String[] pieces) {
        String result = "\n";
        for (String r : pieces) {
            result += r + "\n";
        }
        return result;
    }

}
