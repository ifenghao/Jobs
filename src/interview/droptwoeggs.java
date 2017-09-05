package interview;

/**
 * n层楼k个鸡蛋，确定安全楼层的最少次数
 */
public class droptwoeggs {
    static void eggs2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int tmp, min;
        for (int i = 2; i <= n; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                tmp = 1 + Math.max(j - 1, dp[i - j]);
                if (tmp < min) min = tmp;
            }
            dp[i] = min;
        }
        System.out.println(dp[n]);
    }

    static void eggsk(int n, int k) {
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            dp[i][0] = dp[i][1] = 0;
        }
        for (int i = 1; i <= n; i++) {
            dp[1][i] = i - 1;// 初始化少1，之后要加1
        }
        int tmp, min;
        for (int egg = 2; egg <= k; egg++) {
            for (int i = 2; i <= n; i++) {
                min = Integer.MAX_VALUE;
                for (int j = 1; j <= i; j++) {
                    tmp = 1 + Math.max(dp[egg - 1][j], dp[egg][i - j]);
                    if (tmp < min) min = tmp;
                }
                dp[egg][i] = min;
            }
        }
        System.out.println(dp[k][n]);
    }

    public static void main(String[] args) {
        int n = 100;
        int k = 3;
        eggs2(n);
        eggsk(n, k);
    }
}
