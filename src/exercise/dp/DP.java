package exercise.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfh on 17-3-30.
 */
public class DP {
    private static int MAXVALUE = Integer.MAX_VALUE;

    public static int cutRod(int[] p, int n) {
        if (n == 0) {
            return 0;
        }
        int q = -1;
        for (int i = 1; i <= n; i++) {
            int sub = p[i] + cutRod(p, n - i);
            if (sub > q) {
                q = sub;
            }
        }
        return q;
    }

    public static int cutRodMemAux(int[] p, int[] r, int n) {
        if (r[n] >= 0) {
            return r[n];
        }
        if (n == 0) {
            return 0;
        }
        int q = -1;
        for (int i = 1; i <= n; i++) {
            int sub = p[i] + cutRodMemAux(p, r, n - i);
            if (sub > q) {
                q = sub;
            }
        }
        r[n] = q;
        return q;
    }

    public static int cutRodMem(int[] p, int n) {
        int[] r = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            r[i] = -1;
        }
        return cutRodMemAux(p, r, n);
    }

    public static void cutRodBottom1(int[] p, int[] r, int[] s, int n) {
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = -1;
            for (int i = 1; i <= j; i++) {
                int sub = p[i - 1] + r[j - i];
                if (sub > q) {
                    q = sub;
                    s[j] = i;
                }
            }
            r[j] = q;
        }
    }

    public static List<Integer> cutSolutionBottom(int[] s, int n) {
        List<Integer> solution = new ArrayList<>();
        while (n > 0) {
            solution.add(s[n]);
            n = n - s[n];
        }
        return solution;
    }

    public static void cutRodBottom2(int[] p, int[] r, int[] s, int n) {
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = -1;
            for (int i = 1; i <= j; i++) {
                int sub = (i == j) ? p[i - 1] : r[i] + r[j - i];
                if (sub > q) {
                    q = sub;
                    s[j] = i;
                }
            }
            r[j] = q;
        }
    }

    public static void matrixChain(int[] p, int[][] m, int[][] s) {
        int n = p.length - 1;
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = MAXVALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public static void matrixChainSolution(List<String> result, int[][] s, int i, int j) {
        if (i == j) {
            result.add(Integer.toString(i));
        } else {
            result.add("(");
            matrixChainSolution(result, s, i, s[i][j]);
            matrixChainSolution(result, s, s[i][j] + 1, j);
            result.add(")");
        }
    }

    public static void LCSLength(String[] X, String[] Y, int[][] c, String[][] b) {
        int nX = X.length;
        int nY = Y.length;
        for (int i = 0; i < nX + 1; i++) {
            c[i][0] = 0;
        }
        for (int i = 0; i < nY + 1; i++) {
            c[0][i] = 0;
        }
        for (int i = 1; i <= nX; i++) {
            for (int j = 1; j <= nY; j++) {
                if (X[i - 1].equals(Y[j - 1])) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = "leftup";
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = "up";
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = "left";
                }
            }
        }
    }

    public static void LCSSolution(String[] X, String[][] b, int i, int j, List<String> result) {
        if (i == 0 || j == 0) {
            return;
        }
        if (b[i][j].equals("leftup")) {
            LCSSolution(X, b, i - 1, j - 1, result);
            result.add(X[i - 1]);
        } else if (b[i][j].equals("up")) {
            LCSSolution(X, b, i - 1, j, result);
        } else {
            LCSSolution(X, b, i, j - 1, result);
        }
    }

    public static void pack01(int[] w, int[] v, int[][] f, int[][] s) {
        int n = f.length - 1;
        int W = f[0].length - 1;
        for (int i = 0; i < n + 1; i++) {
            f[i][0] = 0;
        }
        for (int i = 0; i < W + 1; i++) {
            f[0][i] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                int no = f[i - 1][j];
                int yes = j < w[i - 1] ? 0 : f[i - 1][j - w[i - 1]] + v[i - 1];
                if (no >= yes) {
                    f[i][j] = no;
                    s[i][j] = 0;
                } else {
                    f[i][j] = yes;
                    s[i][j] = 1;
                }
            }
        }
    }

    public static void pack01Solution(int[] w, int[][] s, List<Integer> result) {
        int n = s.length - 1;
        int W = s[0].length - 1;
        for (int i = n; i > 0; i--) {
            if (s[i][W] == 1) {
                result.add(i);
                W -= w[i - 1];
            }
        }
    }

    public static void pack01Opt(int[] w, int[] v, int[] f, int[][] s) {
        int n = w.length;
        int W = f.length - 1;
        for (int i = 0; i < W + 1; i++) {
            f[i] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = W; j >= 1; j--) {
                int no = f[j];
                int yes = j < w[i - 1] ? 0 : f[j - w[i - 1]] + v[i - 1];
                if (no >= yes) {
                    f[j] = no;
                    s[i][j] = 0;
                } else {
                    f[j] = yes;
                    s[i][j] = 1;
                }
            }
        }
    }

    public static void palindrome(String[] s, int[][] dp, String[][] so) {
        int n = s.length;
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if (!s[i].equals(s[j])) {
                    if (dp[i][j - 1] > dp[i + 1][j]) {
                        dp[i][j] = dp[i][j - 1];
                        so[i][j] = "left";
                    } else {
                        dp[i][j] = dp[i + 1][j];
                        so[i][j] = "down";
                    }
                } else {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    so[i][j] = "leftdown";
                }
            }
        }
    }

    public static void palindromeSolution(String[] s, String[][] so, int i, int j, List<String> result) {
        if (i>=s.length || j<=0) return;
        if (i == j) {
            result.add(result.size() / 2, s[i]);
            return;
        }
        if ("leftdown".equals(so[i][j])) {
            palindromeSolution(s, so, i + 1, j - 1, result);
            result.add(0, s[i]);
            result.add(result.size(), s[i]);
        } else if ("left".equals(so[i][j])) {
            palindromeSolution(s, so, i, j - 1, result);
        } else {
            palindromeSolution(s, so, i + 1, j, result);
        }
    }

    public static void main(String[] args) {
//        int[] p = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
//        int n = 7;
//        int[] r = new int[n + 1];
//        int[] s = new int[n + 1];
//        cutRodBottom1(p, r, s, n);
//        System.out.println(r[n]);
//        List<Integer> solution = cutSolutionBottom(s, n);
//        System.out.println(solution);

//        int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};
//        int n = p.length - 1;
//        int[][] m = new int[n + 1][n + 1];
//        int[][] s = new int[n + 1][n + 1];
//        matrixChain(p, m, s);
//        System.out.println(m[1][n]);
//        List<String> result = new ArrayList<>();
//        matrixChainSolution(result, s, 1, n);
//        System.out.println(result);

//        String[] X = "ABCBDAB".split("");
//        String[] Y = "BDCABA".split("");
//        int nX = X.length;
//        int nY = Y.length;
//        int[][] c = new int[nX + 1][nY + 1];
//        int[][] b = new int[nX + 1][nY + 1];
//        LCSLength(X, Y, c, b);
//        System.out.println(c[nX][nY]);
//        List<String> result = new ArrayList<>();
//        LCSSolution(X, b, nX, nY, result);
//        System.out.println(result);

//        int[] w = new int[]{2, 2, 6, 5, 4};
//        int[] v = new int[]{6, 3, 5, 4, 6};
//        int n = w.length;
//        int W = 10;
////        int[][] f = new int[n + 1][W + 1];
////        int[][] s = new int[n + 1][W + 1];
////        pack01(w, v, f, s);
////        System.out.println(f[n][10]);
//        int[] f = new int[W + 1];
//        int[][] s = new int[n + 1][W + 1];
//        pack01Opt(w, v, f, s);
//        System.out.println(f[10]);
//        List<Integer> result = new ArrayList<>();
//        pack01Solution(w, s, result);
//        System.out.println(result);

        String str = "fasdjklgnrkljklfdas";
        String[] s = str.split("");
        int n = s.length;
        int[][] dp = new int[n][n];
        String[][] so = new String[n][n];
        palindrome(s, dp, so);
        System.out.println(dp[0][n - 1]);
        List<String> result = new ArrayList<>();
        palindromeSolution(s, so, 0, n - 1, result);
        System.out.println(result);
    }
}
