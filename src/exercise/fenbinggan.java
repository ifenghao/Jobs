package exercise;

import java.util.Scanner;

/**
 * Created by zfh on 17-4-12.
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
//        solution(c, m, n);
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
