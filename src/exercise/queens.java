package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zfh on 17-4-11.
 */
public class queens {
    static List<int[]> results = new ArrayList<>();
    static int[] cols;

    static void solution(int curRow, int n) {
        if (curRow == n) {
            int[] newResult = new int[n];
            System.arraycopy(cols, 0, newResult, 0, n);
            results.add(newResult);
        } else {
            for (int j = 0; j < n; j++) {
                cols[curRow] = j;
                if (!isConflicted(curRow)) {
                    solution(curRow + 1, n);
                }
            }
        }
    }

    static boolean isConflicted(int curRow) {
        for (int i = 0; i < curRow; i++) {
            if (cols[curRow] == cols[i]) return true;
            if (curRow - cols[curRow] == i - cols[i]) return true;
            if (curRow + cols[curRow] == i + cols[i]) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        cols = new int[n];
        solution(0, n);
        System.out.println(results.size());
        for (int[] cols : results) {
            for (int i = 0; i < n; i++) {
                System.out.print(cols[i]);
                if (i != n - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
