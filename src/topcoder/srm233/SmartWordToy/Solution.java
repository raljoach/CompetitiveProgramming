package topcoder.srm233.SmartWordToy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        test = test(test, "aaaa", "zzzz", new String[] { "a a a z", "a a z a",
                "a z a a", "z a a a", "a z z z", "z a z z", "z z a z",
                "z z z a" }, 8);

        // 1)

        test = test(test, "aaaa", "bbbb", new String[] {}, 4);
        // Simply change each letter one by one to the following
        // letter in the alphabet.

        // 2)

        test = test(test, "aaaa", "mmnn", new String[] {}, 50);
        // Just as in the previous example, we have no forbidden
        // words. Simply apply the correct number of button
        // presses for each letter and you're // there.

        // 3)

        test = test(test, "aaaa", "zzzz", new String[] { "bz a a a",
                "a bz a a", "a a bz a", "a a a bz" }, -1);
        // Here is an example where it is impossible to go to
        // any word from "aaaa".

        // 4)

        test = test(test, "aaaa", "zzzz", new String[] {
                "cdefghijklmnopqrstuvwxyz a a a",
                "a cdefghijklmnopqrstuvwxyz a a",
                "a a cdefghijklmnopqrstuvwxyz a",
                "a a a cdefghijklmnopqrstuvwxyz" }, 6);

        // 5)

        test = test(test, "aaaa", "bbbb", new String[] { "b b b b" }, -1);

        // 6)

        test = test(test, "zzzz", "aaaa", new String[] {
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk",
                "abcdefghijkl abcdefghijkl abcdefghijkl abcdefghijk" }, -1);

    }

    private static int test(int test, String start, String finish,
            String[] constraints, int expected) {
        System.out.println("Test" + (test++));
        System.out.println("start: " + start);
        System.out.println("finish:" + finish);
        System.out.println("constraints:" + print(constraints));
        System.out.println("Expected: " + expected);
        int actual = solve(start, finish, constraints);
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static int solve(String start, String finish, String[] constraints) {
        start = start.toLowerCase();
        finish = finish.toLowerCase();
        if (!isAllowed(constraints, start)) {
            return -1;
        }
        if (!isAllowed(constraints, finish)) {
            return -1;
        }
        if (start.equals(finish)) {
            return 0;
        }
        List<String> q = new ArrayList<String>();
        q.add(start);
        Map<String, String> backTrack = new HashMap<String, String>();
        while (q.size() > 0) {
            String next = q.remove(0);
            // System.out.println("Next word: " + next);
            for (String neighbor : findSuccessors(next, constraints)) {
                if (!backTrack.containsKey(neighbor)) {
                    // System.out.println("New word: " + neighbor);
                    if (neighbor.equals(finish)) {
                        String current = next;
                        int dist = 1;
                        while (!current.equals(start)
                                && backTrack.containsKey(current)) {
                            current = backTrack.get(current);
                            dist++;
                        }
                        return dist;
                    } else {
                        q.add(neighbor);
                        backTrack.put(neighbor, next);
                    }
                }
            }
        }

        return -1;
    }

    private static List<String> findSuccessors(String input,
            String[] constraints) {
        List<String> result = new ArrayList<String>();
        boolean hasConstraints = constraints != null && constraints.length > 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            char next;
            char prev;
            if (c == 'z') {
                next = 'a';
            } else {
                next = (char) (c + 1);
            }

            if (c == 'a') {
                prev = 'z';
            } else {
                prev = (char) (c - 1);
            }

            boolean allowed = true;
            String nextWord = input.substring(0, i) + next
                    + input.substring(i + 1);
            if (hasConstraints) {
                allowed = isAllowed(constraints, nextWord);
            }
            if (allowed) {

                result.add(nextWord);
            }

            allowed = true;
            String prevWord = input.substring(0, i) + prev
                    + input.substring(i + 1);
            if (hasConstraints) {
                allowed = isAllowed(constraints, prevWord);
            }
            if (allowed) {
                result.add(prevWord);
            }
        }
        return result;
    }

    private static boolean isAllowed(String[] constraints, String newWord) {

        for (int k = 0; k < constraints.length; k++) {
            String constraint = constraints[k].toLowerCase();
            String[] elems = constraint.split("\\s");
            boolean meetsConstraint = true;
            for (int i = 0; i < newWord.length(); i++) {
                String elem = elems[i];
                char letter = newWord.charAt(i);
                if (elem.indexOf(letter) < 0) {
                    meetsConstraint = false;
                    break;
                }
            }
            if (meetsConstraint) {
                // System.out.println("Eliminate word '" + newWord +
                // "' meets constraint '" + constraint + "'");
                return false;
            }
        }

        return true;
    }

    private static String print(String[] input) {
        String result = "\n";
        for (String s : input) {
            result += s + "\n";
        }
        return result;
    }

}
