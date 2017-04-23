package exercise;

/**
 * Created by zfh on 17-3-26.
 * 一种双核CPU的两个核能够同时的处理任务，现在有n个已知数据量的任务需要交给CPU处理，
 * 假设已知CPU的每个核1秒可以处理1kb，每个核同时只能处理一项任务。n个任务可以按照任意顺序放入CPU进行处理，
 * 现在需要设计一个方案让CPU处理完这批任务所需的时间最少，求这个最小的时间。
 * <p>
 * 输入描述:
 * 输入包括两行：
 * 第一行为整数n(1 ≤ n ≤ 50)
 * 第二行为n个整数length[i](1024 ≤ length[i] ≤ 4194304)，表示每个任务的长度为length[i]kb，每个数均为1024的倍数。
 * <p>
 * <p>
 * 输出描述:
 * 输出一个整数，表示最少需要处理的时间
 * <p>
 * 输入例子:
 * 5
 * 3072 3072 7168 3072 1024
 * <p>
 * 输出例子:
 * 9216
 */

import java.util.Scanner;

public class shuanghe {
    public static int greedyFalse(int[] array) {
        if (array.length == 0) {
            return 0;
        } else if (array.length == 1) {
            return array[0];
        } else if (array.length == 2) {
            return array[0] > array[1] ? array[0] : array[1];
        }
        int length = array.length;
        int max = Integer.MAX_VALUE;
        for (int j = length - 2; j >= 0; j--) {
            int core1 = array[length - 1];
            int core2 = array[j];
            for (int i = length - 2; i >= 0; i--) {
                if (i == j) continue;
                if (core1 > core2) {
                    core2 += array[i];
                } else {
                    core1 += array[i];
                }
            }
            int tmp = core1 > core2 ? core1 : core2;
            if (tmp < max) {
                max = tmp;
            }
        }
        return max;
    }

    public static void DP(int[] array, int[][] f) {
        int n = f.length - 1;
        int cap = f[0].length - 1;
        for (int i = 0; i < cap + 1; i++) {
            f[0][i] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < cap + 1; j++) {
                int no = f[i - 1][j];
                int yes = j < array[i - 1] ? 0 : f[i - 1][j - array[i - 1]] + array[i - 1];
                f[i][j] = no >= yes ? no : yes;
            }
        }
    }

    public static void DPopt(int[] array, int[] f) {
        int n = array.length;
        int cap = f.length - 1;
        for (int i = 0; i < cap + 1; i++) {
            f[i] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = cap; j >= 1; j--) {
                int no = f[j];
                int yes = j < array[i - 1] ? 0 : f[j - array[i - 1]] + array[i - 1];
                f[j] = no >= yes ? no : yes;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
//        Arrays.sort(array);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        int half = sum / 2;
//        int[][] f = new int[n + 1][half + 1];
        int[] f = new int[half + 1];
        DPopt(array, f);
        System.out.println(sum - f[half]);
    }
}