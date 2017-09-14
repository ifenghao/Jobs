package exercise.num;

import java.util.Scanner;

/**
 * 丑数
 */
public class ugly {
    static int min3(int a, int b, int c) {
        a = a < b ? a : b;
        return a < c ? a : c;
    }

    static void solution(int n) {
        int[] nums = new int[n];
        nums[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0, next = 1;
        int curMax;
        while (next != n) {
            curMax = min3(nums[i2] * 2, nums[i3] * 3, nums[i5] * 5);// 三组结果中大于上一个丑数的最小值
            nums[next] = curMax;// 放置下一个丑数，保证记录的丑数有序
            while (nums[i2] * 2 <= curMax) i2++;// 三组中大于curMax的最小值索引
            while (nums[i3] * 3 <= curMax) i3++;
            while (nums[i5] * 5 <= curMax) i5++;
            next++;
        }
        System.out.println(nums[n - 1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        solution(n);
    }
}
