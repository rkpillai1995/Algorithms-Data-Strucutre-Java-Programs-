/*
 * SmallestTwo.java
 *
 * Version :   1.0
 */

import java.util.Scanner;

/**
 * This program stores the first input integer in variable 'n' gives the  smallest and second smallest values among the further list of integers.
 *
 * @author      Rajkumar Lenin Pillai
 *
 */

public class SmallestTwo {
    /**
     * The main program.
     *
     * @param args          command line arguments (ignored)
     */

    public static void main(String[] args) {
        //System.out.println("Enter the nos: ");
        Scanner sc =new Scanner(System.in);

        int n= Integer.parseInt(sc.nextLine());                 // The input integer in first line is stored in variable n
        int a[]=new int[n];                                     // Decalring array a of size 'n'

        for(int i=0;i<n;i++){
            a[i]=Integer.parseInt(sc.nextLine());               // Storing the integers entered in each line in array 'a'
        }


        int small;                                              // Used as a temporary variable

        // Bubble Sort technique
        for (int i=0;i<=n-2;i++) {
            for (int j = 0; j <=n-2; j++) {

                // Checking the value of element in current and next postion
                if (a[j] > a[j + 1]) {

                    //Swapping the positions of large integer with small integer

                    small = a[j];
                    a[j] = a[j+1];
                    a[j+1] = small;
                    //8System.out.println("small" + small);
                }
            }
        }
            /**for ( int i=0;i<n;i++){
                System.out.println(a[i]);
            }**/

        System.out.println(a[0]);                                   // Printing the smallest value
        System.out.println(a[1]);                                   // Printing the second smallest value


    }
}
