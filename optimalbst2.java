//Kevin Lee
//Project description: Implementation of a memory function for the Optimal Binary Search Tree problem.
//Cal Poly Pomona: CS 331

import java.io.*;
import java.util.*;
import java.text.*;

class optimalbst2
{
	static boolean showTables = true;
	static int k;
	static int n;
	static int kUsed;
	static int[][] r;
	//static List<Double> minList;
	static double[][] A;
	static double[][] p;
	static double minValue;
	static double minavg;
	static double[] weight;
	static String[] keys;
	static int key;
	//--------------------------------------------------------------------------------------------------------
	public static void main(String args[])
	{
		if(args.length == 0)  System.out.println("Usage: java memfuncOBST <instance #>");
		else if(args[0].equals("1")) // first instance
		{
			weight = new double[] {0.375, 0.375, 0.125, 0.125};
			keys = new String[] {"Don", "Isabelle", "Ralph", "Wally"};
			compute();
		}
		else if(args[0].equals("2")) // second instance
		{
			weight = new double[]  {0.1, 0.2, 0.4, 0.3};
			keys = new String[] {"A", "B", "C", "D"};
			compute();
		}
		else if(args[0].equals("3")) //third instance
		{
			weight = new double[] {0.142857, 0.142857, 0.142857, 0.142857, 0.142857, 0.142857, 0.142857};
			keys = new String[] {"A", "B", "C", "D", "E", "F", "G"};
			compute();
		}
	}//end of main();
	//--------------------------------------------------------------------------------------------------------
	public static void compute()
	{
		int row = 0; int col = 2;
		n = keys.length;
		p = new double[n+1][n+1];
		r = new int[n+1][n+1];
		getP();
		optsearchtree(n, weight, r);

		if(showTables == true) printTables();
		//DecimalFormat df = new DecimalFormat("#.###");
		//System.out.println(Arrays.toString(weight));
		//System.out.println("The resulting optimal BST for this instance is: " + df.format(c[0][c.length-1]));
	}//end of compute();
	//--------------------------------------------------------------------------------------------------------
	public static double optsearchtree(int n, double weight[], int r[][])
	{
		int i, j, diagonal;
		A = new double[n+1][n+1];

		for(i = 1; i < n; i++)
		{
			A[i][i-1] = 0;
			A[i][i] = weight[i];
			r[i][i] = i;
			r[i][i-1] = 0;
		}
		//A[n+1][n] = 0;
		//r[n+1][n] = 0;

		DecimalFormat df = new DecimalFormat("#.###");

		System.out.println("\n-- A Table: A(i,j) --\n");
		for( i = 0; i < A.length; i++)
		{
			for( j = 0; j < A.length; j++) System.out.print(df.format(A[i][j]) + "\t");
			System.out.println();
		}


		for(diagonal = 1; diagonal <= n - 1; diagonal++)
			for(i = 1; i <= n - diagonal; i++)
			{
				j = i + diagonal;
				A[i][j] = p[i][j] + min(i,j);
				r[i][j] = key;
			}
		minavg = A[0][n];
		return minavg;
		/*DecimalFormat df = new DecimalFormat("#.###");
		for(int m = 0; m < A.length; m++)
		{
			for(int z = 0; z < A.length; z++) System.out.print(df.format(A[m][z]) + "\t");
			System.out.println();
		}*/

	}//end of optsearchtree();
	//--------------------------------------------------------------------------------------------------------
	public static void getP()
	{
		//creates first diagnal if the table is empty
		for(int l = 0; l < weight.length; l++) { p[l][l+1] = weight[l]; }

		// fills in the rest of the table
		int u = 1, m = 0, z = 2, x = 2;
		for(int row = 0; row < weight.length-1; row++)
		{
			for(int col = 0; col < weight.length-u; col++)
			{
				p[m][z] = p[m][z-1] + p[z-1][z];
				z++;
			}
			x++;
			u++;
			m++;
			z = x;

		}
	}//end of getP();
	//--------------------------------------------------------------------------------------------------------
	public static double min(int i, int j)
	{
		ArrayList<Double> minList = new ArrayList<Double>();
		double minValue = 5000;

		for(int k = j; k > i; k--)	minList.add(A[i][k-1] + A[k+1][j]);
		System.out.println(minList);

		//get minimum value and the corresponding key
		for(int z = 0; z < minList.size(); z++)
		{
			if(minList.get(z) < minValue && minList.get(z) > 0)
			{
				minValue = minList.get(z);
				key = minList.indexOf(minValue) + 1;
			}
		}

		System.out.println("min in list: " + minValue);
		System.out.println("key of min: " + key);

		return minValue;
	}
	//--------------------------------------------------------------------------------------------------------
	public static void printOrder(int i, int j)
	{
		if (i == j) System.out.print("A" + i);
		else
		{
			int k = r[i][j];
			System.out.print("(");
			printOrder(i, k);
			printOrder(k+1, j);
			System.out.print(")");
		}
	}
	//--------------------------------------------------------------------------------------------------------
	public static void printTables()
	{
		DecimalFormat df = new DecimalFormat("#.###");

		System.out.println("\n-- P Table: p(i,j) --\n");
		for(int i = 0; i < p.length; i++)
		{
			for(int j = 0; j < p.length; j++) System.out.print(df.format(p[i][j]) + "\t");
			System.out.println();
		}

		/*System.out.println("\n-- C Table: c(i,j) --\n");
		for(int i = 0; i < c.length; i++)
		{
			for(int j = 0; j < c.length; j++) System.out.print(df.format(c[i][j]) + "\t");
			System.out.println();
		}

		System.out.println("\n-- R Table: r(i,j) --\n");
		for(int i = 0; i < r.length; i++)
		{
			for(int j = 0; j < r.length; j++) System.out.print(r[i][j] + "\t");
			System.out.println();
		}

		System.out.println();*/
	}//end of printTables();
	//--------------------------------------------------------------------------------------------------------
}//end of optimal-bst class
