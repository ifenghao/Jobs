package exercise.others;

import java.util.Scanner;

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
