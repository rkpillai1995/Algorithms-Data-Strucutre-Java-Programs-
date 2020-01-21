/*
 * firstgreedyapproach.java
 *
 * Version :   1.0
 */
import java.util.Scanner;

public class firstgreedyapproach {
    /**
     * This program prints the  Longest Increasing Subsequence using the first greedy approach
     * @author      Rajkumar Lenin Pillai
     * @author      Kunal Nayyar
     */



    public static void firstgreedyapproaches(long a[]) {

        long min;

        for(int i=0;i<a.length;i++) {
            min=a[i];

            for (int j = i; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];

                    i=j+1;
                }
            }
            System.out.print(min+" ");

        }
    }



    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);



        int n = Integer.parseInt(sc.nextLine());                 // The input integer in first line is stored in variable n

        long a[]=new long [n];
        for (int i=0;i<n;i++){
            a[i]=Long.parseLong(sc.next());
        }

        firstgreedyapproaches(a);



    }
}
