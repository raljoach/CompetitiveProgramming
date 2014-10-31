package topcoder.srm636.Sortish;

import java.util.ArrayList;
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
        test = test(test, 5, new int[] { 4, 0, 0, 2, 0 }, 2);
/*
        // =========================================================================
        // 1)
        test = test(test, 4, new int[] { 0, 0, 0, 0 }, 5);
        // All 5 possible ways are: {1, 3, 4, 2}, {1, 4, 2, 3}, {2, 1, 4, 3},
        // {2, 3, 1, 4}, {3, 1, 2, 4}.

        // =========================================================================
        // 2)
        test = test(test, 2, new int[] { 1, 3, 2 }, 1);

        // There are no gaps and sortedness is indeed equal to 2.

        // =========================================================================
        // 3)
        test = test(test, 3, new int[] { 0, 0, 2, 0, 0, 0 }, 4);

        // =========================================================================
        // 4)
        test = test(test, 87, new int[] { 2, 0 }, 0);

        // The only permutation that matches seq is {2, 1}. However, the
        // sortedness of this sequence is 0, not 87.

        // =========================================================================
        // 5)
        test = test(test, 30, new int[] { 0, 6, 3, 0, 0, 2, 10, 0, 0, 0 }, 34);

        // =========================================================================
        // 6)

        test = test(test, 100, new int[] { 0, 13, 0, 0, 12, 0, 0, 0, 2, 0, 0,
                10, 5, 0, 0, 0, 0, 0, 0, 7, 15, 16, 20 }, 53447326);
*/
    }

    private static int test(int test, int sortedness, int[] seq, long expected) throws Exception {
        System.out.println("Test" + (++test));
        long actual = ways(sortedness, seq);
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
    
    private static int getSortedness(List<Integer> t) {
        int[] s = new int[t.size()];
        for(int i=0; i<t.size(); i++){
            s[i]=t.get(i);
        }
        return getSortedness(s);
    }    
    
    private static long ways2(int sortedness, int[] seq) throws Exception
    {
        List<Integer> blank = new ArrayList<Integer>();
        List<Integer> missing = new ArrayList<Integer>();
        int n = seq.length;
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
        //do {
        for(List<Integer> perm:createPerms(per1,0)){
            permScore1[pid++] = getSortedness(perm);
        }
        //} while ( next_permutation(per1) );

        // An issue is that the number of missing elements might be odd, so
        // the number of small and large elements might be different so we might
        // need to repeat for large numbers:
        int[] permScore2 = new int[5040];
        int[] per2 = new int[(m+1) / 2];
        for (int i = 0; i < (m+1) / 2; i++) {
            per2[i] = i;
        }
        pid = 0;
        //do {
        for(List<Integer> perm:createPerms(per2,0)){
            permScore2[pid++] = getSortedness(perm);
        }
        //} while ( next_permutation(per2) );


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
        do {
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
            do {
                int s = permScore1[pid++];
                int j = 0;
                for (int i = 0; i < m; i++) {
                    if (comb[i] == 0) {
                        s += addSort[i][ per1[j++] ];
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
                        s += addSort[i][ per2[j++] + (m/2) ];
                    }
                }
                int ws = sortedness - initialSortedness - s - combsort;
                if ( 0 <= ws && ws <= MAX) {
                    res += small[ws];
                }
            } while ( next_permutation(per2) );


        } while (next_permutation(comb));


        return res;
    }


    private static long ways(int sortedness, int[] seq) throws Exception
    {
        List<Integer> blank = new ArrayList<Integer>();
        List<Integer> missing = new ArrayList<Integer>();
        int n = seq.length;
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
        do {
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
            do {
                int s = permScore1[pid++];
                int j = 0;
                for (int i = 0; i < m; i++) {
                    if (comb[i] == 0) {
                        s += addSort[i][ per1[j++] ];
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
                        s += addSort[i][ per2[j++] + (m/2) ];
                    }
                }
                int ws = sortedness - initialSortedness - s - combsort;
                if ( 0 <= ws && ws <= MAX) {
                    res += small[ws];
                }
            } while ( next_permutation(per2) );


        } while (next_permutation(comb));


        return res;
    }

    //private static int current=-1;
    //private static int cycle = 0;
    private static Map<int[],Integer> currentHash = new HashMap<int[],Integer>();
    private static Map<int[],Integer> cycleHash = new HashMap<int[],Integer>();
    private static boolean next_permutation(int[] elems) throws Exception {        
        System.out.println("---------------------");
        System.out.println("next_compute start:");
        int cycle = lookupCycle(elems);
        int current = lookupCurrent(elems);
        /*
        if(current==-1){
            cycle=elems.length-1;
            current = elems.length-1;  
            System.out.println("New array to permute");
            print(elems);
        }*/
        
        System.out.println("start cycle:" + cycle);
        System.out.println("start current:" + current);
        
        if(cycle>0){
            if(current>=1){
                System.out.println("Part D");
                swap(elems,current-1,current);
                --current;
                
                if(current==0){
                    System.out.println("Part C");
                    --cycle;
                    current = elems.length-1;
                }
            }
        }
        else{
            if(current>1){
                System.out.println("Part B");
                swap(elems,current-1,current);
                --current;
            }
//            else{
//                throw new Exception("Should not get here!");
//            }
        }
        
        if(cycle==0){
            if(current<=1){  
                System.out.println("Part A");
                System.out.println("Done!");
                current=-1;
                
                setCycleCurrent(elems,cycle,current);
                System.out.println("end next_compute");
                System.out.println("---------------------");
                return false;
            }
        }
        setCycleCurrent(elems,cycle,current);
        System.out.println("end cycle:" + cycle);
        System.out.println("end current:" + current);
        System.out.println("end next_compute");
        System.out.println("---------------------");
        return true;
    }
        
    private static void setCycleCurrent(int[] elems, int cycle, int current) {
        cycleHash.put(elems, cycle);
        currentHash.put(elems, current);
    }

    private static int lookupCurrent(int[] elems) {
        if(!currentHash.containsKey(elems)){
            int current = elems.length-1;  
            System.out.println("New array to permute");
            print(elems);
            currentHash.put(elems,current);
            return current;
        }
        else{
            System.out.println("Existing array to permute");
            print(elems);
            return currentHash.get(elems);
        }
    }

    private static int lookupCycle(int[] elems) {
        
        if(!cycleHash.containsKey(elems)){
            int cycle=elems.length-1;
            cycleHash.put(elems,cycle);
            return cycle;
        }
        else{
            return cycleHash.get(elems);
        }
    }

    private static void swap(int[] elems, int i, int j) {               
        System.out.println("Swapping indices: " + i + ", " + j);
        System.out.println("Original:");
        print(elems);
        int tmp = elems[i];
        elems[i] = elems[j];
        elems[j] = tmp;   
        System.out.println("Afer Swap:");
        print(elems);
    }

    private static void print(int[] elems) {
        for(int v:elems){
            System.out.print(v + " ");
        }
        System.out.println();
    }

    private static List<List<Integer>> createPerms(int[] set, int index) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(index==set.length){
            List<Integer> s = new ArrayList<Integer>();
            result.add(s);
        }
        else{
            int item = set[index];
            List<List<Integer>> subPerms = createPerms(set,index+1);
            for(List<Integer> t:subPerms){               
                for(int i=0; i<=t.size(); i++){
                    List<Integer> newSet = new ArrayList<Integer>();
                    newSet.addAll(t);
                    newSet.add(i,item);
                    result.add(newSet);
                }
            }
        }
        return result;
    }

    private static int factorial(int n){
        if(n<2){ return 1; }
        return n*factorial(n-1);
    }
}
