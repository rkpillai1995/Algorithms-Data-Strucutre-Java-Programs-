import java.util.Scanner;

/**
 * @author 'Rajkumar Lenin Pillai'
 * @author 'Kunal Nayyar'
 * @desc 'Algorithms = Hw 4.1'
 * Finding length of the longest convex subsequence'
 */

public class LongestConvexSubseq {

    /**
     * Function to calculate the longest convex subsequence
     * using 2D integer matrix
     *
     * @param seqArray input sequence
     * @return maximum length of the longest convex subsequence
     */
    static int longconvex(int[] seqArray) {

        if (seqArray.length <= 2)
            return seqArray.length;


        int LCS_length = seqArray.length;
        int longest_conv_subseq[][] = new int[LCS_length][LCS_length];
        int ans = 0, i = 1;
        longest_conv_subseq[0][0] = 1;

        while (i < LCS_length) {
            for (int j = 0; j < LCS_length; j++) {
                longest_conv_subseq[i][j] = 2;
                for (int k = 0; k < j; k++) {

                    if (longest_conv_subseq[j][k] + 1 > longest_conv_subseq[i][j] && ((seqArray[i] + seqArray[k]) >= 2 * (seqArray[j])))
                        longest_conv_subseq[i][j] = longest_conv_subseq[j][k] + 1;


                    //if ((seqArray[j] + seqArray[l] >= (seqArray[k] * 2))) {
                    //  longest_conv_subseq[j][k] = Math.max(longest_conv_subseq[j][k], 1 + longest_conv_subseq[k][l]);

                }
                ans = Math.max(ans, longest_conv_subseq[i][j]);
            }


            i++;
        }
        return ans;

    }

    /**
     * Main class, takes input and prints the result
     * of the maximum length of the convex subsequence
     *
     * @param args Input from console:
     *             number of elements in the sequence
     *             sequence
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l;
        int input;
        l = sc.nextInt();

        int seqArray[] = new int[l];
        for (int i = 0; i < l; i++) {
            input = sc.nextInt();
            seqArray[i] = input;
        }
        int result;
        result = longconvex(seqArray);

        System.out.println(result);
    }
}