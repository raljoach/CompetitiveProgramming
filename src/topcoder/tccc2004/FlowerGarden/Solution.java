package topcoder.tccc2004.FlowerGarden;

public class Solution {

    public static void main(String[] args) {
        //0)  
        int test=0;
        test = test(test,
        new int[]{5,4,3,2,1},
        new int[]{1,1,1,1,1},
        new int[]{365,365,365,365,365},
        new int[]{ 1,  2,  3,  4,  5 });
        //These flowers all bloom on January 1st and wilt on December 31st. Since they all may block each other, you must order them from shortest to tallest.
        /*1)  
                
        {5,4,3,2,1}
        {1,5,10,15,20}
        {4,9,14,19,24}
        Returns: { 5,  4,  3,  2,  1 }
        The same set of flowers now bloom all at separate times. Since they will never block each other, you can order them from tallest to shortest to get the tallest ones as far forward as possible.
        2)  
                
        {5,4,3,2,1}
        {1,5,10,15,20}
        {5,10,15,20,25}
        Returns: { 1,  2,  3,  4,  5 }
        Although each flower only blocks at most one other, they all must be ordered from shortest to tallest to prevent any blocking from occurring.
        3)  
                
        {5,4,3,2,1}
        {1,5,10,15,20}
        {5,10,14,20,25}
        Returns: { 3,  4,  5,  1,  2 }
        The difference here is that the third type of flower wilts one day earlier than the blooming of the fourth flower. Therefore, we can put the flowers of height 3 first, then the flowers of height 4, then height 5, and finally the flowers of height 1 and 2. Note that we could have also ordered them with height 1 first, but this does not result in the maximum possible height being first in the garden.
        4)  
                
        {1,2,3,4,5,6}
        {1,3,1,3,1,3}
        {2,4,2,4,2,4}
        Returns: { 2,  4,  6,  1,  3,  5 }
        5)  
                
        {3,2,5,4}
        {1,2,11,10}
        {4,3,12,13}
        Returns: { 4,  5,  2,  3 }
*/

    }

    private static int test(int test, int[] height, int[] bloom, int[] wilt, int[] expected) {
        // TODO Auto-generated method stub
        return 0;
    }

}
