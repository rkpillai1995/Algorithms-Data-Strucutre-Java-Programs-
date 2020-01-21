/*
 * LongestIncreasingSubseqRecursive.java
 *
 * Version :   1.0
 */

import java.util.Scanner;



public class LongestIncreasingSubseqRecursive {
    /**
     * This program computes the  length of LongestIncreasing Subsequence using recursion
     * @author      Rajkumar Lenin Pillai
     * @author      Kunal Nayyar
     */

    public static int LongestIncreasingSubseqRec(int a[],int start,int oldsubsequence){

        /**
         * The recursive function which computes the length of current sequence using 2 recursive calls one with the element considered
         * and the other without the element being considered
         *
         * @param a  long array which contains the input sequence
         * @param start The starting position of the sequence considered in recursion
         * @param oldsubsequence The last value in the sequence considered so far
         *
         */


        int newsubsequence;
        int length;

        // Base case if all the input sequence is used
        if (start>=a.length){
            return 0;

        }

        // If the element is considered then the length is also considered
        if(a[start]>oldsubsequence){
            newsubsequence=a[start];
            length=1;
        }

        // If the element is not considered
        else {
            newsubsequence=oldsubsequence;
            length=0;
        }

        return Math.max(length+LongestIncreasingSubseqRec(a,start+1,newsubsequence),LongestIncreasingSubseqRec(a,start+1,oldsubsequence));
    }

    public static void main(String[] args)  {
        /*
         * The main program.
         *
         * @param    args    command line arguments (ignored)
         *
         */
        Scanner sc = new Scanner(System.in);


        int n = Integer.parseInt(sc.nextLine());                 // The input integer in first line is stored in variable n

        // To store the input sequence in an array
        int a[]=new int [n];
        for (int i=0;i<n;i++){
            a[i]=Integer.parseInt(sc.next());
        }

        int result;
        //long startTime = System.currentTimeMillis();
        result=LongestIncreasingSubseqRec(a,0,-1);
        //long endTime = System.currentTimeMillis();
        //System.out.println("That took " + (endTime - startTime) + " milliseconds");

        System.out.println(result);                               // Display the length of Longest Increasing Subsequence

    }
}
