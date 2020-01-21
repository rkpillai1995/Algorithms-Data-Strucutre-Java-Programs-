import java.util.Scanner;

/**
 * Program that prints the endpoints of such an edge (if more than one such edge can be made, print the
 * one whose source vertex is lexicographically earliest, with further ties broken by the destination vertex).
 *
 * NetworkConnect.java
 *
 *  Version:
 *  1.3
 *  12/09/2018
 *
 *  @author - Kunal Nayyar
 *  @author - Rajkumar Lenin Pillai
 *
 */
public class NetworkConnect {


    private static void merge(int arr[], int l, int m, int r) {
        /**
         * Merge sort
         */
        int i, j, k;
        int diff1 = m - l + 1;
        int diff2 = r - m;

        int Larray[] = new int[diff1];
        int Rarray[] = new int[diff2];

        for (i = 0; i < diff1; i++)
            Larray[i] = arr[l + i];
        for (j = 0; j < diff2; j++)
            Rarray[j] = arr[m + 1 + j];

        i = 0;
        j = 0;
        k = l;
        while (i < diff1 && j < diff2) {
            if (Larray[i] < Rarray[j]) {
                arr[k] = Larray[i];
                i++;
            } else {
                arr[k] = Rarray[j];
                j++;
            }
            k++;
        }

        while (i < diff1) {
            arr[k] = Larray[i];
            i++;
            k++;
        }
        while (j < diff2) {
            arr[k] = Rarray[j];
            j++;
            k++;
        }
    }


    private static void mergeSort(int arr[], int left, int right) {
        /**
         * mergeSort
         */
        if (left < right) {

            int m = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(arr, left, m);
            mergeSort(arr, m + 1, right);

            merge(arr, left, m, right);
        }
    }


    static class Myqueue {
        /**
         * Array implementation of Queue
         */

        public int queue[];
        public int front = -1, rear = -1;

        public Myqueue(int n) {
            queue = new int[n];

        }


        public boolean queueisempty() {

            if (front != -1) {
                return false;
            } else {
                return true;
            }

        }

        public void queueadd(int x) {
            if (rear != -1) {
                rear++;
                queue[rear] = x;
            } else if (rear == -1) {
                rear++;
                front = 0;
                queue[rear] = x;
            }


        }

        public int removeelement() {
            int removedlement = -1;

            if (front != rear) {
                front++;
                int pos = front - 1;
                removedlement = queue[pos];
            } else if (front == rear) {
                removedlement = queue[front];
                front = -1;
                rear = -1;
            }
            return removedlement;
        }
    }


    static int V = 0;    //Number of vertices in graph
    static Myqueue queue;
    static int rGraph[][];

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */
    static boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        queue = new Myqueue(V);
        queue.queueadd(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (!queue.queueisempty()) {
            int u = queue.removeelement();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.queueadd(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }

        }
        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }

    /* Using the residual graph and the original graph, it finds all nodes
       reachable from the source, all nodes reachable from the sink and sorts
       the two lists of nodes, Then prints out the first lexicographically
       possible edge that we can add to our graph.
        */
    static void path_bfs(int rGraph[][], int graph[][], int s, int t) {
        // Create a visited array and mark all vertices as not visited
        int vist[] = new int[V];
        int vist_forward[] = new int[V];
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        queue = new Myqueue(V);
        queue.queueadd(s);
        visited[s] = true;
        int count_forward = 0;
        int count_back = 0;

        // Standard BFS Loop from source
        vist_forward[count_forward++] = s;
        while (!queue.queueisempty()) {
            int u = queue.removeelement();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    if ((graph[u][v] != 0 && rGraph[u][v] != 0) && graph[u][v] > 0) {
                        queue.queueadd(v);
                        vist_forward[count_forward++] = v;
                        visited[v] = true;
                    }
                }
            }
        }
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Standard BFS loop from sink
        while (!queue.queueisempty())
            queue.removeelement();

        queue.queueadd(t);
        visited[t] = true;
        vist[count_back++] = t;
        while (!queue.queueisempty()) {
            int u = queue.removeelement();
            for (int j = 0; j < V; j++) {
                if (visited[j] == false && rGraph[j][u] > 0) {
                    if (graph[j][u] != 0 && rGraph[j][u] != 0) {
                        queue.queueadd(j);
                        vist[count_back++] = j;
                        visited[j] = true;
                    }
                }
            }
        }

        //removing extra 0's from vist & vist_forward array
        int bfs_from_source[] = new int[count_forward];
        int bfs_from_dst[] = new int[count_back];

        for(int i = 0; i < count_forward; i++){
            bfs_from_source[i] = vist_forward[i];
        }
        for (int i = 0; i < count_back; i++){
            bfs_from_dst[i] = vist[i];
        }

        //for (int i = 0; i < bfs_from_dst.length; i++){
        //    System.out.print(bfs_from_dst[i] + " ");
        //}
        //System.out.println();
        //for (int i = 0; i < bfs_from_source.length; i ++){
        //    System.out.print(bfs_from_source[i] + " ");
        //}

        mergeSort(bfs_from_source, 0, bfs_from_source.length - 1);
        mergeSort(bfs_from_dst, 0, bfs_from_dst.length - 1);

        here:
        for (int i = 0; i < bfs_from_source.length; i++){
            for (int j = 0; j < bfs_from_dst.length; j++){
                if (graph[bfs_from_source[i]][bfs_from_dst[j]] == 0){
                    // printing the first possible path
                    System.out.println(++bfs_from_source[i] + " " + ++bfs_from_dst[j]);
                    break here;
                }
            }



        }
    }




    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge.
        rGraph = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is path from source to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edges along the path filled by BFS.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
            // Add path flow to overall flow
            max_flow += path_flow;
        }

//        for (int i = 0; i < rGraph.length; i++) {
//            for (int j = 0; j < rGraph.length; j++) {
//                System.out.print(rGraph[i][j] + "\t");
//            }
//            System.out.println();
//        }


        //System.out.println(bfs(rGraph, s, t, parent));
        //System.out.println(bfs(rGraph, 0, 7, parent));

        // Return the overall flow
        return max_flow;

    }

    public static void main(String[] args) {

        NetworkConnect n = new NetworkConnect();

        int vertices, edges, source, sink;

        Scanner sc = new Scanner(System.in);
        vertices = sc.nextInt();
        edges = sc.nextInt();
        source = sc.nextInt();
        sink = sc.nextInt();
        int x, y, w;
        V = vertices;

        int adjlist[][] = new int [V][V];
        for(int i = 0; i < V; i++)
        {
                adjlist[i] = new int[V];

        }
        for (int i = 0; i < edges; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            w = sc.nextInt();
            adjlist[x-1][y-1] = w;
        }

        n.fordFulkerson(adjlist, source - 1, sink - 1);
        path_bfs(rGraph, adjlist, source - 1, sink - 1);
    }
}

