package exercise.dp;

import java.util.Scanner;

/**
 * 1-A, 2-B, ... , 26-Z 解码字符串
 */
public class decodeways {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(solution(s));
    }
}
