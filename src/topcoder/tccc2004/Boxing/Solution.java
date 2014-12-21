package topcoder.tccc2004.Boxing;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        
          test = test(test, new int[] { 1, 2, 3, 4, 5, 6 }, new int[] { 1, 2,
          3, 4, 5, 6, 7 }, new int[] { 1, 2, 3, 4, 5, 6 }, new int[] { 0, 1, 2
          }, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 6);
         

        // This match had a fast start, with 6 punches credited in the first 6
        // milliseconds of the match! One way to choose 6 disjoint intervals is
        // to choose [1,1], [2,2], [3,3], [4,4], [5,5], [6,6] each of which
        // contains button presses from judges a, b, and c. There are three
        // button presses in the time interval from 7 through 8, but only from
        // two different judges so no additional credit can be given.

        // 1)

        test = test(test, new int[] { 100, 200, 300, 1200, 6000 },
                new int[] {}, new int[] { 900, 902, 1200, 4000, 5000, 6001 },
                new int[] { 0, 2000, 6002 },
                new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 3);
        
         // One way to form three intervals is [0,1000], [1001,2000], [6000,6002]
         //2)
          
        test = test(test, new int[] {5000,6500},new int[]  {6000},new int[]  {6500},new int[]  {6000},new int[]  {0,5800,6000},1);
          /*Any
          interval that doesn't include 6000 will not have enough button
          presses, so we can form only one disjoint interval with credit for a
          punch.*/
         
    }

    private static int test(int test, int[] a, int[] b, int[] c, int[] d,
            int[] e, int expected) {
        System.out.println("Test" + (test++));
        System.out.println("a: " + print(a));
        System.out.println("b: " + print(b));
        System.out.println("c: " + print(c));
        System.out.println("d: " + print(d));
        System.out.println("e: " + print(e));
        System.out.println("Expected: " + expected);
        int actual = solve(a, b, c, d, e);
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static int solve(int[] a, int[] b, int[] c, int[] d, int[] e) {
        int aIndex = 0, bIndex = 0, cIndex = 0, dIndex = 0, eIndex = 0;
        int start = -1;
        int credit = 0;
        boolean[] set = new boolean[5];
        for (int i = 0; i < 5; i++) {
            set[i] = false;
        }
        while (true) {
            int aVal = -1, bVal = -1, cVal = -1, dVal = -1, eVal = -1, min = -1;
            int[] vals = new int[5];
            for (int i = 0; i < vals.length; i++) {
                vals[i] = -1;
            }
            if (aIndex >= 0 && aIndex < a.length) {
                aVal = a[aIndex];
                vals[0] = aVal;
                min = aVal;
            } else {
                aIndex = -1;
            }
            if (bIndex >= 0 && bIndex < b.length) {
                bVal = b[bIndex];
                vals[1] = bVal;
                if (min < 0) {
                    min = bVal;
                } else {
                    min = Math.min(min, bVal);
                }
            } else {
                bIndex = -1;
            }
            if (cIndex >= 0 && cIndex < c.length) {
                cVal = c[cIndex];
                vals[2] = cVal;
                if (min < 0) {
                    min = cVal;
                } else {
                    min = Math.min(min, cVal);
                }
            } else {
                cIndex = -1;
            }
            if (dIndex >= 0 && dIndex < d.length) {
                dVal = d[dIndex];
                vals[3] = dVal;
                if (min < 0) {
                    min = dVal;
                } else {
                    min = Math.min(min, dVal);
                }
            } else {
                dIndex = -1;
            }
            if (eIndex >= 0 && eIndex < a.length) {
                eVal = e[eIndex];
                vals[4] = eVal;
                if (min < 0) {
                    min = eVal;
                } else {
                    min = Math.min(min, eVal);
                }
            } else {
                eIndex = -1;
            }

            if (min < 0) {
                break;
            }

            if (aIndex >= 0 && min == a[aIndex]) {
                ++aIndex;
                set[0] = true;
            }
            if (bIndex >= 0 && min == b[bIndex]) {
                ++bIndex;
                set[1] = true;
            }
            if (cIndex >= 0 && min == c[cIndex]) {
                ++cIndex;
                set[2] = true;
            }
            if (dIndex >= 0 && min == d[dIndex]) {
                ++dIndex;
                set[3] = true;
            }
            if (eIndex >= 0 && min == e[eIndex]) {
                ++eIndex;
                set[4] = true;
            }

            if (start < 0) {
                start = min;
            }
            if ((min - start) > 1000) {
                start = min;
                for (int i = 0; i < 5; i++) {
                    if (vals[i] < min) {
                        set[i] = false;
                    }
                }
            } else {
                int count = 0;
                for (boolean v : set) {
                    if (v) {
                        ++count;
                    }
                }
                if (count >= 3) {
                    ++credit;
                    // start = -1;
                    // count = 0;
                    for (int i = 0; i < 5; i++) {
                        set[i] = false;
                    }
                }
            }

        }
        return credit;
    }

    private static String print(int[] input) {
        String result = "\n[";
        boolean first = true;
        for (int v : input) {
            if (first) {
                first = false;
            } else {
                result += " ";
            }
            result += v;
        }
        result += "]\n";
        return result;
    }

}
