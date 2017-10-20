package exercise.graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 推箱子游戏，.表示可以经过,*表示箱子,S表示人,E表示目的地,#表示障碍
 * 人和箱子的位置确定了当前玩家的一个状态,下一步要去往的状态无非是这么两种情况：人走到另一个空地,箱子没动；人推箱子前进一步
 * 广度优先搜索，保存状态数组{人的位置，箱子的位置}
 */

public class pushbox {
    static final int[][] steps = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void solution(char[][] maze) {
        int m = maze.length, n = maze[0].length;
        int[][][][] state = new int[50][50][50][50];
        int xman = 0, yman = 0, xend = 0, yend = 0, xbox = 0, ybox = 0;
        int xmann = 0, ymann = 0, xboxn = 0, yboxn = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (maze[i][j] == 'S') {// 人的初始位置
                    xman = i;
                    yman = j;
                } else if (maze[i][j] == 'E') {// 终止位置
                    xend = i;
                    yend = j;
                } else if (maze[i][j] == '*') {// 箱子的初始位置
                    xbox = i;
                    ybox = j;
                }
            }
        }
        Deque<Integer[]> dq = new LinkedList<>();
        dq.addLast(new Integer[]{xman, yman, xbox, ybox});
        state[xman][yman][xbox][ybox] = 1;
        while (!dq.isEmpty()) {
            Integer[] cur = dq.removeFirst();
            xman = cur[0];
            yman = cur[1];
            xbox = cur[2];
            ybox = cur[3];
            for (int i = 0; i < 4; i++) {
                xmann = xman + steps[i][0];// 人的下一个位置
                ymann = yman + steps[i][1];
                xboxn = xmann + steps[i][0];// 人推箱子时箱子的下一个位置
                yboxn = ymann + steps[i][1];
                if ((xmann != xbox || ymann != ybox) &&// 人还没有推到箱子
                        isValid(xmann, ymann, maze) &&// 人的下一位置合法
                        state[xmann][ymann][xbox][ybox] == 0) {// 下一状态没有走过
                    state[xmann][ymann][xbox][ybox] = state[xman][yman][xbox][ybox] + 1;
                    dq.addLast(new Integer[]{xmann, ymann, xbox, ybox});
                } else if (xmann == xbox && ymann == ybox &&// 人已经推到箱子
                        isValid(xboxn, yboxn, maze) &&// 直接看箱子的下一位置合法
                        state[xmann][ymann][xboxn][yboxn] == 0) {// 下一状态没有走过
                    state[xmann][ymann][xboxn][yboxn] = state[xman][yman][xbox][ybox] + 1;
                    if (xboxn == xend && yboxn == yend) {
                        System.out.println(state[xmann][ymann][xboxn][yboxn] - 1);
                        return;
                    }
                    dq.addLast(new Integer[]{xmann, ymann, xboxn, yboxn});
                }
            }
        }
        System.out.println(-1);
    }

    static boolean isValid(int x, int y, char[][] maze) {
        if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != '#')
            return true;
        return false;
    }

    public static void main(String[] args) {
        char[][] maze = new char[][]{
                {'.', '.', '.', '.'},
                {'.', '.', '*', 'E'},
                {'.', '.', '.', '.'},
                {'.', 'S', '.', '.'}
        };
        solution(maze);
    }
}
