package topcoder.srm219.TurntableService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) throws Exception {
        // 0)
        int test = 0;
         test = test(test, new String[] { "0 2", "1", "0 1" }, 32);

        // The first two people serve themselves immediately (15 seconds). Then,
        // we have to turn the turntable one unit, in either direction (2
        // seconds), so the last person can serve himself (another 15 seconds).
        // This takes 32 seconds.

        // 1)

         test = test(test, new String[] {"0", "0", "0"},49);

        /*
         * Here, each person only likes one entree, so they have to be served
         * one at a time. Three servings (15 seconds each) and two single-unit
         * rotations (2 seconds each) takes a total of 49 seconds.
         */

        // 2)

         test = test(test, new String[] {"4", "1", "2", "3", "0"},50);

        /*
         * First, the middle three people take their entrees (15 seconds). Then,
         * we rotate one unit--either way (2 seconds), serve the entree (15
         * seconds), and then rotate back two units (3 seconds) and serve the
         * last person (15 seconds). All together this takes 50 seconds.
         */

        // 3)

        test = test(test, new String[] { "0 004", "2 3", "0 01", "1 2 3 4",
                "1 1" }, 35);
        /*
         * Note here that leading zeros are permitted, and that elements may
         * contain repeats.
         */

    }

    private static int test(int test, String[] favorites, int expected)
            throws Exception {
        System.out.println("Test" + (test++));
        System.out.println("input: " + print(favorites));
        System.out.println("Expected: " + expected);
        int actual = solve(favorites);
        System.out.println("Actual: " + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static int solve(String[] favorites) throws Exception {
        int n = favorites.length;
      
        Map<Integer, List<Integer>> didNotEatHash = new HashMap<Integer, List<Integer>>();
        Map<Integer, List<Integer>> distFood = new HashMap<Integer, List<Integer>>();
        for (int person = 0; person < n; person++) {
            List<Integer> myFaveList = convert(favorites[person]);
            List<Integer> dist = calcDistances(person, myFaveList,n);
            distFood.put(person, dist);
            didNotEatHash.put(person, myFaveList);
        }
        
        /*System.out.println("\nPeople who have not eaten (remaining):");
        printHash(didNotEatHash);
        
        System.out.println("\nShifts to get favorite items");
        printHash(distFood);*/

        Table start = new Table(didNotEatHash, distFood, n, 0, 0);

        List<Table> q = new ArrayList<Table>();
        q.add(start);
        Map<String, Table> backTrack = new HashMap<String, Table>();
        int bestTime = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Table next = q.remove(0);
            // printState(next);
            if (next.time < bestTime) {
                List<Table> neighborList = findSuccessors(next);
                for (Table neighbor : neighborList) {
                    // System.out.print("Neighbor: " + neighbor.path);

                    if (!visited(backTrack, neighbor)) {
                        // System.out.println("(Unvisited)");
                        if (atEndState(neighbor)) {
                            int thisTime = neighbor.time;
                            // System.out.println("Potential answer: " +
                            // thisTime);
                            // System.out.println(neighbor.path);
                            if (thisTime < bestTime) {
                                bestTime = thisTime;
                                /*System.out.println("New answer: " + bestTime);
                                System.out.println(neighbor.path);
                                System.out.println("\nWho ate, When");
                                for (int i = 0; i < neighbor.whenAte.length; i++) {
                                    System.out.print(i + ": ");
                                    System.out.println(neighbor.whenAte[i]);
                                }
                                System.out.println();*/
                                break;
                            }
                        } else {
                            q.add(neighbor);
                            addToVisited(backTrack, next, neighbor);
                        }
                    } else {
                        // System.out.println("(Visited)");
                    }
                }
                // System.out.println("--------------------------------------");
            } else {
                // System.out.println("Same or greater number of crossing as Answer already found: "
                // + bestTime);
                // System.out.println("---------------------------------------------------------------------------");
            }
        }

        if (bestTime < Integer.MAX_VALUE) {
            return bestTime;
        }
        return -1;
    }

    private static List<Integer> convert(String faveRow) {
        List<Integer> myFaves = new ArrayList<Integer>();
        String[] hisFaves = faveRow.split("\\s");
        Map<Integer, Boolean> uniqueFoods = new HashMap<Integer, Boolean>();
        for (int j = 0; j < hisFaves.length; j++) {
            int food = Integer.parseInt(hisFaves[j]);
            if (uniqueFoods.containsKey(food)) {
                continue;
            } else {
                uniqueFoods.put(food, true);
                myFaves.add(food);
            }
        }
        return myFaves;
    }

    private static List<Integer> calcDistances(int person,
            List<Integer> faveList, int n) {
        List<Integer> distList = new ArrayList<Integer>();
        for (int food : faveList) {
            int start = person;
            int dist1 = 0;
            while (start != food) {
                ++dist1;
                if (start == 0) {
                    start = n-1;
                } else {
                    --start;
                }
            }
            int dist2 = 0;
            start = person;
            while (start != food) {
                --dist2;
                if (start == (n - 1)) {
                    start = 0;
                } else {
                    ++start;
                }
            }
            // int shortestDist = Math.min(dist1, dist2);
            int shortestDist = dist2;
            if (Math.abs(dist1) < Math.abs(dist2)) {
                shortestDist = dist1;
            }
            distList.add(shortestDist);
        }
        return distList;
    }

    private static void printState(Table next) {
        System.out.println("Next: " + next.path);
    }

    private static void addToVisited(Map<String, Table> backTrack, Table next,
            Table neighbor) {
        backTrack.put(neighbor.path, next);
    }

    private static boolean visited(Map<String, Table> backTrack, Table neighbor) {
        return backTrack.containsKey(neighbor.path);
    }

    private static boolean atEndState(Table neighbor) {
        return neighbor.didNotEatHash.size() == 0;
    }

    private static List<Table> findSuccessors(Table current) throws Exception {
        List<Table> list = new ArrayList<Table>();
        for (int person : current.didNotEatHash.keySet()) {
            for (int delta : current.distFood.get(person)) {
                Table newTable = current.clone();
                newTable.shiftItems(delta);
                list.add(newTable);
            }
        }
        return list;
    }

    private static String print(String[] input) {
        String result = "\n  ";
        int col = 0;
        for (; col < input[0].length(); col++) {
            result += col % 10;
        }
        result += "\n";
        int count = 0;
        for (String s : input) {
            result += (count % 10) + ":" + s + "\n";
            count++;
        }
        return result;
    }

    private static void printHash(Map<Integer, List<Integer>> hash) {
        String result = "";

        int maxLength = 0;
        for (int k : hash.keySet()) {
            List<Integer> list = hash.get(k);
            result += k + ":";
            for (int s : list) {
                result += s + " ";

            }

            result += "\n";
            int l = list.size();
            if (l > maxLength) {
                maxLength = l;
            }
        }

        int col = 0;
        String header = "  ";
        for (; col < maxLength; col++) {
            header += col % 10 + " ";
        }
        result = header + "\n" + result;

        System.out.println(result + "\n");
    }
}
