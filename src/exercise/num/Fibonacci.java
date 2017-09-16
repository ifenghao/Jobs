package exercise.num;

/**
 * 快速生成斐波那契数列的第n项 1 1 2 3 5 8 13 21 34 55 89 144 233 377
 * [f(n), f(n-1); f(n-1), f(n-2)] =[1, 1; 1, 0]^(n-1)
 * <p>
 * 相关数学问题
 * 1、有一段楼梯有10级台阶，规定每一步只能跨一级或两级,要登上第10级台阶有几种不同的走法?
 * dp[i]=dp[i-1]+dp[i-2]可见为斐波那契数列的第n+1项，第10阶为89
 * 2、一枚均匀的硬币掷n次，问不连续出现正面的可能情形有n+2种
 * 3、兔子在出生两个月后，就有繁殖能力，一对兔子每个月能生出一对小兔子来。如果所有兔都不死，那么一年以后可以繁殖233对兔子
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

    static int fibonacci2(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;
        int f1 = 1, f2 = 1, tmp;
        while (--n > 1) {
            tmp = f1 + f2;
            f1 = f2;
            f2 = tmp;
        }
        return f2;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.print(fibonacci2(i) + " ");
        }
    }
}
