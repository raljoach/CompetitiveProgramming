//-----------------------------------------------------------------------
// <copyright file="Solution.cs" company="MyCompany">
//     Copyright (c) MyCompany. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------
package topcoder.srm636.ChocolateDividingEasy;
import java.util.*;

public class Solution {
	public static void main(String[] args)
	{
		int[][] grid = new int[3][4];
		grid[0] = new int[]{9, 7, 6, 8};
		grid[1] = new int[]{6, 7, 6, 7};
		grid[2] = new int[]{5, 3, 1, 3};
		
		int numVerticalCuts = 2;
		int numHorizontalCuts = 2;
		solve(grid, numVerticalCuts, numHorizontalCuts);
		System.out.println("Ans: " + bestMinPiece);
	}

	private static int bestMinPiece = Integer.MIN_VALUE;
	private static int solve(
			int[][] grid, int numVerticalCuts, int numHorizontalCuts) {		
		
		List<List<Integer>> vCutsList = getCuts(grid.length, numVerticalCuts);
		List<List<Integer>> hCutsList = getCuts(grid[0].length, numHorizontalCuts);
		
		for(List<Integer> vCuts:vCutsList){
			for(List<Integer> hCuts:hCutsList){
				calculateBestMinPiece(grid, hCuts, vCuts);
			}
		}
					
		return bestMinPiece;
	}
	private static List<List<Integer>> getCuts(int length, int numCuts) {
		List<Integer> set = new ArrayList<Integer>();
		for(int j=1; j<length; j++)
		{
			set.add(j);
		}
		List<List<Integer>> results = 
				new ArrayList<List<Integer>>();
		int n = (int)Math.pow(2, length-1);		
		for(int i=0; i<n; i++)
		{
			int k=i;
			int index = 0;
			List<Integer> combo = new ArrayList<Integer>();
			while((k&1)!=0){	
				combo.add(set.get(index));
				k>>=1;
		        ++index;
			}
			if(combo.size()==numCuts){
				results.add(combo);
			}
		}
		return results;
	}
	private static void calculateBestMinPiece(int[][] grid,
			List<Integer> hCuts1, List<Integer> vCuts1) {

		System.out.print("Vertical cuts: ");
		for(int v:vCuts1){
			System.out.print(v + " ");
		}
		System.out.println();
		
		System.out.print("Horizontal cuts: ");
		for(int h:hCuts1){
			System.out.print(h + " ");
		}
		System.out.println();
		System.out.println();
						
		ArrayList<Integer> vCuts = new ArrayList<Integer>(vCuts1);
		vCuts.add(grid[0].length);
		ArrayList<Integer> hCuts = new ArrayList<Integer>(hCuts1);
		hCuts.add(grid.length);
		
		int vPrev = 0, min=Integer.MAX_VALUE;
		for(int i=0; i<vCuts.size(); i++){
			int v = vCuts.get(i);
			int hPrev=0;
			for(int j=0; j<hCuts.size(); j++){				
				int h = hCuts.get(j);
				
				int thisSum=getSum(grid, hPrev,h-1, vPrev, v-1);
				System.out.println("This sum:" + thisSum);			
				min = Math.min(min, thisSum);
				hPrev = h;
			}
            System.out.println("---------------------");
			vPrev = v;
		}
		
		bestMinPiece = Math.max(min, bestMinPiece);
		System.out.println("Best min piece:" + bestMinPiece);
		System.out.println();
		System.out.println("---------------------");		
	}
	private static int getSum(int[][] grid, int rowStart, Integer rowEnd, int colStart, Integer colEnd) {
		System.out.println("Colstart:" + colStart + " Colend:" + colEnd);
		System.out.println("Rowstart:" + rowStart + " Rowend:"+rowEnd);
		
		if(rowEnd<rowStart)
		{
			System.out.println("That sucks!");
		}
		if(colEnd<colStart)
		{
			System.out.println("That sucks!");
		}
		int sum = 0;
		for(int i=rowStart; i<=rowEnd; i++){
			for(int j=colStart; j<=colEnd; j++){
				sum+=grid[i][j];
			}
		}
		return sum;		
	}

}
