package exercise.num;

import java.util.Scanner;

/**
 * 小易拥有一个拥有魔力的手环上面有n个数字(构成一个环),当这个魔力手环每次使用魔力的时候就会发生一种奇特的变化：
 * 每个数字会变成自己跟后面一个数字的和(最后一个数字的后面一个数字是第一个),
 * 一旦某个位置的数字大于等于100就马上对100取模(比如某个位置变为103,就会自动变为3).
 * 现在给出这个魔力手环的构成，请你计算出使用k次魔力之后魔力手环的状态。
 * 输入描述:
 * 输入数据包括两行：
 * 第一行为两个整数n(2 ≤ n ≤ 50)和k(1 ≤ k ≤ 2000000000),以空格分隔
 * 第二行为魔力手环初始的n个数，以空格分隔。范围都在0至99.
 * <p>
 * <p>
 * 输出描述:
 * 输出魔力手环使用k次之后的状态，以空格分隔，行末无空格。
 * <p>
 * 输入例子:
 * 3 2
 * 1 2 3
 * <p>
 * 输出例子:
 * 8 9 7
 *
 * 矩阵行、列变换可以完成移动和相加，于是构造出快速幂矩阵求解
 * [[1 1 0] [0 1 1] [1 0 1]]*[[a][b][c]] = [[a+b][b+c][c+a]]
 */
public class molishouhuan {
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

    static int[][] mod(int[][] A, int n) {
        int rows = A.length;
        int cols = A[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                A[i][j] %= n;
            }
        }
        return A;
    }

    static int[] solution(int[] nums, int n, long k) {
        int[][] M = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            M[i][i] = 1;
            M[i + 1][i] = 1;
        }
        M[n - 1][n - 1] = 1;
        M[0][n - 1] = 1;
        int[][] result = new int[1][n];
        for (int i = 0; i < n; i++) {
            result[0][i] = nums[i];
        }
        while (k > 0) {
            if (k % 2 == 1) {
                result = mod(dot(result, M), 100);
            }
            k /= 2;
            M = mod(dot(M, M), 100);
        }
        return result[0];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int[] result = solution(nums, n, k);
        for (int i = 0; i < n; i++) {
            System.out.print(result[i]);
            if (i != n - 1) System.out.print(" ");
        }
    }
}
