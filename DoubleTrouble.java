import java.util.Scanner;

/**
 * Java program for finding helping Thing 1 and 2 out of the house
 *
 * @author Kunal Nayyar, Rajkumar Lenin Pillai
 *
 */

public class DoubleTrouble {
    /**
     *  A graph class that stores current position of a given node.
     *  Along with the all of its parent nodes.
     */
    static class Graph {
        /**
         *
         */
        int pos_x1;    //current 1 pos
        int pos_x2;
        int pos_y1;
        int pos_y2;// current 2 pos
        Graph parent;

        Graph(int x1, int y1, int x2, int y2, Graph parent) {
            this.pos_x1 = x1;
            this.pos_y1 = y1;
            this.pos_x2 = x2;
            this.pos_y2 = y2;
            this.parent = parent;
        }

        Graph getParent(){
            // returns parents
            return parent;
        }

    }

    static class CircularQueue<T> {
        /**
         * An array implementation of CircularQueue
         */
        T[] data;
        int first = -1;
        int last = -1;
        int size; // for quick access

         CircularQueue(int size) {
            this.size = size;
            data = (T[]) new Object[size];
        }

         boolean isEmpty() {
            return first == -1 && last == -1;
        }

         void enqueue(T el) throws IllegalStateException {
            if (isEmpty()) {
                first = last = 0; }
            else { last = (last + 1) % size; }
            data[last] = el;
        }
         T dequeue() throws IllegalStateException {
            T tmp;

            if (first == last) {
                tmp = data[first];
                first = last = -1;
            } else {
                tmp = data[first];
                first = (first + 1) % size;
            }
            return tmp;
        }
    }

    // list of static variables
    static String adjMatrix[][] = new String[0][0];
    static int a = 0;
    static int b = 0;
    static CircularQueue<Graph> myqueue;
    static boolean visited[][][][];


