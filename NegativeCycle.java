import java.util.Scanner;

/**
 * Program to determine whether G contains a negative-weight cycle given exactly one negative weight
 *
 * NegaticeCycel.java
 *
 *  Version:
 *  1.1
 *  12/09/2018
 *
 *  @author - Kunal Nayyar
 *  @author - Rajkumar Lenin Pillai
 *
 */
class WeightedGraph {

    private int[][] edges;  // adjacency matrix

    public WeightedGraph(int n) {
        edges = new int[n][n];
    }

    public void addEdge(int source, int target, int w) {
        edges[source][target] = w;
    }

    public boolean isEdge(int source, int target) {
        return edges[source][target] > 0;
    }


    public int getWeight(int source, int target) {
        return edges[source][target];
    }
}


public class NegativeCycle {

    static int V = 0;

    static int minDistance(int dist[], Boolean treeSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!treeSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    static int[] dijkstra(WeightedGraph graph, int src) {
        int dist[] = new int[V]; // The output array. dist[i] has the shortest path to all nodes

        // treeSet[i] will be true if vertex i is included in shortest path tree
        Boolean treeSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and treeSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            treeSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed.
            int u = minDistance(dist, treeSet);

            // Mark the picked vertex as processed
            treeSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in treeSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!treeSet[v] && graph.isEdge(u, v) &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph.getWeight(u, v) < dist[v])
                    dist[v] = dist[u] + graph.getWeight(u, v);
        }

        return dist;
    }


    public static void main(String[] args) throws NumberFormatException {
        int vertices, edges;
        int neg_u = 0, neg_v = 0, neg_w = 0;
        Scanner sc = new Scanner(System.in);
        vertices = sc.nextInt();
        V = vertices;
        edges = sc.nextInt();
        final WeightedGraph graph = new WeightedGraph(vertices);


        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            if (w < 0) {
                neg_u = u;
                neg_v = v;
                neg_w = w;
            }

            graph.addEdge(u - 1, v - 1, w);
        }
        int dist[] = dijkstra(graph, neg_v - 1);
        if (dist[neg_u - 1] + neg_w < 0)
            System.out.println("YES");
        else System.out.println("NO");
    }
}

