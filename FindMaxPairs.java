/*
 * FindMaxPairs.java
 *
 * Version :   1.0
 */

import java.util.Scanner;
/**
 * This program gives the maximum no of pairs wih the same sum
 *
 * @author      Rajkumar Lenin Pillai
 *
 */

public class FindMaxPairs {
    /**
     * This method performs the sorting of array using the mergesort technique
     *
     * @param n integer n size of array
     *  @param a long array to be sorted
     * @return a  sorted array
     */
    public static   long[] mergesort(long a[],int n){

        long temp[]=new long[a.length];
        int i,j,k,lb1,lb2,ub1,ub2=0,size;
        size=1;

        while (size<n) {

            lb1 = 0;
            k = 0;
            while (lb1 + size < n) {
                lb2 = lb1 + size;
                ub1 = lb2 - 1;
                if (ub1 + size < n) {
                    ub2 = ub1 + size;
                }
                else {
                    ub2 = n - 1;
                }

                i = lb1;
                j = lb2;


                while (i <= ub1 && j <= ub2) {
                    if (a[i] < a[j]) {
                        temp[k++] = a[i++];
                    }
                    else {
                        temp[k++] = a[j++];
                    }
                }

                while (i <= ub1) {
                    temp[k++] = a[i++];
                }

                while (j <= ub2) {
                    temp[k++] = a[j++];
                }

                lb1 = ub2 + 1;

            }
            for (i = 0; i <= ub2; i++) {
                a[i] = temp[i];
            }

            size = size * 2;
        }

        return a;                // return the sorted array
    }
    /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     */
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);

        int n= Integer.parseInt(sc.nextLine());                 // The input integer in first line is stored in variable n

        long temp=0;
        long sum=0;
        long a[]=new long[n];                               // Decalring array a of size 'n'
        for(int i=0;i<n;i++){
            a[i]=Long.parseLong(sc.next());               // Storing the integers entered in each line in array 'a'
        }

        long sumarray[]=new long[(n*(n-1))/2];              // Declaring the sumarray which stores sum of the pairs
        int k=-1;                                           // Used as position variable for sumarray


        //Storing the sum of all possible pairs in sumarray
        for(int i=0;i<a.length;i++) {

            temp = a[i];

            for (int j = i + 1; j < a.length; j++) {
                sum = temp + a[j];
                sumarray[++k]=sum;


            }
        }


        sumarray=mergesort(sumarray,sumarray.length);                //Sorting the sumarray

        int t=1;                                                    // To keep track of most frequently occuring value
        int max=1;
        int pos=1;                                                 // To keep track of position of t
        int i=0;



        // Searching for the most frequently occuring element in sumarray
        while(i<sumarray.length-1){
            if (sumarray[i] == sumarray[i+1]) {
                t++;
            }

        else{
                if(t>max) {
                    max = t;
                    pos=i;
                }
                t=1;
            }
            i++;

        }


        System.out.println(sumarray[pos]);                      // Printing the value of t



    }
}
