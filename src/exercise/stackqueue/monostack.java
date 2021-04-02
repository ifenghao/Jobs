package exercise.stackqueue;

import java.util.Stack;

public class monostack {
    // 单调栈应用1：对数组中的每个数a[i]，在它之前的数中找到r[i]，使得r[i]<a[i]且距离a[i]最近，若没有则为-1
    // 关键点：一旦后面的数比前面的数小，则前面的数就不可能被使用了
    static int[] solution1(int[] a) {
        int n = a.length;
        int[] r = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && a[i] <= stack.peek()) {// 保证栈元素递增
                stack.pop();// 如果当前数比栈顶小，就要一直出栈，表示栈顶元素不可能被使用
            }
            if (stack.isEmpty()) {
                r[i] = -1;
            } else {
                r[i] = stack.peek();
            }
            stack.add(a[i]);
        }
        return r;
    }

    // 单调栈应用2：对数组中的每个数a[i]，向左找到最长连续递增数组的长度
    static int[] solution2(int[] a) {
        int n = a.length;
        int[] r = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && a[i] <= stack.peek()) {// 小于栈顶则栈清空，保证栈元素连续且递增
                stack.clear();
                r[i] = 1;
            } else {
                r[i] = stack.size() + 1;
            }
            stack.add(a[i]);
        }
        return r;
    }

    // 单调栈应用3：对数组中的每个数a[i]，在它之后的数中找到r[i]，使得r[i]>a[i]且距离a[i]最近，若没有则为-1
    // 关键点：栈保存元素索引
    static int[] solution3(int[] a) {
        int n = a.length;
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && a[i] >= a[stack.peek()]) {// 保证栈元素递减
                r[stack.pop()] = a[i];// 对栈顶元素，当前元素即为较大且距离最近
            }
            stack.add(i);
        }
        return r;
    }

    // 单调栈应用4：对数组中的每个数a[i]，找大于a[i]的最左边界，构成以a[i]为最小值的最长左区间
    static int[] solution4(int[] a) {
        int n = a.length;
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {// 初始化左边界为当前索引
            r[i] = i;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && a[i] <= a[stack.peek()]) {// 保证栈元素递增
                r[i] = r[stack.pop()];// 更新当前元素的左边界，栈顶大元素弹出不再使用
            }
            stack.add(i);
        }
        return r;
    }

    // 单调栈应用5：寻找数组中区间最小值乘以区间和的最大值
    // 关键点：栈保存元素索引
    static void solution5(int[] a) {
        int n = a.length;
        int[] cumsum = new int[n];
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            tmp += a[i];
            cumsum[i] = tmp;
        }
        int[] left = new int[n], right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = i;
            right[i] = i;
        }
        Stack<Integer> stack = new Stack<>();
        // 找大于a[i]的最左边界
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && a[i] <= a[stack.peek()]) {
                left[i] = left[stack.pop()];
            }
            stack.add(i);
        }
        stack.clear();
        // 找大于a[i]的最右边界(和最左边界对称)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && a[i] <= a[stack.peek()]) {
                right[i] = right[stack.pop()];
            }
            stack.add(i);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            tmp = a[i] * (cumsum[right[i]] - cumsum[left[i]] + a[left[i]]);
            if (tmp > max) max = tmp;
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 1, 6, 4, 5};
//        int[] r1 = solution4(a);
//        for (int i : r1) {
//            System.out.print(i + " ");
//        }
        solution5(a);
    }
}
