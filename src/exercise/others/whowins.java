package exercise.others;
import java.util.Scanner;

/**
 * 两个人轮流取n个石子，直到取到最后一个石子的人获胜，要求
 * 1、第一次不能全取
 * 2、每次不能不取
 * 3、每次取的数量不能多于上一次执行的人取的个数
 * 问先取的话有没有必胜取法，若有求第一次可取的最大值
 *
 * 有必胜方案，只要谁面对2^k个石子必输，因此每次取的时候剩给对方石头数为比n小的最大的2^k即可
 */
public class whowins {
    static int solution(int n) {
        int maxk = (int) Math.floor(Math.log(n) / Math.log(2));
        int deadPoint = (int) Math.pow(2, maxk);
        return n - deadPoint;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(solution(n));
    }
}
