package exercise.dp;

import java.util.Scanner;

/**
 * 易老师购买了一盒饼干，盒子中一共有k块饼干，但是数字k有些数位变得模糊了，看不清楚数字具体是多少了。
 * 易老师需要你帮忙把这k块饼干平分给n个小朋友，易老师保证这盒饼干能平分给n个小朋友。现在你需要计算出k有多少种可能的数值
 * 输入描述:
 * 输入包括两行：
 *  第一行为盒子上的数值k，模糊的数位用X表示，长度小于18(可能有多个模糊的数位)
 *  第二行为小朋友的人数n
 * 输出描述:
 * 输出k可能的数值种数，保证至少为1
 * 示例1
 * 输入
 * 9999999999999X 3
 * 输出
 * 4
 */
public class fenbinggan {
    static void solution(long[][] c, int m, int n) {
        for (int i = 0; i < n; i++) {
            c[0][i] = 0L;
        }
        c[0][0] = 1L;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 10; k++) {
                    c[i][(j * 10 + k) % n] += c[i - 1][j];
                }
            }
        }
    }

    static void solution2(long[][] c, String seq, int n) {
        int length = seq.length();
        for (int i = 0; i < n; i++) {
            c[0][i] = 0L;
        }
        c[0][0] = 1L;
        for (int i = 1; i <= length; i++) {
            char ch = seq.charAt(i - 1);
            for (int j = 0; j < n; j++) {
                if (ch == 'X') {
                    for (int k = 0; k < 10; k++) {
                        c[i][(j * 10 + k) % n] += c[i - 1][j];
                    }
                } else {
                    int k = ch - '0';
                    c[i][(j * 10 + k) % n] += c[i - 1][j];
                }
            }
        }
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int m = sc.nextInt();
//        int n = sc.nextInt();
//        long[][] c = new long[m + 1][n];
//        zichuan(c, m, n);
//        System.out.println(c[m][0]);
//        for (int i = 0; i < 1000; i++) {
//            for (int j = 0; j < 1000; j++) {
//                for (int k = 2; k < 200; k++) {
//                    int a=(i+j)%k;
//                    int b=((i%k)+(j%k))%k;
//                    if (a!=b){
//                        System.out.println(a+" "+b);
//                    }
//                }
//            }
//        }
        Scanner sc = new Scanner(System.in);
        String seq = sc.next();
        int length = seq.length();
        int n = sc.nextInt();
        long[][] c = new long[length + 1][n];
        solution2(c, seq, n);
        System.out.println(c[length][0]);
    }
}
