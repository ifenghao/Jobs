package exercise.stackqueue;

import java.util.Stack;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 */
public class removeduplicate {
    static String solution(String s) {
        char[] chs = s.toCharArray();
        int[] lastPos = new int[26]; // 保存每个字母最后出现的位置，用O(1)时间判断当前字母是否最后一次出现
        for (int i = 0; i < chs.length; i++) {
            lastPos[chs[i] - 'a'] = i;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chs.length; i++) {
            if (stack.contains(chs[i])) continue;
            while (!stack.isEmpty() && stack.peek() > chs[i] && lastPos[stack.peek() - 'a'] >= i) {// 单调栈递增
                stack.pop(); // 不是最后一次出现的字母出栈
            }
            stack.push(chs[i]);
        }
        StringBuffer sb = new StringBuffer();
        for (char ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("bcabc"));
    }
}
