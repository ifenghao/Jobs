package exercise.sort;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by zfh on 2017/8/26.
 */
public class kthnum {
    static int partation1(int[] array, int lo, int hi) {
        int i = lo + 1, j = hi;
        int point = array[lo];
        while (true) {
            while (i < hi && array[i] < point) i++;
            while (j > lo && array[j] > point) j--;
            if (i >= j) break;
            int tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
        int tmp = array[j];
        array[j] = array[lo];
        array[lo] = tmp;
        return j;
    }

    static int partation2(int[] array, int lo, int hi) {
        int j = lo, point = array[hi], tmp;
        for (int i = lo; i < hi; i++) {
            if (array[i] <= point) {
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                j++;
            }
        }
        array[hi] = array[j];
        array[j] = point;
        return j;
    }

    // 选择第k大的数递归实现
    static int selectKth(int[] array, int k, int lo, int hi) {
        int j = partation2(array, lo, hi);
        if (j == k) {
            return array[j];
        } else if (j > k) {
            return selectKth(array, k, lo, j - 1);
        } else {
            return selectKth(array, k, j + 1, hi);
        }
    }


    // 选择第k大的数非递归实现
    static int selectKth2(int[] array, int k) {
        int lo = 0, hi = array.length - 1;
        int j = partation2(array, lo, hi);
        while (j != k) {
            if (j > k) {
                hi = j - 1;
            } else {
                lo = j + 1;
            }
            j = partation2(array, lo, hi);
        }
        return array[j];
    }

    // 选择前k小的所有数，允许修改数组，O(n)
    static int[] selectKall(int[] array, int k) {
        int[] res = new int[k];
        selectKth2(array, k); // 经过寻找第k大的数后，左边的数均比它小
        for (int i = 0; i < k; i++) {
            res[i] = array[i];
        }
        return res;
    }

    // 选择前k小的所有数，不允许修改数组，O(nlogk)
    static int[] selectKall2(int[] array, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < array.length; i++) {
            if (pq.size() < k) {
                pq.offer(array[i]);
            } else {
                if (pq.peek() > array[i]) {
                    pq.poll();
                    pq.offer(array[i]);
                }
            }
        }
        Object[] pqres = pq.toArray();
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = (int) pqres[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] string = sc.nextLine().split(" ");
        int n = string.length;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(string[i]);
        }
        int k = sc.nextInt();
        System.out.println(selectKth(array, n - k, 0, n - 1));
        System.out.println(selectKth2(array, k));

        int[] resk = selectKall(array, k);
        for (int i : resk) {
            System.out.print(i + " ");
        }
        System.out.println();
        resk = selectKall2(array, k);
        for (int i : resk) {
            System.out.print(i + " ");
        }
    }
}
