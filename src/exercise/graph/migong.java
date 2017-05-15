package exercise.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by zfh on 17-4-22.
 */
public class migong {
    static final int N = 10;
    static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static class Pos {
        int x;
        int y;
        int layer;

        Pos(int x, int y, int layer) {
            this.x = x;
            this.y = y;
            this.layer = layer;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Pos) obj).x == this.x && ((Pos) obj).y == this.y;
        }
    }

    static int solution(char[][] maze) {
        Queue<Pos> queue = new ArrayDeque<>(N * N);
        Pos start = new Pos(0, 1, 0);
        Pos end = new Pos(N - 1, N - 2, 0);
        queue.add(start);
        Pos curPos;
        int minSteps = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            curPos = queue.remove();
            if (curPos.equals(end) && curPos.layer < minSteps) {
                minSteps = curPos.layer;
            }
            maze[curPos.x][curPos.y] = 'o';// 标记已经访问过的点，保证前向搜索
            for (int[] step : MOVES) {
                int x = curPos.x + step[0];
                int y = curPos.y + step[1];
                if (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == '.') {
                    int layer = curPos.layer + 1;
                    Pos nextPos = new Pos(x, y, layer);
                    queue.add(nextPos);
                }
            }
        }
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] maze = new char[N][N];
        for (int i = 0; i < N; i++) {
            maze[i] = sc.nextLine().toCharArray();
        }
        System.out.println(solution(maze));
    }
}
