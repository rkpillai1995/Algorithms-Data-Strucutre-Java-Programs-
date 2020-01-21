/*
 * MatrixChainParenthesize.java
 *
 * Version :   1.0
 */

import java.util.Scanner;
/**
 * This program computes the  minimum cost to multiply two matrices and finds the corresponding parenthesizing
 *
 * @author      Rajkumar Lenin Pillai
 * @author      Kunal Nayyar
 */

public class MatrixChainParenthesize {


    public static int ind=0;              // Index of the parenthesis expression to be stored

    /**
     * This method is use to compute the final expression with the parenthesis
     * @param i Left index of expresssion considered so far
     * @param j Right index of expresssion considered so far
     * @param s the heart of solution
     * @param exp the final solution array with the expression
     * @return exp the final solution array with the expression
     */
   public static String[] bracketprinting(int i, int j,
                                           int s[][], String exp[])
    {

        if (i != j){

            exp[ind++]="(";

            int k = s[i][j];
            // Placing the brackets recursively from pos i to k
            bracketprinting(i, k, s,exp);


            exp[ind++]="x";

            // Placing the brackets recursively from pos k to j

            bracketprinting(k + 1, j, s,exp);
            exp[ind++]=")";
        }

        // When we have only one matrix to consider
        else
        {

            exp[ind++]="A"+Integer.toString(i);
            return exp;
        }
        return exp;

    }


    /**
     * This method computes the minimum cost of parenthesizing the matrices
     * @param a the input dimensions of matrices
     */
    public static void MatrixChain(int a[]){

        int n=a.length;
        int R;
        int temp;
        int s[][]=new int[n][n];
        int backtrack[][]=new int[n][n];

        for (int d = 1; d < n ; d++) {

                for (int L = 1; L < n-d ; L++) {
                    R=L+d;

                    s[L][R]=Integer.MAX_VALUE;

                    for (int k =L ; k <= R-1 ; k++) {
                        //System.out.println(k);
                        temp=s[L][k] + s[k+1][R]+ a[L-1]*a[k]*a[R];
                        //System.out.println("testig "+s[L][k]+ " K: "+k+" L: "+L);
                           if(s[L][R] > temp){
                               s[L][R] =temp;
                               backtrack[L][R]=k;  // Storing the position of each openeing and closing brackets in a 2-array for backtracking
                           }

                    }

                }
            }


        System.out.println(s[1][n-1]);    //Printing the minimum cost



        String exp[]=new String[n+500];
        exp=bracketprinting(1, n-1, backtrack,exp); // Function call to print the parentehesis

        // Printing the final solution sored in exp array
        for (int i = 0; i < exp.length; i++) {
            if(exp[i]!=null){
                System.out.print(exp[i]+" ");
            }
        }

    }

    /**
     * The main program
     * @param args command line arguments (ignored)
     */
    public static void main(String[] args) {


        //int a[]={2,3,1,4,2};
        //int a[]={4 ,6 ,3 ,6 ,2 ,8 ,5 ,6 ,1 ,6 ,9 };
        //int a[]={1 ,2 ,3 ,8 ,9 ,1 ,10 ,8 ,5 ,2 ,5 };
        //int a[]={88 ,32 ,91 ,13, 54, 35, 37, 84, 7 ,93, 26, 100, 61, 48, 30, 19, 64 ,36 ,64 ,12 ,79, 72, 35, 49, 44, 7, 96, 46, 72, 52, 27, 60 ,63 ,10, 56, 6, 99, 8, 64, 3, 30, 54, 76, 16, 9 ,77 ,3 ,77 ,86, 72, 91, 35, 78, 18, 66, 27, 12, 65, 50 ,69 ,60 ,19 ,50 ,17, 75, 80, 34, 49, 78, 57, 89, 89, 25, 44, 10, 100, 34, 60, 18 ,64 ,26, 34 ,63, 32 ,32 ,96 ,84 ,15 ,51 ,33 ,7 ,43 ,69 ,25 ,87 ,3 ,69 ,66 ,83 ,74 ,56 };
        //int a[]={6, 3, 3 ,7 ,7, 1, 9, 3, 3, 6, 3, 7, 1, 4, 10, 4, 3, 8, 9, 9, 10, 10, 2 ,9 ,5 ,2, 5, 3 ,2 ,6 ,1 ,10 ,10, 4 ,7 ,10, 4 ,8, 7 ,3 ,4 ,5 ,7 ,3, 5, 6 ,4 ,6 ,1 ,6 ,9 ,10 ,1 ,9 ,5 ,1 ,3 ,3 ,1 ,4 ,5 ,3 ,7 ,7 ,6, 3 ,10 ,9 ,8 ,2 ,9 ,5 ,8 ,1, 4, 8, 3, 7, 7, 3, 6 ,7 ,7 ,3 ,8 ,2 ,8 ,5 ,9 ,8 ,4 ,10 ,2 ,7 ,2 ,4 ,10 ,7 ,6 ,8 ,5 };

        Scanner sc=new Scanner(System.in);
        int n=Integer.parseInt(sc.next());
        int a[]=new int[n+1];

        //String the dimensions of matrices
        for(int i=0;i<n+1;i++){
            a[i]=Integer.parseInt(sc.next());
        }
        MatrixChain(a);





    }
}
