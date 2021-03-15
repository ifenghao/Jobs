package exercise.dp;

import java.util.Scanner;

/**
 * 1-A, 2-B, ... , 26-Z 给出一串数字，可解码成多少种字母序列
 */
public class decodenum2letter {
    static int solution(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[] dp = new int[n];
        if (s.charAt(0) == '0') return 0;
        dp[0] = 1;
        if (n == 1) return 1;
        if (s.charAt(1) == '0') {
            if (s.charAt(0) > '2') {
                return 0;
            } else {
                dp[1] = 1;
            }
        } else {
            if (s.charAt(0) == '1') {
                dp[1] = 2;
            } else if (s.charAt(0) == '2') {
                if (s.charAt(1) <= '6') {
                    dp[1] = 2;
                } else {
                    dp[1] = 1;
                }
            } else {
                dp[1] = 1;
            }
        }
        for (int i = 2; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) > '2' || s.charAt(i - 1) == '0') {
                    return 0;
                } else {
                    dp[i] = dp[i - 2];
                }
            } else {
                if (s.charAt(i - 1) == '0') {
                    dp[i] = dp[i - 1];
                } else if (s.charAt(i - 1) == '1') {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else if (s.charAt(i - 1) == '2') {
                    if (s.charAt(i) <= '6') {
                        dp[i] = dp[i - 1] + dp[i - 2];
                    } else {
                        dp[i] = dp[i - 1];
                    }
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[n - 1];
    }

    static int solutionSimple(String s) {
        int n = s.length();
        int[] ways = new int[n];
        ways[0] = 1;
        int tmp;
        for (int i = 1; i < n; i++) {
            tmp = ways[i - 1];
            if (i > 1 && canDecodeLetter(s, i - 2, i)) {
                tmp += ways[i - 2];
            }
            ways[i] = tmp;
        }
        return ways[n - 1];
    }

    static boolean canDecodeLetter(String s, int start, int end) {
        String sub = s.substring(start, end);
        int num = Integer.valueOf(sub);
        return num > 0 && num < 27;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(solution(s));
        System.out.println(solutionSimple(s));
    }
}
