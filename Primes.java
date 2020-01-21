/*
 * Primes.java
 *
 * Version :   1.0
 */

import java.util.Scanner;

/**
 * This program gives a list of prime numbers less than or equal to input no 'n' in increasing order.
 *
 * @author      Rajkumar Lenin Pillai
 *
 */

public class Primes {
    /**
     * This method checks if the specified number is a prime no or not
     *
     * @param n integer n
     */

    public static void checkprime(int n) {

        // This loop checks if the number is divisible by a number other than itself
        for (int i = 2; i <n ; i++) {
            if (n % i == 0) {
                //System.out.println("not  prime" + n);
                return;
            }
        }

        System.out.println(n); // Print the prime numbers

    }
    /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     */

    public static void main(String[] args) {

        //System.out.print("Enter the no:");
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());                    //  Store the first input in variable n


        // This loop checks every no less than or equal to n is prime or not
        for (int j=2;j<=n;j++) {

            if(n==0 || n==1) {
            break;
            }

            checkprime(j);                                       // Call to method checkprime

        }
    }
}















