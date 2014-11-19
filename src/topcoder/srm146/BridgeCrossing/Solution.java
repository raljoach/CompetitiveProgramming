package topcoder.srm146.BridgeCrossing;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        // 0)
        int test = 0;
        test = test(test, new int[] { 1, 2, 5, 10 }, 17);

        // The example from the text.

        /*
         * 1)
         * 
         * { 1, 2, 3, 4, 5 } Returns: 16 One solution is: 1 and 2 cross together
         * (2min), 1 goes back (1min), 4 and 5 cross together (5min), 2 goes
         * back (2min), 1 and 3 cross together (3min), 1 goes back (1min), 1 and
         * 2 cross together (2min). This yields a total of 2 + 1 + 5 + 2 + 3 + 1
         * + 2 = 16 minutes spent. 2)
         * 
         * { 100 } Returns: 100 Only one person crosses the bridge once. 3)
         * 
         * { 1, 2, 3, 50, 99, 100 } Returns: 162
         */

    }

    private static int test(int test, int[] times, int expected) {
        // TODO Auto-generated method stub
        System.out.println("Test" + (test++));
        System.out.println("times: " + print(times));
        System.out.println("Expected: " + expected);
        int actual = solve(times);
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static final boolean goRight = true;
    private static final boolean goLeft = !goRight;
    private static int minTime = Integer.MAX_VALUE;
    private static int solve(int[] times) {
        boolean[] goneOver = new boolean[times.length];
        List<String> sequence = new ArrayList<String>();
        int totalTime = 0;
        
        
        for (int i = 0; i < times.length; i++) {
            goneOver[i] = false;
        }
        //totalTime = crossBridge(sequence,goneOver,goRight,0);
        
        totalTime = crossBridge(times, goneOver, sequence, totalTime, goRight);
        return minTime;
    }

    private static int crossBridge(int[] times, boolean[] goneOver,
            List<String> sequence, int totalTime, boolean direction) {
        boolean allCrossedOver = true;
        for(boolean v:goneOver){
            if(!v){
                allCrossedOver = false;
                break;
            }
        }
        if(allCrossedOver){
            return totalTime;
        }
        if (direction==goRight) {
            for (int i = 0; i < times.length; i++) {
                if (goneOver[i] != goRight) {
                    goneOver[i] = goRight;
                    for (int j = i + 1; j < times.length; j++) {
                        if (goneOver[j] != goRight) {
                            goneOver[j] = goRight;
                            sequence.add(i + " " + j);
                            int addTime = Math.max(times[i], times[j]);
                            totalTime += addTime;
                            int thisTime = crossBridge(times,goneOver,sequence,totalTime,goLeft);
                            if (thisTime < minTime) {
                                minTime = totalTime;
                            }
                            totalTime -= addTime;
                            goneOver[j] = !goRight;
                            sequence.remove(sequence.size()-1);
                        }
                    }
                    goneOver[i] = !goRight;
                }
            }
        } else if (direction==goLeft) {
            for (int i = 0; i < times.length; i++) {
                if (goneOver[i] == goRight) {

                    goneOver[i] = goLeft;
                    totalTime += times[i];
                    sequence.add(String.format("%d", i));
                    int thisTime = crossBridge(times, goneOver, sequence, totalTime, goRight);
                    if (thisTime < minTime) {
                        minTime = totalTime;
                    }
                    goneOver[i] = !goLeft;
                    totalTime -= times[i];
                    sequence.remove(sequence.size()-1);
                }
            }
        }
        return minTime;
    }


    private static String print(int[] input) {
        String result = "\n[";
        for(int v:input){
            result+= v + " ";
        }
        result+="]\n";
        return result;
    }

}
