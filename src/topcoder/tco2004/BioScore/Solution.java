package topcoder.tco2004.BioScore;

public class Solution {

    public static void main(String[] args) {
        int test=0;
        //0)  
        
        test = test(test,new String[]{"AAA","AAA","AAC"},30.0);
        //Make score(A,A) and score(A,C) be 10. It is then easy to satisfy the remaining requirements. All three pairwise comparisons score 30.0.
        //1)  
                
        /*test = test(test,new String[]{"ACTGACTGACTG","GACTTGACCTGA"},
        -4.0);*/
        
        //Here, there are no positions in which the letters in the two strings match each other. So we should give each exact match (each diagonal entry of the score matrix) the smallest possible score, 1. The remaining 12 pairs are each present at one position in the comparison so the best we can do is to choose those 12 scores so they add up to -4 in any manner. Now the sum of all the matrix entries is 0, and the resulting score for the one pairwise comparison is -4.
        //2)  
                
        /*test = test(test,new String[]{"ACTAGAGAC","AAAAAAAAA","TAGTCATAC","GCAGCATTC"},
        50.5);*/

    }

    private static int test(int test, String[] strings, double d) {
        // TODO Auto-generated method stub
        return 0;
    }

}
