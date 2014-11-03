//-----------------------------------------------------------------------
// <copyright file="Solution.cs" company="MyCompany">
//     Copyright (c) MyCompany. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------
package topcoder.srm637.GreaterGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {

        // 0)
        /*
         * Obviously, Snuke can determine the numbers on Sothe's cards, but he
         * does not necessarily know the order in which Sothe is going to play
         * his cards. You are given the information Snuke has about Sothe in a
         * int[] sothe. For each i, element i of sothe is either the number on
         * the card Sothe will play in turn i (0-based index), or -1 if Snuke
         * does not know the card Sothe will play in that turn.
         */

        /*
         * Snuke wants to play according to a strategy that maximizes the
         * expected number of points he'll get. Find that strategy and return
         * the corresponding expected number of Snuke's points at the end of the
         * game.
         */

        int test = 0;
        // test = test(test, new int[] { 4, 2 }, new int[] { -1, -1 }, 1.5);

        /*
         * Sothe will play the cards 1 and 3 in some unknown order. The best
         * strategy for Snuke is to flip a coin and to play his cards either in
         * the order {2,4} or in the order {4,2} with equal probability. This
         * leads to two equally likely results of the game: Snuke plays his 2
         * against Sothe's 1, and his 4 against Sothe's 3. In this case, Snuke
         * wins both turns and thus scores 2 points. Snuke plays his 2 against
         * Sothe's 3, and his 4 against Sothe's 1. In this case, Snuke scores 1
         * point. Therefore, the expected number of Snuke's points is 1.5.
         */
        //===========================================================
        // 1)
        //test = test(test, new int[] { 4, 2 }, new int[] { 1, 3 }, 2.0);

        // If Snuke plays card 2 first and card 4 next, he is guaranteed to
        // score 2 points.
 
        //===========================================================
        // 2)
        //test = test(test, new int[] { 2 }, new int[] { -1 }, 1.0);
        // Sothe's only card has to be 1, and thus Snuke is guaranteed to win
        // the only turn of this game.

        //===========================================================
        // 3)
        test = test(test, new int[] { 1, 3, 5, 7 }, new int[] { 8, -1, 4, -1
         },
         2.5);

        // 4)
        // test = test(test, new int[] { 6, 12, 17, 14, 20, 8, 16, 7, 2, 15 },
        // new int[] { -1, -1, 4, -1, 11, 3, 13, -1, -1, 18 }, 8.0);
    }

    private static int test(int test, int[] snuke, int[] sothe, double expected) {
        System.out.println("Test" + (test++));
        System.out.println("snuke: " + print(snuke));
        System.out.println("sothe: " + print(sothe));
        System.out.println("Expected:" + expected);
        double actual = solve(snuke, sothe);

        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static double solve(int[] snuke, int[] sothe) {
        int n = snuke.length;
        int max = 2 * n;

        Map<Integer, Boolean> snukeHash = new HashMap<Integer, Boolean>();
        Map<Integer, Boolean> sotheHash = new HashMap<Integer, Boolean>();
        // List<Integer> dontKnow = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            snukeHash.put(snuke[i], true);
            if (sothe[i] != -1) {
                sotheHash.put(sothe[i], true);
            }
            // else{
            // dontKnow.add(i);
            // }
        }

        List<Integer> sotheOtherCards = new ArrayList<Integer>();
        for (int j = 1; j <= max; j++) {
            if (!sotheHash.containsKey(j) && !snukeHash.containsKey(j)) {
                sotheOtherCards.add(j);
                // if (sotheOtherCards.size() == 0) {
                // sotheOtherCards.add(j);
                // } else {
                // int z = sotheOtherCards.size();
                // int insertAt = z;
                // for (int y = 0; y < z; y++) {
                // if (j < sotheOtherCards.get(y)) {
                // insertAt = y;
                // }
                // }
                // sotheOtherCards.add(insertAt,j;
                // }
            }
        }

        double total = 0;
        double ways = 0;
        int bestScore = Integer.MIN_VALUE;
        // do {

        List<Integer> sotheCopy = new ArrayList<Integer>();
        int m = 0;
        for (int turn = 0; turn < sothe.length; turn++) {
            int plays = sothe[turn];
            if (plays != -1) {
                sotheCopy.add(sothe[turn]);
            } else {
                sotheCopy.add(sotheOtherCards.get(m++));
            }
        }

        List<Integer> snukeCopy = new ArrayList<Integer>();
        for (int p = 0; p < snuke.length; p++) {
            // snukeCopy.add(snuke[p]);

            int val = snuke[p];
            if (snukeCopy.size() == 0) {
                snukeCopy.add(val);
            } else {
                int z = snukeCopy.size();
                int insertAt = z;
                for (int y = 0; y < z; y++) {
                    if (val < snukeCopy.get(y)) {
                        insertAt = y;
                    }
                }
                snukeCopy.add(insertAt, val);
            }
        }
        do {

            int score = 0;            
            for (int t = 0; t < n; t++) {
                if (snukeCopy.get(t) > sotheCopy.get(t)) {
                    ++score;
                }
            }

            if (sotheOtherCards.size() > 0) {
                total += score;
                ++ways;
            } else {
                if(score>bestScore){
                    bestScore = score;
                }
            }

        } while (next_permutation(snukeCopy, max));

        // } while (next_permutation(sotheOtherCards, max));
        if (sotheOtherCards.size() > 0) {
            return total / ways;
        } else {
            return bestScore;
        }
    }

    private static boolean next_permutation(List<Integer> input, int max) {
        int n = input.size();

        //if (input.get(0) == max) {
            boolean lastPerm = true;
            int prev = input.get(0);
            for (int j = 1; j < n; j++) {
                int v = input.get(j);
                if (v > prev) {
                    lastPerm = false;
                    break;
                }
                prev = v;
            }

            if (lastPerm) {
                return false;
            }
        //}

        Map<Integer, Integer> fixed = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            int v = input.get(i);
            addToFixed(fixed, v);
        }

        List<Integer> remaining = new ArrayList<Integer>();

        for (int current = n - 1; current >= 0; --current) {
            int currentVal = input.get(current);
            if (current == n - 1) {
                if (fixed.containsKey(currentVal)) {
                    addToRemaining(fixed, remaining, currentVal);
                }
            } else if (canIncrement(max, current, fixed, input, remaining)) {
                break;
            } else {
                if (fixed.containsKey(currentVal)) {
                    addToRemaining(fixed, remaining, currentVal);
                }
            }
        }

        return true;
    }

    private static void addToFixed(Map<Integer, Integer> fixed, int v) {
        if (fixed.containsKey(v)) {
            fixed.put(v, fixed.get(v) + 1);
        } else {
            fixed.put(v, 1);
        }
    }

    private static void addToRemaining(Map<Integer, Integer> fixed,
            List<Integer> remaining, int currentVal) {
        if (fixed.containsKey(currentVal)) {
            int count = fixed.get(currentVal);
            --count;
            if (count == 0) {
                fixed.remove(currentVal);
            } else {
                fixed.put(currentVal, count);
            }
        }

        int n = remaining.size();
        if (n == 0) {
            remaining.add(currentVal);
        } else {
            int insertAt = n;
            for (int i = 0; i < remaining.size(); i++) {
                int thisVal = remaining.get(i);
                if (currentVal < thisVal) {
                    insertAt = i;
                    break;
                }
            }
            remaining.add(insertAt, currentVal);
        }

    }

    private static boolean canIncrement(int max, int current,
            Map<Integer, Integer> fixed, List<Integer> input,
            List<Integer> remaining) {
        int currentVal = input.get(current);
        if (currentVal == max || current == input.size() - 1) {
            return false;
        }

        if (remaining.size() == 0) {
            return false;
        }

        boolean found = false;
        for (int k = 0; k < remaining.size(); k++) {
            int newVal = remaining.get(k);
            if (currentVal < newVal) {
                found = true;
                remaining.remove(k);
                addToRemaining(fixed, remaining, currentVal);
                addToFixed(fixed, newVal);
                input.set(current, newVal);
                break;
            }
        }

        if (!found) {
            return false;
        }

        for (int index = current + 1; index < input.size(); index++) {
            int otherVal = remaining.get(0);
            remaining.remove(0);
            addToFixed(fixed, otherVal);
            input.set(index, otherVal);
        }
        return true;
    }

    private static String print(int[] elems) {
        String result = "";
        for (int v : elems) {
            result += v + " ";
        }
        return result;
    }

}
