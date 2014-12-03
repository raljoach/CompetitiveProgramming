package topcoder.srm148.MNS;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;

        test = test(test, new int[] { 1, 2, 3, 3, 2, 1, 2, 2, 2 }, 18);
        /*
         * 1)
         * 
         * {4,4,4,4,4,4,4,4,4} Returns: 1 2)
         * 
         * {1,5,1,2,5,6,2,3,2} Returns: 36 3)
         * 
         * {1,2,6,6,6,4,2,6,4} Returns: 0
         */

    }

    private static int test(int test, int[] numbers, int expected) {
        System.out.println("Test" + (test++));
        System.out.println("times: " + print(numbers));
        System.out.println("Expected: " + expected);
        int actual = solve(numbers);
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static String print(int[] input) {
        String result = "\n[";
        for(int v:input){
            result+= v + " ";
        }
        result+="]\n";
        return result;
    }

    //Backtracking solution requires recursion to generate all permutations of numbers
    //For each permutation, 
    //   populate square, row-wise
    //   then check if it's a magic number square
    //   clear square and start all over and try new permutation
    private static int solve(int[] numbers) {
        int[][] square = new int[3][3];
        int ways = 0;
        
        Map<String,Boolean> hash = new HashMap<String,Boolean>();
        List<List<Integer>> perms = getDistinctPermutations(hash,numbers,0);
        for(List<Integer> perm:perms){
            fillSquare(perm, square);
            if(isMagicNumber(square)){
                ++ways;
            }
        }
        return ways;
    }

    private static List<List<Integer>> getDistinctPermutations(Map<String,Boolean> hash, int[] numbers, int index) {
        
        
        if(index==numbers.length){
            List<Integer> list = new ArrayList<Integer>();
            return Arrays.asList(list);
        }
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int item = numbers[index];
        List<List<Integer>> subPerms = getDistinctPermutations(hash,numbers,index+1);
        for(List<Integer> thisPerm:subPerms){
            
            for(int i=0; i<=thisPerm.size(); i++)
            {
                List<Integer> newPerm = new ArrayList<Integer>();
                newPerm.addAll(thisPerm);
                newPerm.add(i,item);
                String permStr = newPerm.toString();
                if(hash.containsKey(permStr)){
                    continue;
                }
                else{
                    hash.put(permStr, true);
                }
                result.add(newPerm);
            }
        }
        
        return result;
    }

    private static void fillSquare(List<Integer> perm, int[][] square) {
        int pos=0;
        for(int i=0; i<square.length; i++)
        {
            for(int j=0; j<square[0].length; j++)
            {
                square[i][j] = perm.get(pos++);
            }
        }
    }

    private static boolean isMagicNumber(int[][] square) {
        int expectedSum=-1;
        for (int row = 0; row < square.length; row++) {
            int sum = 0;
            for (int col = 0; col < square[0].length; col++) {
                sum += square[row][col];
            }

            if (row == 0) {
                expectedSum = sum;
            } else {
                if (expectedSum != sum) {
                    return false;
                }
            }
        }
        for (int col = 0; col < square[0].length; col++) {
            int sum = 0;
            for (int row = 0; row < square.length; row++) {
                sum += square[row][col];
            }

            if (expectedSum != sum) {
                return false;
            }        
        }
        
        return true;
    }

}