    private static Graph solveMaze(int x1, int y1, int x2, int y2) throws OutOfMemoryError {
        /**
         * Initializes CQueue, visited matrix, and starts checking current exit cases
         *  else next possible moves.
         * @param x1,y1,x2,y2 coordinates of Point 1 and 2
         */
        //initializing circular queue with an arbitrary value
        myqueue = new CircularQueue<>(800000);
        //put first positions of 1&2 as start positions into the queue with null as their parent
        myqueue.enqueue(new Graph(x1, y1, x2, y2, null));
        //initializing boolean array that stores if a given combination of points has been visited previously
        visited = new boolean[a][b][a][b];
        while (!myqueue.isEmpty()) {
            Graph g = myqueue.dequeue();
            // initialize graph node with current coordinates of Point 1 and 2
            x1 = g.pos_x1;
            y1 = g.pos_y1;
            x2 = g.pos_x2;
            y2 = g.pos_y2;

            // exit cases : if both Points 1 and 2 are on either of the boundaries of the maze at the same time.
            if ((x1 == 0 && x2 == 0)
                    || (x1 == a - 1 && x2 == a - 1) ||
                    (y1 == 0 && y2 == 0) ||
                    (y1 == b - 1 && y2 == b - 1)) {
                //System.out.println("Exit is reached!");
                return g;
            }

            // Checking if previously both Point 1 and 2 were at these coords., then skip the loop.
            if (visited[x1][y1][x2][y2]){
                continue;
            }

            // Checking for all possible Valid moves
            //down , (if both can move one row down together)
            if (isSafe(g.pos_x1 + 1, g.pos_y1) && isSafe(g.pos_x2 + 1, g.pos_y2)) {
                if (x1!= a-1 && x2!= a-1){
                    Graph next_g = new Graph(x1 + 1, y1, x2 + 1, y2, g);
                    if (!visited[x1 + 1][y1][x2 + 1][y2]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            // point 1 : moves down
            else if (isSafe(g.pos_x1 + 1, g.pos_y1)) {

                if (x2 != a - 1) {
                    Graph next_g = new Graph(x1 + 1, y1, x2, y2, g);
                    if (!visited[x1 + 1][y1][x2][y2]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            // point 2 moves down
            else if (isSafe(g.pos_x2 + 1, g.pos_y2)) {

                if (x1 != a - 1) {
                    Graph next_g = new Graph(x1, y1, x2 + 1, y2, g);
                    if (!visited[x1][y1][x2+1][y2]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            // up (if both can move one row up together)
            if (isSafe(g.pos_x1 - 1, g.pos_y1) && isSafe(g.pos_x2 - 1, g.pos_y2)) {
                if (x1 != 0 && x2 != 0){
                    Graph next_g = new Graph(x1 - 1, y1, x2 - 1, y2, g);
                    if (!visited[x1- 1][y1][x2 - 1][y2]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            // Point 1 moves up
            else if (isSafe(g.pos_x1 - 1, g.pos_y1)) {

                if (x2 != 0) {
                    Graph next_g = new Graph(x1 - 1, y1, x2, y2, g);
                    if (!visited[x1 - 1][y1][x2][y2]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            // Point 2 moves up
            else if (isSafe(g.pos_x2 - 1, g.pos_y2)) {

                if (x1 != 0) {
                    Graph next_g = new Graph(x1, y1, x2 - 1, y2, g);

                    if (!visited[x1][y1][x2 - 1][y2]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            //left (if both can move one col left together)
            if (isSafe(g.pos_x1, g.pos_y1 - 1) && isSafe(g.pos_x2, g.pos_y2 - 1)) {

                if(y1 != 0 && y2 != 0){
                    Graph next_g = new Graph(x1, y1 - 1, x2, y2 - 1, g);
                    if (!visited[x1][y1 - 1][x2][y2-1]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            // Point 1 moves left
            else if (isSafe(g.pos_x1, g.pos_y1 - 1)) {

                if (y2 != 0) {
                    Graph next_g = new Graph(x1, y1 - 1, x2, y2, g);
                    if (!visited[x1][y1 - 1][x2][y2]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            // Point 2 moves left
            else if (isSafe(g.pos_x2, g.pos_y2 - 1)) {

                if (y1 != 0) {
                    Graph next_g = new Graph(x1, y1, x2, y2 - 1, g);
                    if ( !visited[x1][y1][x2][y2-1]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            //right (if both can move one col right together)
            if (isSafe(g.pos_x1, g.pos_y1 + 1) && isSafe(g.pos_x2, g.pos_y2 + 1)) {

                if (y1 != b-1 && y2 != b-1){
                    Graph next_g = new Graph(x1, y1 + 1, x2, y2 + 1, g);
                    if (!visited[x1][y1 + 1][x2][y2+1]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            // Point 1 moves right
            else if (isSafe(g.pos_x1, g.pos_y1 + 1)) {

                if (y2 != b - 1) {
                    Graph next_g = new Graph(x1, y1 + 1, x2, y2, g);
                    if (!visited[x1][y1 + 1][x2][y2]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }
            // Point2 moves right
            else if (isSafe(g.pos_x2, g.pos_y2 + 1)) {

                if (y1 != b - 1) {
                    Graph next_g = new Graph(x1, y1, x2, y2 + 1, g);
                    if (!visited[x1][y1][x2][y2+1]) {
                        myqueue.enqueue(next_g);
                    }
                }
            }

            // for the current Coords of Point 1 & 2, if all possible moves explored:
            //  then set current's Visited=true.
            visited[x1][y1][x2][y2] = true;
        }
        return null;
    }

    private static boolean isSafe(int x, int y) {
        /**
         * returns TRUE if the coords respond to '.' and not 'x'
         */
        return (x >= 0 && x < a && y >= 0 &&
                y < b && (adjMatrix[x][y].equals(".")));
    }



    public static void main(String[] args) {
        /**
         * Main class that takes input matrix and calls helper function solvemaze to carry on the processing
         * Prints count of steps taken to reach exit if any, else prints 'STUCK'
         */

        Scanner sc = new Scanner(System.in);
        a = Integer.parseInt(sc.next());
        b = Integer.parseInt(sc.next());

        adjMatrix = new String[a][b];
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

        for (int i = 0; i < a; i++) {
            String str = sc.next();
            for (int j = 0; j < b; j++) {
                if (str.substring(j, j + 1).equals("1")) {
                    x1 = i;
                    y1 = j;
                    adjMatrix[i][j] = ".";

                } else if (str.substring(j, j + 1).equals("2")) {
                    x2 = i;
                    y2 = j;
                    adjMatrix[i][j] = ".";

                }
                adjMatrix[i][j] = str.substring(j, j + 1);
           }
        }

        // pass input positions of Point 1 and 2 to helper function.
        Graph g = solveMaze(x1, y1, x2, y2);

        // print result count of steps taken to exit, else 'STUCK'
        if (g == null) {
            System.out.println("STUCK");
        }else {
            int count = 0;
            while (g.getParent() != null) {
                count++;
                g = g.getParent();
            }
            System.out.println(count + 1);
        }
    }
}