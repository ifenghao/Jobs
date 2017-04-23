package exercise;

/**
 * Created by zfh on 17-4-2.
 */

import java.util.*;

public class paidui {
    static int solution(String[] array) {
        int n = array.length;
        int moves = 0;
        int end = 0;
        for (int i = 1; i < n; i++) {
            if (array[i].equals(array[0])) {
                if (i == end + 1) {
                    end = i;
                } else {
                    end = end + 1;
                    moves += i - end;
                }
            }
        }
        return moves;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String queue = sc.nextLine();
        String[] array = queue.split("");
        System.out.println(solution(array));
    }
}
