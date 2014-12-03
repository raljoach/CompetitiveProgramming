package topcoder.srm236.Parking;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        //0)  
        int test=0;
        test = test(test,new String[]{"C.....P",
         "C.....P",
         "C.....P"},
        6);
        
        //Every car just drives to the opposite parking spot.
        //1)  
                
        test = test(test,new String[]{"C.X.....",
         "..X..X..",
         "..X..X..",
         ".....X.P"},
         16);
        
        //The slalom takes the car 16 units of time.
        //2)  
                
        test = test(test,new String[]{"XXXXXXXXXXX",
         "X......XPPX",
         "XC...P.XPPX",
         "X......X..X",
         "X....C....X",
         "XXXXXXXXXXX"},
        5);
        //This would take 11 instead of 5 units of time if the car on the bottom drove to its nearest parking spot.
        //3)  
                
        test = test(test,new String[]{".C.",
         "...",
         "C.C",
         "X.X",
         "PPP"},
        4);
        //While driving, the cars can be on the same empty spot or parking spot, but they have to finish on different parking spots.
        //4)  
                
        test = test(test,new String[]{"CCCCC",
         ".....",
         "PXPXP"},
         -1);
        //There are not enough parking spots for all the cars.
        //5)  
                
        test = test(test,new String[]{"..X..",
         "C.X.P",
         "..X.."},
        -1);
        //The car can't reach the parking spot.

    }

    private static int test(int test, String[] park, int expected) {
        System.out.println("Test" + (test++));
        System.out.println("input: " + print(park));
        System.out.println("Expected: " + expected);
        int actual = solve(park);
        System.out.println("Actual: " + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }
    
    private static List<Point> parking = new ArrayList<Point>();
    private static List<Point> cars = new ArrayList<Point>();
    private static int solve(String[] park) {
        parking.clear();
        cars.clear();
        findLocations(park);
        if(parking.size()<cars.size()) { return -1; }
        int maxCost = Integer.MIN_VALUE;
        int[][] graph = new int[cars.size()][parking.size()];
        for(int i=0; i<cars.size(); i++){
            Point car = cars.get(i);
            for(int j=0; j<parking.size(); j++){
                Point spot = parking.get(j);
                List<Point> path = findPath(park,car,spot);
                graph[i][j] = path.size()-1;
                maxCost = Math.max(maxCost,graph[i][j]);
            }
        }
/*        System.out.println("All Path costs: ");
        printPaths(graph);
        System.out.println("Binary Search: ");
        */
        int start = 0;
        int end = maxCost;
        int prevCost=-1;
        if(maxCost!=Integer.MIN_VALUE){
         /*   System.out.println("start: " + start);
            System.out.println("end: " + end);*/
            int cost = -1;
            boolean lastOutcome = false;
            while(start<=end){
                cost = start + (end-start)/2;
                //System.out.println("cost (mid): " + cost);

                int[][] graphClone = clone(graph);
                deleteOverCost(graphClone,cost);
                //System.out.println("Delete all edges over cost: " + cost);
                //printPaths(graphClone);
                
                if(canAssignAllCars(graphClone)){
                    lastOutcome = true;
                    prevCost = cost;
                    end = cost-1;
                    //System.out.println("start: " + start);
                    //System.out.println("new end: " + end);
                }
                else{
                    start = cost+1;
                    lastOutcome = false;
                    //System.out.println("new start: " + start);
                    //System.out.println("end: " + end);
                }
            }
            if(lastOutcome){ return cost;}
            else { return prevCost; }
        }
        return -1;
    }

    private static void printPaths(int[][] graph) {        
        System.out.print("   ");
        for(int i=0; i<parking.size(); i++){
            System.out.print(i + " ");
        }
        System.out.println();
        int rowNum = 0;
        for (int[] r : graph) {
            System.out.print(rowNum+": ");
            for(int c : r){
                System.out.print(c + " ");
            }
            System.out.println();
            ++rowNum;
        }  
        System.out.println();
    }

    private static boolean canAssignAllCars(int[][] graph) {
        Map<Point,Boolean> usedSpots = new HashMap<Point,Boolean>();
        List<Car> myCars = new ArrayList<Car>();
        for(int row=0; row<graph.length; row++){
            //boolean found = false;
            //int count=0;
            Car car = new Car(row);
            for(int col=0; col<graph[0].length; col++){               
                if(graph[row][col]>=0){
                    //++count;
                    Point spot = parking.get(col);
                    /*if(!usedSpots.containsKey(spot)){                                          
                        usedSpots.put(spot, true);
                        found = true;
                        break;
                    }*/
                    car.spots.add(spot);
                    car.spotIndex.add(col);
                }
            }
            myCars.add(car);
            /*if(!found){
                return false;
            }*/
        }
        
        Collections.sort(myCars);
        List<Integer> removeCars = new ArrayList<Integer>();
        for(int i=0; i<myCars.size(); i++){
            Car thisCar = myCars.get(i);
            for(int c=0; c<thisCar.spots.size(); c++){
               if(!usedSpots.containsKey(thisCar.spots.get(c))){
                   usedSpots.put(thisCar.spots.get(c),true);
                   removeCars.add(i);
                   //System.out.println("Assign car " + i + " to spot " + thisCar.spotIndex.get(c));
                   break;
               }
            }
        }
        if(removeCars.size()==myCars.size()){
            //System.out.println("True: all cars assigned spots");
            //System.out.println("---------------------------------");
            return true;
        }
        //System.out.println("False: all cars could not be assigned spots");
        //System.out.println("---------------------------------");
        return false;
        
        /*
        for(int col=0; col<graph[0].length; col++){
            int count = 0;
            for(int row=0; row<graph.length; row++){
                if(graph[row][col]>=0){
                    ++count;
                }
            }
                
        }*/
    }

    private static void deleteOverCost(int[][] graph, int cost) {
        for(int row=0; row<graph.length; row++){
            for(int col=0; col<graph[0].length; col++){
                int thisCost = graph[row][col];
                if(thisCost>cost){
                    graph[row][col]=-1;
                }
            }
        }
        
    }

    private static int[][] clone(int[][] graph) {
        int[][] result = new int[graph.length][graph[0].length];
        for(int row=0; row<graph.length; row++){
            for(int col=0; col<graph[0].length; col++){
                result[row][col] = graph[row][col];
            }
        }
        return result;
    }

    private static void findLocations(String[] park) {
        for(int y=0; y<park.length; y++){
            String row = park[y];
            for(int x=0; x<row.length(); x++){
                char loc = row.charAt(x);
                if(loc=='C' || loc=='c'){
                    cars.add(new Point(x,y));
                }
                else if(loc=='P' || loc=='p'){
                    parking.add(new Point(x,y));
                }
            }
        }
        
    }

    private static List<Point> findPath(String[] park, Point start,
            Point target) {

        if(start.x==target.x && start.y==target.y){
            return Arrays.asList(start);
        }
        List<Point> q = new ArrayList<Point>();
        q.add(start);
        Map<Point,Point> backTrack = new HashMap<Point,Point>();
        backTrack.put(start,null);
        while(!q.isEmpty()){
            Point next = q.remove(0);
            for(Point neighbor:getNeighbors(park,next)){
                if(!backTrack.containsKey(neighbor)){
                    boolean found = matches(target,neighbor);
                    if(found){
                        List<Point> result = new ArrayList<Point>();
                        result.add(next);
                        result.add(neighbor);
                        //int nextId = getId(park, next);
                        while(backTrack.containsKey(next) && backTrack.get(next)!=null){
                            Point parent = backTrack.get(next); //getPoint(park,backTrack.get(nextId));
                            result.add(0,parent);
                            next = parent;     
                            //nextId = getId(park, next);
                        }
                        return result;
                    }
                    else{
                        //int neighborId = getId(park, neighbor);
                        //int nextId = getId(park,next);
                        backTrack.put(neighbor,next); //put(neighborId, nextId);
                        q.add(neighbor);
                    }
                }
            }
        }
        return new ArrayList<Point>();
    }

    private static boolean matches(Point target, Point current) {
        return target.x==current.x && target.y==current.y;
    }

    private static Point getPoint(String[] park, int id) {
        int targetCell = id;
        int currentCell = -1;
        for(int row=0; row<park.length; row++){
            for(int col=0; col<park[0].length(); col++){
                ++currentCell;
                if(currentCell == targetCell){
                    
                }
            }
        }
        
        int rowLength = park[0].length();
        int y = targetCell/rowLength;
        int x = targetCell%rowLength;
        return new Point(x,y);
    }

    private static int getId(String[] park, Point next) {
        return next.y*park[0].length() + next.x;
    }

    private static List<Point> getNeighbors(String[] park, Point current) {
        int prevRow = current.y-1;
        int nextRow = current.y+1;
        int prevCol = current.x-1;
        int nextCol = current.x+1;
        int row = current.y;
        int col = current.x;
        List<Point> list = new ArrayList<Point>();
        Point newPoint = new Point(col,prevRow);
        if(withinBounds(park,newPoint)){
            list.add(newPoint);
        }
        newPoint = new Point(col,nextRow);
        if(withinBounds(park,newPoint)){
            list.add(newPoint);
        }
        newPoint = new Point(prevCol,row);
        if(withinBounds(park,newPoint)){
            list.add(newPoint);
        }
        newPoint = new Point(nextCol,row);
        if(withinBounds(park,newPoint)){
            list.add(newPoint);
        }
        return list;
    }

    private static boolean withinBounds(String[] park, Point newPoint) {
        boolean inside = newPoint.x>=0 && newPoint.x<park[0].length() &&
                newPoint.y>=0 && newPoint.y<park.length;
        
        if(!inside) { return false; }
        
        char loc = park[newPoint.y].charAt(newPoint.x);
        return loc!='X' && loc!='x';
    }

    private static boolean findMatch(List<Point> list, Point p) {
        for(Point thisPoint:list){
            if(thisPoint.x==p.x && thisPoint.y==p.y){
                return true;
            }
        }
        return false;
    }

    private static String print(String[] input) {
        String result = "\n";
        for (String r : input) {
            result += r + "\n";
        }
        return result;
    }
}
