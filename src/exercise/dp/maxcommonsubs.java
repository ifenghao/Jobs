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
        int n1 = a.length;
        int n2 = b.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        int maxLen = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                dp[i][j] = a[i - 1] == b[j - 1] ? dp[i - 1][j - 1] + 1 : 0; // 不等时子串重新开始计算长度
                if (maxLen < dp[i][j])
                    maxLen = dp[i][j];
            }
        }
        System.out.println(maxLen);
    }

    // 最长公共子序列，可不连续，dp[i][j]表示a的长度为i前缀与b的长度为j前缀的公共连续子序列长度
    static void zixulie(char[] a, char[] b) {
        int m = a.length + 1;
        int n = b.length + 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else if (dp[i - 1][j] >= dp[i][j - 1]) { // 不等时子序列寻找最长的那一段
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1];
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
