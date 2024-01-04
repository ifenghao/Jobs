package exercise.dp;

import java.util.Scanner;
import java.util.Stack;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 匹配的序列中一定是最左边为(，最右边为)
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 *
 * 输入：s = ""
 * 输出：0
 */
public class matchbracket {
    static void solution(char[] bracket) {
        int n = bracket.length;
        int[] dp = new int[n + 1]; // 以第i个括号结尾的最大匹配长度
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (bracket[i - 1] == '(') {// 当前括号无法匹配
                dp[i] = 0;
            } else if (i >= 2) {
                if (bracket[i - 2] == '(') {// i括号和i-1括号匹配
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
