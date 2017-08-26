package exercise.sort;

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

    static int select(int[] array, int k, int lo, int hi) {
        int j = partation2(array, lo, hi);
        if (j == k) {
            return array[j];
        } else if (j > k) {
            return select(array, k, lo, j - 1);
        } else {
            return select(array, k, j + 1, hi);
        }
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
        System.out.println(select(array, n - k, 0, n - 1));
    }
}
