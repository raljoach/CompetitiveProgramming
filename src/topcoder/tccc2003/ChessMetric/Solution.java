package topcoder.tccc2003.ChessMetric;

public class Solution {

    public static void main(String[] args) {
        //0)  
        int test=0;
        test = test(test,3,
        new int[]{0,0},
        new int[]{1,0},
        1,
        1);
        //Only 1 way to get to an adjacent square in 1 move
/*        1)  
                
        3
        {0,0}
        {1,2}
        1
        Returns: 1
        A single L-shaped move is the only way
        2)  
                
        3
        {0,0}
        {2,2}
        1
        Returns: 0
        Too far for a single move
        3)  
                
        3
        {0,0}
        {0,0}
        2
        Returns: 5
        Must count all the ways of leaving and then returning to the corner
        4)  
                
        100
        {0,0}
        {0,99}
        50
        Returns: 243097320072600
*/

    }

    private static int test(int test, int size, int[] start, int[] end, int numMoves, int expected) {
        // TODO Auto-generated method stub
        return 0;
    }

}
