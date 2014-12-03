package topcoder.srm234.WeirdRooks;

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
        test = test(test, new int[] { 3, 3, 3 },
                "0,9 1,4 1,5 1,6 1,7 1,8 2,1 2,2 2,3 2,4 2,5 2,6 3,0 3,1 3,2 3,3");
        /*
         * If no rooks are placed on the board, all 9 squares are special. The
         * following diagram depicts the scenario where 3 rooks are placed, and
         * no squares are special.
         * 
         * R.. .R. ..R 1)
         * 
         * {1,2,3} Returns: "0,6 1,3 1,4 1,5 2,1 2,2 2,3 3,0" The case with 2
         * rooks and 3 special squares is depicted below. R .R ... 2)
         * 
         * {1} Returns: "0,1 1,0" 3)
         * 
         * {2,9} Returns:
         * "0,11 1,2 1,3 1,4 1,5 1,6 1,7 1,8 1,9 1,10 2,0 2,1 2,2 2,3 2,4 2,5 2,6 2,7 2,8"
         */

    }

    private static int test(int test, int[] cols, String expected) {
        System.out.println("Test" + (test++));
        System.out.println("cols: " + print(cols));
        System.out.println("Expected: " + expected);
        String actual = solve(cols);
        System.out.println("Actual: " + actual);
        boolean nullCheck = expected == null && actual == null;
        if (nullCheck || (expected != null && expected.equals(actual))) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static String solve(int[] cols) {
        // cols => What does this tell us
        // => for row i => there are j columns
        int[] columnOfRook = new int[cols.length];
        for (int x = 0; x < columnOfRook.length; x++) {
            columnOfRook[x] = -1;
        }
        int i = 0;
        // for(int i=0; i<cols.length; i++)
        // {
        Map<Integer, List<Integer>> table = new HashMap<Integer, List<Integer>>();
        // if(i==cols.length){
        addToTable(table, countSpecial(cols, columnOfRook));
        // }
        for (; i < cols.length; i++) {
            placeRook(i, cols, columnOfRook, table);
        }
        // }
        String result = "";
        Integer[] keys = new Integer[table.keySet().size()];
        table.keySet().toArray(keys);
        Arrays.sort(keys);
        for (int key : keys) {
            List<Integer> values = table.get(key);
            Collections.sort(values);
            for (int numSpecial : values) {
                String entry = key + "," + numSpecial;
                if (result != "") {
                    result += " ";
                }
                result += entry;
            }
        }
        return result;
    }

    private static void addToTable(Map<Integer, List<Integer>> table,
            String countSpecial) {
        String[] tokens = countSpecial.split(",");
        int numRooks = Integer.parseInt(tokens[0]);
        int numSpecial = Integer.parseInt(tokens[1]);
        if (table.containsKey(numRooks)) {
            List<Integer> list = table.get(numRooks);
            if (!list.contains(numSpecial)) {
                list.add(numSpecial);
            }
        } else {
            List<Integer> list = new ArrayList<Integer>();
            list.add(numSpecial);
            table.put(numRooks, list);
        }
    }

    private static void placeRook(int i, int[] cols, int[] columnOfRook,
            Map<Integer, List<Integer>> table) {
        if (i != cols.length) {
            int numCols = cols[i];
            // Now we know we have numCols columns => What does this tell us?
            // => for col j => can place a rook?
            // => store where a rook is in each column
            for (int j = 0; j < numCols; j++) {
                if (canPlace(columnOfRook, i, j)) {
                    columnOfRook[i] = j;
                    addToTable(table, countSpecial(cols, columnOfRook));
                    placeRook(i + 1, cols, columnOfRook, table);
                    columnOfRook[i] = -1;
                }
            }
        }
    }

    private static boolean canPlace(int[] columnOfRook, int row, int col) {
        for (int i = 0; i < columnOfRook.length; i++) {
            int j = columnOfRook[i];
            if (j != -1) {
                if (i == row || j == col) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String countSpecial(int[] cols, int[] columnOfRook) {
       // printRooks(cols, columnOfRook);
        int special = 0;
        int numRooks = 0;
        int numRows = cols.length;
        for (int row = 0; row < numRows; row++) {
            int rookAt = columnOfRook[row];
            int numCols = cols[row];
            for (int col = 0; col < numCols; col++) {
                boolean unoccupied = rookAt != col;
                boolean noRookInRow = rookAt == -1;
                if (unoccupied) {
                    if (noRookInRow || rookAt < col) {
                        boolean noRookInColumn = true;
                        // noRookInColumn => What does this mean?
                        // => We check each row and see if there is rook in the
                        // same column
                        for (int thisRow = row + 1; thisRow < numRows; thisRow++) {
                            if (col == columnOfRook[thisRow]) {
                                noRookInColumn = false;
                                break;
                            }
                        }
                        if (noRookInColumn) {
                            ++special;
                        }
                    }
                } else {
                    ++numRooks;
                }
            }
        }

        String r = numRooks + "," + special;
        //System.out.println(r);
        //System.out.println();
        return r;
    }

    private static int config = 0;

    private static void printRooks(int[] cols, int[] columnOfRook) {
        System.out.println("Configuration #" + (++config));
        for (int i = 0; i < cols.length; i++) {
            int numCols = cols[i];
            for (int j = 0; j < numCols; j++) {
                if (columnOfRook[i] == j) {
                    System.out.print("R");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

    }

    private static String print(int[] input) {
        String result = "\n[";
        for (int v : input) {
            result += v + " ";
        }
        result += "]\n";
        return result;
    }

}
