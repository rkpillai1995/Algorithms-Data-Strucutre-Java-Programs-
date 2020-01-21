/*
 * LongestIncreasingSubseqDP.java
 *
 * Version :   1.0
 */

import java.util.Scanner;

public class LongestIncreasingSubseqDP {
    /**
     * This program computes the  length of LongestIncreasing Subsequence using Dynamic Programming
     * @author      Rajkumar Lenin Pillai
     * @author      Kunal Nayyar
     */


    public static long LongestIncreasingSubseqDynamicProgram(int a[]){

        /**
         * This method returns the  length of Longest Increasing Subsequence
         * @param a long array which contains the input sequence
         * @return max length of Longest Increasing Subsequence
         */

        //Implementation of dynamic programming-based  approach
        int s[]=new int[a.length];
        for(int j=0;j<a.length;j++){
            s[j]=1;
            for(int k=0;k<=j-1;k++){
                if(a[k]<a[j] && s[j]<s[k]+1){
                    s[j]=s[k]+1;
                }
            }
        }

        // To find the element with maximum value in array
        long max=0;
        for(int i=0;i<s.length;i++){
            if (s[i]>max){
                max=s[i];
            }
        }

        return max;

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

        long result=0;
        //long startTime = System.currentTimeMillis();

        result=LongestIncreasingSubseqDynamicProgram(a);

        //long endTime = System.currentTimeMillis();
        // System.out.println("That took " + (endTime - startTime) + " milliseconds");
        System.out.println(result);

    }
}
