import java.util.*;


public class Solution {
	public static void main(String[] args)
	{/*
		Random ranGen = new Random();
		int[][] grid = new int[9][9];
		for(int i=0; i<9; i++)
		{
			for(int j=0; j<9; j++)
			{
				grid[i][j] = ranGen.nextInt(10);
				
			}
		}*/
		int[][] grid = new int[3][4];
		grid[0] = new int[]{9, 7, 6, 8};
		grid[1] = new int[]{6, 7, 6, 7};
		grid[2] = new int[]{5, 3, 1, 3};
		
		ArrayList<Integer> hCuts = new ArrayList<Integer>();
		ArrayList<Integer> vCuts = new ArrayList<Integer>();
		process(grid,hCuts, vCuts);
		System.out.println("Ans: " + bestMinPiece);
	}

	private static int bestMinPiece = Integer.MIN_VALUE;
	private static int process(int[][] grid, ArrayList<Integer> hCuts,
			ArrayList<Integer> vCuts) {
		if(hCuts.size()==2 && vCuts.size()==2){
			calculateBestMinPiece(grid, hCuts, vCuts);
		}
		else {
			int vLast = 1, hLast=1;
			if(vCuts.size()>0)
			{
				vLast = vCuts.get(vCuts.size()-1)+1;
			}
			if(hCuts.size()>0)
			{
				hLast = hCuts.get(hCuts.size()-1)+1;
			}
			for(int v=vLast; vCuts.size()<2 && v<grid[0].length; v++){
				vCuts.add(v);
				process(grid,hCuts,vCuts);
				if(vCuts.size()==2){
					vCuts.remove(vCuts.size()-1);
				}
			}
			for(int h=hLast; hCuts.size()<2 && h<grid.length; h++){
				hCuts.add(h);
				process(grid,hCuts,vCuts);
				if(hCuts.size()==2){
					hCuts.remove(hCuts.size()-1);
				}
			}
		}
		return bestMinPiece;
	}
	private static void calculateBestMinPiece(int[][] grid,
			ArrayList<Integer> hCuts1, ArrayList<Integer> vCuts1) {

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
		
		/*int v1=getSum(grid, 0,hCuts.get(0)-1, 0, vCuts.get(0)-1);
		int v2=getSum(grid, 0,hCuts.get(0)-1,vCuts.get(0), grid[0].length-1);
		int v3=getSum(grid, hCuts.get(1), grid.length-1, 0, vCuts.get(0)-1);
		int v4=getSum(grid, hCuts.get(1), grid.length-1,vCuts.get(0), grid[0].length-1);
		bestMinPiece = Math.max(bestMinPiece,Math.max(v1, Math.max(v2, Math.max(v3, v4))));*/
		
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
//			int thisSum = getSum(grid, hCuts.get(hCuts.size()-1), grid.length-1,vPrev, v-1);
//			min = Math.min(min, thisSum);
//			System.out.println("This sum:" + thisSum);	
			vPrev = v;
		}
		
//		int thisSum = getSum(grid, hCuts.get(hCuts.size()-1), grid.length-1,vCuts.get(vCuts.size()-1), grid[0].length-1);
//		min = Math.min(min, thisSum);
//		System.out.println("Final This sum:" + thisSum);	
//		System.out.println("Min:" + min);	
		bestMinPiece = Math.max(min, bestMinPiece);
		System.out.println("Best min piece:" + bestMinPiece);
		System.out.println();
		
	}
	private static int getSum(int[][] grid, int rowStart, Integer rowEnd, int colStart, Integer colEnd) {
		System.out.println("Rowstart:" + rowStart + " Rowend:"+rowEnd);
		System.out.println("Colstart:" + colStart + " Colend:" + colEnd);
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
