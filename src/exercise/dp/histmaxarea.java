package exercise.dp;

import java.util.Scanner;
import java.util.Stack;

/**
 * 给定一组非负整数组成的数组h，代表一组柱状图的高度，其中每个柱子的宽度都为1。
 * 在这组柱状图中找到能组成的最大矩形的面积。 入参h为一个整型数组，代表每个柱子的高度，返回面积的值。
 * 输入描述:
 * 输入包括两行,第一行包含一个整数n(1 ≤ n ≤ 10000)
 * 第二行包括n个整数,表示h数组中的每个值,h_i(1 ≤ h_i ≤ 1,000,000)
 * <p>
 * 输出描述:
 * 输出一个整数,表示最大的矩阵面积。
 * <p>
 * 输入例子1:
 * 6
 * 2 1 5 6 2 3
 * <p>
 * 输出例子1:
 * 10
 */
public class histmaxarea {
    static void solution(int n, int[] h) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < n + 1; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (h[stack.peek()] < h[i]) {// 保持栈里的元素递增
                stack.push(i);
            } else if (h[stack.peek()] > h[i]) {// 遇到小于栈顶的元素
                int tmpArea;
                int start = 0;
                while (!stack.isEmpty() && h[stack.peek()] > h[i]) {// 开始出栈，直到遇到比h[i]小的元素
                    start = stack.pop();
                    tmpArea = (i - start) * h[start];// 先对中间较大高度的元素计算面积
                    if (tmpArea > maxArea) maxArea = tmpArea;
                }// 较小元素的构成的面积后算
                stack.push(start);// 关键是入栈上一个出栈的位置索引，表示以h[i]为高的矩形宽度可以从i到start
                h[start] = h[i];// 保存当前h[i]到这个索引，方便后续取值
            }
        }
        System.out.println(maxArea);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] h = new int[n + 1];
        for (int i = 0; i < n; i++) {
            h[i] = sc.nextInt();
        }
        h[n] = -1;
        solution(n, h);
    }
}
