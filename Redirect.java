/*
 * Redirect.java
 *
 * Version :   1.0
 */
import java.util.Scanner;
/**
 * This program checks if graph G is strongly connected after reversing a single edge and prints YES otherwise NO
 *
 * @author      Rajkumar Lenin Pillai
 * @author      Kunal Nayyar
 *
 */

public class Redirect {



    public static int poscount=0;                    // Index variable to store visited vertices
    public static int councompoenents=0;             // To count no of scc
    public static  boolean EdgePresentBetweenComponents =false; // To check if there exist a single edge which can be reversed
    public static int u=0; // One vertex of the edge
    public static int v=0; // Other vertex of the edge
    public static int noOfComponentsInGraph =0;               // Total no of strongly connected compoenents in graph


    /**
     * This method performs DFS on the graph
     * @param vertex  The vertex to be visited
     * @param seen    The vertex already visited
     * @param adjlist The adjaceny list of all vertex in graph
     * @param vertexvisitedlist The vertex which are visited
     */

    public static void DFS(int vertex, boolean seen[],int [][]adjlist,int[] vertexvisitedlist) {



        seen[vertex] = true;

        int neighboutOfVertexList[] = adjlist[vertex];
        for (int j = 0; j < neighboutOfVertexList.length; j++) {
            int neighbour = neighboutOfVertexList[j];
            if (neighbour != 0) {
                if (!seen[j]) {
                    DFS(j, seen, adjlist,vertexvisitedlist);  //Recursive DFS call
                }
            }
        }
        vertexvisitedlist[poscount++]=vertex;                 // Storing the visited vertex
    }

    /**
     * This method calls the DFS function and finds all teh visited vertex in DFS
     * @param adjlist The adjaceny list of all vertex in graph
     * @param noofvertices  The total no of vertices in graph
     * @return firstDFS      The list which contains all vertices visited in previous DFS
     */
    public static int[] scc(int [][]adjlist, int noofvertices){



        /* Initalizing  the seen array*/
        boolean seen[] = new boolean[noofvertices + 1];
        for (int i = 0; i < noofvertices+1 ; i++) {
            seen[i] = false;
        }



        int[]vertexvisitedlist=new int[noofvertices+1];


        for (int i = 0; i <noofvertices+1 ; i++) {

            if (!seen[i]) {

                DFS(i,seen,adjlist,vertexvisitedlist);

            }
        }

        int pos=0;

        /* Storing the visited vertex in reverse order so that it can be used as tack for popping the vertices*/
        int firstDFS[]=new int[noofvertices+1];
        for(int i=vertexvisitedlist.length-1; i>=0; i--){
            firstDFS[pos++]=vertexvisitedlist[i];
        }

        return firstDFS;



    }

    /**
     * This method finds transpose of the entire graph
     * @param adjlist The adjaceny list of all vertex in graph
     * @param noofvertices  The total no of vertices in graph
     * @return adjlist      The graph with all edges reversed
     */

    public static int[][] transposeOfAdjlist(int [][] adjlist,int noofvertices) {

        /* Initaizing the transpose graph*/
        int transposeadjlist[][]=new int[noofvertices+1][noofvertices+1];
        for(int i = 0; i < noofvertices; i++)
        {
            transposeadjlist[i] = new int[noofvertices+1];
        }

        /* Computing the transpose of graph*/
        for (int i = 0; i <noofvertices+1; i++) {
            int neighboutOfVertexList[] = adjlist[i];
            for (int j = 0; j < neighboutOfVertexList.length; j++) {
                int neighbour = neighboutOfVertexList[j];
                if (neighbour != 0) {
                    transposeadjlist[j][i]=1;                          // Storing the Reversedthe edges
                }
            }
        }


        return transposeadjlist;
    }


    /**
     * This method performs DFS on the graph in the order of previous DFS visited vertices
     * @param vertex  The vertex to be visited
     * @param seen    The vertex already visited
     * @param adjlist The adjaceny list of all vertex in graph
     * @param vertexvisitedlist The list containing all visited vertex in prrevious DFS
     * @param scc_components    The strongly connected component
     * @param position          The index variable to store the components
     * @return scc_components    The strongly connected component
     */
    public static int[][] DFSAfterTranspose(int vertex, boolean seen[],int [][]adjlist,int[] vertexvisitedlist,int[][] scc_components,int position) {

        seen[vertex] = true;
        councompoenents++;              // Update no of components found till now
        int neighboutOfVertexList[] = adjlist[vertex];
        for (int j = 0; j < neighboutOfVertexList.length; j++) {
            int neighbour = neighboutOfVertexList[j];
            if (neighbour != 0) {
                if (!seen[j]) {
                    scc_components=DFSAfterTranspose(j, seen, adjlist,vertexvisitedlist,scc_components,position);
                }
            }
        }

        scc_components[position][vertex]=1;   // Storing the strongly connected component

        vertexvisitedlist[poscount++]=vertex;   // Storing the visited vertex
        return scc_components;
    }


