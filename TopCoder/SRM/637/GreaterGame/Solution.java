//-----------------------------------------------------------------------
// <copyright file="Solution.cs" company="MyCompany">
//     Copyright (c) MyCompany. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------
package topcoder.srm637.GreaterGame;

public class Solution {

	public static void main(String[] args) {
		
		//0)
		/*
		 * Obviously, Snuke can determine the numbers on Sothe's cards, but he does not necessarily know the order 
		 * in which Sothe is going to play his cards. You are given the information Snuke has about Sothe in a int[] sothe. 
		 * For each i, element i of sothe is either the number on the card Sothe will play in turn i (0-based index), or -1 
		 * if Snuke does not know the card Sothe will play in that turn.
		 */
		
		/*Snuke wants to play according to a strategy that maximizes the expected number of points he'll get. 
		 * Find that strategy and return the corresponding expected number of Snuke's points at the end of the game.
		 */
		 
    	int test=0;
		test = test(
				test,new int[]{4,2}, //snuke
				new int[]{-1,-1}, //sothe
				1.5); //expected
		
		/*Sothe will play the cards 1 and 3 in some unknown order. The best strategy for Snuke is to flip a coin and to play his cards either in the order {2,4} or in the order {4,2} with equal probability. This leads to two equally likely results of the game:
		Snuke plays his 2 against Sothe's 1, and his 4 against Sothe's 3. In this case, Snuke wins both turns and thus scores 2 points.
		Snuke plays his 2 against Sothe's 3, and his 4 against Sothe's 1. In this case, Snuke scores 1 point.
		Therefore, the expected number of Snuke's points is 1.5.*/
		
		//1)	
		    	
		test = test(test,new int[]{4,2},
		new int[]{1,3},
		2.0);
		
		//If Snuke plays card 2 first and card 4 next, he is guaranteed to score 2 points.
		
		//2)			    	
		test = test(test,new int[]{2},
		new int[]{-1},
		1.0);
		//Sothe's only card has to be 1, and thus Snuke is guaranteed to win the only turn of this game.
		
		//3)			    	
		test = test(test,new int[]{1,3,5,7},
		new int[]{8,-1,4,-1},
		2.5);
		
		//4)			    	
		test = test(test,new int[]{6,12,17,14,20,8,16,7,2,15},
		new int[]{-1,-1,4,-1,11,3,13,-1,-1,18},
		8.0);
	}

	private static int test(int test, int[] snuke, int[] sothe, double expected) {
		return 0;
	}

}
