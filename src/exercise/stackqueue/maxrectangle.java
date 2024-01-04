package exercise.stackqueue;

import java.util.Stack;

/**
 * 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例 1：
 * 输入：matrix =
 * [["1","0","1","0","0"],
 *  ["1","0","1","1","1"],
 *  ["1","1","1","1","1"],
 *  ["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 * 输入：matrix = [["0","0"]]
 * 输出：0
 */
public class maxrectangle {
    static void solution(char[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] hist = new int[n];
        int max = 0, tmp;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {// 每行当做直方图
                if (mat[i][j] == 0) {// 遇到0就重新置零
                    hist[j] = 0;
                } else {// 遇到1就在原来基础上加一
                    hist[j] += 1;
                }
            }
            tmp = histmax(n, hist);
            if (tmp > max) max = tmp;
        }
        System.out.println(max);
    }

    // 转化为一维柱状图中组成的最大矩形的面积
    static int histmax(int n, int[] h) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0, idx, tmpArea;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && h[stack.peek()] > h[i]) {// 保持栈里的元素递增
                idx = stack.pop(); // 当前元素比栈顶小，最大面积取决于当前元素，栈顶不会再使用需要弹出
                tmpArea = stack.isEmpty() ? i * h[idx] : (i - stack.peek() - 1) * h[idx];
                if (tmpArea > maxArea) maxArea = tmpArea;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {// 继续比较剩下元素
            idx = stack.pop();
            tmpArea = stack.isEmpty() ? n * h[idx] : (n - stack.peek() - 1) * h[idx];
            if (tmpArea > maxArea) maxArea = tmpArea;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        char[][] mat = new char[][]{
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}};
        solution(mat);
    }
}
