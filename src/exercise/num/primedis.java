package exercise.num;

import java.util.Scanner;

/**
 * 排除合数法找n以内的素数
 * 以两个数之间素数的个数作为距离，计算一列从大到小的数两两之间距离之和
 */
public class primedis {
    static boolean[] allPrime(int n) {
        boolean[] prime = new boolean[n + 1];
        prime[2] = true;
        for (int i = 3; i <= n; i += 2) {
            prime[i] = true;// 奇数有可能是质数
        }
        for (int i = 3; i * i <= n; i++) {
            if (prime[i]) {
                for (int j = i; j * i <= n; j++) {
                    prime[j * i] = false;
                }
            }
        }
        return prime;
    }

    static int dis(boolean[] prime, int lo, int hi) {
        int cnt = 0;
        for (int i = lo + 1; i < hi; i++) {
            if (prime[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    static void solution(int n, int[] a) {
        boolean[] prime = allPrime(a[n - 1]);
        int tmp, sum = 0;
        for (int i = 1; i < n; i++) {
            tmp = dis(prime, a[i - 1], a[i]);
            sum += tmp * i * (n - i);// 每段距离重复了n-i(i后)+(i-1)*(n-i)(i前)
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        solution(n, a);
    }
}
