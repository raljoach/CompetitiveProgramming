//-----------------------------------------------------------------------
// <copyright file="Solution.cs" company="MyCompany">
//     Copyright (c) MyCompany. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------
package topcoder.srm636.Sortish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        int sortedness = 5;
        int[] seq = new int[] { 4, 0, 0, 2, 0 };
        int expected = 2;

        /*
         * There are six ways to fill in the missing elements. Out of those six
         * permutations, only two have sortedness 5: {4, 1, 5, 2, 3} and {4, 3,
         * 1, 2, 5}.
         */
        test = test(test, sortedness, seq, expected);

        // 1)
        sortedness = 4;
        seq = new int[] { 0, 0, 0, 0 };
        expected = 5;

        // All 5 possible ways are: {1, 3, 4, 2}, {1, 4, 2, 3}, {2, 1, 4, 3},
        // {2, 3, 1, 4}, {3, 1, 2, 4}.

        // 2)

        test = test(test, 2, new int[] { 1, 3, 2 }, 1);

        // There are no gaps and sortedness is indeed equal to 2.

        // 3)

        test = test(test, 3, new int[] { 0, 0, 2, 0, 0, 0 }, 4);

        // 4)

        test = test(test, 87, new int[] { 2, 0 }, 0);

        // The only permutation that matches seq is {2, 1}. However, the
        // sortedness of this sequence is 0, not 87.

        // 5)

        test = test(test, 30, new int[] { 0, 6, 3, 0, 0, 2, 10, 0, 0, 0 }, 34);

        // 6)

        test = test(test, 100, new int[] { 0, 13, 0, 0, 12, 0, 0, 0, 2, 0, 0,
                10, 5, 0, 0, 0, 0, 0, 0, 7, 15, 16, 20 }, 53447326);

    }

    private static int test(int test, int sortedness, int[] seq, int expected) {
        System.out.println("Test" + (++test));
        int actual = solve(sortedness, seq);
        System.out.println("sortedness: " + sortedness);
        System.out.println("seq: ");
        for (int val : seq) {
            System.out.print(val);
        }
        System.out.println();

        System.out.println("Expected:" + expected);
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static int solve(int sortedness, int[] seq) {
        MissingValues mv = getMissingValues(seq);
        List<List<Integer>> perms = getPerms(mv.values);
        int numValid = 0;
        for (List<Integer> set : perms) {
            int j = 0;
            for (int index : mv.indices) {
                int t = set.get(j);
                seq[index] = t;
                ++j;
            }

            int count = 0;
            int s = sortedness;
            for (int k = 0; k < seq.length; k++) {
                int first = seq[k];
                for (int m = k + 1; m < seq.length; m++) {
                    int second = seq[m];
                    if (second > first) {
                        ++count;
                    }
                }
            }

            if (count == sortedness) {
                numValid++;
            }
        }
        return numValid;
    }

    private static List<List<Integer>> getPerms(List<Integer> items) {
        return getPerms(items, 0);
    }

    private static List<List<Integer>> getPerms(List<Integer> items, int index) {
        if (index == items.size()) {
            ArrayList<Integer> empty = new ArrayList<Integer>();
            return Arrays.asList(empty);
        }
        int item = items.get(index);
        List<List<Integer>> subPerms = getPerms(items.subList(1, items.size()),
                0);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (List<Integer> set : subPerms) {
            for (int i = 0; i <= set.size(); i++) {
                List<Integer> newSet = new ArrayList<Integer>();
                newSet.addAll(set);
                newSet.add(i, item);
                result.add(newSet);
            }
        }
        return result;
    }

    private static MissingValues getMissingValues(int[] seq) {
        List<Integer> missing = new ArrayList<Integer>();
        Map<Integer, List<Integer>> hash = new HashMap<Integer, List<Integer>>();
        int index = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int i : seq) {

            if (i != 0) {
                hash.put(i, null);
            } else {
                list.add(index);
            }
            ++index;
        }

        for (int v = 1; v <= seq.length; v++) {
            boolean found = hash.containsKey(v);
            if (!found) {
                missing.add(v);
            }
        }
        MissingValues mv = new MissingValues();
        mv.indices = list;
        mv.values = missing;
        return mv;
    }
}
