package exercise.num;

import java.util.Scanner;

/**
 * 约瑟夫环：编号为0~n-1的n个人围成一个圈，从第1个人开始报数，报m的人出圈，再从他的下一个人起重新报数，
 * 如此下去，直到所有人全部出圈为止。求最后出圈的人的编号。
 * <p>
 * 第一个被删除的数为(m - 1) % n
 * 第二轮的开始数字为m%n，假如已知n-1个人的最后人编号为x，那么n个人时其编号为(x+m%n)%n=(x+m)%n
 * 第二个被删除的数为(m - 1) % (n - 1)
 * 第三轮的开始数字为m%(n-1)，假如已知n-2个人的最后人编号为x，那么n-1个人时其编号为(x+m%(n-1))%(n-1)=(x+m)%(n-1)
 * f [1] = 0;
 * f [i] = (f[i-1] + m) % i; (i>1)
 */
public class Josephuse {
    static void solution(int n, int m) {
        int f = 0;
        for (int i = 2; i <= n; i++) {
            f = (f + m) % i;
        }
        System.out.println(f);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        solution(n, m);
    }
}
