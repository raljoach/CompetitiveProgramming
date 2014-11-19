package topcoder.srm232.WordFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        test = test(test, new String[] { "TEST", "GOAT", "BOAT" },
                new String[] { "GOAT", "BOAT", "TEST" }, new String[] { "1 0",
                        "2 0", "0 0" });
        // These words are pretty easy to find.

        /*
         * 1)
         * 
         * {"SXXX", "XQXM", "XXLA", "XXXR"} {"SQL", "RAM"} Returns: { "0 0", ""
         * } While "RAM" may be found going up at "3 3", we are only allowing
         * words that go down and right. 2)
         * 
         * {"EASYTOFINDEAGSRVHOTCJYG", "FLVENKDHCESOXXXXFAGJKEO",
         * "YHEDYNAIRQGIZECGXQLKDBI", "DEIJFKABAQSIHSNDLOMYJIN",
         * "CKXINIMMNGRNSNRGIWQLWOG", "VOFQDROQGCWDKOUYRAFUCDO",
         * "PFLXWTYKOITSURQJGEGSPGG"} {"EASYTOFIND", "DIAG", "GOING",
         * "THISISTOOLONGTOFITINTHISPUZZLE"} Returns: { "0 0", "1 6", "0 22", ""
         * }
         */

    }

    private static int test(int test, String[] grid, String[] wordList,
            String[] expected) {
        System.out.println("Test" + (test++));
        System.out.println("grid: " + print(grid));
        System.out.println("wordList: " + print(wordList));
        System.out.println("Expected: " + print(expected));
        List<String> actual = solve(grid, wordList);
        System.out.println("Actual: \n" + actual);
        if (areEqual(expected, actual)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static List<String> solve(String[] grid, String[] wordList) {
        // Arrays.sort(wordList);
        List<String> result = new ArrayList<String>();
        int remWords = wordList.length;
        for (int row = 0; row < grid.length && remWords > 0; row++) {
            for (int col = 0; col < grid[0].length() && remWords > 0; col++) {
                // char firstLetter = grid[row].charAt(col);

                // while (row < grid.length && col < grid[0].length() &&
                // remWords>0) {
                int rowStart = row;
                int colStart = col;
                
                for (int k = 0; k < wordList.length && remWords > 0; k++) {
                    String word = wordList[k];
                    
                    if (word != null) {
                        boolean found = true;
                        for (int i = 0; i < word.length(); i++) {
                            char w = word.charAt(i);
                            boolean match1 = false;
                            boolean match2 = false;
                            if ((colStart + i) < grid[0].length()) {
                                char letter1 = grid[rowStart].charAt(colStart
                                        + i);
                                match1 = letter1 == w;

                                if ((rowStart + i) < grid.length) {
                                    char letter2 = grid[rowStart + i]
                                            .charAt(colStart + i);
                                    match2 = letter2 == w;
                                }
                            }

                            boolean match3 = false;
                            if ((rowStart + i) < grid.length) {
                                char letter3 = grid[rowStart + i]
                                        .charAt(colStart);

                                if (!match1 && !match2 && !match3) {
                                    found = false;
                                    break;
                                }
                            }                            
                        }
                        
                        if (found) {
                            result.add(rowStart + " " + colStart);
                            wordList[k] = null;
                            --remWords;
                        }
                    }
                }
                // }
            }
        }
        return result;
    }

    private static boolean areEqual(String[] expected, List<String> actual) {
        if (expected == null) {
            return actual == null;
        }
        if (actual == null) {
            return false;
        }
        if (actual.size() != expected.length) {
            System.out
                    .println("Expected length (" + expected.length
                            + ") and Actual length (" + actual.size()
                            + ") don't match");
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
            if (!found) {
                System.out.println("Error: Did not find " + e);
                return false;
            }
        }

        return true;
    }

    private static String print(String[] expected) {
        String result = "\n";
        for (String r : expected) {
            result += r + "\n";
        }
        return result;
    }

}
