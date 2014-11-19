package topcoder.srm222.WalkingHome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        test = test(test, new String[] { "S.|..", "..|.H" }, 1);
        
         //Here, Johnny lives right across the street from the school, so
         //inevitably, he's crossing the street once to get home. 
        
        //1)
         
        test = test(test, new String[] {"S.|..", "..|.H", "..|..", "....."},0); 
        
         /*Similar to above, but
         * since the road has a dead end (maybe even a cul-de-sac at the end),
         * Johnny can get home without actually having to cross the road. 
         */
         
        //2)
        test = test(test, new String[] {"S.||...", "..||...", "..||...", "..||..H"},1);
         
         /*Notice here
         * that even though it's a 2-lane highway, it only counts as a single
         * crossing. 
         */
        
         //3)
         
        
        test = test(test, new String[] {"S.....", "---*--", "...|..", "...|.H"}, 1);
        
        /*Here, Johnny
         * could go down across the street and then right across another street
         * to his house. However, if he first goes to the right before crossing
         * down, he will only cross 1 street. 
         */
        
        //4)
        test = test(test, new String[] {"S.F..", "..F..", "--*--", "..|..", "..|.H"}, 2); 
        /*Similar to
         * above, but because there's a fence around the school, Johnny has no
         * choice but to cross twice. 
         */
        
        //5)
        //test = test(test, new String[] {"H|.|.|.|.|.|.|.|.|.|.|.|.|.", "F|F|F|F|F|F|F|F|F|F|F|F|F|-",
        // "S|.|.|.|.|.|.|.|.|.|.|.|.|."},27);
         /*Poor Johnny lives so close
         * to school, but that fence makes him cross the street quite a bit just
         * to get home. 
         */
        
         //6)
        test = test(test, new String[] {"S-H"},-1);
         
    }

    private static int test(int test, String[] map, int expected) {
        System.out.println("Test" + (test++));
        System.out.println("input: " + print(map));
        System.out.println("Expected: " + expected);
        int actual = solve(map);
        System.out.println("Actual: " + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static Cell start;
    private static Cell end;

    private static int solve(String[] map) {
        processMap(map);
        if (checkFound(start, end)) {
            return 0;
        }
        List<Cell> q = new ArrayList<Cell>();
        q.add(start);
        int ans = Integer.MAX_VALUE;
        Map<String, Boolean> bounceBack = new HashMap<String, Boolean>();
        while (!q.isEmpty()) {
            Cell next = q.remove(0);
            //String debugPath = print(next);
            if (getNumCrossings(next) < ans) {
                boolean isStuck = true;
                while (isStuck) {
                    List<Cell> neighborList = findSuccessors(map, next);
                    int numNeighbors = neighborList.size();
                    for (Cell neighbor : neighborList) {
                        //System.out.print("Neighbor: " + neighbor.getId());
                        if (!next.hasVisited(neighbor)) {
                            //System.out.println("(Unvisited) Crossing:"
                            //        + neighbor.crossing);
                            isStuck = false;
                            if (checkFound(neighbor, end)) {
                                int thisAns = getNumCrossings(neighbor);
                                //System.out.println("Potential Answer: " + thisAns);
                                if (thisAns < ans) {
                                    //System.out.println("New Answer: " + thisAns);
                                    ans = thisAns;
                                    break;
                                }
                            } else {
                                q.add(neighbor);
                                next.addToPath(neighbor);
                            }
                        } else {
                            //System.out.println("(Visited) Crossing:"
                            //        + neighbor.crossing);
                        }
                    }

                    if (isStuck) {
                        if (numNeighbors == 1) {
                            if (bounceBack.containsKey(next.getId())) {
                               // System.out
                               //         .println("Been in this corner already, exiting!");
                                break;
                            } else {
                             //   System.out
                             //           .println("Stuck in a corner! Let's get this guy unstuck and try again!");
                                bounceBack.put(next.getId(), true);
                                next.clearVisited();
                            }
                        } else {
                           // System.out
                           //         .println("Completely surrounded by walls, exiting!");
                            break;
                        }
                    }

                    //System.out.println("------------------------------------------");
                }
            }
            else{
                //System.out.println("Same or greater number of crossing as Answer already found: " + ans);
                //System.out.println("-------------------------------------------------------------");
            }
        }
        if (ans < Integer.MAX_VALUE) {
            return ans;
        }
        return -1;
    }

    private static String print(Cell next) {
        System.out.println("Next: " + next.getId() + " (Crossing="
                + next.crossing + ")");
        System.out.println("Path:");
        String path = next.printPath();
        if (path == null || path.length() == 0) {
            System.out.println("None");
        } else {
            System.out.println(path);
        }
        System.out.println();
        return path;
    }

    private static int getNumCrossings(Cell next) {
        return next.crossing;
    }

    private static List<Cell> findSuccessors(String[] map, Cell current) {
        int r = current.row;
        int c = current.column;
        int prevRow = r - 1, nextRow = r + 1;
        int prevCol = c - 1, nextCol = c + 1;
        List<Cell> list = new ArrayList<Cell>();
        if (withinBounds(map, prevRow, c)) {
            Cell newCell = getCell(map, prevRow, c);
            newCell.crossing = current.crossing;
            Cell freeCell = getFreeCell(map, current, newCell);
            if (freeCell != null) {
                list.add(freeCell);
            }
        }
        if (withinBounds(map, nextRow, c)) {
            Cell newCell = getCell(map, nextRow, c);
            newCell.crossing = current.crossing;
            Cell freeCell = getFreeCell(map, current, newCell);
            if (freeCell != null) {
                list.add(freeCell);
            }
        }
        if (withinBounds(map, r, prevCol)) {
            Cell newCell = getCell(map, r, prevCol);
            newCell.crossing = current.crossing;
            Cell freeCell = getFreeCell(map, current, newCell);
            if (freeCell != null) {
                list.add(freeCell);
            }
        }
        if (withinBounds(map, r, nextCol)) {
            Cell newCell = getCell(map, r, nextCol);
            newCell.crossing = current.crossing;
            Cell freeCell = getFreeCell(map, current, newCell);
            if (freeCell != null) {
                list.add(freeCell);
            }
        }
        return list;
    }

    private static Cell getFreeCell(String[] map, Cell prev, Cell newCell) {
        // F * | - S H .
        Cell freeCell = null;
        char v = newCell.value;
        if (v == 'F' || v == '*') {
            return null;
        }
        if (v == '|') {
            if (prev.row == newCell.row) {
                int direction = newCell.column - prev.column;
                int col = direction + newCell.column;
                if (!withinBounds(map, newCell.row, col)) {
                    return null;
                }
                Cell checkCell = getCell(map, newCell.row, col);
                checkCell.crossing = newCell.crossing;
                Cell nextFreeCell = getFreeCell(map, newCell, checkCell);
                if (nextFreeCell != null) {
                    if(prev.value!=v){
                        nextFreeCell.crossing++;
                    }
                }
                return nextFreeCell;

            } else {
                return null;
            }
        }
        if (v == '-') {
            if (prev.column == newCell.column) {
                int direction = newCell.row - prev.row;
                int row = direction + newCell.row;
                if (!withinBounds(map, row, newCell.column)) {
                    return null;
                }
                Cell checkCell = getCell(map, row, newCell.column);
                checkCell.crossing = newCell.crossing;
                Cell nextFreeCell = getFreeCell(map, newCell, checkCell);
                if (nextFreeCell != null) {
                    if(prev.value!=v){
                        nextFreeCell.crossing++;
                    }
                }
                return nextFreeCell;

            } else {
                return null;
            }
        }
        freeCell = newCell;
        return freeCell;
    }

    private static Cell getCell(String[] map, int row, int column) {
        return new Cell(row, column, map[row].charAt(column));
    }

    private static boolean withinBounds(String[] map, int row, int column) {
        return row >= 0 && row < map.length && column >= 0
                && column < map[0].length();
    }

    private static boolean checkFound(Cell current, Cell target) {
        return current.value == target.value;
    }

    private static void processMap(String[] map) {
        start = null;
        end = null;
        for (int i = 0; i < map.length; i++) {
            String row = map[i];
            for (int j = 0; j < map[0].length(); j++) {
                char c = row.charAt(j);
                if (c == 'S' || c == 's') {
                    start = new Cell(i, j, c);
                } else if (c == 'H' || c == 'h') {
                    end = new Cell(i, j, c);
                }

                if (start != null && end != null) {
                    break;
                }
            }
        }
    }

    private static String print(String[] input) {
        String result = "\n  ";
        int col = 0;
        for (; col < input[0].length(); col++) {
            result += col % 10;
        }
        result += "\n";
        int count = 0;
        for (String s : input) {
            result += (count % 10) + ":" + s + "\n";
            count++;
        }
        return result;
    }

}
