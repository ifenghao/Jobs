package beauty;

import java.util.Scanner;

/**
 * 求数组中最长的递增子序列
 */
public class dizengxulie {
    static int max(int[] array) {
        int maxValue = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > maxValue) maxValue = i;
        }
        return maxValue;
    }

    static int solution(int[] array, int[] lis) {
        int n = array.length;
        lis[0] = 1;
        for (int i = 1; i < n; i++) {
            int maxLen = 0;// array[i]之前的最长子序列长度
            for (int k = 0; k < i; k++) {
                if (array[k] < array[i] && lis[k] > maxLen) {
                    maxLen = lis[k];
                }
            }
            // 若没有找到比array[i]小的值，array[i]构成长度为1的子序列
            // 若找到则在最长递增序列末尾加上array[i]构成新最长子序列
            lis[i] = maxLen + 1;
        }
        return max(lis);
    }

    static int solution2(int[] array, int[] tail) {
        int n = array.length;
        tail[0] = array[0];
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            if (array[i] > tail[maxLen - 1]) {
                tail[maxLen] = array[i];
                ++maxLen;
            } else {
                for (int k = 0; k < maxLen; k++) {
                    if (tail[k] >= array[i]) {
                        tail[k] = array[i];
                        break;
                    }
                }
            }
        }
        return maxLen;
    }

    static int solution3(int[] array, int[] tail) {
        int n = array.length;
        tail[0] = array[0];
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            if (array[i] > tail[maxLen - 1]) {
                tail[maxLen] = array[i];
                ++maxLen;
            } else {
                int pos = binarySearch(tail, maxLen, array[i]);
                tail[pos] = array[i];
            }
        }
        return maxLen;
    }

    static int binarySearch(int[] array, int limit, int value) {
        int start = 0;
        int end = limit - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        int[] lis = new int[n];
        System.out.println(solution2(array, lis));
    }
}
