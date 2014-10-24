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
				".#####.#####..#....#", //8
				 "#......#....#.##..##",//13
				 ".####..#####..#.##.#",//7
				 ".....#.#...#..#....#",//15
				 "#####..#....#.#....#"};//11

		r=19;
		expected = 5.77311527122319;	
		test = test(test, grid, r, expected);		
	}

	private static int test(int test, String[] grid, int rabbitCount, double expected)
			throws Exception {
		System.out.println("Test"+ (++test));
		double avg = solve2(grid,rabbitCount);
		for(String line:grid){
			System.out.println(line);
		}
		System.out.println(rabbitCount);
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
	
	private static double solve2(String[] grid, int r) throws Exception{
		List<Cell> emptyCells = getEmptyCells(grid);
		int n = emptyCells.size();
		if(r>emptyCells.size()){
			throw new Exception("Too many rabbits:" + r + 
					" Not enough empty cells:" + n);
		}
		double[][] C = createPascalTriangle(n);
		//double[][] distMat = calcCellDistances(emptyCells);
		double totalCombos = C[n][r]; 
		double result = 0;
		for(int i=0; i<n; i++){
			//Cell u = emptyCells.get(i);
			for(int j=i+1; j<n; j++){
				//Cell v = emptyCells.get(j);
				int b = countBad(emptyCells,i,j);
				result+=C[n-2-b][r-2]/totalCombos;
			}
		}
		return result;
	}

	private static int countBad(List<Cell> emptyCells,int i, int j) {
		int bad=0;
		Cell u = emptyCells.get(i);
		Cell v = emptyCells.get(j);
		double bestDist = calcDistance(u.location, v.location);
		for(int k=0; k<emptyCells.size();k++){
			if(k==i || k==j) { continue; }
			Cell w = emptyCells.get(k);
			double distU = calcDistance(u.location,w.location);
			double distV = calcDistance(v.location,w.location);
			if(distU<bestDist || distV<bestDist){
				++bad;
			}
			else if(distU==bestDist && k<j){
				++bad;
			}
			else if(distV==bestDist && k<i){
				++bad;
			}
		}
		return bad;
	}

	private static double[][] createPascalTriangle(int n) {
		double[][] pascal = new double[n+1][n+1];
		
		for(int i=0; i<=n; i++){
			for(int k=0; k<=i; k++){
				if(k==0 || k==i){
					pascal[i][k] = 1;
				}
				else{
					double left = pascal[i-1][k-1];
					double right = pascal[i-1][k];
					pascal[i][k] = left + right;
				}
			}
		}
		return pascal;
	}

	private static double[][] calcCellDistances(List<Cell> emptyCells) {
		// TODO Auto-generated method stub
		return null;
	}

	private static double solve(String[] grid, int rabbitCount) throws Exception
	{	
		ArrayList<Cell> emptyCells = getEmptyCells(grid);
		if(rabbitCount>emptyCells.size()){
			throw new Exception("Too many rabbits:" + rabbitCount + 
					" Not enough empty cells:" + emptyCells.size());
		}
		List<List<RabbitLocation>> perms = 
				chooseRabbitCells(emptyCells,rabbitCount);
		System.out.println("Number of combos: " + perms.size());
		double sum = 0;
		double count = perms.size();
		double howMany=0;
		for(List<RabbitLocation> rabbitLocs:perms){
			int[] neighbors = createGraph(rabbitLocs);
			int n = calcComponents(rabbitLocs,neighbors);
			sum+=n;
			howMany++;
		}
		double avg = sum/count;
		return avg;
	}

	private static List<List<RabbitLocation>> chooseRabbitCells(
			List<Cell> emptyCells, int rabbitCount) {
		List<Cell[]> sets = getCombos(emptyCells,rabbitCount);
		List<List<RabbitLocation>> results = new ArrayList<List<RabbitLocation>>();
		for(Cell[] set:sets){
			List<RabbitLocation> rabbitSet = new ArrayList<RabbitLocation>();
			int rCount=0;
			for(int i=0; i<set.length; i++){
				Cell cell = set[i];
				RabbitLocation rabbit = new RabbitLocation();
				rabbit.location = cell.location;
				rabbit.rabbit = rCount++;
				rabbit.label = cell.label;
				rabbitSet.add(rabbit);				
			}
			results.add(rabbitSet);
		}
		return results;
	}

	private static List<Cell[]>  getCombos(
			List<Cell> items,
			int numSlots){
		List<Cell[]> result = new ArrayList<Cell[]>();
		Cell[] combo = new Cell[numSlots];		
		setNextSlot(combo,items,0,numSlots,0,result);
		return result;
	}

	private static void setNextSlot(Cell[] combo, List<Cell> items, int startItemIndex,
			int numSlots, int startSlotIndex,List<Cell[]> result) {
		int slotIndex = startSlotIndex;		
		int remainingSlots = numSlots-slotIndex-1;

		if(!addCombo(combo,slotIndex,numSlots,result)){
			for(int itemIndex=startItemIndex; 
				itemIndex<items.size() && remainingSlots<=(items.size()-itemIndex-1); itemIndex++){
			
				setSlot(combo,items,itemIndex,slotIndex);
				setNextSlot(combo,items,itemIndex+1,numSlots,slotIndex+1,result);
			}
		}
	}

	private static void setSlot(Cell[] combo, List<Cell> items, int itemIndex,
			int slotIndex) {
		combo[slotIndex] = items.get(itemIndex);
		//System.out.println("Slot "+slotIndex+", Cell:"+combo[slotIndex].label);
	}

	private static boolean addCombo(Cell[] combo, int slotIndex, int numSlots,
			List<Cell[]> result) {
		boolean added = false;
		if(slotIndex==numSlots){
			added = true;
			//result.add(combo);
			//System.out.println("------------------");
			//System.out.print("Set: ");
			Cell[] newCombo = new Cell[combo.length];
			int current = 0;
			for(Cell cell:combo){
				//System.out.print(cell.label+" ");
				newCombo[current++]=cell;
			}
			result.add(newCombo);
			//System.out.println("\n------------------");
		}
		return added;
	}

	private static ArrayList<Cell> getEmptyCells(String[] grid) {
		ArrayList<Cell> results = new ArrayList<Cell>();
		int count = 0;
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[0].length(); j++){
				char c = grid[i].charAt(j);
				if(c=='.'){
					results.add(new Cell(new Point(i,j),"E"+(++count)));
				}
			}
		}
		return results;
	}

	private static Integer calcComponents(
			List<RabbitLocation> rabbitLocs,int[] neighbors) throws Exception {		
		int components = 0;
		/*
		for(int i=0; i<rabbitLocs.size(); i++){
			RabbitLocation start = rabbitLocs.get(i);
			if(!start.visited){
				++components;
				bfs(start);
			}
		}*/

		for(int i=0; i<neighbors.length; i++){
			for(int j=0; j<neighbors.length; j++){
				if(i!=j){
					if(neighbors[i]==j && i==neighbors[j] && i<j){
						++components;
					}
				}
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

	private static int[] createGraph(List<RabbitLocation> rabbitLocs) {
		int[] neighbors = new int[rabbitLocs.size()];
		for(int i=0; i<rabbitLocs.size(); i++){
			RabbitLocation thisRabbit = rabbitLocs.get(i);
			RabbitLocation nearest = findClosestRabbit(i,rabbitLocs,neighbors);
			thisRabbit.nearest = nearest;
		}
		return neighbors;
	}

	private static RabbitLocation findClosestRabbit(
			int i,
			List<RabbitLocation> rabbitLocs,
			int[] neighbors) {
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
				if(thisRabbit.location.x < closestRabbit.location.x){
					closestRabbit = thisRabbit;
				}
				else if(thisRabbit.location.x == closestRabbit.location.x && thisRabbit.location.y < closestRabbit.location.y){
					closestRabbit = thisRabbit;
				}
			}
		}
		neighbors[x.rabbit]=closestRabbit.rabbit;	
		return closestRabbit;
	}

	private static double calcDistance(Point p1, Point p2)
	{
		return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y-p2.y,2));
	}
}
