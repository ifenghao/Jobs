package beauty;

import java.util.Scanner;

/**
 * 一个数组有 N 个元素，求连续子数组的最大和。
 * 例如：[-1,2,1]，和最大的连续子数组为[2,1]，其和为 3
 * 输入描述:
 * 输入为两行。
 * 第一行一个整数n(1 <= n <= 100000)，表示一共有n个元素
 * 第二行为n个数，即每个元素,每个整数都在32位int范围内。以空格分隔。
 * <p>
 * 输出描述:
 * 所有连续子数组中和最大的值。
 * <p>
 * 输入例子:
 * 3
 * -1 2 1
 * <p>
 * 输出例子:
 * 3
 * ref https://blog.csdn.net/zhufenghao/article/details/70544156
 */
public class zuidaheshuzu {
    static int solution(int[] array) {
        int n = array.length;
        int[] tail = new int[n];
        int[] total = new int[n];
        tail[0] = total[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            tail[i] = Math.max(array[i], tail[i - 1] + array[i]);
            total[i] = Math.max(total[i - 1], tail[i]);
        }
        return total[n - 1];
    }

    static int solution2(int[] array) {
        int tail = array[0];
        int total = array[0];
        for (int i = 1; i < array.length; i++) {
            tail = Math.max(array[i], tail + array[i]);
            total = Math.max(total, tail);
        }
        return total;
    }

    static int solution3(int[] array) {
        int n = array.length;
        int[] tail = new int[n];
        tail[0] = array[0];
        int total = array[0];
        for (int i = 1; i < n; i++) {
            tail[i] = Math.max(array[i], tail[i - 1] + array[i]);
            if (tail[i] > total) {
                total = tail[i];
            }
        }
//        int total = Integer.MIN_VALUE;
//        for (int i = 0; i < n; i++) {
//            if (tail[i] > total) {
//                total = tail[i];
//            }
//        }
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(solution2(array));
    }
}
