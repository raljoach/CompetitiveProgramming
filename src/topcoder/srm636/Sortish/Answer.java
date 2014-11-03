package topcoder.srm636.Sortish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Answer {

    public static void main(String[] args) throws Exception {
        // 0)
        /*
         * There are six ways to fill in the missing elements. Out of those six
         * permutations, only two have sortedness 5: {4, 1, 5, 2, 3} and {4, 3,
         * 1, 2, 5}.
         */
        int test = 0;
        //test = test(test, 5, new int[] { 4, 0, 0, 2, 0 }, 2);

        // =========================================================================
        // 1)
        //test = test(test, 4, new int[] { 0, 0, 0, 0 }, 5);
        // All 5 possible ways are: {1, 3, 4, 2}, {1, 4, 2, 3}, {2, 1, 4, 3},
        // {2, 3, 1, 4}, {3, 1, 2, 4}.
        
        // =========================================================================
        // 2)
        //test = test(test, 2, new int[] { 1, 3, 2 }, 1);

        // There are no gaps and sortedness is indeed equal to 2.

        // =========================================================================
        // 3)
        //test = test(test, 3, new int[] { 0, 0, 2, 0, 0, 0 }, 4);

        // =========================================================================
        // 4)
        //test = test(test, 87, new int[] { 2, 0 }, 0);

        // The only permutation that matches seq is {2, 1}. However, the
        // sortedness of this sequence is 0, not 87.

        // =========================================================================
        // 5)
        //test = test(test, 30, new int[] { 0, 6, 3, 0, 0, 2, 10, 0, 0, 0 }, 34);

        // =========================================================================
        // 6)

        test = test(test, 100, new int[] { 0, 13, 0, 0, 12, 0, 0, 0, 2, 0, 0,
                10, 5, 0, 0, 0, 0, 0, 0, 7, 15, 16, 20 }, 53447326);

    }

    private static int test(int test, int sortedness, int[] seq, long expected) throws Exception {
        System.out.println("Test" + (test++));
        long actual = ways(sortedness, seq);
        System.out.println("sortedness: " + sortedness);
        System.out.println("seq: ");
        boolean start = true;
        for (int val : seq) {
            if(!start){System.out.print(","); }
            System.out.print(val);
            start = false;
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

    private static int getSortedness(int[] s) {
        int r = 0;
        for (int j = s.length - 1; j >= 0; j--) {
            for (int i = 0; i < j; i++) {
                if (s[i] < s[j]) {
                    r++;
                }
            }
        }
        return r;
    }
    
    private static long ways(int sortedness, int[] seq) throws Exception
    {
        List<Integer> blank = new ArrayList<Integer>();
        List<Integer> missing = new ArrayList<Integer>();
        int n = seq.length;
        int maxSortedness = ((n-1)*n)/2;
        if(sortedness>maxSortedness){return 0;}
        for (int i = 0; i < n; i++) {
            if (seq[i] == 0) {
                blank.add(i);
            }
        }
        for (int x = 1; x <= n; x++) {
            boolean found = false;
            for(int val:seq){
                if (val==x) {
                    found = true;
                    break;
                }
            }
            if(!found){
                missing.add(x);
            }
        }
        // calculate the initial sortedness
        int initialSortedness = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ( (seq[i] < seq[j]) && (seq[i] != 0) && (seq[j] != 0) ) {
                    initialSortedness++;
                }
            }
        }
        
        // calculate the sortedness added by choosing a number in each of the blank places
        int m = blank.size();
        if(m<=1){return 1;}
        int[][] addSort = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                // assign missing[j] to position blank[i]
                addSort[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    if (seq[k] != 0) {
                        if ( k < blank.get(i) ) {
                            if ( seq[k] < missing.get(j) ) {
                                addSort[i][j]++;
                            }
                        } else if (k > blank.get(i)) {
                            if ( seq[k] > missing.get(j) ) {
                                addSort[i][j]++;
                            }
                        }
                    }
                }
            }
        }
        // we need to precalculate the sortedness of each permutation.
        // we *could* just use the permutation as a key for an associative array
        // but the following is a neat trick. We know that next_permutation will
        // always generate all permuations in the same order, so we can assign 
        // an unique id to each permutation by the order it is visited.
        int[] permScore1 = new int[5040];       
        int[]  per1 = new int[m / 2];
        for (int i = 0; i < m / 2; i++) {
            per1[i] = i;
        }
        int pid = 0;
        for (int i = 0; i < m / 2; i++) {
            per1[i] = i;
        }
        do {
            permScore1[pid++] = getSortedness(per1);
        } while ( next_permutation(per1) );

        // An issue is that the number of missing elements might be odd, so
        // the number of small and large elements might be different so we might
        // need to repeat for large numbers:
        int[] permScore2 = new int[5040];
        int[] per2 = new int[(m+1) / 2];
        for (int i = 0; i < (m+1) / 2; i++) {
            per2[i] = i;
        }
        pid = 0;
        do {
            permScore2[pid++] = getSortedness(per2);
        } while ( next_permutation(per2) );


        long res = 0;
        // we can generate all combinations using next_permutation. Okay, this
        // solution is very next_permutation-specific, I admit.
        int[] comb = new int[m];
        for (int i = 0; i < m/2; i++) {
            comb[i] = 0;
        }
        for (int j = m/2; j < m; j++) {
            comb[j] = 1;
        }
        int comboNum = 0;
        Map<String,Boolean> seen = new HashMap<String,Boolean>();
        do {
            //System.out.println("------------------------");
            //System.out.print("Combo: ");
            //print(comb);
            int MAX = 1999*7; //The maximum value of s. Roughly you can
            // imagine that for each of the m/2 small numbers we might add less
            // than n-1 sortedness. The real limit is smaller but is not needed
            // to find a tight bound.

            int combsort = getSortedness(comb); //sortedness by this combination

            // small : For each small permutation, find s and increment small[s]
            long[] small = new long[MAX + 1];
            pid = 0;
            for (int i = 0; i < m / 2; i++) {
                per1[i] = i;
            }
            int[] copy = new int[seq.length];
            do {
                int s = permScore1[pid++];
                int j = 0;
                for (int i = 0; i < m; i++) {
                    if (comb[i] == 0) {
                        int missingIndex = per1[j++];
                        copy[blank.get(i)] = missing.get(missingIndex);                        
                        s += addSort[i][ missingIndex ];
                    }
                }
                small[s]++;
            } while ( next_permutation(per1) );

            // large : For each large permutation, find the required s for a
            // matching small permutation and add small[s] to res
            pid = 0;
            for (int i = 0; i < (m+1) / 2; i++) {
                per2[i] = i;
            }
            do {
                int s = permScore2[pid++];
                int j = 0;
                
                for (int i = 0; i < m; i++) {
                    if (comb[i] == 1) {
                        int missingIndex = per2[j++] + (m/2);
                        copy[blank.get(i)] = missing.get(missingIndex);
                        s += addSort[i][ missingIndex ];
                    }
                }
                String str = "";
                for(int v:copy){
                    str+=v;
                }
                if(seen.containsKey(str)){
                    continue;
                }
                else{
                    seen.put(str, true);
                }
                int ws = sortedness - initialSortedness - s - combsort;
                
                if ( 0 <= ws && ws <= MAX) {
                    res += small[ws];
                }
                //System.out.print("seq: ");
                //print(seq);
                //System.out.println("combo["+comboNum+"]: remaining="+ws + " res:"+res);
                ++comboNum;
            } while ( next_permutation(per2) );
            
        } while (next_permutation(comb));


        return res;
    }    

    private static Map<int[],int[]> origHash = new HashMap<int[],int[]>();
    private static Map<int[],int[]> indexHash = new HashMap<int[],int[]>();
    private static boolean next_permutation(int[] input) {
        int n = input.length;
        int max = n - 1;
        
        int[] indices = getIndices(input);
        int[] original = getOrig(input);
        
        boolean lastPerm = true;
        int x = n - 1;
        for (int j = 0; j < n; j++) {
            int v = indices[j];
            if (v != x) {
                lastPerm = false;
                break;
            }
            --x;
        }
        
        if(lastPerm) {
            indexHash.remove(input);
            origHash.remove(input);
            return false; 
        }

        Map<Integer, Boolean> fixed = new HashMap<Integer, Boolean>();
        for (int i = 0; i < n; i++) {
            int v = indices[i];
            fixed.put(v, true);
        }

        List<Integer> remaining = new ArrayList<Integer>();

        for (int current = n - 1; current >= 0; --current) {
            int currentVal = indices[current];
            if (current == n - 1) {
                if (fixed.containsKey(currentVal)) {
                    addToRemaining(fixed, remaining, currentVal);
                }
            } else if (canIncrement(max, current, fixed, indices, remaining)) {
                break;
            } else {
                if (fixed.containsKey(currentVal)) {
                    addToRemaining(fixed, remaining, currentVal);
                }
            }
        }
        
        indexHash.put(input, indices);
        for(int k=0; k<n; k++){
            input[k] = original[indices[k]];
        }
                
        return true;
    }

    private static int[] getIndices(int[] input) {
        if(indexHash.containsKey(input)){
            return indexHash.get(input);
        }
        else{
            int n = input.length;
            int[] indices = new int[n];
            for(int i=0; i<n; i++){
                indices[i] = i;
            }
            indexHash.put(input, indices);
            return indices;
        }
    }

    private static int[] getOrig(int[] input) {
        if(origHash.containsKey(input)){
            return origHash.get(input);
        }
        else{
            int n = input.length;
            int[] orig = new int[n];
            for(int i=0; i<n; i++){
                orig[i] = input[i];
            }
            origHash.put(input, orig);
            return orig;
        }
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
    
    private static void print(int[] elems) {
        for(int v:elems){
            System.out.print(v + " ");
        }
        System.out.println();
    }
}
