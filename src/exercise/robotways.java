package exercise;

import java.util.Scanner;

/**
 * 有一个X x Y的网格，一个机器人只能走格点且只能向右或向下走，要从左上角走到右下角。
 * 请设计一个算法，计算机器人有多少种走法。
 * 给定两个正整数int x,int y，请返回机器人的走法数目。保证x＋y小于等于12。
 * 测试样例：
 * 2,2
 * 返回：2
 */
public class robotways {
    static void solution1(int[][] n, int x, int y) {
        for (int j = 0; j < y; j++) {
            n[0][j] = 1;
        }
        for (int i = 0; i < x; i++) {
            n[i][0] = 1;
        }
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                n[i][j] = n[i - 1][j] + n[i][j - 1];
            }
        }
    }

    static int solution2(int x, int y) {
        if (x == 1 || y == 1) return 1;
        return solution2(x - 1, y) + solution2(x, y - 1);
    }

    static boolean isForbidden(int x, int y, int[] xforbid, int[] yforbid) {
        int length = xforbid.length;
        for (int i = 0; i < length; i++) {
            if (x == xforbid[i] && y == yforbid[i]) return true;
        }
        return false;
    }

    static void variation(int[][] n, int x, int y, int[] xforbid, int[] yforbid) {
        for (int j = 0; j < y; j++) {
            n[0][j] = 1;
        }
        for (int i = 0; i < x; i++) {
            n[i][0] = 1;
        }
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                if (isForbidden(i, j, xforbid, yforbid)) n[i][j] = Integer.MIN_VALUE;
                else if (n[i - 1][j] == Integer.MIN_VALUE && n[i][j - 1] == Integer.MIN_VALUE) {
                    n[i][j] = Integer.MIN_VALUE;
                } else if (n[i - 1][j] != Integer.MIN_VALUE && n[i][j - 1] == Integer.MIN_VALUE) {
                    n[i][j] = n[i - 1][j];
                } else if (n[i - 1][j] == Integer.MIN_VALUE && n[i][j - 1] != Integer.MIN_VALUE) {
                    n[i][j] = n[i][j - 1];
                } else {
                    n[i][j] = n[i - 1][j] + n[i][j - 1];
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[][] n = new int[x][y];
        solution1(n, x, y);
        System.out.println(n[x - 1][y - 1]);
        int nforbid = sc.nextInt();
        int[] xforbid = new int[nforbid];
        int[] yforbid = new int[nforbid];
        for (int i = 0; i < nforbid; i++) {
            xforbid[i] = sc.nextInt();
            yforbid[i] = sc.nextInt();
        }
        variation(n, x, y, xforbid, yforbid);
        System.out.println(n[x - 1][y - 1]);
    }
}
