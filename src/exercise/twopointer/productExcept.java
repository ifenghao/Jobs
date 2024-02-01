package exercise.twopointer;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 */
public class productExcept {
    static int[] solution(int[] nums) {
        int n = nums.length;
        int[] left = new int[n]; // 维护每个位置前缀乘积
        int[] right = new int[n]; // 维护每个位置后缀乘积
        int cum = 1;
        for (int i = 0; i < n; i++) {
            cum *= nums[i];
            left[i] = cum;
        }
        cum = 1;
        for (int i = n-1; i >= 0; i--) {
            cum *= nums[i];
            right[i] = cum;
        }
        int[] res = new int[n];
        res[0] = right[1];
        res[n-1] = left[n-2];
        for (int i = 1; i < n-1; i++) {
            res[i] = left[i - 1] * right[i + 1];
        }
        return res;
    }

    static int[] solutionTwoPoint(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        int left = 1, right = 1; // 双指针分别记录前缀、后缀乘积
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            res[i] *= left; // 先排除本身乘以前缀
            res[j] *= right;
            left *= nums[i]; // 再用本身更新前缀
            right *= nums[j];
        }
        return res;
    }

    static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        printArray(solution(nums));
        printArray(solutionTwoPoint(nums));
    }
}