    /**
     * This methd finds the scc in entire graph
     * @param adjlist The adjaceny list of all vertex in graph
     * @param noofvertices  The total no of vertices in graph
     * @param firstDFS      The list which contains all vertices visited in previous DFS
     * @param oldadjlist     The original graph and their edges
     */
    public static void findsccaftertranspose(int [][]adjlist, int noofvertices,int[] firstDFS,int[][]oldadjlist ){

        /* Initalizing  the seen array*/
        boolean seen[] = new boolean[noofvertices + 1];
        for (int i = 0; i < noofvertices+1 ; i++) {
            seen[i] = false;
        }


        int[]vertexvisitedlist=new int[noofvertices+1];
        poscount=0;
        int[][] scc_components=new int [noofvertices+1][noofvertices+1];
        for (int i = 0; i < noofvertices+1; i++) {
            scc_components[i]=new int[noofvertices+1];
        }
        int position=0;
        for (int i = 0; i <firstDFS.length ; i++) {

            if (!seen[firstDFS[i]]) {
                councompoenents = 0;                      //Counting no of scc till now
                scc_components=DFSAfterTranspose(firstDFS[i],seen,adjlist,vertexvisitedlist,scc_components,position);
                position++;
                if(councompoenents> noOfComponentsInGraph){
                    noOfComponentsInGraph =councompoenents;   //counting total no of scc
                }
            }
        }

        int secondDFS[]=new int[noofvertices+1];
        int pos=0;
        /* Storing the visited vertex in reverse order so that it can be used as tack for popping the vertices*/
        for(int i=vertexvisitedlist.length-1; i>=0; i--){
            secondDFS[pos++]=vertexvisitedlist[i];
        }

        if(!EdgePresentBetweenComponents) {
            findlink(scc_components, position, oldadjlist, noofvertices);   // To find  the single edge which can be reversed
        }

    }

    /**
     * This method finds an edge between two scc
     * @param scc_components    The strongly connected component
     * @param position          The index variable to store the components
     * @param adjlist The adjaceny list of all vertex in graph
     * @param noofvertices  The total no of vertices in graph
     */

    public static void findlink(int[][] scc_components,int position,int[][] adjlist, int noofvertices ){




        int [] firstcomponent=scc_components[0];              // Component with indegree zero
        int [] lastcomponent=scc_components[position-2];      // Component with outdegree zero
        boolean reverseedgefound=false;

        for (int i = 0; i < firstcomponent.length; i++) {
            if(firstcomponent[i]!=0){

                int vertex_from_firstscc=i;

                for (int j = 0; j < lastcomponent.length; j++) {
                    if(lastcomponent[j]!=0){

                        int vertex_from_lastscc=j;

                        if(adjlist[vertex_from_firstscc][vertex_from_lastscc]==1){    // checking if there is edge between scc
                            adjlist[vertex_from_firstscc][vertex_from_lastscc]=0;
                            adjlist[vertex_from_lastscc][vertex_from_firstscc]=1;
                            u=vertex_from_firstscc; // Storing one vertex of edge
                            v=vertex_from_lastscc;  // Storing other vertex of edge
                            reverseedgefound=true;
                            break;
                        }
                    }
                }
                if(reverseedgefound){
                    break;
                }
            }
        }

    }


    /**
     * This method is helper function which is used to run the algorithm twice
     * @param adjlist The adjaceny list of all vertex in graph
     * @param noofvertices  The total no of vertices in graph
     * @param edgereversed  true if edge is reversed and false otherwise
     */
    public static void findscc(int[][]adjlist, int noofvertices,boolean edgereversed){

        int firstDFS[];
        poscount=0;
        firstDFS=scc(adjlist,noofvertices ); // Calling scc to find vertex visited in first DFS operation
        int newadjlist[][]=transposeOfAdjlist(adjlist,noofvertices);  // calling transposeOfAdjlist to reverse alledges of graph
        /*Checking if function is called for first time or second time*/
        if(edgereversed){
            EdgePresentBetweenComponents =true;
        }

        findsccaftertranspose(newadjlist,noofvertices ,firstDFS,adjlist);   // Finding all scc in entire graph

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int noofvertices = Integer.parseInt(sc.next());            //Store no of vertices



        int adjlist[][] = new int [noofvertices+1][noofvertices+1];    // To store the graph

        for(int i = 0; i < noofvertices; i++)
        {
            adjlist[i] = new int[noofvertices+1];
        }


        // To store the adjlist for each vertex in a array.

        int i=0;


        while(sc.hasNextLine()) {

            String line =sc.nextLine();
            Scanner newsc=new Scanner(line);
            while(newsc.hasNext()) {
                int x = Integer.parseInt(newsc.next());
                 adjlist[i][x] = 1;                         // Initializing the vertex position with 1
            }

            i++;

            if (i==noofvertices+1){
                break;
            }

        }

        findscc(adjlist,noofvertices,false);  // Finding scc
        findscc(adjlist,noofvertices,true);   //finding scc after edge is reversed


        /* Checking if graph becomes stromgly connected after reversing the edge*/
        if (noOfComponentsInGraph == noofvertices) {

            System.out.println("YES");         // Printing YES
            System.out.println(u + " " + v);  //  Printing the edge that can be reversed

        } else {
            System.out.println("NO");         //If no such edge print no
        }
    }
}







