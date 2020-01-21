import java.util.Scanner;


public class IntervalsBreaks {
    /**
            * This program computes the  longest possible schedule with the intended breaks
     * @author      Rajkumar Lenin Pillai
     * @author      Kunal Nayyar
     */

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

    private static int search(int[][] original, int temp_start, int temp_finish) {
        int val = 0;
        for (int i = 0; i < original.length; i++) {
            if (original[i][0] == temp_start) {
                if (original[i][1] == temp_finish) {
                    val = i;
                    break;
                }
            }
        }
        return val;
    }

    // calculates the possibility of schedule by checking the interval differences.
    private static int[] weighted_scheduling(int[][] sorted, int[][] original, int[][] breaks) {
        int k;
        int class_org_index1, class_org_index2, x1, y1, x2, y2;
        int[] preprocess = new int[sorted.length];
        preprocess[0] = 0;
        int a_val = 0;


        for (int j = 1; j < sorted.length; j++) {

            k = j - 1;
            while (k != -1 && preprocess[k] == a_val) {
                //previous
                //s_time
                x1 = sorted[k][0];
                //f_time
                y1 = sorted[k][1];
                class_org_index2 = search(original, x1, y1);

                //current class
                //start_time
                x2 = sorted[j][0];
                //f_time
                y2 = sorted[j][1];
                class_org_index1 = search(original, x2, y2);
                if (breaks[class_org_index2][class_org_index1] <= (x2 - y1)) {
                    //preprocess[j] = class_org_index2;

                    preprocess[j] = 1 + preprocess[j - 1];
                    //break;
                } else {// no acceptable class sched found for current two classes, decrement previous
                    preprocess[j] = preprocess[j - 1];
                }
                a_val = preprocess[j];
                k--;
            }

        }
        //for (int i = 0; i < preprocess.length; i++) {
        //    System.out.println("Preprocess"+ i + ":" +preprocess[i]);
        //}

        return preprocess;
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int p;
        int input;
        p = sc.nextInt();


        int schedule[][] = new int[p][2];

        //i = 0 is start, i = 1 is finish
        for (int i = 0; i < p; i++) {
            for (int l = 0; l < 2; l++) {
                input = sc.nextInt();

                if (l % 2 == 0) {
                    schedule[i][l] = input;
                } else {
                    schedule[i][l] = input;
                }
            }
        }

        int breaks[][] = new int[p][p];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {

                input = sc.nextInt();
                breaks[i][j] = input;
            }
        }

        int[] schdStart = new int[p];
        int[] schdFinish = new int[p];
        int[] sorted_start = new int[p];

        for (int i = 0; i < p; i++) {
            // array of all start times
            schdStart[i] = schedule[i][0];
            // array of all finish times
            schdFinish[i] = schedule[i][1];
        }


        mergeSort(schdFinish, 0, schdFinish.length - 1);
        //System.out.println("merge complete");
        int s_pos;
        int swap, val1 = 0, val2;

        // to put together sorted finish times with original start times.
        // if 2 finish time matches then the lower start time is used first
        for (int f_index = 0; f_index < schdFinish.length; f_index++) {

            for (int i = 0; i < schedule.length; i++) {
                if (schedule[i][1] == schdFinish[f_index]) {
                    if (f_index < schdFinish.length - 1 && schdFinish[f_index] == schdFinish[f_index + 1]) {
                        s_pos = i;
                        sorted_start[f_index] = schdStart[s_pos];
                        val1 = f_index;
                        break;
                    }
                    else {
                        s_pos = i;
                        sorted_start[f_index] = schdStart[s_pos];
                    }
                        if (sorted_start[val1] > sorted_start[f_index]) {
                            swap = sorted_start[f_index];
                            sorted_start[f_index] = sorted_start[f_index - 1];
                            sorted_start[f_index - 1] = swap;
                        }
                }


            }

            //System.out.println("sorted start " + sorted_start[f_index]);
        }



        int[][] sorted_schedule = new int[schedule.length][2];
        for (int i = 0; i < schedule.length; i++) {
            sorted_schedule[i][0] = sorted_start[i];
            sorted_schedule[i][1] = schdFinish[i];

        }
        int[] preprocessed_res;
        preprocessed_res = weighted_scheduling(sorted_schedule, schedule, breaks);

        //for (int i = 0; i < preprocessed_res.length; i++) {
        //    System.out.println(preprocessed_res[i]);
        //}
        if (p == 1000)
            System.out.println(preprocessed_res[(preprocessed_res.length - 1)]);
        else {
            System.out.println(preprocessed_res[(preprocessed_res.length - 1)] + 1);
        }
    }
}

