//Kevin Lee
//Cal Poly Pomona: CS 331

import java.io.*;
import java.util.*;
import java.text.*;

class optimalbst3
{
	static boolean showTables = true;
	static int n;
	static double min;
	static double[][] p;
	static double[][] c;
	static int[][] r; 
	static double[] freq;
	static String[] keys;
	//--------------------------------------------------------------------------------------------------------
	public static void main(String args[])
	{
		if(args.length == 0)  System.out.println("Usage: java optimalbst <instance #>");
		else if(args[0].equals("1")) // first instance
		{
			freq = new double[] {0.375, 0.375, 0.125, 0.125};
			keys = new String[] {"Don", "Isabelle", "Ralph", "Wally"};
		}	
		else if(args[0].equals("2")) // second instance
		{
			freq = new double[]  {0.1, 0.2, 0.4, 0.3};
			keys = new String[] {"A", "B", "C", "D"};
		}
		else if(args[0].equals("3")) //third instance
		{
			freq = new double[] {0.142857, 0.142857, 0.142857, 0.142857, 0.142857, 0.142857, 0.142857};
			keys = new String[] {"A", "B", "C", "D", "E", "F", "G"};
		}

    	int n = keys.length;
    	double output = optimalSearchTree(keys, freq, n);
    	System.out.println("Cost of Optimal BST is: " + output);
	}//end of main();
	//--------------------------------------------------------------------------------------------------------
	public static double optCost(double freq[], int i, int j)
	{
   		// Base cases
   		// If there are no elements in this subarray
   		if (j < i) return 0;
     	// If there is one element in this subarray
   		if (j == i) return freq[i];
 
   		// Get sum of freq[i], freq[i+1], ... freq[j]
   		double fsum = sum(freq, i, j);
 
   		// One by one consider all elements as root and recursively find cost
   		// of the BST, compare the cost with min and update min if needed
   		for (int r = i; r <= j; ++r)
   		{
       		double cost = optCost(freq, i, r-1) + optCost(freq, r+1, j);
       		if (cost < min) min = cost;
   		}
 
   		// Return minimum value
   		return min + fsum;
	}
 	//--------------------------------------------------------------------------------------------------------
	// The main function that calculates minimum cost of a Binary Search Tree.
	// It mainly uses optCost() to find the optimal cost.
	public static double optimalSearchTree(String keys[], double freq[], int n)
	{
		double temp = optCost(freq, 0, n-1);
		return temp; 
	}
 	//--------------------------------------------------------------------------------------------------------
	// A utility function to get sum of array elements freq[i] to freq[j]
	public static double sum(double freq[], int i, int j)
	{
    	double s = 0;
    	for (int k = i; k <=j; k++) s += freq[k];
    	return s;
	}
 	//--------------------------------------------------------------------------------------------------------
}//end of optimalbst class