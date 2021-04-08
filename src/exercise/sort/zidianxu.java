package exercise.sort;

import java.util.Scanner;

/**
 * 给定整数n和m, 将1到n的这n个整数按字典序排列之后, 求其中的第m个数。
 * 对于n=11, m=4, 按字典序排列依次为1, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 因此第4个数是2.
 */
public class zidianxu {
    static void solution(long n, long m) {
        long i = 1;
        m--;// 排除当前i
        long cnt, start, end;
        while (m != 0) {
            start = i;
            end = i + 1;
            cnt = 0;// 在start到end之间数字个数
            while (start <= n) {
                cnt += Math.min(n + 1, end) - start;
                start *= 10;// 一个分支的下一层
                end *= 10;
            }
            if (m < cnt) {// 在本分支中
                i *= 10;// 前缀确定，进入下一层
                m--;// 排除当前i
            } else {// 不在本分支中
                m -= cnt;// 后续分支中的第几个
                i++;// 下一个分支的前缀
            }
        }
        System.out.println(i);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong(), m = sc.nextLong();
        solution(n, m);
    }
}
