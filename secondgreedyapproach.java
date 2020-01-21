/*
 * secondgreedyapproach.java
 *
 * Version :   1.0
 */
import java.util.Scanner;

public class secondgreedyapproach {
    /**
     * This program prints the  Longest Increasing Subsequence using the second greedy approach
     * @author      Rajkumar Lenin Pillai
     * @author      Kunal Nayyar
     */


    public static long[] secondgreedyapproach(long a[]) {

        long allsubsequence[][]=new long [a.length][a.length];
        int l=0;
        long result[]=new long[a.length];
        for(int i=0;i<a.length;i++){
            allsubsequence[i][0]=a[i];
            l=i;
            for(int j=i;j<a.length-1;j++) {
                if (a[l] < a[j+1]){
                    allsubsequence[i][j+1]=a[j+1];
                    l=j+1;
                }

            }
        }
        int count=0;
        int maxcount=0;
        long longestincreasingsubsequence[]=new long[a.length];
        for(int i=0;i<a.length;i++){

            for(int j=0;j<a.length;j++) {
                if(allsubsequence[i][j]!=0){
                    count+=1;
                }
            }
            if(count>maxcount){
                maxcount=count;
                longestincreasingsubsequence=allsubsequence[i];
            }
            count=0;
        }

        return longestincreasingsubsequence;

    }

        public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);



        int n = Integer.parseInt(sc.nextLine());                 // The input integer in first line is stored in variable n

        long a[]=new long [n];
        for (int i=0;i<n;i++){
            a[i]=Long.parseLong(sc.next());
        }


        long result[];
        result= secondgreedyapproach(a);
        for (int i=0;i<n;i++){
                if(result[i]!=0){
                    System.out.print(result[i]+" ");
                }
        }



    }
}
