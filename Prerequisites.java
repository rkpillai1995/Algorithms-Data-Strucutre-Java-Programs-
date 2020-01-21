
/**
 * Java program for finding number of courses in the longest prerequisite chain.
 *
 * @author Kunal Nayyar, Rajkumar Lenin Pillai
 *
 */

import java.util.Scanner;


class Graph {
    /**
     *
     * Data[] - stores the nodes that the vertex is pointing to.
     *
     */
    int Data[], Vert;

    Graph(int len, int vert) {
        Data = new int[len];
        Vert = vert;
    }

    int size = 0;

    void setData(int data) {
        Data[size++] = data;
    }
}

class Myqueue {
    /**
     * A generic queue implementation
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

    public boolean contains(int value) {
        boolean flag = false;
        if (!queueisempty()) {
            for (int i = 0; i < queue.length; i++) {
                if (queue[i] == value)
                    flag = true;
            }
        }
        return flag;
    }
}


public class Prerequisites {

    /**
     * Initializes graph and calculates the indegree and outdegree
     * Returns maximum number of prereq classes
     */
    static Graph g[]; // creates a graph array

    static int graph_size = 1;
    static Myqueue visited;

    static void topo_sort(Graph[] graph) {
        int LPT[] = new int[graph_size + 1];
        int indgree[] = new int[graph_size + 1];
        // initialize indegree of every node as 0
        for (int i = 0; i < graph_size + 1; i++) {
            indgree[i] = 0;
            LPT[i] = 0;
        }
        // set indegrees of each node according to the number of nodes pointing to it
        for (int i = 1; i < graph_size + 1; i++) {
            for (int j = 0; j < graph[i].Data.length; j++) {
                if (graph[i].Data[j] == 0)
                    continue;
                indgree[graph[i].Data[j]] = indgree[graph[i].Data[j]] + 1;
            }
        }
        // selct nodes with indegree 0
        for (int i = 1; i < graph_size + 1; i++) {
            if (indgree[i] == 0)
                visited.queueadd(i);
        }
        // while we have nodes with indgree 0
        while (!visited.queueisempty()) {
            int j = visited.removeelement();
            if (j!= 0) {
                for (int k = 0; k < graph[j].Data.length; k++) {
                    //reduce indegree of the visited vertex.
                    indgree[graph[j].Data[k]] = indgree[graph[j].Data[k]] - 1;
                    LPT[graph[j].Data[k]] = Math.max(LPT[graph[j].Data[k]], 1 + LPT[j]);
                    if (indgree[graph[j].Data[k]] == 0) {
                        // add to queue if indegree 0
                            visited.queueadd(graph[j].Data[k]);
                    }
                }
            }
        }

        System.out.println(LPT[0]); // print the longest pre-req value
    }


    public static void main(String[] args) throws NumberFormatException {
        /**
         * Main class takes input of the class number and the prerequisites classes for it.
         * Calls the helper function topo_sort to create the graph.
         *
         */
        int vertices;
        int vertex_counter = 0;
        Scanner sc = new Scanner(System.in);
        vertices = sc.nextInt();

        Graph g[] = new Graph[vertices + 1];
        visited = new Myqueue(g.length - 1);
        graph_size = g.length - 1;

        sc.nextLine();
        while (vertex_counter < vertices) {
            vertex_counter++;
            String str = sc.nextLine();
            String[] s = str.split(" ");
            int len;
            if (s.length > 1) {
                len = s.length - 1;
            } else len = s.length;
            g[vertex_counter] = new Graph(len, vertex_counter);

            if (str.equals("0") || str.equals("")) {
                continue;
            } else {
                String[] array = str.split(" ");
                for (int i = 0; i <  array.length; i++) {
                    if (array[i].equals("") || array[i].equals("0")) {
                        continue;
                    } else
                        g[vertex_counter].setData(Integer.parseInt(array[i]));
                }
            }
        }
        topo_sort(g); // call the topo function to solve
    }
}

