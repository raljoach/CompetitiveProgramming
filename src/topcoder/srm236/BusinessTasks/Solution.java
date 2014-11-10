//-----------------------------------------------------------------------
// <copyright file="Solution.cs" company="MyCompany">
//     Copyright (c) MyCompany. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------
package topcoder.srm236.BusinessTasks;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        //0)
        int test=0;
        test = test(test, new String[]{"a","b","c","d"},2,"a");
        //We start counting from a. So a is 1, b is 2. We remove b, so list is now {a,c,d}. We continue from c. So c is 1, d is 2. We remove d, so list is now {a,c}. We continue from a. So a is 1, c is 2. We remove c, and now we are left with the last task a.
        
        //1)                
        test = test(test,new String[]{"a","b","c","d","e"},3,"d");
        
        //We start counting from a. So a is 1, b is 2, c is 3. We remove c, now list = {a,b,d,e}. We continue from d. So d is 1, e is 2, a is 3. We remove a, now list = {b,d,e}. We continue from b. So b is 1, d is 2, e is 3. We remove e, now list = {b,d}. We continue from b. So b is 1, d is 2 and finally b is 3. We remove b, and now we are left with just one task d.
        
        //2)                 
        test = test(test,new String[]{"alpha","beta","gamma","delta","epsilon"},1,"epsilon");
        
        //3)                 
        test = test(test,new String[]{"a","b"},1000,"a");
        
        //4)                 
        test = test(test,new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"},17,"n");
        
        //5)                  
        test = test(test,new String[]{"zlqamum","yjsrpybmq","tjllfea","fxjqzznvg","nvhekxr","am","skmazcey","piklp","olcqvhg","dnpo","bhcfc","y","h","fj","bjeoaxglt","oafduixsz","kmtbaxu","qgcxjbfx","my","mlhy","bt","bo","q"},9000000,"fxjqzznvg");
    }

    private static int test(int test, String[] tasks, int n, String expected) {
        System.out.println("Test" + (test++));
        System.out.println("tasks: " + print(tasks));
        System.out.println("n: " + n);
        System.out.println("Expected:" + expected);
        String actual = solve(tasks, n);        
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static String solve(String[] tasks, int n) {
        int arrayLength = tasks.length;
        int counter=0;
        while(arrayLength>1){            
            for(int j=0;arrayLength>1 && j<tasks.length;j++){
                String current = tasks[j];
                if(current!=null){
                    ++counter;
                    
                    if(counter==n){
                        tasks[j] = null;
                        arrayLength--;
                        counter=0;
                    }
                }
            }
        }
        
        for(String e:tasks){
            if(e!=null){
                return e;
            }
        }
        return null;
    }

    private static String print(String[] tasks) {
        String result = "";
        for (String v : tasks) {
            result += v + " ";
        }
        return result;
    }

}
