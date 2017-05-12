package beauty;

import java.util.Scanner;

/**
 * Created by zfh on 17-5-12.
 */
public class arraysplit {
    static void wrongSolution(int[] array, int[][] s, int n, int sum) {
        for (int i = 0; i < sum / 2 + 1; i++) {
            s[0][i] = 0;
        }
        s[1][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum / 2 + 1; j++) {
                int skipi = s[(i - 1) % 2][j];
                int takei = j < array[i - 1] ? 0 :
                        s[(i - 1) % 2][j - array[i - 1]] + array[i - 1];
                s[i % 2][j] = Math.max(takei, skipi);
            }
        }
    }

    static void solution(int[] array, int[][][] s, int n, int sum) {
        for (int i = 0; i < sum / 2 + 1; i++) {
            s[0][0][i] = 0;
            s[1][0][i] = 0;
        }
        for (int i = 0; i < n / 2 + 1; i++) {
            s[0][i][0] = 0;
            s[1][i][0] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            int maxj = Math.min(i, n / 2);
            for (int j = 1; j <= maxj; j++) {
                for (int k = 1; k < sum / 2 + 1; k++) {
                    int skipi = s[(i - 1) % 2][j][k];
                    int takei = k < array[i - 1] ? 0 :
                            s[(i - 1) % 2][j - 1][k - array[i - 1]] + array[i - 1];
                    s[i % 2][j][k] = Math.max(takei, skipi);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
            sum += array[i];
        }
//        int[][] s = new int[2][sum / 2 + 1];
//        wrongSolution(array, s, n, sum);
//        int left = s[n % 2][sum / 2];
//        System.out.println(left + " " + (sum - left));
        int[][][] s = new int[2][n / 2 + 1][sum / 2 + 1];
        solution(array, s, n, sum);
        int left = s[n % 2][n / 2][sum / 2];
        System.out.println(left + " " + (sum - left));
    }
}
