package exercise.others;

/**
 * Created by zfh on 17-4-9.
 */
public class Fibonacci {
    static int[][] dot(int[][] A, int[][] B) {
        int Arows = A.length;
        int Acols = A[0].length;
        int Brows = B.length;
        int Bcols = B[0].length;
        assert (Acols == Brows);
        int tmp;
        int[][] R = new int[Arows][Bcols];
        for (int i = 0; i < Arows; i++) {
            for (int j = 0; j < Bcols; j++) {
                tmp = 0;
                for (int k = 0; k < Acols; k++) {
                    tmp += A[i][k] * B[k][j];
                }
                R[i][j] = tmp;
            }
        }
        return R;
    }

    static int fibonacci(int n) {
        if (n == 0) return 0;
        n -= 1;
        int[][] result = new int[][]{{1, 0}, {0, 1}};
        int[][] A = new int[][]{{1, 1}, {1, 0}};
        while (n > 0) {
            if (n % 2 == 1) {
                result = dot(result, A);
            }
            n /= 2;
            A = dot(A, A);
        }
        return result[0][0];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(fibonacci(i));
        }
    }
}
