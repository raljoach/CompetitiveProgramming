//-----------------------------------------------------------------------
// <copyright file="Solution.cs" company="MyCompany">
//     Copyright (c) MyCompany. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------
package topcoder.srm636.ClosestRabbit;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RabbitLocation{
	public int rabbit;
	public Point location;
	public RabbitLocation nearest;
}
public class Solution {
    private static HashMap<Integer, List<Integer>> emptyCells = new HashMap<Integer, List<Integer>>();

	public static void main(String[] args) throws Exception {
		//test();
		String[] grid = new String[]{
				".#..#"				
		};
		int r = 2;
		solve(grid,r);

	}
	
	private static void solve(String[] grid, int r)
	{
		processGrid(grid);
		ArrayList<RabbitLocation> rabbitLocs = new ArrayList<RabbitLocation>();
		for(int i=1; i<=r; i++)
		{
			Point loc = getEmptyCell();
			RabbitLocation rabbit = new RabbitLocation();
			rabbit.rabbit = r;
			rabbit.location = loc;
			rabbitLocs.add(rabbit);
		}		
		
		createGraph(rabbitLocs);
		int ans = calcConnectedComponents(rabbitLocs);
		System.out.println(ans);
	}
	
	private static Integer calcConnectedComponents(
			ArrayList<RabbitLocation> rabbitLocs) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void createGraph(ArrayList<RabbitLocation> rabbitLocs) {
		// TODO Auto-generated method stub
		for(int i=0; i<rabbitLocs.size(); i++){
			RabbitLocation thisRabbit = rabbitLocs.get(i);
			RabbitLocation nearest = getMinDistance(i,rabbitLocs);
			thisRabbit.nearest = nearest;
		}
	}

	private static RabbitLocation getMinDistance(int i,
			ArrayList<RabbitLocation> rabbitLocs) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void test() throws Exception
	{
		//testCalcDistance();
		
		addEmptyCell(1,5);
		addEmptyCell(2,6);
		addEmptyCell(5,8);
		addEmptyCell(2,8);
		
		Set<Integer> keys = emptyCells.keySet();
		if(keys.size()!=3){
			throw new Exception("Test addEmptyCell failed: Wrong number of keys stored");
		}
		
		for(int key:keys)
		{
			List<Integer> list = emptyCells.get(key);
		    if(key==1){
		    	if(list.size()!=1){
		    		throw new Exception("Test addEmptyCell failed: Incorrect # of entries in Row1 empty cells list ");
		    	}
		    	if(list.get(0)!=5){
		    		throw new Exception("Test addEmptyCell failed: Row 1 list is incorrect!");
		    	}
		    	list.remove(0);
		    	emptyCells.remove(key);
		    }
		    
		    if(key==2){
		    	if(list.size()!=1){
		    		throw new Exception("Test addEmptyCell failed: Incorrect # of entries in Row1 empty cells list ");
		    	}
		    	if(list.get(0)!=5){
		    		throw new Exception("Test addEmptyCell failed: Row 1 list is incorrect!");
		    	}
		    }
		}
	}

	private static Point getEmptyCell()
	{
		//int n = grid.length;
		int n = emptyCells.keySet().size();
		Object[] keys = emptyCells.keySet().toArray();
		
		Random r = new Random();
		int row = r.nextInt()%n;
		Object key = keys[row];
		List<Integer> list = emptyCells.get(key);

		int m = list.size();
		int index = r.nextInt()%m;
		int col = list.get(index);
		
		if(m==1){
			emptyCells.remove(key);
		}
		else{
			list.remove(index);
		}
		
		return new Point(row,col);
	}
	
	private static void processGrid(String[] grid){
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[0].length(); j++){
				char c = grid[i].charAt(j);
				if(c=='.'){
					addEmptyCell(i,j);
				}
			}
		}
	}

	private static void addEmptyCell(Integer row, Integer col) {
		List<Integer> list;
		if(emptyCells.containsKey(row)){
			list = emptyCells.get(row);
		}
		else{
			list = new ArrayList<Integer>();
			emptyCells.put(row, list);
		}
		list.add(col);
	}
	
	private static double calcDistance(Point p1, Point p2)
	{
		return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y-p2.y,2));
	}
	
	private static void testCalcDistance() throws Exception {
		Point p1 = new Point(3,6);
		Point p2 = new Point(5,4);
		double ans = calcDistance(p1,p2);
		double exp = Math.sqrt(8);
		if(ans!=exp)
		{
			throw new Exception("Euclidean distance calculation incorrect! Expected:" + exp + " Ans:"+ans);
		}
		System.out.println("Test CalcDistance passed!");
	}
}
