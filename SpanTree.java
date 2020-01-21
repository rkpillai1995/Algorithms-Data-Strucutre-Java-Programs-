/*
 * SpanTree.java
 *
 * Version :   1.0
 */

import java.util.Scanner;
/**
 * This program gives  minimum-cost F-containing spanning tree of graph G
 *
 * @author      Rajkumar Lenin Pillai
 * @author      Kunal Nayyar
 *
 */


public class SpanTree {

    /**
     * This method performs mergesort of 1-D arrays based on 3rd element in each array
     * @param a The 2-d array in which 1-d array is stored
     * @param n The size of the array
     * @return a The sorted 2-d array
     */

    public static   int[][] mergesort(int a[][],int n) {

        int temp[][] = new int[n][1];
        int i, j, k, lb1, lb2, ub1, ub2 = 0, size;
        size = 1;

        while (size < n) {

            lb1 = 0;
            k = 0;
            while (lb1 + size < n) {
                lb2 = lb1 + size;
                ub1 = lb2 - 1;
                if (ub1 + size < n) {
                    ub2 = ub1 + size;
                } else {
                    ub2 = n - 1;
                }

                i = lb1;
                j = lb2;


                while (i <= ub1 && j <= ub2) {
                    if (a[i][2] < a[j][2]) {
                        temp[k++] = a[i++];
                    } else {
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
        return a;


    }


    /**
     * This method implements the kruskal algorithm based on the new weights of f-containing  edges and gives the
     * inimum-cost F-containing spanning tree of graph G based on original weight of each edge
     * @param adjlist  The edges of the Graph and their weights
     * @param boss     The array which stores the root of each tree
     * @param size     The size of the set
     * @param noofvertices  The no of vertices in graph
     * @param m             The no of edges
     */

    public static void Kruskal(int [][]adjlist,int[]boss,int[]size,int noofvertices,int m){


        // Sorting the edges
        adjlist=mergesort(adjlist,m);

        // Initializing the set
        int set[][] = new int[noofvertices + 1][1];
        for (int i = 0; i < noofvertices + 1; i++) {
            set[i][0] = i;
        }
        int ans=0;
        boolean containcycle=false;                 // To keep track if edges form cycle
        int AllVertices[]={0};                      //An array to store if all vertices are present in tree


        /*** Iterating through the edges ***/
        for (int j = 0; j < m ; j++) {

            int src = adjlist[j][0];
            int dest = adjlist[j][1];
            int weight=adjlist[j][4];
            int fcontain=adjlist[j][3];



            int u = boss[src];
            int v = boss[dest];

            /* Checking if edges form cycle */
            if( u==v && fcontain==1){

                containcycle=true;
                break;

            }


            else {
                /*Checking if edges don't form a cycle*/
                if (u != v) {

                    ans += weight;
                    //Implementation of union


                    if (size[boss[u]] > size[boss[v]]) {

                        int unionarray[] = new int[set[boss[u]].length + set[boss[v]].length];
                        int pos = 0;

                        for (int i = 0; i < set[boss[u]].length; i++) {
                            unionarray[i] = set[boss[u]][i];
                            pos++;
                        }

                        for (int i = 0; i < set[boss[v]].length; i++) {
                            unionarray[pos++] = set[boss[v]][i];
                        }
                        set[boss[u]] = unionarray;

                        size[boss[u]] += size[boss[v]];

                        int zset[] = set[boss[v]];
                        for (int i = 0; i < zset.length; i++) {
                            boss[zset[i]] = boss[u];
                        }
                        /*Checking if MST has all the vertices visited*/
                        if(unionarray.length==noofvertices) {
                            AllVertices = unionarray;
                        }




                    } else {
                        /* switching u and v*/
                        int temp = 0;
                        temp = u;
                        u = v;
                        v = temp;
                        int unionarray[] = new int[set[boss[u]].length + set[boss[v]].length];
                        int pos = 0;

                        for (int i = 0; i < set[boss[u]].length; i++) {
                            unionarray[i] = set[boss[u]][i];
                            pos++;
                        }


                        for (int i = 0; i < set[boss[v]].length; i++) {
                            unionarray[pos++] = set[boss[v]][i];
                        }
                        /*Checking if MST has all the vertices visited*/

                        if(unionarray.length==noofvertices) {
                            AllVertices = unionarray;
                        }
                        set[boss[u]] = unionarray;
                        size[boss[u]] += size[boss[v]];
                        int zset[] = set[boss[v]];
                        for (int i = 0; i < zset.length; i++) {
                            boss[zset[i]] = boss[u];
                        }
                    }
                }
            }
        }

        /*Checking if f-contianing edge forms a cycle or is the kruskal algo generating forest instead of tree*/
        if(containcycle || AllVertices.length!=noofvertices){

            ans=-1;
            System.out.println(ans);


        }
        /*Printing cost of f-containing min-cost tree*/
        else {
            System.out.println(ans);

        }

    }


    /**
     * The main program
     * @param args
     */


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int v = Integer.parseInt(sc.next());            //Store no of vertices
        int m = Integer.parseInt(sc.next());            //Store no of edges




        int oldadjlist[][]=new int[m][1];


        for (int i = 0; i < m; i++) {
            oldadjlist[i] = new int[5];
        }


        for (int i = 0; i < m; i++) {
            int src = Integer.parseInt(sc.next());            // Store one vertex of edge
            int dest = Integer.parseInt(sc.next());           // Store the other vertex of edge
            int w = Integer.parseInt(sc.next());              // Store the weight of edge
            int fcontaing = Integer.parseInt(sc.next());     //  Edge belongs to F

            oldadjlist[i][0] = src;
            oldadjlist[i][1] = dest;
            oldadjlist[i][2] = w;
            oldadjlist[i][3] = fcontaing;



        }

        //Sorting to find min weight of all edges in graph
        int newadjlist[][]=mergesort(oldadjlist,m);

        // Storing the min weight of all edges in graph
        int minweight=newadjlist[0][2];

        // Modifiying the weights of edges belonging to F as edges with minimum weight in entire graph
        for (int i = 0; i < m; i++) {
            if(newadjlist[i][3]==1){
                newadjlist[i][4]=newadjlist[i][2];
                newadjlist[i][2]=minweight-1;

            }
            if(newadjlist[i][3]==0){
                newadjlist[i][4]=newadjlist[i][2];

            }


        }


        // Inintializing the boss and size array
        int boss[]=new int[v+1];
        int size[]=new int[v+1];

        for (int i = 0; i < v+1; i++) {
            boss[i]=i;
            size[i]=1;
        }


        Kruskal(newadjlist,boss,size,v,m);


    }
}