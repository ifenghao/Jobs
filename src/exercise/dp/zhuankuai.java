package exercise.dp;

import java.util.Scanner;

/**
 * 小易有n块砖块，每一块砖块有一个高度。小易希望利用这些砖块堆砌两座相同高度的塔。
 * 为了让问题简单，砖块堆砌就是简单的高度相加，某一块砖只能使用在一座塔中一次。
 * 小易现在让能够堆砌出来的两座塔的高度尽量高，小易能否完成呢。
 * <p>
 * 输入描述:
 * 输入包括两行：
 * 第一行为整数n(1 ≤ n ≤ 50)，即一共有n块砖块
 * 第二行为n个整数，表示每一块砖块的高度height[i] (1 ≤ height[i] ≤ 500000)
 * <p>
 * <p>
 * 输出描述:
 * 如果小易能堆砌出两座高度相同的塔，输出最高能拼凑的高度，如果不能则输出-1.
 * 保证答案不大于500000。
 * <p>
 * 输入例子:
 * 3
 * 2 3 5
 * <p>
 * 输出例子:
 * 5
 * ref https://blog.csdn.net/zhufenghao/article/details/69802667
 */

public class zhuankuai {

    static int mod(int x) {
        return x % 2;
    }

    static void solution1(int[] bricks, int n, int[][] f) {
        int length = f[0].length;
        for (int i = 0; i < length; i++) {
            f[0][i] = -1;
        }
        f[0][(length - 1) / 2] = 0;
        for (int i = 1; i <= n; i++) {
            int h = bricks[i - 1];
            for (int j = 0; j < length; j++) {
                f[mod(i)][j] = f[mod(i - 1)][j];
                if (j - h >= 0 && f[mod(i - 1)][j - h] != -1) {
                    f[mod(i)][j] = Math.max(f[mod(i)][j], f[mod(i - 1)][j - h]);
                }
                if (j + h <= length - 1 && f[mod(i - 1)][j + h] != -1) {
                    f[mod(i)][j] = Math.max(f[mod(i)][j], f[mod(i - 1)][j + h] + h);
                }
            }
        }
    }

    static void solution2(int[] bricks, int n, int sum, int[][] f) {
        f[0][0] = 0;
        for (int i = 1; i <= sum; i++) {
            f[0][i] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            int h = bricks[i - 1];
            for (int j = 0; j <= sum; j++) {
                f[mod(i)][j] = f[mod(i - 1)][j];
                if (j + h <= sum) {
                    f[mod(i)][j] = Math.max(f[mod(i)][j], f[mod(i - 1)][j + h] + h);
                }
                if (h - j >= 0) {
                    f[mod(i)][j] = Math.max(f[mod(i)][j], f[mod(i - 1)][h - j] + h - j);
                } else {
                    f[mod(i)][j] = Math.max(f[mod(i)][j], f[mod(i - 1)][j - h]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] bricks = new int[n];
//        int sum = 0;
//        for (int i = 0; i < n; i++) {
//            bricks[i] = sc.nextInt();
//            sum += bricks[i];
//        }
//        int[][] f = new int[2][2 * sum + 1];
//        solution1(bricks, n, f);
//        System.out.println(f[1][sum]);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            bricks[i] = sc.nextInt();
            sum = sum + bricks[i];
        }
        int[][] f = new int[2][sum + 1];
        solution2(bricks, n, sum, f);
        System.out.println(f[mod(n)][0] > 0 ? f[mod(n)][0] : -1);
    }
}
