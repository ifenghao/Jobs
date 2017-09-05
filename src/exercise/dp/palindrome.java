package exercise.dp;

import java.util.Scanner;

/**
 * 最长回文序列
 * 最长回文子串
 */
public class palindrome {
    static void dpnc(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if (s.charAt(i) != (s.charAt(j))) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
            }
        }
        System.out.println(dp[0][n - 1]);
    }

    static void dpc(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            dp[i][i] = true;
            if (s.charAt(i - 1) == s.charAt(i)) {
                dp[i - 1][i] = true;
            } else {
                dp[i - 1][i] = false;
            }
        }
        int maxLen = 0;
        for (int l = 3; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        System.out.println(maxLen);
    }

    static void manacher(String s) {
        int n = s.length();
        int m = n * 2 + 1;
        char[] chs = new char[m];
        for (int i = 0; i < n; i++) {
            chs[i * 2] = '#';
            chs[i * 2 + 1] = s.charAt(i);
        }
        chs[2 * n] = '#';
        int id = 0;// 当前回文串中心
        int maxR = 0;// 最长回文串右端点最大值
        int maxLen = 0;
        int[] p = new int[m];// 最长回文串中心i到右端点的长度
        for (int i = 0; i < m; i++) {
            if (i < maxR) {// i在最长回文串内部，j = 2 * id - i为之前匹配过的i的对称点
                p[i] = Math.min(p[2 * id - i], maxR - i);// 跳过了已经匹配的长度，可以避免重复匹配
            } else {// 还未匹配中心为i的回文串
                p[i] = 1;
            }
            while (i - p[i] >= 0 && i + p[i] < m && chs[i - p[i]] == chs[i + p[i]]) {
                p[i]++;// 从i中心到两边扩展
            }
            if (p[i] + i > maxR) {// 到最长回文串的外部，更新右端点和中心
                maxR = p[i] + i;
                id = i;
            }
            maxLen = Math.max(maxLen, p[i]);
        }
        System.out.println(maxLen - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        manacher(s);
    }
}
