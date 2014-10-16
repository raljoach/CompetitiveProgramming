//-----------------------------------------------------------------------
// <copyright file="Solution.cs" company="MyCompany">
//     Copyright (c) MyCompany. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------
package topcoder.srm636.Sortish;

import java.util.Random;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=9;
		int[] seq = new int[n];
		for(int i=1; i<=9; i++){
			seq[i] = i;
		}
		shuffle(seq);
		int sortedNess = calcSortedness(seq);
		eraseSome(seq);
		solve(seq,sortedNess);
	}
	
	private static void solve(int[] seq, int sortedNess) {
		// TODO Auto-generated method stub
		
	}

	private static void eraseSome(int[] seq) {
		// TODO Auto-generated method stub
		Random randGen = new Random();
		int count = (int)Math.ceil(Math.random()*(seq.length/2));
		for(int i=0; i<count; i++){
			int loc = randGen.nextInt(seq.length);
			seq[loc] = 0;
		}
	}

	private static void shuffle(int[] seq)
	{
		Random randGen = new Random();
		for(int i=seq.length-1; i>=0; i--){
			int loc = randGen.nextInt(i);
			int tmp = seq[loc];
			seq[loc] = seq[i];
			seq[i] = tmp;
		}
			
	}

	private static int calcSortedness(int[] seq)
	{
		int count=0;
		for(int i=0; i<seq.length; i++){
			for(int j=i+1; j<seq.length; j++){
				int firstNum = seq[i];
				int secondNum = seq[j];
				if(firstNum<secondNum){
					count++;
				}
			}
		}
		return count;
	}
}
