package exercise.dp;

import java.util.Arrays;

/**
 * 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 */
public class minpathsum {
    static int solution(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n]; // 到达i,j的最小路径和
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += grid[i][0];
            dp[i][0] = sum;
        }
        sum = 0;
        for (int j = 0; j < n; j++) {
            sum += grid[0][j];
            dp[0][j] = sum;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    static int solutionOpt(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[0][i];
            dp[i] = sum;
        }
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(solution(grid));
        System.out.println(solutionOpt(grid));
    }
}