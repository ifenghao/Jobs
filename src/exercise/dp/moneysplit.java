package exercise.dp;

import java.util.Scanner;

/**
 * 给你六种面额 1、5、10、20、50、100 元的纸币，假设每种币值的数量都足够多，
 * 编写程序求组成N元（N为0~10000的非负整数）的不同组合的个数。
 * 输入描述:
 * 输入包括一个整数n(1 ≤ n ≤ 10000)
 * 输出描述:
 * 输出一个整数,表示不同的组合方案数
 */
public class moneysplit {
    static void solution(int total, int n, int[] money, long[][] dp) {
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= total; j++) {
                for (int k = 0; k <= j / money[i - 1]; k++) { // 当前纸币选k张的总方法数
                    dp[i][j] += dp[i - 1][j - k * money[i - 1]];
                }
            }
        }
        System.out.println(dp[n][total]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        int[] money = new int[]{1, 5, 10, 20, 50, 100};
        int n = money.length;
        long[][] dp = new long[n + 1][total + 1];
        solution(total, n, money, dp);
    }
}
