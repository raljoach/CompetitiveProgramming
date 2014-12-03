package topcoder.tccc2003.ZigZag;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        //test = test(test, new int[] { 1, 7, 4, 9, 2, 5 }, 6);
        // The entire sequence is a zig-zag sequence.
        
         //1)
        test = test(test, new int[] { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 },7); 
        /*There are several
         * subsequences that achieve this length. One is 1,17,10,13,10,16,8. 2)
         * 
         * { 44 } Returns: 1 3)
         * 
         * { 1, 2, 3, 4, 5, 6, 7, 8, 9 } Returns: 2 4)
         * 
         * { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000,
         * 32, 32 } Returns: 8 5)
         * 
         * { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 600, 947, 978, 46,
         * 100, 953, 670, 862, 568, 188, 67, 669, 810, 704, 52, 861, 49, 640,
         * 370, 908, 477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 249, 22,
         * 176, 279, 23, 22, 617, 462, 459, 244 } Returns: 36
         */

    }

    private static int test(int test, int[] sequence, int expected) {
        System.out.println("Test" + (test++));
        System.out.println("times: " + print(sequence));
        System.out.println("Expected: " + expected);
        int actual = solve(sequence);
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static int solve(int[] sequence) {
        // Index Longest Sequence Extends Direction
        // [1] 1 1 [1] 0
        // [2] 7 2 [2] +
        TableElement[] table = new TableElement[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            int val = sequence[i];
            if (i == 0) {
                TableElement row = new TableElement(i, val, 1, i, 0);
                table[i] = row;
            } else if (i == 1) {
                TableElement row = new TableElement(i, val, 2, i-1, sequence[i]
                        - sequence[0]);
                table[i] = row;
            } else {
                for (int j = i-1; j >= 0; j--) {
                    TableElement current = table[j];
                    if (current != null) {
                        int direction = sequence[i] - current.value;                        
                        if ((current.direction < 0 && direction >= 0)
                                || (current.direction >= 0 && direction < 0)) {
                            TableElement row = new TableElement(i, val,
                                    current.longestSequence + 1, j,
                                    direction);
                            if (table[i] == null) {
                                
                                table[i] = row;
                            }
                            else{
                                TableElement thisRow = table[i];
                                if(thisRow.longestSequence<row.longestSequence){
                                    table[i] = row;
                                }
                            }
                        }
                    }
                }
                if(table[i]==null){
                    TableElement row = new TableElement(i, val, 1, i, 0);
                    table[i] = row;
                }
            }

        }
        
        int max = Integer.MIN_VALUE;
        for(TableElement t:table){
            max = Math.max(max, t.longestSequence);
        }
        if(max==Integer.MIN_VALUE){
            return -1;
        }
        return max;
    }

    private static String print(int[] input) {
        String result = "\n[";
        for(int v:input){
            result+= v + " ";
        }
        result+="]\n";
        return result;
    }
}
