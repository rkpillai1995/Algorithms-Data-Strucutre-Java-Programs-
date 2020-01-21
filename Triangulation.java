import java.util.Scanner;

public class Triangulation {
    /**
     * @author 'Rajkumar Lenin Pillai'
     * @author 'Kunal Nayyar'
     * @desc 'Algorithms = Hw 4.5'
     * Finding minimum length of polygon triangulation'
     */


    private static float minPolygonTri(float[] xArray, float[] yArray, int cords) {

        float S[][] = new float[cords][cords];

        float cost_l_k = 0, cost_d_k = 0, val = 0;
        for (int d = 3; d < cords; d++) {
            for (int l = d - 3; l >= 0; l--) {
                S[l][d] = Integer.MAX_VALUE;

                for (int k = d - 1; k > l; k--) {
                    cost_l_k = 0;
                    cost_d_k = 0;

                    if (l != k - 1) {
                        cost_l_k = distance(xArray, yArray, l, k);
                    }
                    if (d != k + 1) {
                        cost_d_k = distance(xArray, yArray, d, k);
                    }
                    val = cost_l_k + cost_d_k + S[l][k] + S[k][d];

                }
                if (S[l][d] > val) {
                    S[l][d] = val;
                }
            }
        }
        return (float) (Math.round(S[0][cords - 1] * 10000.0) / 10000.0);
    }


    private static float distance(float[] xArray, float[] yArray, int i, int j) {
        return (float) Math.sqrt(((xArray[i] - xArray[j]) * (xArray[i] - xArray[j])) + ((yArray[i] - yArray[j]) * (yArray[i] - yArray[j])));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cords;
        float input;
        cords = sc.nextInt();

        float xArray[] = new float[cords];
        float[] yArray = new float[cords];
        int l = 0, j = 0;
        for (int i = 1; i < 2 * cords; i++) {
            input = sc.nextFloat();
            if (i % 2 == 0)
                xArray[l++] = input;
            else
                yArray[j++] = input;
        }
        sc.close();
        System.out.println(minPolygonTri(xArray, yArray, cords));
    }
}
