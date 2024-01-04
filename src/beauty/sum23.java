package beauty;

import java.util.*;

/**
 * Created by zfh on 17-5-3.
 * 找到数组中和为sum的2个数、3个数
 */
public class sum23 {
    // 1) O(nlog(n)) 二分查找差值
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

    // 2) O(nlog(n)) 双指针头尾分别遍历
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

    // 3) O(n) 哈希查找差值
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

    // 1) O(n^2log(n)) 二分查找差值
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

    // 2) O(n^2) 3个数可以重复 双指针头尾分别遍历
    static void sum3bt(int[] array, int sum) {
        Arrays.sort(array);
        int n = array.length;
        int remain2, j, k;
        for (int i = 0; i < n; i++) {
            remain2 = sum - array[i];
            j = i;// 可重复
            k = n - 1;
            while (j <= k) {// 可重复
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

    // O(n^2) 3个数不能重复
    static void sum3nodup(int[] array, int sum) {
        Arrays.sort(array);
        int n = array.length, pre = Integer.MIN_VALUE;
        int remain2, j, k;
        for (int i = 0; i < n - 2; i++) {
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

    // O(n^2)，求和最接近target的3个数
    static int sum3Closest(int[] array, int target) {
        Arrays.sort(array);
        int n = array.length;
        int j, k, sum, diff;
        int res = array[0] + array[1] + array[2];
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && array[i] == array[i - 1]) {
                continue;
            }
            j = i + 1;
            k = n - 1;
            while (j < k) {
                sum = array[i] + array[j] + array[k];
                diff = sum - target;
                if (Math.abs(diff) < Math.abs(res - target)) {
                    res = sum;
                }
                if (diff == 0) {
                    return target;
                } else if (diff > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
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
