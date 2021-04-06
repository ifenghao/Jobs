package exercise.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 按层处理top，left，down，right
 */
public class spiralMatrix {
    static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        int nrows = matrix.length, ncols = matrix[0].length;
        int top = 0, left = 0, down = nrows - 1, right = ncols - 1;
        while (top <= down && left <= right) {
            for (int col = left; col <= right; col++) {
                res.add(matrix[top][col]);
            }
            for (int row = top + 1; row <= down; row++) {
                res.add(matrix[row][right]);
            }
            if (top < down && left < right) {
                for (int col = right - 1; col >= left; col--) {
                    res.add(matrix[down][col]);
                }
                for (int row = down - 1; row >= top + 1; row--) {
                    res.add(matrix[row][left]);
                }
            }
            top++;left++;down--;right--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(spiralOrder(matrix));
    }
}
