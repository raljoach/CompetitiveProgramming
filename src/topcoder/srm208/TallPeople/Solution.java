package topcoder.srm208.TallPeople;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        //0)  
        int test = 0;
        test = test(test,new String[]{"9 2 3", "4 8 7"}, new int[]{ 4,  7 });
        /*
        The heights 2 and 4 are the shortest from the rows, so 4 is the taller of the two. 
        The heights 9, 8, and 7 are the tallest from the columns, so 7 is the shortest of the 3.
        */
        
        //1)                
        test = test(test,new String[]{"1 2","4 5","3 6"}, new int[]{ 4,  4 });
        
        //2)                 
        test = test(test,new String[]{"1 1","1 1"},new int[]{ 1,  1 });
    }

    private static int test(int test, String[] input, int[] expected) {
        System.out.println("Test" + (test++));
        System.out.println("input: " + print(input));
        System.out.println("Expected:" + printInts(expected));
        int[] actual = solve(input);        
        System.out.println("Actual:" + printInts(actual));
        if ((expected == null && actual == null) || areEqual(expected,actual)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }
    
    private static int[] solve(String[] input) {
        int[] ans = new int[2];
        int tallest = Integer.MIN_VALUE;
        List<Integer> columns = new ArrayList<Integer>();
        int i=0;
        for(String row:input){
            String[] tokens = row.split("\\s");
            int smallest = Integer.MAX_VALUE;
            int j=0;
            for(String height:tokens){
                int h = Integer.parseInt(height);
                if(i==0){
                    columns.add(h);
                }
                else{
                   if(columns.get(j)<h){
                       columns.set(j,h);
                   }
                }
                if(h<smallest){
                    smallest = h;
                }
                ++j;
            }
            
            if(tallest<smallest){
                tallest=smallest;
            }
            ++i;
        }
        ans[0] = tallest;
        int smallest = Integer.MAX_VALUE;
        for(int v:columns){
            if(v<smallest){
                smallest = v;
            }
        }
        ans[1]=smallest;
        return ans;
    }

    private static boolean areEqual(int[] expected, int[] actual) {
        if (expected.length != actual.length) {
            System.out
                    .println("Expected and Actual are not the same size or length");
            return false;
        }

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) {
                System.out.println("Expected and Actual don't match at index: "
                        + i + " expected: " + expected[i] + " actual: "
                        + actual[i]);
                return false;
            }
        }

        return true;
    }

    private static String print(String[] input) {
        String result = "\n";
        for (String v : input) {
            result += v + "\n";
        }
        return result;
    }
    
    private static String printInts(int[] input) {
        String result = "";
        for (int v : input) {
            result += v + " ";
        }
        return result;
    }

}
