package exercise.graph;

/**
 * 在矩形中匹配字符串路径
 * 遍历每个节点深搜
 */
public class matchstring {
    static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int m, n;

    static boolean dfs(char[][] mat, String str, boolean[][] visited, int curs, int curx, int cury) {
        if (curs == str.length()) return true;// 字符串匹配结束
        if (mat[curx][cury] == str.charAt(curs)) {
            visited[curx][cury] = true;
            int x, y;
            boolean res = false;
            for (int i = 0; i < MOVES.length; i++) {// 对每个方向进行搜索
                x = curx + MOVES[i][0];
                y = cury + MOVES[i][1];
                if (x >= 0 && y >= 0 && x < m && y < n && !visited[x][y]) {
                    res = res || dfs(mat, str, visited, curs + 1, x, y);
                }
            }
            if (res) {// 可以匹配
                return true;
            } else {// 不能匹配要清除访问标志
                visited[curx][cury] = false;
                return false;
            }
        }
        return false;
    }

    static boolean solution(char[][] mat, String str) {
        m = mat.length;
        n = mat[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(mat, str, visited, 0, i, j))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] mat = new char[][]{{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 's'}, {'a', 'd', 'e', 'e'}};
        String str = "csec";
        System.out.println(solution(mat, str));
    }
}
