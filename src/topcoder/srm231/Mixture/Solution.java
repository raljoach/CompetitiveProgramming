package topcoder.srm231.Mixture;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Jama.Matrix;

// Uses http://math.nist.gov/javanumerics/jama/
public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        /*test = test(test, new int[] { 1, 2, 3 }, new String[] { "1 0 0 1",
                "0 1 0 2", "0 0 1 3" }, 14.0);*/
        // Here, there are three chemicals, and each one is available in only
        // its pure form. Given the prices of the three chemicals and the
        // desired quantities, the total cost is 1*1+2*2+3*3=14.
        
         //1)
          
        //test = test(test, new int[] {1,2,3}, new String[] {"1 0 0 1","0 1 0 2","0 0 1 3","2 2 2 4"},10.0);
        
        /*Here,
          it is cheaper if we buy some of the mixture of all three chemicals.*/
         
        // 2)
          
/*        test = test(test, new int[] {1,1,1,1,1,1,1,1,1,1}, new String[] {"10 9 9 9 9 9 9 9 9 10 0",
         "0 10 9 9 9 9 9 9 9 0 0", "0 0 10 9 9 9 9 9 9 0 0",
         "0 0 0 10 9 9 9 9 9 0 0", "0 0 0 0 10 9 9 9 9 0 0",
         "0 0 0 0 0 10 9 9 9 0 0", "0 0 0 0 0 0 10 9 9 0 0",
         "0 0 0 0 0 0 0 10 9 0 0", "0 0 0 0 0 0 0 0 10 1 0",
         "0 0 0 0 0 0 0 0 0 10 1"},-1.0);*/
         /*This mixture is impossible.
         * It can almost be achieved, but the closest you can get is to have the
         * right amount of the first 9 chemicals, but just a little bit too much
         * of the last one.*/
         
         //3)
        test = test(test, new int[] {7,7,8,10}, new String[]{"9 0 4 8 4", "8 8 9 0 1", "0 10 3 10 7", "10 2 2 0 1",
          "8 9 10 2 6", "1 2 5 8 8", "4 7 8 9 6", "2 10 6 8 10", "6 3 9 7 1",
          "3 6 9 9 1"}, 3.5855425945563804);
        /*The following table shows
         * which mixtures to purchase to achieve the desired mixture as cheaply
         * as possible: i | amount ---+-------- 0 | 202 / 943 2 | 239 / 943 3 |
         * 595 / 1886 9 | 1808 / 2829
         */

    }

    private static int test(int test, int[] mixture,
            String[] availableMixtures, double expected) {
        System.out.println("Test" + (test++));
        System.out.println("mixtures: " + printInts(mixture));
        System.out.println("mixtures: " + printStr(availableMixtures));
        System.out.println("Expected: " + expected);
        double actual = solve(mixture, availableMixtures);
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static double solve(int[] mixture, String[] availableMixtures) {
        Vector<Double> target = createVector(mixture);
        List<Vector<Double>> availMixes = createVectors(availableMixtures);
        List<List<Vector<Double>>> subsets = getSubsets(availMixes,
                mixture.length);
        double cheapest = Double.MAX_VALUE;
        for (List<Vector<Double>> availSubSet : subsets) {
            Vector<Double> useAmount = matrixSolve(availSubSet, target);
            double totalCost = 0;
            double val = 0;
            for (int i = 0; i < useAmount.size(); i++) {
                val = useAmount.get(i);
                if (val < 0 || new Double(val).equals(Double.NaN)) {
                    val = -1;
                    break;
                } else {
                    totalCost += val;
                }
            }
            if(val<0){
                continue;
            }
            cheapest = Math.min(cheapest, totalCost);
        }
        if(cheapest == Double.MAX_VALUE){
            return -1;
        }
        else {
            return cheapest;
        }
    }

    private static Vector<Double> matrixSolve(List<Vector<Double>> availSubSet,
            Vector<Double> target) {
        // double[][] array = {{1.,2.,3},{4.,5.,6.},{7.,8.,10.}};
        double[][] array = 
                new double[availSubSet.get(0).size()][availSubSet.size()];
        for (int i = 0; i < availSubSet.get(0).size(); i++) {
            for (int j = 0; j < availSubSet.size(); j++) {
                array[i][j] = availSubSet.get(j).get(i);
            }
        }
        double[][] bArray = new double[target.size()][1];
        for(int i=0; i<bArray.length; i++){
            for(int j=0; j<bArray[0].length; j++){
                bArray[i][j] = target.get(i);
            }
        }
        Matrix A = new Matrix(array);
        Matrix b = new Matrix(bArray);
        Matrix x = A.solve(b);
        
        Vector<Double> ans = new Vector<Double>();
        for(int i=0; i<x.getRowDimension(); i++){
            for(int j=0; j<x.getColumnDimension(); j++){
                ans.add(x.get(i,j));
            }
        }
        return ans;
    }

    private static List<List<Vector<Double>>> getSubsets(
            List<Vector<Double>> mixtures, int subSetSize) {
        int n = mixtures.size();
        int numSubSets = (int) Math.pow(2, n);
        List<List<Vector<Double>>> result = new ArrayList<List<Vector<Double>>>();
        for (int i = 0; i < numSubSets; i++) {
            int k = i;
            int pos = 0;
            List<Vector<Double>> subSet = new ArrayList<Vector<Double>>();
            int numItems = 0;
            while (k > 0) {
                if ((k & 1) != 0) {
                    ++numItems;
                    subSet.add(mixtures.get(pos));
                }
                k >>= 1;
                ++pos;
            }

            if (numItems == subSetSize) {
                result.add(subSet);
            }
        }
        return result;
    }

    private static List<Vector<Double>> createVectors(String[] availableMixtures) {
        List<Vector<Double>> mixtures = new ArrayList<Vector<Double>>();
        for (int i = 0; i < availableMixtures.length; i++) {
            Vector<Double> v = new Vector<Double>();
            String someMixture = availableMixtures[i];
            String[] tokens = someMixture.split("\\s");
            double price = Double.parseDouble(tokens[tokens.length - 1]);
            for (int j = 0; j < tokens.length - 1; j++) {
                double q = Double.parseDouble(tokens[j]);
                double qP = q / price;
                v.add(qP);
            }
            mixtures.add(v);
        }
        return mixtures;
    }

    private static Vector<Double> createVector(int[] mixture) {
        Vector<Double> v = new Vector<Double>();
        for (int quantity : mixture) {
            v.add((double) quantity);
        }
        return v;
    }

    private static String printStr(String[] input) {
        String result = "\n[";
        boolean first = true;
        for (String v : input) {
            if (first) {
                first = false;
            } else {
                result += "\n ";
            }
            result += v;
        }
        result += "]\n";
        return result;
    }

    private static String printInts(int[] input) {
        String result = "\n[";
        boolean first = true;
        for (int v : input) {
            if(first){
                first = false;
            }
            else{
                result += " ";                
            }
            result += v;
        }
        result += "]\n";
        return result;
    }

}
