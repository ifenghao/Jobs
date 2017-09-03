package exercise.others;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 找到长度为n数组中和是k的整数倍的最长连续序列，输出最长的长度
 */
public class zuichanghe {
    static void solution(int n, int k, int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int tmp, sum = 0, maxLen = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];// 所有前缀
            tmp = sum % k;// 余数
            if (!map.containsKey(tmp)) {
                map.put(tmp, i);
            } else {// 余数相同时之间就是一个和是k的整数倍的序列
                maxLen = Math.max(maxLen, i - map.get(tmp));
            }
        }
        System.out.println(maxLen);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        solution(n, k, array);
    }
}
