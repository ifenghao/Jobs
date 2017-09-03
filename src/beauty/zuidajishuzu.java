package beauty;

import java.util.Scanner;

/**
 * 最大积连续子数组
 * 由于有正负号的问题，需要分别记录以array[i]为结尾的子数组的最大积和最小积
 */
public class zuidajishuzu {
    static int solution(int n, int[] array) {
        int[] postail = new int[n], negtail = new int[n];
        if (array[0] > 0) {
            postail[0] = array[0];
        } else if (array[0] < 0) {
            negtail[0] = array[0];
        }
        int max = postail[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > 0) {
                postail[i] = Math.max(postail[i - 1] * array[i], array[i]);
                negtail[i] = negtail[i - 1] * array[i];
            } else if (array[i] < 0) {
                postail[i] = negtail[i - 1] * array[i];
                negtail[i] = Math.min(postail[i - 1] * array[i], array[i]);
            }// array[i]==0时postail和negtail都为0，之后的记录就要重新开始
            if (postail[i] > max) {
                max = postail[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(solution(n, array));
    }
}
