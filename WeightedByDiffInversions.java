/*
 * WeightedByDiffInversion.java
 *
 * Version :   1.0
 */



import java.util.Scanner;

/**
 * This program computes the  weighted count of inversions for a  given input sequence
 *
 * @author      Rajkumar Lenin Pillai
 * @author      Kunal Nayyar
 */


public class WeightedByDiffInversions {



    public static long difference = 0;                           // The final result which contains the weighted count of inversions

    /*
     * This method performs sorting of array using mergesort

     * @param a  int array to be sorted
     * @param n  int size of the array to be sorted
     * @return a  sorted array
     *
     * */
    public static long[] mergesort(long a[], int n) {

        long temp[] = new long[a.length];           // To store the result of sorted array
        int i, j, k, lb1, lb2, ub1, ub2 = 0, size;
        size = 1;
        long sumOfLeftSortedArray = 0;
        while (size < n) {

            lb1 = 0; // lb1 is starting position of left subarray
            k = 0;
            while (lb1 + size < n) {
                lb2 = lb1 + size; // lb2 is starting position of right subarray
                ub1 = lb2 - 1;   //  ub1 is last position of left subarray
                if (ub1 + size < n) {
                    ub2 = ub1 + size; //  ub2 is last position of right subarray
                } else {
                    ub2 = n - 1;
                }

                i = lb1;
                j = lb2;
                sumOfLeftSortedArray = 0;                      // To keep track of the sum of the elements of left array after dividing the original array


                // To find the sum of elements of the left subarray
                for (int l = lb1; l <= ub1; l++) {
                    sumOfLeftSortedArray = sumOfLeftSortedArray + a[l];
                }

                // Loop to compare  elements of left and right subarray
                while (i <= ub1 && j <= ub2) {
                    if (a[i] < a[j]) {

                        // If element on the left array is less compared to element to right array we remove the element's contibution from the sum of left subarray
                        sumOfLeftSortedArray = sumOfLeftSortedArray - a[i];
                        temp[k++] = a[i++];

                    }
                    else {
                        // The weighted count is updated if value of left subarray is greater than the value in right subarray
                        // The updated value in each comparison is the sum of all differences between the left subarray elements and element on the right subarray
                        WeightedByDiffInversions.difference = WeightedByDiffInversions.difference + (sumOfLeftSortedArray - ((ub1 + 1 - i) * a[j]));
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

        return a;                                                   // return the sorted array
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


        long a[] = new long[n];

        // To store the input sequence in an array
        for(int i=0;i<a.length;i++){
            a[i] = Long.parseLong(sc.next());
        }

        mergesort(a, a.length);                              // Calling the mergersort method to perform computions to find the weighted sequence

        System.out.println(WeightedByDiffInversions.difference);   //Display the weighted count of inversions

    }

}

