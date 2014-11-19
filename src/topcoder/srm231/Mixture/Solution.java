package topcoder.srm231.Mixture;

public class Solution {

    public static void main(String[] args) {
        //0)  
        int test=0;
        test = test(test, new int[]{1,2,3},
        new String[]{"1 0 0 1","0 1 0 2","0 0 1 3"},
        14.0);
        //Here, there are three chemicals, and each one is available in only its pure form. Given the prices of the three chemicals and the desired quantities, the total cost is 1*1+2*2+3*3=14.
        /*1)  
                
        {1,2,3}
        {"1 0 0 1","0 1 0 2","0 0 1 3","2 2 2 4"}
        Returns: 10.0
        Here, it is cheaper if we buy some of the mixture of all three chemicals.
        2)  
                
        {1,1,1,1,1,1,1,1,1,1}
        {"10 9 9 9 9 9 9 9 9 10 0",
         "0 10 9 9 9 9 9 9 9 0 0",
         "0 0 10 9 9 9 9 9 9 0 0",
         "0 0 0 10 9 9 9 9 9 0 0",
         "0 0 0 0 10 9 9 9 9 0 0",
         "0 0 0 0 0 10 9 9 9 0 0",
         "0 0 0 0 0 0 10 9 9 0 0",
         "0 0 0 0 0 0 0 10 9 0 0",
         "0 0 0 0 0 0 0 0 10 1 0",
         "0 0 0 0 0 0 0 0 0 10 1"}
        Returns: -1.0
        This mixture is impossible. It can almost be achieved, but the closest you can get is to have the right amount of the first 9 chemicals, but just a little bit too much of the last one.
        3)  
                
        {7,7,8,10}
        {"9 0 4 8 4",
         "8 8 9 0 1",
         "0 10 3 10 7",
         "10 2 2 0 1",
         "8 9 10 2 6",
         "1 2 5 8 8",
         "4 7 8 9 6",
         "2 10 6 8 10",
         "6 3 9 7 1",
         "3 6 9 9 1"}
        Returns: 3.5855425945563804
        The following table shows which mixtures to purchase to achieve the desired mixture as cheaply as possible:
         i | amount
        ---+--------
         0 | 202  / 943
         2 | 239  / 943
         3 | 595  / 1886
         9 | 1808 / 2829
         
         */

    }

    private static int test(int test, int[] mixture, String[] availableMixtures, double expected) {
        // TODO Auto-generated method stub
        return 0;
    }

}
