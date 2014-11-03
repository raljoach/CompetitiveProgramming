package topcoder.srm636.Sortish.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        int test = 0;
        test = test(test, new int[] { 0, 1, 2, 3 }, Arrays.asList(
                Arrays.asList(0, 1, 3, 2), Arrays.asList(0, 2, 1, 3),
                Arrays.asList(0, 2, 3, 1), Arrays.asList(0, 3, 1, 2),
                Arrays.asList(0, 3, 2, 1),

                Arrays.asList(1, 0, 2, 3), Arrays.asList(1, 0, 3, 2),
                Arrays.asList(1, 2, 0, 3), Arrays.asList(1, 2, 3, 0),
                Arrays.asList(1, 3, 0, 2), Arrays.asList(1, 3, 2, 0),

                Arrays.asList(2, 0, 1, 3), Arrays.asList(2, 0, 3, 1),
                Arrays.asList(2, 1, 0, 3), Arrays.asList(2, 1, 3, 0),
                Arrays.asList(2, 3, 0, 1), Arrays.asList(2, 3, 1, 0),

                Arrays.asList(3, 0, 1, 2), Arrays.asList(3, 0, 2, 1),
                Arrays.asList(3, 1, 0, 2), Arrays.asList(3, 1, 2, 0),
                Arrays.asList(3, 2, 0, 1), Arrays.asList(3, 2, 1, 0)));
    }

    private static int test(int test, int[] input, List<List<Integer>> expected) {
        System.out.println("Test" + (++test));
        System.out.println("input: " + print(input));

        int n = expected.size();
        for (int i = 0; i < n; i++) {
            System.out.println("["+i+"]");
            List<Integer> e = expected.get(i);
            System.out.println("Next perm: " + printPerm(e));
            boolean output = next_permutation(input);
            System.out.println("Actual: " + print(input));

            boolean sameNumbers = areEqual(e, input);
            boolean expectedOutput = true;
            if (i == n - 1) {
                expectedOutput = false;
            }
            boolean sameOutput = expectedOutput == output;
            if (sameNumbers && sameOutput) {
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED");
                break;
            }
            System.out.println("+++++++++++");
        }

        System.out.println("-----------------");
        return test;
    }

    private static boolean next_permutation(int[] input) {
        int n = input.length;
        int max = n - 1;

        Map<Integer, Boolean> fixed = new HashMap<Integer, Boolean>();
        for (int i = 0; i < n; i++) {
            int v = input[i];
            fixed.put(v, true);
        }

        List<Integer> remaining = new ArrayList<Integer>();

        for (int current = n - 1; current >= 0; --current) {
            int currentVal = input[current];
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

        int x = n - 1;
        for (int j = 0; j < n; j++) {
            int v = input[j];
            if (v != x) {
                return true;
            }
            --x;
        }

        return false;
    }

    private static void addToRemaining(Map<Integer, Boolean> fixed,
            List<Integer> remaining, int currentVal) {
        fixed.remove(currentVal);
        remaining.add(currentVal);
        Collections.sort(remaining);
    }

    private static boolean canIncrement(int max, int current,
            Map<Integer, Boolean> fixed, int[] input, List<Integer> remaining) {
        int currentVal = input[current];
        if (currentVal == max || current == input.length - 1) {
            return false;
        }

        if (remaining.size() == 0) {
            return false;
        }

        boolean  found = false;
        for (int k = 0; k < remaining.size(); k++) {
            int newVal = remaining.get(k);
            if (currentVal < newVal) {
                found = true;
                remaining.remove(k);
                addToRemaining(fixed, remaining, currentVal);
                fixed.put(newVal, true);
                input[current] = newVal;
                break;
            }
        }

        if (!found) {
            return false;
        }

        for (int index = current + 1; index < input.length; index++) {
            int otherVal = remaining.get(0);
            remaining.remove(0);
            fixed.put(otherVal, true);
            input[index] = otherVal;
        }
        return true;
    }

    private static boolean areEqual(List<Integer> e, int[] input) {
        if (e.size() != input.length) {
            System.out
                    .println("Expected and Actual are not the same size or length");
            return false;
        }

        for (int i = 0; i < e.size(); i++) {
            if (e.get(i) != input[i]) {
                System.out
                        .println("Expected and Actual don't match at index: "
                                + i + " expected: " + e.get(i) + " actual: "
                                + input[i]);
                return false;
            }
        }

        return true;
    }

    private static String printPerm(List<Integer> e) {
        String result = "";
        for (Integer v : e) {
            result += v + " ";
        }
        return result;
    }

    private static String print(int[] input) {
        String result = "";
        for (int v : input) {
            result += v + " ";
        }
        return result;
    }

}
