package beauty;

import java.util.*;

/**
 * 求数组中最长的递增子序列LIS长度和字典序最小的LIS序列
 */
public class longIncSeq {
    static int max(int[] array) {
        int maxValue = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > maxValue) maxValue = i;
        }
        return maxValue;
    }

    // 以第i个元素结尾的最长递增子序列长度lis[i] = max(lis[k])+1 if array[k]<array[i] for k=0∼i−1
    static int getLISLength(int[] array, int[] lis) {
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

    static int[] getLISSeq(int[] array, int[] lis) {
        int lenLIS = getLISLength(array, lis);
        int[] seq = new int[lenLIS]; // 结果长度即为lenLIS
        int i = array.length - 1;
        while (lenLIS > 0) { // 逆序寻找
            if (lis[i] == lenLIS) { // 逆序找到的第一个末尾元素一定为字典序最小
                seq[lenLIS - 1] = array[i]; // 保存到结果末尾
                lenLIS--; // 寻找下一个末尾元素
            }
            i--;
        }
        return seq;
    }

    // tail[len]表示长度为len的最长递增子序列的末尾元素，线性查找
    static int getLISLength2(int[] array, int[] tail) {
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

    static int[] getLISSeq2(int[] array, int[] lis, int[] tail) { // 需要lis来返回序列
        int n = array.length;
        lis[0] = 1;
        tail[0] = array[0];
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            if (array[i] > tail[maxLen - 1]) {
                tail[maxLen] = array[i];
                lis[i] = maxLen + 1;
                ++maxLen;
            } else {
                for (int k = 0; k < maxLen; k++) {
                    if (tail[k] >= array[i]) {
                        tail[k] = array[i];
                        lis[i] = k + 1;
                        break;
                    }
                }
            }
        }
        int lenLIS = maxLen;
        int[] seq = new int[lenLIS]; // tail的前lenLIS个元素并非LIS序列，因为可能会被后面更小的元素替换
        for (int i = n - 1; i >= 0; i--) {
            if (lis[i] == lenLIS) {
                seq[lenLIS - 1] = array[i];
                lenLIS--;
            }
        }
        return seq;
    }

    // tail[len]表示长度为len的最长递增子序列的末尾元素，二分查找
    static int getLISLength3(int[] array, int[] tail) {
        int n = array.length;
        tail[0] = array[0];
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            if (array[i] > tail[maxLen - 1]) {
                tail[maxLen] = array[i];
                ++maxLen;
            } else {
                int pos = lowbound(tail, maxLen, array[i]);
                tail[pos] = array[i];
            }
        }
        return maxLen;
    }

    static int[] getLISSeq3(int[] array, int[] lis, int[] tail) { // 需要lis来返回序列
        int n = array.length;
        lis[0] = 1;
        tail[0] = array[0];
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            if (array[i] > tail[maxLen - 1]) {
                tail[maxLen] = array[i];
                lis[i] = maxLen + 1;
                ++maxLen;
            } else {
                int pos = lowbound(tail, maxLen, array[i]);
                tail[pos] = array[i];
                lis[i] = pos + 1;
            }
        }
        int lenLIS = maxLen;
        int[] seq = new int[lenLIS]; // tail的前lenLIS个元素并非LIS序列，因为可能会被后面更小的元素替换
        for (int i = n - 1; i >= 0; i--) {
            if (lis[i] == lenLIS) {
                seq[lenLIS - 1] = array[i];
                lenLIS--;
            }
        }
        return seq;
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

    static int lowbound(int[] array, int limit, int value) {
        int start = 0;
        int end = limit - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] >= value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] array = new int[n];
//        for (int i = 0; i < n; i++) {
//            array[i] = sc.nextInt();
//        }

        int[] array = new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7};
        int n = array.length;
        // O(n^2)
        int[] lis = new int[n];
        System.out.println(getLISLength(array, lis));
        System.out.println(Arrays.toString(getLISSeq(array, lis)));
        // O(n^2)
        int[] tail = new int[n];
        System.out.println(getLISLength2(array, tail));
        System.out.println(Arrays.toString(getLISSeq2(array, lis, tail)));
        // O(nlogn)
        System.out.println(getLISLength3(array, tail));
        System.out.println(Arrays.toString(getLISSeq3(array, lis, tail)));
    }
}
