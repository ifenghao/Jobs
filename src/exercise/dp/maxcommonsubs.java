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
public class maxsubstring {
    static void solution(char[] chs1, char[] chs2) {
        int n1 = chs1.length;
        int n2 = chs2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        int maxLen = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                dp[i][j] = chs1[i - 1] == chs2[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                maxLen = maxLen < dp[i][j] ? dp[i][j] : maxLen;
            }
        }
        System.out.println(maxLen);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        solution(chs1, chs2);
    }
}
