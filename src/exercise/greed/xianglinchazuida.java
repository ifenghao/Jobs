package exercise.greed;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 令每对相邻数之差的绝对值总和最大
 * 例如25-10-40-5-25, 差绝对值的总和为15+30+35+20=100最大
 */
public class xianglinchazuida {
    static void solution(int n, int[] array) {
        Arrays.sort(array);
        int sum1 = 0, sum2 = 0;
        int res;
        if (n % 2 == 1) {// 奇数，按照(高-低-高-低-高)的形式排列
            for (int i = 0; i < n / 2; i++) {
                sum1 += array[i];// 所有的低值都减了两次
            }
            for (int i = n / 2 + 2; i < n; i++) {
                sum2 += array[i];// 将最小的两个高值放在两边，其余高值都加了两次
            }
            res = 2 * (sum2 - sum1) + array[n / 2] + array[n / 2 + 1];// 将最小的两个高值最后加上
        } else {// 偶数，按照(高-低-高-低-高-低)的形式排列
            for (int i = 0; i < n / 2 - 1; i++) {
                sum1 += array[i];// 将最大的低值放在最后，其余低值都减了两次
            }
            for (int i = n / 2 + 1; i < n; i++) {
                sum2 += array[i];// 将最小的高值放在最前，其余高值都加了两次
            }
            res = 2 * (sum2 - sum1) + array[n / 2] - array[n / 2 - 1];// 加最小的高值减最大低值
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        solution(n, array);
    }
}
