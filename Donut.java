import java.util.Scanner;

import static java.lang.Math.abs;

public class Donut {
    /**
     * This program computes the  minimum distance from the police cars to the donut store
     *
     * @author Rajkumar Lenin Pillai
     * @author Kunal Nayyar
     */

    static int jmean = 0;
    static int input_len, input;
    static int[] mean;

    private static int select(int[] anArray, int k) {
        int n, final_mean;
        n = anArray.length - 1;
        int[] new_array;

        //sort in linear time
        if (n <= 5) {
            int temp;
            for (int i = 0; i <= n; i++) {
                for (int j = i; j <= n; j++) {
                    if (anArray[i] > anArray[j]) {
                        temp = anArray[i];
                        anArray[i] = anArray[j];
                        anArray[j] = temp;
                    }
                }
            }
            return anArray[n - k];
        } else {    // split into multiple subarrays and call self
            int index;
            index = n % 5;

            for (int i = 0; i < n; i = i + 5) {
                if (i == n - index) {
                    new_array = new int[n - i + 1];
                    arraycopy(anArray, new_array, i, n);
                    mean[jmean] = select(new_array, (index + 1) / 2);
                    jmean++;
                } else {
                    new_array = new int[5];
                    arraycopy(anArray, new_array, i, i + 4);
                    mean[jmean] = select(new_array, 6 / 2);
                    jmean++;
                }
            }
        }
        final_mean = select(mean, mean.length / 2);
        int[] rearranged_array = new int[n + 1];


        int j1 = 0, j2 = 0;
        int x = 0;

        //everything < mean is on the left
        for (int i = 0; i <= n; i++) {
            if (anArray[i] < final_mean) {
                rearranged_array[x++] = anArray[i];
            }
        }
        //everything = the median is after the left
        for (int i = 0; i <= n; i++) {
            if (anArray[i] == final_mean) {
                rearranged_array[x++] = anArray[i];
            }
        }
        // everything > the median is after the middle
        for (int i = 0; i <= n; i++) {
            if (anArray[i] > final_mean) {
                rearranged_array[x++] = anArray[i];
            }
        }

        //find position of 1st occurrence of the mean
        for (int i = 0; i <= n; i++) {
            if (rearranged_array[i] == final_mean) {
                j1 = i;
                break;
            }
        }
        //finds position of the last occurrence of the mean
        for (int i = 0; i <= n; i++) {
            if (rearranged_array[i] == final_mean)
                j2 = i;
        }
        jmean = 0;
        // if only 1 value of mean
        if (j1 == j2) {
            if ((k == j1)) {
                return final_mean;
            } else if (k < j1) {
                mean = new int[(j1 - 1) / 5 * ((j1 - 1) % 5)];
                new_array = new int[j1 - 1];
                arraycopy(rearranged_array, new_array, 0, j1 - 2);
                return select(new_array, k);
            } else {
                new_array = new int[n - j2 + 1];
                arraycopy(rearranged_array, new_array, j2, n);
                return select(new_array, k - j2);
            }
        } else {
            if (k < j1) {
                new_array = new int[j1 - 1];
                arraycopy(rearranged_array, new_array, 0, j1 - 2);
                return select(new_array, k);
            } else if (k > j2) {
                new_array = new int[n - j2 + 1];
                arraycopy(rearranged_array, new_array, j2, n);
                return select(new_array, k - j2);
            } else if (k == j1) {
                return final_mean;
            } else {
                return final_mean;
            }
        }
    }

    //Function to pass a subset of the array
    private static void arraycopy(int[] from_array, int[] to_array, int start, int finish) {

        int j = 0;
        for (int i = start; i <= finish; i++) {
            to_array[j] = from_array[i];
            j++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input_len = sc.nextInt();
        int[] x = new int[input_len];
        int[] y = new int[input_len];

        int size = (input_len / 5 * input_len % 5);
        mean = new int[size];

        int j = 0, k = 0;
        for (int i = 0; i < input_len * 2; i++) {
            input = sc.nextInt();
            if (i % 2 == 0) {
                x[k] = input;
                k++;
            } else {
                y[j] = input;
                j++;
            }
        }

        int x_val, y_val;

        x_val = select(x, (input_len + 1) / 2); // stores the x median
        y_val = select(y, (input_len + 1) / 2); // stores the y median

        int answer = 0;
        for (int i = 0; i < x.length; i++) {
            //takes difference of x median from xcord and same for y
            answer = answer + abs(x[i] - x_val) + abs(y[i] - y_val);

        }
        System.out.println(answer);
    }
}

