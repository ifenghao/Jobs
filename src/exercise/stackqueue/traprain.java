package exercise.stackqueue;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 */
public class traprain {
    // 单调栈，速度较慢
    static int solution(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int total = 0, pos, trapHeight, trapWidth;
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (height[i] <= height[stack.peek()]) { // 保持栈里的元素递减或相等
                stack.push(i);
            } else { // 遇到大于栈顶的元素
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) { // 将所有小元素出栈
                    pos = stack.pop();
                    if (stack.isEmpty()) break; // 左边无柱子则放弃
                    trapHeight = Math.min(height[stack.peek()], height[i]) - height[pos];
                    trapWidth = i - stack.peek() - 1;
                    total += trapHeight * trapWidth; // 矩形面积
                }
                stack.push(i);
            }
        }
        return total;
    }

    // 双指针法，左右最大值之间取较小的，其间的高度差都算入结果
    static int solution2(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        int left = 0, right = n - 1;
        int leftMax = height[left], rightMax = height[right];
        int total = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]); // 左侧高点更新
            rightMax = Math.max(rightMax, height[right]); // 右侧高点更新
            if (leftMax < rightMax) {
                total += leftMax - height[left]; // 左侧低点计入结果
                left++;
            } else {
                total += rightMax - height[right]; // 右侧低点计入结果
                right--;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution2(height));
    }
}
