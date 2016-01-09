//Kevin Lee
//Cal Poly Pomona: CS 331

import java.io.*;
import java.util.*;
import java.text.*;

class optimalbst
{
	static boolean showTables = true;
	static int n;
	static double[][] p;
	static double[][] c;
	static int[][] r;
	static double[] weight;
	static int key;
	static String[] keys;
	//--------------------------------------------------------------------------------------------------------
	public static void main(String args[])
	{
		if(args.length == 0)  System.out.println("Usage: java optimalbst <instance #>");
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
		n = weight.length;
		p = new double[n+1][n+1];
		getP();
		getCR();

		if(showTables == true) printTables();
		System.out.println("Probabilities: " + Arrays.toString(weight));
		DecimalFormat df = new DecimalFormat("#.###");
		System.out.println("The resulting optimal BST: " + df.format(c[0][c.length-1]));
		//printOrder(0,n);
	}//end of compute();
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
	public static void getCR()
	{
		c = new double[p.length][p.length];
		r = new int[p.length-1][p.length-1];

		//creates first diagnal of numbers
		for(int i = 0; i < p.length-1; i++)
		{
			c[i][i+1] = p[i][i+1];
			r[i][i] = i+1;
		}

		/*int i = 0; int j = 2;
		c[i][j] = p[i][j] + min(i,j);
		r[i][j] = key;
		//System.out.println("min value: " + min(i,j));*/

		// fills in the rest of the table
		int u = 2, x = 1, i = 1, j = 1;
		for(int row = 0; row < p.length-1; row++)
		{
			for(int col = 0; col < p.length-u; col++)
			{
				if ( c[i-1][j-1] + c[i][j+1] <= c[i-1][j] + c[i+1][j+1] )
				{
					c[i-1][j+1] = p[i-1][j+1] + c[i-1][j-1] + c[i][j+1];
					r[i-1][j] = i+1;
					//r[i-1][j] = i;
				}
				else
				{
					c[i-1][j+1] = p[i-1][j+1] + c[i-1][j] + c[i+1][j+1];
					r[i-1][j] = i;
					//r[i-1][j] = i+1;
				}
				j++;
			}
			x++;
			u++;
			i++;
			j = x;
		}
	}//end of getCR();
	//--------------------------------------------------------------------------------------------------------
	public static double min(int i, int j)
	{
		ArrayList<Double> minList = new ArrayList<Double>();
		double minValue = 5000;

		for(int k = j; k > i; k--)	minList.add(c[i][k-1] + c[k+1][j]);
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
		//System.out.println("i: " + i + ", j: " + j);
		if(i == j) System.out.print("A" + i);
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

		System.out.println("\n-- C Table: c(i,j) --\n");
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

		System.out.println();
	}//end of printTables();
	//--------------------------------------------------------------------------------------------------------
}//end of optimalbst class
