import java.util.Scanner;

public class MinWeightKnapsack {

    /**
     * @author 'Rajkumar Lenin Pillai'
     * @author 'Kunal Nayyar'
     * @desc 'Algorithms = Hw 4.3'
     * Finding Minimum possible weight
     */


    public static void knapsack(int val[], int wt[], int W) {
        int N = wt.length;
        int[][] S = new int[N + 1][W + 1];

        for (int col = 0; col <= W; col++) {
            S[0][col] = 0;
        }
        //What if there are no items. Fill the first row with 0
        for (int row = 0; row <= N; row++) {
            S[row][0] = 0;
        }

        int new_index, current_index = 0;
        int current_cost = 0, new_cost;
        for (int item = 1; item <= N; item++) {

            for (int column = 1; column <= W; column++) {

                //Is the current items column less than or equal to running column
                if (wt[item - 1] <= column) {
                    //Given a column, check if the value of the current item + value of the item that we could afford with the remaining column
                    //is greater than the value without the current item itself

                    S[item][column] = Math.max(val[item - 1] + S[item - 1][column - wt[item - 1]], S[item - 1][column]);
                    if (S[item][column] >= W) {
                        new_index = column;
                        new_cost = S[item][column];
                        if (new_cost != 0 && new_cost >= W) {
                            if (current_cost == 0 && current_index == 0) {
                                current_cost = new_cost;
                                current_index = column;
                            }

                            //If new_column is less than current_column priority is given to the index of columns.
                            if (current_index > new_index) {
                                current_index = new_index;
                                current_cost = new_cost;
                            }
                        }
                    }
                } else {
                    //carry forward the value without the current item
                    S[item][column] = S[item - 1][column];
                }
            }
        }
        System.out.println(current_index);
    }

    /**
     * Main class, takes input
     *
     * @param args Input from console:
     *             number of elements in the knapsack and the cost for each item
     *
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int w, c;
        int input;
        w = sc.nextInt();
        c = sc.nextInt();


        int wArray[] = new int[w];
        int[] cArray = new int[w];
        int l = 0, j = 0;
        for (int i = 0; i < 2 * w; i++) {
            input = sc.nextInt();
            if (i % 2 == 0)
                wArray[l++] = input;
            else
                cArray[j++] = input;
        }
        sc.close();

        knapsack(cArray, wArray, c);
    }
}
