package topcoder.srm234.WeirdRooks;

public class Solution {

    public static void main(String[] args) {
        //0)  
        int test = 0;
        test = test(test, new int[]{3,3,3},
        "0,9 1,4 1,5 1,6 1,7 1,8 2,1 2,2 2,3 2,4 2,5 2,6 3,0 3,1 3,2 3,3");
        /*If no rooks are placed on the board, all 9 squares are special. The following diagram depicts the scenario where 3 rooks are placed, and no squares are special.
         
          R..
          .R.
          ..R 
        1)  
                
        {1,2,3}
        Returns: "0,6 1,3 1,4 1,5 2,1 2,2 2,3 3,0"
        The case with 2 rooks and 3 special squares is depicted below.
          R
          .R
          ...
        2)  
                
        {1}
        Returns: "0,1 1,0"
        3)  
                
        {2,9}
        Returns: "0,11 1,2 1,3 1,4 1,5 1,6 1,7 1,8 1,9 1,10 2,0 2,1 2,2 2,3 2,4 2,5 2,6 2,7 2,8"
*/

    }

    private static int test(int test, int[] cols, String expected) {
        // TODO Auto-generated method stub
        return 0;
    }

}
