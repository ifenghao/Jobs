package beauty;

import java.util.*;

/**
 * Created by zfh on 17-5-3.
 * 找到数组中和为sum的2个数、3个数
 */
public class sum23 {
    static void sum2bs(int[] array, int sum) {
        Arrays.sort(array);
        int n = array.length;
        int remain, pos;
        for (int i = 0; i < n; i++) {
            remain = sum - array[i];
            pos = binarySearch(array, remain, i);// 如果一个数只使用一次则左边界为i+1
            if (pos != -1)
                System.out.println(array[i] + " " + remain);
        }
    }

    // 数组从左向右遍历，设置查找的左边界就可以避免重复组合
    static int binarySearch(int[] array, int target, int left) {
        int i = left, j = array.length - 1;
        int mid;
        while (i <= j) {
            mid = i + (j - i) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return -1;
    }

    static void sum2bt(int[] array, int sum) {
        Arrays.sort(array);
        int n = array.length;
        int i = 0, j = n - 1;
        while (i <= j) {// 如果一个数只使用一次则i<j
            if (array[i] + array[j] == sum) {
                System.out.println(array[i] + " " + array[j]);
                i++;
                j--;
            } else if (array[i] + array[j] < sum) {
                i++;
            } else {
                j--;
            }
        }
    }

    static void sum2hash(int[] array, int sum) {
        int n = array.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(array[i]);
        }
        int remain;
        for (int i = 0; i < n; i++) {
            remain = sum - array[i];
            if (set.contains(remain))
                System.out.println(array[i] + " " + remain);
        }
    }

    static void sum3bs(int[] array, int sum) {
        Arrays.sort(array);
        int n = array.length;
        int remain1, remain2, pos;
        for (int i = 0; i < n; i++) {
            remain2 = sum - array[i];
            for (int j = i; j < n; j++) {
                remain1 = remain2 - array[j];
                pos = binarySearch(array, remain1, j);// 如果一个数只使用一次则左边界为j+1
                if (pos != -1)
                    System.out.println(array[i] + " " + array[j] + " " + remain1);
            }
        }
    }

    static void sum3bt(int[] array, int sum) {
        Arrays.sort(array);
        int n = array.length;
        int remain2, j, k;
        for (int i = 0; i < n; i++) {
            remain2 = sum - array[i];
            j = i;// 如果一个数只使用一次则j=i+1
            k = n - 1;
            while (j <= k) {// 如果一个数只使用一次则条件为j<k
                if (array[j] + array[k] == remain2) {
                    System.out.println(array[i] + " " + array[j] + " " + array[k]);
                    j++;
                    k--;
                } else if (array[j] + array[k] < remain2) {
                    j++;
                } else {
                    k--;
                }
            }
        }
    }

    static void sum3nodup(int[] array, int sum) {
        Arrays.sort(array);
        int n = array.length, pre = Integer.MIN_VALUE;
        int remain2, j, k;
        for (int i = 0; i < n; i++) {
            if (array[i] == pre) continue; // 控制首元素不重复
            pre = array[i];
            remain2 = sum - array[i];
            j = i + 1;
            k = n - 1;
            while (j < k) {
                int small = array[j], large = array[k];
                if (small + large == remain2) {
                    System.out.println(array[i] + " " + array[j] + " " + array[k]);
                    while (array[j] == small && j < k) j++; // 指针首尾元素不重复
                    while (array[k] == large && j < k) k--;
                } else if (array[j] + array[k] < remain2) {
                    while (array[j] == small && j < k) j++;
                } else {
                    while (array[k] == large && j < k) k--;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = sc.nextInt();
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        sum3bs(array, sum);
    }
}
