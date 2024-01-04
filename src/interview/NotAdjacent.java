package interview;

import java.util.Scanner;

/**
 * Created by zfh on 17-5-12.
 * ref https://blog.csdn.net/zhufenghao/article/details/71791777
 */
public class NotAdjacent {
    static int solution(int[] array, int n) {
        if (n < 1) return 0;
        if (n == 1) return array[0];
        return Math.max(solution(array, n - 1), solution(array, n - 2) + array[n - 1]);
    }

    static int solution2(int[] array, int n) {
        int[] s = new int[n + 1];
        s[0] = 0;
        s[1] = array[0];
        for (int i = 2; i < n + 1; i++) {
            int takei = s[i - 2] + array[i - 1];
            int skipi = s[i - 1];
            s[i] = Math.max(takei, skipi);
        }
        return s[n];
    }

    static int circleSolution(int[] array, int n) {
        int[] s = new int[n + 1];
        boolean[] isUsed = new boolean[n];// 元素是否使用，用于构造最优解
        s[0] = 0;
        s[1] = array[0];
        isUsed[0] = true;
        for (int i = 2; i < n + 1; i++) {
            int takei = s[i - 2] + array[i - 1];
            int skipi = s[i - 1];
            if (takei > skipi) {
                s[i] = takei;
                isUsed[i - 1] = true;
            } else {
                s[i] = skipi;
                isUsed[i - 1] = false;
            }
        }
        return makeCircle(s, isUsed, n);
    }

    static int makeCircle(int[] s, boolean[] isUsed, int n) {
        if (!isUsed[n - 1]) return s[n];// 最后一个元素没用，首尾不会相邻
        int i = n - 1;
        boolean isFirstUsed = false;// 第一个元素是否使用
        while (i >= 0) {// 构造最优解的过程
            if (isUsed[i]) {
                if (i == 0) isFirstUsed = true;
                i -= 2;
            } else {
                i -= 1;
            }
        }
        return isFirstUsed ? s[n - 1] : s[n];// 如果首尾相邻，删除尾元素
    }

    static int[] leftShift(int[] array, int steps) {
        int n = array.length;
        int[] newArray = new int[n];
        System.arraycopy(array, steps, newArray, 0, n - steps);
        System.arraycopy(array, 0, newArray, n - steps, steps);
        return newArray;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        int r1 = circleSolution(array, n);
        array = leftShift(array, 1);
        int r2 = circleSolution(array, n);
        r2 = Math.max(r1, r2);
        array = leftShift(array, 1);
        int r3 = circleSolution(array, n);
        r3 = Math.max(r2, r3);
        System.out.println(r3);
    }
}
