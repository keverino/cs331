//Kevin Lee
//Cal Poly Pomona: CS 331

import java.io.*;
import java.util.*;
import java.text.*;

class optimalbst4
{
  static boolean showTables = true;
  static int n;
  static double min;
  static double[][] w;
  //static double[][] c;
  static double[] cost;
  static int[][] r;
  static double[] p;
  static double[][] a;
  static String[] keys;
  static double result;
  //--------------------------------------------------------------------------------------------------------
  public static void main(String args[])
  {
    if(args.length == 0)  System.out.println("Usage: java optimalbst <instance #>");
    else if(args[0].equals("1")) // first instance
    {
      p = new double[] {0.375, 0.375, 0.125, 0.125};
      keys = new String[] {"Don", "Isabelle", "Ralph", "Wally"};
      //compute();
    }
    else if(args[0].equals("2")) // second instance
    {
      p = new double[]  {0.1, 0.2, 0.4, 0.3};
      keys = new String[] {"A", "B", "C", "D"};
      //compute();
    }
    else if(args[0].equals("3")) //third instance
    {
      p = new double[] {0.142857, 0.142857, 0.142857, 0.142857, 0.142857, 0.142857, 0.142857};
      keys = new String[] {"A", "B", "C", "D", "E", "F", "G"};
      //compute();
    }
  }//end of main();
  //--------------------------------------------------------------------------------------------------------
  public static double A(int i, int j)
  {
     if( a[i][j] == null s)
     {
        //A(i, j)
        A(i, i) = p[i];
        A(i, i-1) = 0;
        A(j+1, j) = 0;
     }
     else result a[i][j];
     return result;
  }
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
  public static void getW()
  {
     // creates first diagnal if the table is empty
     for(int l = 0; l < weight.length; l++) { w[l][l+1] = weight[l]; }

     // fills in the rest of the table
     int u = 1, m = 0, z = 2, x = 2;
     for(int row = 0; row < weight.length-1; row++)
     {
        for(int col = 0; col < weight.length-u; col++)
        {
           w[m][z] = w[m][z-1] + w[z-1][z];
           z++;
        }
        x++;
        u++;
        m++;
        z = x;

     }
  }//end of getW();
  //--------------------------------------------------------------------------------------------------------
}//end of optimalbst class
