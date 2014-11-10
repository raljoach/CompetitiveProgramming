package topcoder.srm207.CaptureThemAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        //0)  
        int test=0;
        test = test(test,
        "a1",
        "b3",
        "c5",
        2);
        //From "a1", the knight can move directly to "b3" and capture the rook. From there, the knight can move directly to "c5" and capture the queen.
       
        //1)  
        test = test(test,        
        "b1",
        "c3",
        "a3",
         3);
        //The rook and the queen are both 1 move away from the knight. Once the knight captures one of them (it doesn't matter which one), it can return to its starting location, and capture the other piece in one more move.
        
        //2)  
        test = test(test,        
        "a1",
        "a2",
        "b2",
         6);
        //The rook and the queen are close, but it takes 6 moves to capture them.
        
        //3)  
        test = test(test,        
        "a5",
        "b7",
        "e4",
         3);
        
        //4)  
        test = test(test,        
        "h8",
        "e2",
        "d2",
         6);

    }

    private static int test(int test, String knight, String rook,
            String queen, int expected) {
        System.out.println("Test" + (test++));
        System.out.println("knight: " + knight);
        System.out.println("rook:" + rook);
        System.out.println("queen:" + queen);
        System.out.println("Expected: " + expected);
        int actual = solve(knight,rook,queen);
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static int solve(String knight, String rook, String queen) {
        List<String> q = new ArrayList<String>();
        q.add(knight);
        int totalMoves = 0;
        boolean hasQueen = false, hasRook = false;
        Map<String,String> backTrack = new HashMap<String,String>();
        while(!q.isEmpty()){
            String next = q.remove(0);
            for(String neighbor:findSuccessors(next)){
                if(!backTrack.containsKey(neighbor)){
                    if( (!hasQueen && (hasQueen=neighbor.equals(queen))) || 
                        (!hasRook && (hasRook=neighbor.equals(rook)))){
                        String current = next;
                        int moves = 1;
                        while(current!=knight && backTrack.containsKey(current)){
                            if(!hasQueen && next.equals(queen)){
                                hasQueen = true;
                            }
                            if(!hasRook && next.equals(rook)){
                                hasRook = true;
                            }
                            current = backTrack.get(current);
                            ++moves;
                        }
                        totalMoves+=moves;
                        if(hasQueen && hasRook){
                            return totalMoves;
                        }
                        else{                            
                            backTrack.clear();
                            knight = neighbor;
                            q.clear();
                            q.add(knight);
                            backTrack.put(knight,null);
                            break;
                        }
                    }
                    else{
                        q.add(neighbor);
                        backTrack.put(neighbor, next);
                    }
                }
                    
            }
        }
        return -1;
    }
    
    private static List<String> findSuccessors(String knight){
        List<int[]> knightDirections = Arrays.asList(
                new int[] {-2,-1},
                new int[] {-2,1},
                new int[] {2,-1},
                new int[] {2,1},
                new int[] {-1,-2},
                new int[] {-1,2},
                new int[] {1,-2},
                new int[] {1,2});
        
        List<String> possibleMoves = new ArrayList<String>();
        char col = knight.charAt(0);
        int row = Integer.parseInt(knight.substring(1));
        for(int[] direction:knightDirections){
           int deltaCol = direction[0];
           int deltaRow = direction[1];
           
           char newCol = (char)(col + deltaCol);
           if(newCol>='a'&&newCol<='z'){
               int newRow = row + deltaRow;
               if(newRow>=1 && newRow<=8){
                   String newPos = new String(new char[]{newCol}) + newRow;
                   possibleMoves.add(newPos);
               }
           }
        }
        return possibleMoves;        
    }

}
