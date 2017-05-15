package exercise.dp;

import java.util.Scanner;

/**
 * 给定n个气球，下标为0到n-1。每个气球上都标有一个分数，用数组nums表示。
 * 扎破第i个气球可以获得的分数为nums[left] * nums[i] * nums[right]。
 * 这里left和right是与i相邻的下标。扎破气球以后，left和right就变成相邻的了。
 * 要求扎破所有气球，使得获取的分数最高。
 * <p>
 * 注意：
 * (1) 你可以假设nums[-1] = nums[n] = 1. 它们并非真实的因此不能扎破。
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * <p>
 * 输入描述:
 * 输入包括两行：
 * 第一行为气球的个数，第二行为每个气球的分数
 * <p>
 * <p>
 * 输出描述:
 * 输出一个整数，表示最多可以得到的分数
 * <p>
 * 输入例子:
 * 3
 * 3 1 5
 * <p>
 * 输出例子:
 * 35
 */
public class balloonburst {
    static int solution(int[] array, int n) {
        int[][] dp = new int[n + 2][n + 2];
        for (int l = 1; l <= n; l++) {// 不包含两端气球的中间气球个数
            for (int left = 0; left <= n - l; left++) {// 左端气球
                int right = left + l + 1;// 右端气球
                for (int k = left + 1; k < right; k++) {// 对最后被扎破的中间气球进行遍历
                    int split = array[left] * array[k] * array[right] + dp[left][k] + dp[k][right];
                    dp[left][right] = Math.max(dp[left][right], split);// 不包含端点left和right被扎破
                }
            }
        }
        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n + 2];
        array[0] = array[n + 1] = 1;
        for (int i = 1; i < n + 1; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(solution(array, n));
    }
}
