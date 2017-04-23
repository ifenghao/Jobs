package exercise;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by zfh on 17-4-22.
 */
public class Graph {
    static boolean[] visited;
    static int[][] mat;

    static int[][] buildMatrix(int[][] edges, int n) {
        int[][] mat = new int[n][n];
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            mat[i][j] = 1;
            mat[j][i] = 1;
        }
        return mat;
    }

    static void dfs(int cur, int n) {
        System.out.println(cur);
        visited[cur] = true;
        for (int i = 0; i < n; i++) {
            if (mat[cur][i] == 1 && !visited[i]) {
                dfs(i, n);
            }
        }
    }

    static void bfs(int cur, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        System.out.println(cur);
        visited[cur] = true;
        queue.add(cur);// 在入队前访问
        while (!queue.isEmpty()) {
            cur = queue.remove();
            for (int i = 0; i < n; i++) {
                if (mat[cur][i] == 1 && !visited[i]) {
                    System.out.println(i);
                    visited[i] = true;
                    queue.add(i);// 在入队前访问
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 4}, {1, 2}, {1, 3}, {3, 4}};
        mat = buildMatrix(edges, 5);
        visited = new boolean[5];
        bfs(0, 5);
    }
}
