package exercise.dp;

import java.util.Scanner;

/**
 * 给定一个有n个正整数的数组A和一个整数sum,求选择数组A中部分数字和为sum的方案数。
 * 当两种选取方案有一个数字的下标不一样,我们就认为是不同的组成方案。
 * 输入描述:
 * 输入为两行:
 * <p>
 * 第一行为两个正整数n(1 ≤ n ≤ 1000)，sum(1 ≤ sum ≤ 1000)
 * <p>
 * 第二行为n个正整数A[i](32位整数)，以空格隔开。
 * <p>
 * 输出描述:
 * 输出所求的方案数
 * <p>
 * 输入例子:
 * 5 15
 * 5 5 10 2 3
 * <p>
 * 输出例子:
 * 4
 */
public class shuzihe {
    static long method = 0;

    // 回溯法超时
    static void solution(int acc, int i, int sum, int[] array) {
        if (acc == sum) {
            ++method;
        } else if (acc < sum) {
            if (i < array.length) {
                solution(acc + array[i], i + 1, sum, array);// 选择第i个
                solution(acc, i + 1, sum, array);// 不选择第i个
            }
        }
    }

    static void solution2(int[] array, long[][] m, int sum) {
        int n = array.length;
        for (int i = 1; i <= sum; i++) {
            m[0][i] = 0;
        }
        for (int i = 0; i <= n; i++) {
            m[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - array[i - 1] >= 0) {
                    m[i][j] = m[i - 1][j] + m[i - 1][j - array[i - 1]];
                } else {
                    m[i][j] = m[i - 1][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        // solution(0, 0, sum, array);
        // System.out.println(method);
        long[][] m = new long[n + 1][sum + 1];
        solution2(array, m, sum);
        System.out.println(m[n][sum]);
    }
}
