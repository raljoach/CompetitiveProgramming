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

    public static void main(String[] args) throws Exception {

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
        test = test(test, new int[] { 4, 2 }, new int[] { -1, -1 }, 1.5);

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
        // ===========================================================
        // 1)
        test = test(test, new int[] { 4, 2 }, new int[] { 1, 3 }, 2.0);

        // If Snuke plays card 2 first and card 4 next, he is guaranteed to
        // score 2 points.

        // ===========================================================
        // 2)
        test = test(test, new int[] { 2 }, new int[] { -1 }, 1.0);
        // Sothe's only card has to be 1, and thus Snuke is guaranteed to win
        // the only turn of this game.

        // ===========================================================
        // 3)
        test = test(test, new int[] { 1, 3, 5, 7 }, new int[] { 8, -1, 4,
         -1},
         2.5);

        // 4)
        // test = test(test, new int[] { 6, 12, 17, 14, 20, 8, 16, 7, 2, 15 },
        // new int[] { -1, -1, 4, -1, 11, 3, 13, -1, -1, 18 }, 8.0);

        // Extra test cases:
        // NOTE: I haven't solved this on paper yet, expected answer 1.5 is not
        // correct
        test = test(test, new int[] { 1, 2, 4 }, new int[] { -1, -1, 6 },
        0.5);

        // NOTE: I haven't solved this on paper yet, expected answer 1.5 is not
        // correct
        test = test(test, new int[] { 1, 2, 4 }, new int[] { 5, -1, 6 }, 1);

        // NOTE: I haven't solved this on paper yet, expected answer 1.5 is not
        // correct
        test = test(test, new int[]{3,4,5}, new int[]{2,1,6}, 2);
    }

    private static int test(int test, int[] snuke, int[] sothe, double expected)
            throws Exception {
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

    private static double solve(int[] snukeCards, int[] sotheHand)
            throws Exception {
        int n = snukeCards.length;
        int max = 2 * n;

        /*
         * SnukeHash and SotheHash are needed so we know what cards out of the
         * 2N are missing and are in Sothe's hands
         */
        Map<Integer, Boolean> snukeHash = new HashMap<Integer, Boolean>();
        Map<Integer, Boolean> sotheHash = new HashMap<Integer, Boolean>();
        List<Integer> dontKnowSothe = new ArrayList<Integer>();
        List<Integer> dontKnowSnuke = new ArrayList<Integer>();
        List<Integer> sotheRandomCards = new ArrayList<Integer>();
        List<Integer> snukeRandomCards = new ArrayList<Integer>();
        int[] snukeHand = new int[n];

        /*
         * Keep track of cards that Sothe will play each round Find Snuke's min
         * card
         */
        int snukeMin = Integer.MAX_VALUE;
        boolean sotheHasMax = false;
        int sotheMaxIndex = -1;
        for (int i = 0; i < n; i++) {
            snukeMin = Math.min(snukeMin, snukeCards[i]);
            snukeHash.put(snukeCards[i], true);
            int sotheCard = sotheHand[i];
            if (sotheCard == -1) {
                dontKnowSothe.add(i);
                dontKnowSnuke.add(i);
                snukeHand[i] = -1;
            } else {
                sotheHash.put(sotheCard, true);
                if (sotheCard == max) {
                    sotheHasMax = true;
                    sotheMaxIndex = i;
                }
            }
        }

        // Figure out what other cards are in Sothe's hands, but we don't know
        // his strategy for
        for (int j = 1; j <= max; j++) {
            if (!sotheHash.containsKey(j) && !snukeHash.containsKey(j)) {
                if (j == max) {
                    sotheHasMax = true;
                }
                if (dontKnowSothe.size() > 1) {
                    sotheRandomCards.add(j);
                } else { // if we only have 1 dontKnow location for Sothe,
                         // then we know what card is played at that location
                         // (so it's a false dontKnow)
                    int index = dontKnowSothe.get(0);
                    dontKnowSothe.remove(0);
                    dontKnowSnuke.remove(0);
                    sotheHand[index] = j;
                    if (j == max) {
                        sotheMaxIndex = index;
                    }
                }

            }
        }

        // Create optimal matchup against Sothe's known playing strategy
        // Strategy - Sothe has Max card: play Snuke's min card against it
        if (sotheHasMax) {
            if (sotheMaxIndex > -1) {
                snukeHand[sotheMaxIndex] = snukeMin;
                snukeHash.remove(snukeMin);
            } else {
                insertSort(snukeRandomCards, snukeMin);
                snukeHash.remove(snukeMin);
            }
        }
        // Strategy - Sothe's known hands: play Snuke's closest next biggest
        // card
        for (int i = 0; i < n; i++) {
            int sotheCard = sotheHand[i];
            if (sotheCard != -1) {
                if (sotheCard != max) {// We already handled the max card
                                       // strategy
                    int card = findNextBiggest(snukeHash, sotheCard, max);
                    if (card > 0) { // snuke has a card just bigger than sothe's
                                    // card played at that turn
                        snukeHand[i] = card; // Snuke knows he has to play this
                                             // coard on this turn
                    } else { // snuke doesn't have a card, so we play a random
                             // card
                        snukeHand[i] = -1; // Snuke doesn't know what card to
                                           // play on this turn
                        insertSort(dontKnowSnuke, i);
                    }
                }
            }
        }

        // Strategy - Sothe's unknown cards: pick Snuke's next closest biggest
        // card to beat it
        // Randomly permute these cards (since we don't know the order Sothe
        // will play them)
        for (int k = 0; k < sotheRandomCards.size(); k++) {
            int sotheCard = sotheRandomCards.get(k);
            if (sotheCard != max) {// We already handled the max card strategy
                int card = findNextBiggest(snukeHash, sotheCard, max);
                if (card > 0) { // if we find a card that's just big enough, we
                                // have to play it
                    insertSort(snukeRandomCards, card);
                } else { // if we don't find a card big enough, then we just
                         // randomly pick from snuke's shuffle cards
                         // snukeHand[j] = -1;
                         // throw new
                         // Exception("I don't know what to do here!");
                }
            }
        }

        /*
         * Strategy: If Sothe plays the max card, Snuke has to play his min card
         * For each card that we know Sothe is going to play and when we find
         * the smallest card in Snuke's hand to beat that card and place it in
         * that slot permanently For other cards that Sothe has we find the
         * smallest card to beat his card but because we don't know what order
         * he is going to play those cards we need to > create an array with
         * these numbers in sorted order (Sothe's leftover cards) > we copy
         * these values into the -1 slots of Sothe's hand > we compute score of
         * Snuke's hand vs Sothe's hand > we permute Sothe's leftover cards and
         * repeat
         */

        if (dontKnowSnuke.size() == 1) {
            for (int card : snukeHash.keySet()) {
                int index = dontKnowSnuke.get(0);
                dontKnowSnuke.remove(0);
                snukeHand[index] = card;
            }
        } else {
            // Whatever is remaining in the hash is what Snuke
            // doesn't know how to play
            for (int card : snukeHash.keySet()) {
                insertSort(snukeRandomCards, card);
            }
        }
        snukeHash.clear(); // we put Snuke's remaining cards into
                           // snukeOtherCards for Shuffling

        /*
         * Sanity check: Make sure dontKnow arrays match in count with
         * randomCard arrays
         */

        if (dontKnowSothe.size() != sotheRandomCards.size()) {
            throw new Exception(
                    "Error: dontKnowSothe.size!=sotheRandomCards.size");
        }
        if (dontKnowSnuke.size() != snukeRandomCards.size()) {
            throw new Exception(
                    "Error: dontKnowSnuke.size!=snukeRandomCards.size");
        }
        if (snukeRandomCards.size() != sotheRandomCards.size()) {
            throw new Exception(
                    "Error: snukeRandomCards.size!=sotheRandomCards.size");
        }

        // Now we need to do for each shuffle of Sothe random cards
        // compare them to Snuke's hand
        // so we
        // 1) Use dontKnowSothe to place the random cards in the appropriate
        // slots in sotheHand
        // 2) Use dontKnowSnuke to place the random cards in the appropriate
        // slots in snukeHand
        // 3) Compute score
        //
        double total = 0;
        double ways = 0;
        placeCards(dontKnowSothe, sotheRandomCards, sotheHand);
        do {
            placeCards(dontKnowSnuke, snukeRandomCards, snukeHand);
            int score = 0;
            for (int t = 0; t < n; t++) {
                if (snukeHand[t] > sotheHand[t]) {
                    ++score;
                }
            }

            total += score;
            ++ways;

        } while (snukeRandomCards.size() > 0
                && next_permutation(snukeRandomCards, max));

        return total / ways;
    }

    private static void placeCards(List<Integer> indices,
            List<Integer> randomCards, int[] hand) {
        for (int k = 0; k < randomCards.size(); k++) {
            hand[indices.get(k)] = randomCards.get(k);
        }
    }

    private static int findNextBiggest(Map<Integer, Boolean> snukeHash,
            int sotheCard, int max) {
        int nextCard = sotheCard + 1;
        while (nextCard <= max) {
            if (snukeHash.containsKey(nextCard)) {
                snukeHash.remove(nextCard);
                return nextCard;
            }
            ++nextCard;
        }
        return -1;
    }

    private static void insertSort(List<Integer> list, int newVal) {
        if (list.size() == 0) {
            list.add(newVal);
        } else {
            int end = list.size();
            int insertAt = end;
            for (int i = 0; i < end; i++) {
                if (newVal < list.get(i)) {
                    insertAt = i;
                }
            }
            list.add(insertAt, newVal);
        }
    }

    private static boolean next_permutation(List<Integer> input, int max) {
        int n = input.size();

        // if (input.get(0) == max) {
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
        // }

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
