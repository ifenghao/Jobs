package beauty;

import java.util.Scanner;

/**
 * Created by zfh on 17-4-22.
 */
public class zuidaheshuzu {
    static int solution(int[] array) {
        int n = array.length;
        int[] tail = new int[n];
        int[] total = new int[n];
        tail[0] = total[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            tail[i] = Math.max(array[i], tail[i - 1] + array[i]);
            total[i] = Math.max(total[i - 1], tail[i]);
        }
        return total[n - 1];
    }

    static int solution2(int[] array) {
        int tail = array[0];
        int total = array[0];
        for (int i = 1; i < array.length; i++) {
            tail = Math.max(array[i], tail + array[i]);
            total = Math.max(total, tail);
        }
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(solution2(array));
    }
}
