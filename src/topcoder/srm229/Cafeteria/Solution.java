package topcoder.srm229.Cafeteria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Solution {

    public static void main(String[] args) {
        //0)  
        int test = 0;
        test = test(test,
        new int[]{9},
        new int[]{1},
        new int[]{1},
        "02:28");
        //There is only one bus stop. One bus departs at 2:29 and arrives at 2:30. Since the time to walk to the bus stop is 1 minute, the return value is 02:28.
        /*1)  
                
        {6}
        {9}
        {120}
        Returns: "12:17"
        Leaving the house at 12:17 lets me catch the bus at 12:26 which arrives at 2:26 at the university. Note that the bus departing at 12:36 would arrive too late.
        2)  
                
        {6,9}
        {9,10}
        {120,121}
        Returns: "12:19"
        Although the bus needs one minute more from the 2nd bus stop, and the walking time is also one minute longer, it is still better to leave the house at 12:19 and catch the bus at the 2nd bus stop at 12:29.
        3)  
                
        {0,1,2,3,4,5,6,7,8,9}
        {11,11,11,11,11,11,11,11,11,11}
        {190,190,190,190,190,190,190,190,190,190}
        Returns: "11:09"
        This simulates a bus departing every minute.
        4)  
                
        {7,4,0,0,2,1,6,7,7,0,8,6,0,5,0,6,7,9,0,2,4,8,4,7,
        9,2,4,4,3,1,4,5,8,8,2,5,7,8,7,5,6,8,8,0,1,3,5,0,8}
        {26,14,1,4,16,28,16,6,4,5,21,18,5,2,21,21,28,22,5,22,26,16,14,
        19,19,19,4,12,24,4,30,16,28,20,25,2,30,18,4,6,9,22,8,3,7,29,8,30,6}
        {151,264,280,89,63,57,15,120,28,296,76,269,90,106,31,222,
        291,52,102,73,140,248,44,187,76,49,296,106,54,119,54,283,263,
        285,275,127,108,82,84,241,169,203,244,256,109,288,9,262,103}
        Returns: "02:07"
*/

    }

    private static int test(int test, int[] offset, int[] walkingTime, int[] drivingTime,
            String expected) {        
        System.out.println("Test" + (test++));
        System.out.println("offset: " + print(offset));
        System.out.println("walk: " + print(walkingTime));
        System.out.println("drive: " + print(drivingTime));
        System.out.println("Expected: " + expected);
        String actual = solve(offset, walkingTime, drivingTime);
        System.out.println("Actual: " + actual);
        if (expected.equals(actual)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }
    
    private static String solve(int[] offset, int[] walkingTime,
            int[] drivingTime) {
        int target = 14*60 + 30;//convertToMinutes("14/02/28 02:30 pm");
        int latest = Integer.MIN_VALUE;
        for(int i=0; i<offset.length; i++){
            //Time i need to be at bus stop
            target -= drivingTime[i];
            
            //Find nearest bus time >= target time
            int busDepart=offset[i];
            for(; busDepart<= target; busDepart+=10){
                
            }
            
            target = busDepart-walkingTime[i]-10;
            if(target>latest){
                latest = target;
            }                                    
        }
        String result = convertToHHMM(latest);
        return result;
    }
    
    private static String print(int[] elems) {
        String result = "\n[";
        boolean first = true;
        for (int e : elems) {
            if(first){
                first = false;
            }
            else{
                result+=" ";
            }
            result += e;
        }
        result+="]\n";
        return result;
    }


    private static String convertToHHMM(int targetMin) {
        if(targetMin>=0){
            int hour = (targetMin/60)%12;
            if(hour==0) { hour = 12; }
            int min = targetMin%60;
            return String.format("%02d", hour) + ":" + String.format("%02d", min);
        }
        else {
            
            do{
                targetMin = 24*60 + targetMin;
            }while(targetMin<0);
            
            return convertToHHMM(targetMin);
        }
    }

    private static int convertToMinutes(String timeStr) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("YY/MM/dd hh:mm a");

        LocalDateTime time = LocalDateTime.parse(timeStr,formatter);
        int mins = time.getHour() * 60;
        mins+=time.getMinute();
        return mins;
    }

}
