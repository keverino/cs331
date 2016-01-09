//Kevin Lee
//Cal Poly Pomona: CS 331

import java.io.*;
import java.util.*;
import java.text.*;

class obst
{
   static String [] idnt;
   static double [] p,q;
   static double [][] c,w;
   static int [][] r;
   static int n;
//--------------------------------------------------------------------------------------------------------
   public static void main(String args[])
   {
      Scanner sc=new Scanner(System.in);
      System.out.println("enter the total number of keys");
      n=sc.nextInt();
      int s=n+2;
      idnt=new String[s];
      c=new double[s][s];
      r=new int[s][s];
      p=new double[s];
      q=new double[s];
      w=new double[s][s];
      System.out.println("enter keys");
      for(int i=1;i<=n;i++) idnt[i]=sc.next();
      System.out.println("enter propability for keys (i.e. 0.375 0.375 0.125 0.125)");
      for(int i=1;i<=n;i++)
      {
         p[i]=sc.nextDouble();
         q[i]=p[i];
      }

      fillArray(0);

      for(int m=2;m<=n;m++)
         for(int i=0,j,k;i<=n-m;i++)
         {
            j=i+m;
            w[i][j]=w[i][j-1]+p[j]+q[j];
            k=findMin(i,j);
            r[i][j]=k;
            c[i][j]=w[i][j]+c[i][k-1]+c[k][j];
         }

       System.out.println("\nProbabilities: " + Arrays.toString(p));
       System.out.println("Result: " + w[0][n-1]);
       System.out.print( "Tree in preorder: ");
       printTree(0,n);
       System.out.println();
   }//end of main()
//--------------------------------------------------------------------------------------------------------
   static int fillArray(int i)
   {
      if(i > n)
      {
         w[n][n]=q[n];
         r[n][n]=0;
         c[n][n]=0;
         return 1;
      }
      else
      {
         w[i][i]=q[i];
         c[i][i]=r[i][i] = 0;
         w[i][i+1]=q[i]+q[i+1]+p[i+1];
         r[i][i+1]=i+1;
         c[i][i+1]=q[i]+q[i+1]+p[i+1];
         return fillArray(i+1);
      }
   }//end of fillArray()
//--------------------------------------------------------------------------------------------------------
   static void printTree(int i,int j)
   {
      if(i<j)
      {
         System.out.print(idnt[r[i][j]] + " ");
         System.out.print("(");
      }
      else return;
      printTree(i,r[i][j]-1);
      printTree(r[i][j],j);
      System.out.print(") ");
   }//end of printTree()
//--------------------------------------------------------------------------------------------------------
   static int findMin(int i,int j)
   {
      int minValue=0; double min=2000;
      for(int m=i+1;m<=j;m++)
         if(c[i][m-1]+c[m][j]<min)
         {
            min=c[i][m-1]+c[m][j];
            minValue=m;
         }
      return minValue;
   }//end of findMin()
 //--------------------------------------------------------------------------------------------------------
}//end of class
