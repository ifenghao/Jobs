package exercise.graph;

import java.util.*;

/**
 * 小青蛙有一天不小心落入了一个地下迷宫,小青蛙希望用自己仅剩的体力值P跳出这个地下迷宫。
 * 为了让问题简单,假设这是一个n*m的格子迷宫,迷宫每个位置为0或者1,0代表这个位置有障碍物,
 * 小青蛙达到不了这个位置;1代表小青蛙可以达到的位置。小青蛙初始在(0,0)位置,地下迷宫的出口
 * 在(0,m-1)(保证这两个位置都是1,并且保证一定有起点到终点可达的路径),小青蛙在迷宫中水平
 * 移动一个单位距离需要消耗1点体力值,向上爬一个单位距离需要消耗3个单位的体力值,向下移动
 * 不消耗体力值,当小青蛙的体力值等于0的时候还没有到达出口,小青蛙将无法逃离迷宫。
 * 现在需要你帮助小青蛙计算出能否用仅剩的体力值跳出迷宫(即达到(0,m-1)位置)。
 * 输入描述:
 * 输入包括n+1行:
 * <p>
 * 第一行为三个整数n,m(3 <= m,n <= 10),P(1 <= P <= 100)
 * <p>
 * 接下来的n行:
 * <p>
 * 每行m个0或者1,以空格分隔
 * <p>
 * 输出描述:
 * 如果能逃离迷宫,则输出一行体力消耗最小的路径,输出格式见样例所示;如果不能逃离迷宫,则输出"Can not escape!"。
 * 测试数据保证答案唯一
 * <p>
 * 输入例子:
 * 4 4 10
 * 1 0 0 1
 * 1 1 0 1
 * 0 1 1 1
 * 0 0 1 1
 * <p>
 * 输出例子:
 * [0,0],[1,0],[1,1],[2,1],[2,2],[2,3],[1,3],[0,3]
 */
public class migong2 {
    static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static final int[] COST = new int[]{1, 1, 3, 0};
    static List<Pos> path;

    static class Pos {
        int x, y, layer, rest;
        Pos prePos;

        Pos(int x, int y, int layer, int rest, Pos prePos) {
            this.x = x;
            this.y = y;
            this.layer = layer;
            this.rest = rest;
            this.prePos = prePos;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Pos) obj).x == this.x && ((Pos) obj).y == this.y;
        }
    }

    static void solution(int[][] maze, int p) {
        int n = maze.length;
        int m = maze[0].length;
        Queue<Pos> queue = new ArrayDeque<>(m * n);
        Pos start = new Pos(0, 0, 0, p, null);
        Pos end = new Pos(0, m - 1, 0, 0, null);
        queue.add(start);
        Pos curPos;
        int minSteps = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            curPos = queue.remove();
            if (curPos.equals(end) && curPos.layer < minSteps) {
                minSteps = curPos.layer;
                path = new LinkedList<>();
                findPath(curPos);
            }
            maze[curPos.x][curPos.y] = 0;
            for (int i = 0; i < 4; i++) {
                int x = curPos.x + MOVES[i][0];
                int y = curPos.y + MOVES[i][1];
                int rest = curPos.rest - COST[i];
                if (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 1 && rest >= 0) {
                    int layer = curPos.layer + 1;
                    Pos nextPos = new Pos(x, y, layer, rest, curPos);
                    queue.add(nextPos);
                }
            }
        }
    }

    static void findPath(Pos end) {
        if (end == null) return;
        findPath(end.prePos);
        path.add(end);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();
        int[][] maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maze[i][j] = sc.nextInt();
            }
        }
        solution(maze, p);
        if (path == null) {
            System.out.println("Can not escape!");
        } else {
            int steps = path.size();
            for (int i = 0; i < steps; i++) {
                Pos pos = path.get(i);
                System.out.print("[" + pos.x + "," + pos.y + "]");
                if (i != steps - 1) System.out.print(",");
            }
        }
    }
}
