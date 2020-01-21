/*
 * LongestIncreasingSubseqCount.java
 *
 * Version :   1.0
 */

import java.util.Scanner;
public class LongestIncreasingSubseqCount {

    /**
     * This program computes the  total number  of  Increasing Subsequence
     * @author      Rajkumar Lenin Pillai
     * @author      Kunal Nayyar
     */

    public static long LongestIncreasingSubseqDynamicProgram(long a[]) {
        /**
         * This method returns the total number  of  Increasing Subsequence
         * @param a long array which contains the input sequence
         * @return sum  total number  of  Increasing Subsequence
         */

        //Implementation of dynamic programming-based  approach
        long s[] = new long[a.length];

        for (int j = 0; j < a.length; j++) {
            s[j] = 1;

            for (int k = 0; k <=j-1; k++) {
                // Total number of increasing sequence including the jth element is stored in jth position
                if (a[k] < a[j] ) {
                    s[j] = (((s[j]% 1000000) + ( s[k]% 1000000)));
                }
            }
        }
        
        long sum=0;
        for(int i=0;i<s.length;i++){
            sum+=s[i];
        }
        sum=((sum % 1000000) + 1);                  // 1 is added to include the empty set subsequence
        return sum;

    }

    public static void main(String[] args) throws Exception {
        /*
         * The main program.
         *
         * @param    args    command line arguments (ignored)
         *
         */
        Scanner sc = new Scanner(System.in);



        int n = Integer.parseInt(sc.nextLine());                 // The input integer in first line is stored in variable n

        // To store the input sequence in an array
        long a[]=new long [n];
        for (int i=0;i<n;i++){
            a[i]=Long.parseLong(sc.next());
        }


        long result=0;
        result=LongestIncreasingSubseqDynamicProgram(a);
        System.out.println(result);                            // Display the total number  of  Increasing Subsequence
    }
}
