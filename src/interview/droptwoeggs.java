package interview;

/**
 * n层楼k个鸡蛋，确定安全楼层的最少次数
 */
public class droptwoeggs {
    static int eggs2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int tmp, min;
        for (int i = 2; i <= n; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                tmp = 1 + Math.max(j - 1, dp[i - j]);
                if (tmp < min) min = tmp;
            }
            dp[i] = min;
        }
        return dp[n];
    }

    static int eggsk(int n, int egg) {
        int[][] dp = new int[egg + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
            dp[1][i] = i;
        }
        for (int i = 2; i <= egg; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        int tmp, min;
        for (int k = 2; k <= egg; k++) {
            for (int i = 2; i <= n; i++) {
                min = Integer.MAX_VALUE;
                for (int j = 1; j < i; j++) {
                    tmp = 1 + Math.max(dp[k - 1][j - 1], dp[k][i - j]);
                    if (tmp < min) min = tmp;
                }
                dp[k][i] = min;
            }
        }
        return dp[egg][n];
    }

    // 使用二分法优化线性查找
    static int eggskBisearch(int n, int egg) {
        int[][] dp = new int[egg + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
            dp[1][i] = i;
        }
        for (int i = 2; i <= egg; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        int min;
        for (int k = 2; k <= egg; k++) {
            for (int i = 2; i <= n; i++) {
                min = Integer.MAX_VALUE;
//                for (int j = 1; j < i; j++) {
//                    tmp = 1 + Math.max(dp[k - 1][j - 1], dp[k][i - j]);
//                    if (tmp < min) min = tmp;
//                }
                int lo = 1, hi = i;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    int broken = dp[k - 1][mid - 1];
                    int notBroken = dp[k][i - mid];
                    if (broken > notBroken) {
                        hi = mid - 1;
                        min = Math.min(min, broken + 1);
                    } else {
                        lo = mid + 1;
                        min = Math.min(min, notBroken + 1);
                    }
                }
                dp[k][i] = min;
            }
        }
        return dp[egg][n];
    }

    // 使用反向定义状态转移
    static int egg2reverse(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int i = 0;
        while (dp[i] < n) {
            i++;
            dp[i] = i + dp[i - 1];
        }
        return i;
    }

    static int eggkreverse(int n, int egg) {
        int[][] dp = new int[egg + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
            dp[1][i] = i;
        }
        for (int i = 2; i <= egg; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        int i = 0;
        while (dp[egg][i] < n) {
            i++;
            for (int k = 1; k <= egg; k++) {
                dp[k][i] = dp[k - 1][i - 1] + 1 + dp[k][i - 1];
//                System.out.println(i + " " + k + ": " + dp[k][i]);
            }
        }
        return i;
    }

    // 降低2D数组为1D数组
    static int eggkreverse1D(int n, int egg) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = 0;
        }
        for (int k = 1; k <= egg; k++) {
            int i = 1, preBroken = 0; // 相当于尝试i=0时楼层数dp也为0
            for (; i <= n ; i++) {
                int broken = dp[i]; // 先取出防止被覆盖
                dp[i] = preBroken + 1 + dp[i - 1]; // 直接覆盖原值
                preBroken = broken; // 存储并参与下一次计算
                if (k == egg && dp[i] >= n) return i;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        int n = 100;
        int k = 3;
        System.out.println(eggs2(n));
        System.out.println(eggsk(n, k));
        System.out.println(eggskBisearch(n, k));
        System.out.println(egg2reverse(n));
        System.out.println(eggkreverse(n, k));
        System.out.println(eggkreverse1D(n, k));
    }
}
