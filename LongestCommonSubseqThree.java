/*
 * LongestCommonSubseqThree.java
 *
 * Version :   1.0
 */


import java.util.*;
/**
 * This program computes the  longest common subsequence among three given sequences
 *
 * @author      Rajkumar Lenin Pillai
 * @author      Kunal Nayyar
 */

public class LongestCommonSubseqThree {

    /**
     * This method is used to reconstruct the solution and print the LCS
     * @param s int array which stores the length of longest common subsequnece
     * @param u  First input sequence
     * @param v  second input sequence
     * @param w  Third input sequence
     */

    public static void printtheLCS(int s[][][],int u[],int v[],int w[]){

        int n=s[u.length][v.length][w.length];
        int longestcommonsubsequence[]=new int[n];

        // Backtracking loop
        for(int p=u.length,q=v.length,r=w.length;p>0 &&q>0 && r>0;){

            int backtrackValue=s[p][q][r];
            if(backtrackValue==s[p-1][q][r]){

                p--;
            }


            else if(backtrackValue==s[p][q-1][r]){
                q--;
            }
            else if(backtrackValue==s[p][q][r-1]){

                r--;
            }

            else if(u[p-1]==v[q-1] && u[p-1]==w[r-1])
            {
                //System.out.println("Pvalue"+p);
                longestcommonsubsequence[n-1]=u[p-1];
                p--;
                q--;
                r--;
                n--;

            }


        }
        // Printing the LCS
        for (int i = 0; i < longestcommonsubsequence.length; i++) {
            System.out.print(longestcommonsubsequence[i]+" ");
        }


    }


    /**
     * This method finds the length of common sequence among the three sequence and stores them in array s
     * @param u  First input sequence
     * @param v  second input sequence
     * @param w  Third input sequence
     */


        public static void longestcommonsubseq(int u[],int v[],int w[]){



            int s[][][]=new int[u.length+1][v.length+1][w.length+1];

                for (int p = 1; p < u.length+1; p++) {
                    for (int q = 1; q < v.length+1; q++) {
                        for (int r = 1; r < w.length+1; r++) {


                            s[p][q][r] = Math.max(s[p - 1][q][r],Math.max( s[p][q - 1][r],s[p][q][r-1]));
                            //System.out.println(u[p-1]+" "+v[q-1]);
                            if (u[p - 1] == v[q - 1] && u[p - 1]==w[r-1]) {
                                s[p][q][r] = s[p - 1][q - 1][r-1] + 1;
                            }
                        }
                    }
                }

            System.out.println(s[u.length][v.length][w.length]);
            printtheLCS(s,u,v,w);                                                    // Function call to backtrack the solution







        }

    /**
     * The main program
     * @param args command line arguments (ignored)
     */
    public static void main(String[] args) {


        //endregion
        //plength=6;
        //qlength=5;
        //int Sarray2d[][]=new int[qlength+1][plength+1];
        //System.out.println(Arrays.deepToString(Sarray2d));
        //int p[]={1,2,3,4,1};
        //int q[]={3,4,1,2,1,3};

        //int p[]={4 ,3 ,3 ,3 ,3 ,1 ,3 ,3 ,5 ,1};
        //int q[]={1, 3, 2, 5, 5, 5, 3, 1, 5, 3};
        //int r[]={3 ,3 ,2 ,5 ,4 ,5 ,4 ,2 ,1 ,1 };

        // Storing the  length of each sequence
        Scanner sc=new Scanner(System.in);
        int plength=Integer.parseInt(sc.next());
        int qlength=Integer.parseInt(sc.next());
        int rlength=Integer.parseInt(sc.next());


        int p[]=new int[plength];
        int q[]=new int[qlength];
        int r[]=new int[rlength];


        // Storing the sequence in arrays p,q and r
        for(int i=0;i<p.length;i++){
            p[i]=Integer.parseInt(sc.next());
        }

        for(int i=0;i<q.length;i++){
            q[i]=Integer.parseInt(sc.next());
        }

        for(int i=0;i<r.length;i++){
            r[i]=Integer.parseInt(sc.next());
        }


        //System.out.println();


        longestcommonsubseq(p,q,r);                          // Function call to store the length of  LCS using dynamic programing

    }
}
