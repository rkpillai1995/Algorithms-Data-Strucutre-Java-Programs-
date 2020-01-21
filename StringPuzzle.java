/*
 * StringPuzzle.java
 *
 * Version :   1.0
 */

import java.util.Scanner;
/**
 * This program gives the minimum no of operations required to convert string u to v
 *
 * @author      Rajkumar Lenin Pillai
 *
 */

public class StringPuzzle{
    /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     */


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        // taking input strings
        String str1= sc.next();
        String str2= sc.next();
        int n = 5;
        // Declaring the array in which ascii values are to be stored
        int ascii1[] = new int[str1.length()];

        int ascii2[] = new int[str2.length()];
        int ascii3[] = new int[str2.length()];
        int ascii4[] = new int[str2.length()];










        /*Storing the ascii value of 1st string*/
        for (int i = 0; i < str1.length(); i++) {

            ascii1[i] = str1.charAt(i);

        }

        /*Storing the ascii value of 1st string*/
        for (int i = 0; i < str2.length(); i++) {

            ascii2[i] = str2.charAt(i);

        }


        /* Finding the difference of ascii values*/

        for (int i = 0; i < ascii1.length; i++) {
            ascii3[i] = ascii1[i] - ascii2[i];
        }


        int min = 9999;
        int count = 0;          // To keep track of start position in group
        int end = 0;            // To keep track of last position in group
        int k=0;
        int nop=0;
        // No of iterations is fixed which is 26
        while (k<=26){

        // Iterating through the entire difference ascii array
        for (int i = 0; i < ascii3.length-1; i++) {

            // Checking for negative groups
            if (ascii3[i] < 0 && ascii3[i + 1] < 0 && (end+1)!=ascii3.length) {

                // Finding minimum ascii value in group
                if (Math.abs(ascii3[i]) < Math.abs(min) && ascii3[i] != 0) {
                    min = ascii3[i];
                }

                count++;

                end = i + 1;


            } else if (min != 99999 &&  ascii3[i]!=0 && ascii3[i]<0) {


                if (Math.abs(ascii3[end]) < Math.abs(min) && ascii3[i] != 0) {
                    min = ascii3[end];
                }

                nop=Math.abs(nop)+Math.abs(min);

                // Subtracting min ascii value for a group
                for (int j = end - count; j <= end; j++) {
                    ascii3[j] = ascii3[j] - min;
                }


                min = 99999;
                end = 0;
                count = 0;


            }
            // Checking for negative groups

            if (ascii3[i] > 0 && ascii3[i + 1] > 0&& (end+1)!=ascii3.length) {

                // Finding minimum ascii value in group
                if (Math.abs(ascii3[i]) < Math.abs(min) && ascii3[i] != 0) {
                    min = ascii3[i];

                }
                count++;

                end = i + 1;


            } else if (min != 99999 && ascii3[i]!=0 && ascii3[i]>0) {


                if (Math.abs(ascii3[end]) < Math.abs(min) && ascii3[i] != 0) {
                    min = ascii3[end];
                }



                nop=Math.abs(nop)+Math.abs(min);

                // Subtracting min ascii value for a group
                for (int j = end - count; j <= end; j++) {
                    ascii3[j] = ascii3[j] - min;

                }
                min = 99999;
                end = 0;
                count = 0;


            }







        }
        k++;

    }


        int totalop=0;

        // Calcualting the ascii value of characters not in any group
        for(int i=0;i<ascii3.length;i++){
            totalop=totalop+Math.abs(ascii3[i]);
        }

        totalop=totalop+nop;
        System.out.println(totalop);                    // Printing the minimum no of operations








    }




}
