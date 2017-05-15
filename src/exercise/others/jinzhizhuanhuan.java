package exercise.others;

import java.util.Scanner;

/**
 * 给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数
 * 输入描述:
 * 输入为一行，M(32位整数)、N(2 ≤ N ≤ 16)，以空格隔开。
 * <p>
 * 输出描述:
 * 为每个测试实例输出转换后的数，每个输出占一行。如果N大于9，则对应的数字规则参考16进制（比如，10用A表示，等等）
 * <p>
 * 输入例子:
 * 7 2
 * <p>
 * 输出例子:
 * 111
 */
public class jinzhizhuanhuan {
    static final String[] LETTER = new String[]{"A", "B", "C", "D", "E", "F"};

    static StringBuilder solution(int m, int n) {
        StringBuilder sb = new StringBuilder();
        int offset = 0;
        if (m < 0) {// 可能有负数
            m = -m;
            ++offset;
            sb.append("-");
        }
        while (m > 0) {
            int rem = m % n;
            m /= n;
            sb.insert(offset, rem < 10 ? rem : LETTER[rem % 10]);// 大于十进制要用字母
        }
        return sb;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        System.out.println(solution(m, n));
    }
}
