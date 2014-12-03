package topcoder.srm231.Mixture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        test = test(test, new int[] { 1, 2, 3 }, new String[] { "1 0 0 1",
                "0 1 0 2", "0 0 1 3" }, 14.0);
        // Here, there are three chemicals, and each one is available in only
        // its pure form. Given the prices of the three chemicals and the
        // desired quantities, the total cost is 1*1+2*2+3*3=14.
        /*
         * 1)
         * 
         * {1,2,3} {"1 0 0 1","0 1 0 2","0 0 1 3","2 2 2 4"} Returns: 10.0 Here,
         * it is cheaper if we buy some of the mixture of all three chemicals.
         * 2)
         * 
         * {1,1,1,1,1,1,1,1,1,1} {"10 9 9 9 9 9 9 9 9 10 0",
         * "0 10 9 9 9 9 9 9 9 0 0", "0 0 10 9 9 9 9 9 9 0 0",
         * "0 0 0 10 9 9 9 9 9 0 0", "0 0 0 0 10 9 9 9 9 0 0",
         * "0 0 0 0 0 10 9 9 9 0 0", "0 0 0 0 0 0 10 9 9 0 0",
         * "0 0 0 0 0 0 0 10 9 0 0", "0 0 0 0 0 0 0 0 10 1 0",
         * "0 0 0 0 0 0 0 0 0 10 1"} Returns: -1.0 This mixture is impossible.
         * It can almost be achieved, but the closest you can get is to have the
         * right amount of the first 9 chemicals, but just a little bit too much
         * of the last one. 3)
         * 
         * {7,7,8,10} {"9 0 4 8 4", "8 8 9 0 1", "0 10 3 10 7", "10 2 2 0 1",
         * "8 9 10 2 6", "1 2 5 8 8", "4 7 8 9 6", "2 10 6 8 10", "6 3 9 7 1",
         * "3 6 9 9 1"} Returns: 3.5855425945563804 The following table shows
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
        Map<Integer, List<Item>> hash = new HashMap<Integer, List<Item>>();
        int rowNum = 0;
        for (String row : availableMixtures) {
            String[] tokens = row.split("\\s");
            double price = Double.parseDouble(tokens[tokens.length - 1]);
            for (int i = 0; i < tokens.length - 1; i++) {
                double q = Double.parseDouble(tokens[i]);
                if (q > 0) {
                    Item item = new Item(i, q, price, rowNum);
                    if (hash.containsKey(i)) {
                        List<Item> list = hash.get(i);
                        list.add(item);
                    } else {
                        List<Item> list = new ArrayList<Item>();
                        hash.put(i, list);
                        list.add(item);
                    }
                }
            }
            rowNum++;
        }
        
        int[] amountNeeded = mixture;
        int[] itemQuantitiesBought = new int[mixture.length];
        double[] itemCost = new double[mixture.length];
        List<Integer> rareItems = new ArrayList<Integer>();
        for (int key : hash.keySet()) {
            Collections.sort(hash.get(key));
            if(hash.get(key).size()==1){
                rareItems.add(key);
                double cost = mixture[key]*hash.get(key).get(0).pricePerUnit;
                itemCost[key] = cost;
                amountNeeded[key] = 0;
                itemQuantitiesBought[key] = mixture[key];
                amountNeeded[key]-=mixture[key];
            }
        }

        
        
        //Strategy: DP - for cost 0.....N? calculate how much of each quantity i can get
        //Strategy: Greedy - Go for the cheapest price per quanity and hope it comes to be the lowest cost
        //Strategy: for each available row, find out the cost of getting what you need only using that rowj
        //Strategy: Find the row that gives you the most total items for that price
        //Strategy: Make a list of rare items, get those first,
        //           then go for cheapest alternatives price per quantity
        //            What if none of your items are rare (offered by 1 row only?) hash[i].size==1;
        
        for()

    }

    private static String printStr(String[] input) {
        String result = "\n[";
        boolean first = true;
        for (String v : input) {
            if (first) {
                first = false;
            } else {
                result += "\n";
            }
            result += v;
        }
        result += "]\n";
        return result;
    }

    private static String printInts(int[] input) {
        String result = "\n[";
        for (int v : input) {
            result += v + " ";
        }
        result += "]\n";
        return result;
    }

}
