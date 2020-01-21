/*
 * NumPaths.java
 *
 * Version :   1.0
 */


import java.util.*;
/**
 * This program gives the no of shortest path from source to destination.
 *
 * @author      Rajkumar Lenin Pillai
 * @author      Kunal Nayyar
 *
 */

public class NumPaths {

    /**
     * This method computes the minimum no of shortest path suing bfs and prints the no of shortest path from surce to destination
     * @param adjlist  The adjlist of each vertex stored in a array
     * @param src      The source vertex
     * @param dest     The destination vertex
     * @param v        The no of vertices
     */

    public static void bfs(int[][] adjlist, int src, int dest, int v){


        // Creating a queue object
        queue q=new queue(v+1);

        // Initializing the dist array with infinity value
        int dist[]=new int[v+1];
        for (int i = 0; i < dist.length; i++) {
            dist[i]=Integer.MAX_VALUE;
        }

        // Initializing the paths array with 0
        boolean seen[]=new boolean[v+1];
        int paths[]=new int[v+1];
        for (int i = 0; i < paths.length; i++) {
            paths[i]=0;
        }


        dist[src]=0;
        paths[src]=1;

        q.queueadd(src);
        seen[src]=true;

        // Implementing BFS with src as root node
        while (!q.queueisempty()){

            int currentvertex= q.removeelement();

            // To explore all the neighbouring vertices of current vertex
            int list_of_neighbouring_vertices[]=new int[adjlist[currentvertex].length];
            for (int i = 0; i <list_of_neighbouring_vertices.length ; i++) {
                list_of_neighbouring_vertices[i]=adjlist[currentvertex][i];
            }

            for (int i = 0; i <list_of_neighbouring_vertices.length ; i++) {

                int neigbour_vertex=list_of_neighbouring_vertices[i];


                if (neigbour_vertex != 0) {    // To prevent accessing the array with elements 0

                    //To check if there is a better path to reach the neighbour vertex
                    if (dist[neigbour_vertex] > dist[currentvertex] + 1) {
                        dist[neigbour_vertex] = dist[currentvertex] + 1;
                        paths[neigbour_vertex] = paths[currentvertex];
                    }

                    // If more number of shortest path is possible
                    else if (dist[neigbour_vertex] == dist[currentvertex] + 1) {
                        paths[neigbour_vertex] += paths[currentvertex];
                    }

                    // If neighbour vertex is not yet seen push it in the queue
                    if (seen[neigbour_vertex] == false) {
                        q.queueadd(neigbour_vertex);
                        seen[neigbour_vertex] = true;
                    }
                }
            }


        }

        // Printing the distance of minimum no of shortest path to reach the destination
        System.out.println(paths[dest]);


    }


    /**
     * This method implements the queue datastructure
     */
    public static class queue{
        public int queue[];
        public int front=-1,rear=-1;
        public  queue(int n){
            queue=new int[n];

        }


        public boolean queueisempty(){

            if (front!=-1){
                return false;
            }
            else{
                return true;
            }

        }

        public void queueadd(int x){
            if(rear!=-1 ){
                rear++;
                queue[rear]=x;
            }
            else if(rear==-1) {
                rear++;
                front=0;
                queue[rear]=x;
            }


        }

        public int removeelement(){
            int removedlement=-1;


            if(front!=rear) {
                front++;
                int pos = front - 1;
                removedlement = queue[pos];
            }
            else if (front == rear) {
                removedlement = queue[front];
                front=-1;
                rear=-1;
            }



            return removedlement;
        }






    }

    /**
     * The main program
     * @param args
     */


    public static void main(String[] args) {



        Scanner sc=new Scanner(System.in);
        int v=Integer.parseInt(sc.next());        //Store no of vertices
        int m=Integer.parseInt(sc.next());        //Store no of edges
        int src=Integer.parseInt(sc.next());      //Store the src vertex
        int dest=Integer.parseInt(sc.next());     //Store the dest vertex


        int adjlist[][] = new int [v+1][v+1];    // To store the graph

        for(int i = 0; i < v; i++)
        {
            adjlist[i] = new int[v+1];
        }

        // To store the adjlist for each vertex in a array.
        for (int i = 0; i < m; i++) {

            int x=Integer.parseInt(sc.next());
            int y=Integer.parseInt(sc.next());
            adjlist[x][y]=y;
            adjlist[y][x]=x;

        }

        bfs(adjlist,src,dest,v);


    }
}
