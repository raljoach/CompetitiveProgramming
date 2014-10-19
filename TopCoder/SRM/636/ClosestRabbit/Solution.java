//-----------------------------------------------------------------------
// <copyright file="Solution.cs" company="MyCompany">
//     Copyright (c) MyCompany. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------
package topcoder.srm636.ClosestRabbit;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import sun.misc.Queue;

public class Solution {
    public static void main(String[] args) throws Exception {
    	int test=-1;
        
    	//0
		String[] grid = new String[]{
				".#.#."				
		};
		int r = 2;
		double expected = 1.0;
		test = test(test, grid, r, expected);		
		
		//1
		grid = new String[]{
				"..###.",
		 		".###.#"};		 
		r = 4;
		expected = 1.6;
		test = test(test, grid, r, expected);
		
		//2
		grid = new String[]{
				"..###.",
		 		".###.#"};		 
		r=5;		 
		expected = 2.0;
		test = test(test, grid, r, expected);
		
		//3
		grid = new String[]{
				".....",
				"#...."};
		 
		r=4;		 
		expected = 1.253968253968254;
		test = test(test, grid, r, expected);
		
		//4
		grid = new String[]{
				".#####.#####..#....#",
				 "#......#....#.##..##",
				 ".####..#####..#.##.#",
				 ".....#.#...#..#....#",
				 "#####..#....#.#....#"};
 
		r=19;
		expected = 5.77311527122319;	
		test = test(test, grid, r, expected);
	}

	private static int test(int test, String[] grid, int r, double expected)
			throws Exception {
		System.out.println("Test"+ (++test));
		double avg = solve(grid,r);
		for(String line:grid){
			System.out.println(line);
		}
		System.out.println("Expected:"+expected);
		System.out.println("Actual:"+avg);
		if(expected==avg)
		{
			System.out.println("PASSED");
		}
		else {
			System.out.println("FAILED");
		}
		System.out.println("-----------------");
		return test;
	}
	
	private static double solve(String[] grid, int rabbitCount) throws Exception
	{	
		ArrayList<Cell> emptyCells = getEmptyCells(grid);
		if(rabbitCount>emptyCells.size()){
			throw new Exception("Too many rabbits:" + rabbitCount + 
					            " Not enough empty cells:" + emptyCells.size());
		}
	    List<List<RabbitLocation>> perms = 
	    		getAllPermutations(emptyCells,rabbitCount);
	    double sum = 0;
	    double count = perms.size();
	    for(List<RabbitLocation> rabbitLocs:perms){
	    	createGraph(rabbitLocs);
	    	sum += calcComponents(rabbitLocs);
	    }
	    double avg = sum/count;
		return avg;
	}
	
	private static List<List<RabbitLocation>> getAllPermutations(
			ArrayList<Cell> emptyCells, int rabbitCount) {
		int count = 0;
		List<String> labels = new ArrayList<String>();
		for(int i=0; i<emptyCells.size(); i++){
			if(rabbitCount>0){
				labels.add("R"+rabbitCount);
				--rabbitCount;
			}
			else{
				count++;
				labels.add("E"+count);
			}
		}
				
		List<List<String>> labelPerms = getPerms(labels,0);
		
		List<List<RabbitLocation>> results = new ArrayList<List<RabbitLocation>>();
		for(List<String> set:labelPerms){
			List<RabbitLocation> rabbitSet = new ArrayList<RabbitLocation>();
			for(int i=0; i<set.size(); i++){
				String label = set.get(i);
				Cell cell = emptyCells.get(i);
				if(label.startsWith("R")){					
					RabbitLocation rabbit = new RabbitLocation();
					rabbit.location = cell.location;
					rabbit.rabbit = Integer.parseInt(label.substring(1));
					rabbitSet.add(rabbit);
				}
			}
			results.add(rabbitSet);
		}
		return results;
	}

	private static List<List<String>>  getPerms(			
			List<String> labels,
			int index) {
		List<List<String>> result = new ArrayList<List<String>>();
		if(index==labels.size()) { 			
			result.add(new ArrayList<String>());
			return result;
		}
		String item = labels.get(index); 
		List<List<String>> subPerms = getPerms(labels,index+1);
		for(List<String> subSet:subPerms){
			for(int i=0; i<=subSet.size(); i++){
				List<String> newSet = new ArrayList<String>(subSet);
				newSet.add(i, item);
				result.add(newSet);
			}			
		}
		return result;
	}

	private static ArrayList<Cell> getEmptyCells(String[] grid) {
		ArrayList<Cell> results = new ArrayList<Cell>();
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[0].length(); j++){
				char c = grid[i].charAt(j);
				if(c=='.'){
					results.add(new Cell(new Point(i,j)));
				}
			}
		}
		return results;
	}

	private static Integer calcComponents(
			List<RabbitLocation> rabbitLocs) throws Exception {		
		int components = 0;
		for(int i=0; i<rabbitLocs.size(); i++){
			RabbitLocation start = rabbitLocs.get(i);
			if(!start.visited){
				++components;
				bfs(start);
			}
		}
		return components;
	}

	private static void bfs(RabbitLocation start) throws InterruptedException {
		Queue q = new Queue();
		q.enqueue(start);
		start.visited = true;
		
		while(!q.isEmpty()){
			RabbitLocation next = (RabbitLocation) q.dequeue();
			if(!next.nearest.visited){
				next.nearest.visited = true;
				q.enqueue(next.nearest);
			}
		}
	}

	private static void createGraph(List<RabbitLocation> rabbitLocs) {
		for(int i=0; i<rabbitLocs.size(); i++){
			RabbitLocation thisRabbit = rabbitLocs.get(i);
			RabbitLocation nearest = findClosestRabbit(i,rabbitLocs);
			thisRabbit.nearest = nearest;
		}
	}

	private static RabbitLocation findClosestRabbit(int i,
			List<RabbitLocation> rabbitLocs) {
		double minDist = Double.MAX_VALUE;		
		RabbitLocation x = rabbitLocs.get(i);
		RabbitLocation closestRabbit = null;
		for(int j=0; j<rabbitLocs.size(); j++){
			if(j==i){ continue; }
			RabbitLocation thisRabbit = rabbitLocs.get(j);
			double dist = calcDistance(x.location,thisRabbit.location);
			if(dist<minDist){
				minDist = dist;
				closestRabbit = thisRabbit;
			}
			else if(dist==minDist){
				if(thisRabbit.location.y < closestRabbit.location.y){
					closestRabbit = thisRabbit;
				}
			}
		}
			
		return closestRabbit;
	}
	
	private static double calcDistance(Point p1, Point p2)
	{
		return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y-p2.y,2));
	}
}
