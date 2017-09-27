package exercise.dp;

import java.util.Scanner;
import java.util.Stack;

/**
 * 括号匹配最长连续子串，匹配的序列中一定是最左边为(，最右边为)
 */
public class matchbracket {
    static void solution(char[] bracket) {
        int n = bracket.length;
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (bracket[i - 1] == '(') {// 当前括号无法匹配
                dp[i] = 0;
            } else if (i >= 2) {
                if (bracket[i - 2] == '(') {// i括号和前一括号匹配
                    dp[i] = dp[i - 2] + 2;
                } else if (i >= dp[i - 1] + 2) {
                    if (bracket[i - dp[i - 1] - 2] == '(') {// i括号的前dp[i - 1]个的前一括号匹配
                        dp[i] = dp[i - dp[i - 1] - 2] + dp[i - 1] + 2;
                    } else {
                        dp[i] = 0;
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        System.out.println(max);
    }

    static void solution2(char[] bracket) {
        Stack<Integer> stack = new Stack<>();
        int res = 0, start = -1;
        for (int i = 0; i < bracket.length; i++) {
            if (bracket[i] == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i;// 匹配失败，记录位置
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        res = Math.max(res, i - start);// 完全匹配
                    } else {
                        res = Math.max(res, i - stack.peek());// 部分匹配
                    }
                }
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] bracket = sc.nextLine().toCharArray();
        solution2(bracket);
    }
}
