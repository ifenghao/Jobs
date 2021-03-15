package exercise.backtrace;

import java.util.Deque;
import java.util.LinkedList;

public class permutecombine {
    static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    static void permute(char[] chars, int start) {
        if (start == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }
        for (int i = start; i < chars.length; i++) { // 每个字符与第一个字符交换
            swap(chars, start, i);
            permute(chars, start + 1);
            swap(chars, start, i);
        }
    }

    static void combine(char[] chars) {
        Deque<Character> dq = new LinkedList<>();
        for (int i = 1; i <= chars.length; i++) {
            combineCore(chars, dq, 0, i);
        }
    }

    static void combineCore(char[] chars, Deque<Character> dq, int start, int nSelect) {
        if (nSelect == 0) {
            for (char ch : dq)
                System.out.print(ch);
            System.out.println();
            return;
        }
        if (start == chars.length)
            return;
        // 如果选择start字符的情况
        dq.addLast(chars[start]);
        combineCore(chars, dq, start + 1, nSelect - 1);
        dq.removeLast();
        // 如果跳过start字符的情况
        combineCore(chars, dq, start + 1, nSelect);
    }

    public static void main(String[] args) {
        String x = "abc";
        permute(x.toCharArray(), 0);
        combine(x.toCharArray());
    }
}
