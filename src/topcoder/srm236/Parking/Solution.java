package topcoder.srm236.Parking;

public class Solution {

    public static void main(String[] args) {
        //0)  
        int test=0;
        test = test(test,new String[]{"C.....P",
         "C.....P",
         "C.....P"},
        6);
        //Every car just drives to the opposite parking spot.
        /*1)  
                
        {"C.X.....",
         "..X..X..",
         "..X..X..",
         ".....X.P"}
        Returns: 16
        The slalom takes the car 16 units of time.
        2)  
                
        {"XXXXXXXXXXX",
         "X......XPPX",
         "XC...P.XPPX",
         "X......X..X",
         "X....C....X",
         "XXXXXXXXXXX"}
        Returns: 5
        This would take 11 instead of 5 units of time if the car on the bottom drove to its nearest parking spot.
        3)  
                
        {".C.",
         "...",
         "C.C",
         "X.X",
         "PPP"}
        Returns: 4
        While driving, the cars can be on the same empty spot or parking spot, but they have to finish on different parking spots.
        4)  
                
        {"CCCCC",
         ".....",
         "PXPXP"}
        Returns: -1
        There are not enough parking spots for all the cars.
        5)  
                
        {"..X..",
         "C.X.P",
         "..X.."}
        Returns: -1
        The car can't reach the parking spot.
*/
    }

    private static int test(int test, String[] park, int expected) {
        // TODO Auto-generated method stub
        return 0;
    }

}
