package topcoder.srm209.MedalTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) throws Exception {
        // 0)
        int test = 0;
        test = test(test, new String[] { "ITA JPN AUS", "KOR TPE UKR",
                "KOR KOR GBR", "KOR CHN TPE" }, new String[] { "KOR 3 1 0",
                "ITA 1 0 0", "TPE 0 1 1", "CHN 0 1 0", "JPN 0 1 0",
                "AUS 0 0 1", "GBR 0 0 1", "UKR 0 0 1" });
        /*
         * These are the results of the archery competitions. 
        */
        
        //1)
         
        test = test(test, new String[] {"USA AUT ROM"}, new String[] { "USA 1 0 0", "AUT 0 1 0", "ROM 0 0 1" });
        
        //2)
          
        test = test(test, new String[] {"GER AUT SUI", "AUT SUI GER", "SUI GER AUT"}, new String[]{ "AUT 1 1 1",
          "GER 1 1 1", "SUI 1 1 1" });        
    }

    private static int test(int test, String[] input, String[] expected) throws Exception {
        System.out.println("Test" + (test++));
        System.out.println("input: " + print(input));
        System.out.println("Expected:" + print(expected));
        List<String> actual = solve(input);
        System.out.println("Actual:" + printList(actual));
        if ((expected == null && actual == null) || areEqual(expected, actual)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static List<String> solve(String[] input) throws Exception {
        List<String> output = new ArrayList<String>();
        Map<String,Item> medalHash = new HashMap<String,Item>();

        for (String result : input) {
            String[] countries = result.split("\\s");
            for (int i = 0; i < countries.length; i++) {
                String country = countries[i].toUpperCase();
                if (medalHash.containsKey(country)) {
                    Item item = medalHash.get(country);
                    item.increment(i);
                } else {
                    Item item = new Item(country);
                    item.increment(i);
                    medalHash.put(country,item);
                }

            }
        }
        
        Object[] items = medalHash.values().toArray();        
        Arrays.sort(items);
        for(int i=items.length-1; i>=0; i--){
            Object o = items[i];
            Item item = (Item)o;
            output.add(String.format("%s %d %d %d", item.country, item.gold, item.silver, item.bronze));
        }
        return output;
    }

    private static boolean areEqual(String[] expected, List<String> actual) {
        if (expected.length != actual.size()) {
            System.out
                    .println("Expected and Actual are not the same size or length");
            return false;
        }

        for (int i = 0; i < expected.length; i++) {
            if (!expected[i].equals(actual.get(i))) {
                System.out.println("Expected and Actual don't match at index: "
                        + i + " expected: '" + expected[i] + "' actual: '"
                        + actual.get(i) + "'");
                return false;
            }
        }

        return true;
    }

    private static String print(String[] input) {
        String result = "\n";
        for (String v : input) {
            result += v + "\n";
        }
        return result;
    }

    private static String printList(List<String> input) {
        String result = "\n";
        for (String v : input) {
            result += v + "\n";
        }
        return result;
    }

}
