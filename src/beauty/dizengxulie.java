package beauty;

import java.util.Arrays;

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

    static void solution(int[] array, int[] len) {
        int length = array.length;
        len[0] = 1;
        for (int i = 1; i < length; i++) {
            int maxValue = Integer.MIN_VALUE;// 比array[i]小的最大值
            int maxIndex = 0;// 比array[i]小的最大值下标
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i] && array[j] > maxValue) {
                    maxValue = array[j];
                    maxIndex = j;
                }
            }
            if (maxValue == Integer.MIN_VALUE) {
                len[i] = 1;// 没有找到比array[i]小的值，array[i]本身长度为1
            } else {
                len[i] = len[maxIndex] + 1;// 之前最长递增序列加上array[i]构成新序列
            }
        }
    }

    static int[] partition(int[] array, int target) {
        int[] maxValueAndIndex = new int[]{Integer.MIN_VALUE, 0};
        int i = 0;
        int j = array.length - 1;
        while (true) {
            while (array[i] < target) {
                if (i >= j) break;
                else ++i;
                if (array[i] > maxValueAndIndex[0]) {// 在左侧寻找比array[i]小的最大值
                    maxValueAndIndex[0] = array[i];
                    maxValueAndIndex[1] = i;
                }
            }
            while (array[j] > target) {
                if (j <= i) break;
                else --j;
            }
            if (i >= j) break;
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        return maxValueAndIndex;
    }

    static void solution2(int[] array, int[] len) {
        int length = array.length;
        len[0] = 1;
        for (int i = 1; i < length; i++) {
            int[] tmpArray = new int[i];
            System.arraycopy(array, 0, tmpArray, 0, i);
            int[] maxValueAndIndex = partition(tmpArray, array[i]);// 采用一次划分寻找比array[i]小的最大值
            int maxValue = maxValueAndIndex[0];
            int maxIndex = maxValueAndIndex[1];
            if (maxValue == Integer.MIN_VALUE) {
                len[i] = 1;// 没有找到比array[i]小的值，array[i]本身长度为1
            } else {
                len[i] = len[maxIndex] + 1;// 之前最长递增序列加上array[i]构成新序列
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, -1, 2, 0, 4, -5, 6, -7};
        int[] len = new int[array.length];
        solution2(array, len);
        System.out.println(max(len));
    }
}
