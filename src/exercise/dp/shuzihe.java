package exercise.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1、数字和：选取数组中的部分求和
 * 给定一个有n个正整数的数组A和一个整数sum,求选择数组A中部分数字和为sum的方案数。
 * 当两种选取方案有一个数字的下标不一样,我们就认为是不同的组成方案。
 * 输入描述: 输入为两行: 第一行为两个正整数n(1 ≤ n ≤ 1000)，sum(1 ≤ sum ≤ 1000) 第二行为n个正整数A[i](32位整数)，以空格隔开。
 * 输出描述: 输出所求的方案数
 * 输入例子:
 * 5 15
 * 5 5 10 2 3
 * 输出例子:
 * 4
 *
 * 转化为01背包问题，前i个数和为j的方案数dp[i][j] = dp[i - 1][j] + dp[i - 1][j - a[i]]
 *
 * 2、数字和：数组中数字全选，给每一位安排加减号
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 *
 * 依然是01背包问题但要求物品全选，前i个数和为j的方案数dp[i][j] = dp[i - 1][j - a[i]] + dp[i - 1][j + a[i]]
 * 还有一种可以直接转化为01背包问题，将数组nums分为加+号的部分P和加-号的部分N，P + N = sum，P - N = S，那么P = (sum + S) / 2
 * 只要找到一组P即为一种方案
 */
public class shuzihe {
    static long method = 0;

    // 1、数字和：选取数组中的部分求和（回溯法超时）
    static void solution1BackTrace(int acc, int i, int sum, int[] array) {
        if (acc == sum) {
            ++method;
        } else if (acc < sum) {
            if (i < array.length) {
                solution1BackTrace(acc + array[i], i + 1, sum, array);// 选择第i个
                solution1BackTrace(acc, i + 1, sum, array);// 不选择第i个
            }
        }
    }

    // 1、数字和：选取数组中的部分求和
    static long solution1(int[] array, long[][] m, int sum) {
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
        return m[n][sum];
    }

    // 2、数字和：数组中数字全选，给每一位安排加减号
    static int solution2(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (sum + S) % 2 == 1) return 0;
        int target = (sum + S) / 2;
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) { // 因为是非负整数，所以此处j从0开始；如果是正整数则j从1开始
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        long[][] m = new long[n + 1][sum + 1];
        System.out.println(solution1(array, m, sum));

        int[] nums = new int[]{1,0,0,0,0,0,0,0,0};
        System.out.println(solution2(nums, 1));
    }
}
