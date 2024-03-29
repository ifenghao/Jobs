package exercise.dp;

import java.util.Scanner;

/**
 * ref https://blog.csdn.net/zhufenghao/article/details/69790325
 */

public class palindrome {
    // 最长回文子序列，可不连续，dp[i][j]表示以i开始以j结束的片段中，回文子序列最大长度
    static void dpxulie(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) { // 初始条件为长度为1的子序列
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

    // 递推公式里i只依赖i+1，因此需要从右向左求解；j只依赖j-1，因此需要从左向右求解，且保证j > i
    static void dpxulie2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
            }
        }
        System.out.println(dp[0][n - 1]);
    }

    // 最长回文子串，必须连续，dp[i][j]表示严格以i起始以j结尾的子串是否是回文
    static void dpchuan(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        int maxLen = 1;
        for (int i = 1; i < n; i++) { // 初始条件为长度为1和2的子串
            dp[i][i] = true;
            if (s.charAt(i - 1) == s.charAt(i)) {
                dp[i - 1][i] = true;
                maxLen = 2;
            } else {
                dp[i - 1][i] = false;
            }
        }
        for (int l = 3; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j] && j - i + 1 > maxLen) { // j - i + 1为当前子串长度
                        maxLen = j - i + 1;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        System.out.println(maxLen);
    }

    static void dpchuan2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLen = 1;
        int begin = 0;
        for (int i = 0; i < n; i++) { // 初始条件为长度为1和2的子串
            dp[i][i] = true;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = j - i == 1 ? true : dp[i + 1][j - 1]; // 子串长度为2时即为true
                    if (dp[i][j] && j - i + 1 >= maxLen) { // j - i + 1为当前子串长度
                        begin = i;
                        maxLen = j - i + 1;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        System.out.println(maxLen + " " + s.substring(begin, begin + maxLen));
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
        dpchuan(s);
        dpchuan2(s);
        dpxulie(s);
    }
}
