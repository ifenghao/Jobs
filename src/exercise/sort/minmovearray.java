package exercise.sort;

import java.util.Arrays;

/**
 * 使数组唯一的最小增量
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。返回使 A 中的每个值都是唯一的最少操作次数。
 * 示例 1:
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 示例 2:
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 */
public class minmovearray {
    static int minIncrementForUnique(int[] A) {
        if (A == null || A.length < 2) return 0;
        Arrays.sort(A); // 先排序
        int pre = A[0], moves = 0;
        for (int i = 1; i < A.length; i++) {
            if (pre >= A[i]) { // 让每个元素=pre + 1
                moves += pre - A[i] + 1;
                A[i] = pre + 1;
            }
            pre = A[i];
        }
        return moves;
    }

    public static void main(String[] args) {
        int[] A = {3,2,1,2,1,7};
        System.out.println(minIncrementForUnique(A));
    }
}
