package exercise.dp;

import java.util.Scanner;

/**
 * 给出两个字符串（可能包含空格）,找出其中最长的公共连续子串,输出其长度。
 * 输入描述:
 * 输入为两行字符串（可能包含空格），长度均小于等于50.
 * <p>
 * 输出描述:
 * 输出为一个整数，表示最长公共连续子串的长度。
 * <p>
 * 输入例子1:
 * abcde
 * abgde
 * <p>
 * 输出例子1:
 * 2
 */
public class maxcommonsubs {
    // 最长公共子串，必须连续，dp[i][j]表示a的长度为i前缀与b的长度为j前缀的公共连续子串长度，子串必须以i-1和j-1结尾
    static void zichuan(char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else { // 不等时子串重新开始计算长度
                    dp[i][j] = 0;
                }
                if (maxLen < dp[i][j])
                    maxLen = dp[i][j];
            }
        }
        System.out.println(maxLen);
    }

    // 最长公共子序列，可不连续，dp[i][j]表示a的长度为i前缀与b的长度为j前缀的公共连续子序列长度
    static void zixulie(char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else { // 不等时子序列寻找最长的那一段（和公共子串唯一差别）
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[m - 1][n - 1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        zichuan(chs1, chs2);
        zixulie(chs1, chs2);
    }
}
