package interview;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 方法1，时间复杂度O(1)
 * 方法2，时间空间复杂度均为O(1)
 */
class StackWithMax {
    LinkedList<Integer> items;
    LinkedList<Integer> curMax;

    StackWithMax() {
        items = new LinkedList<>();
        curMax = new LinkedList<>();
    }

    void push(int newItem) {
        items.addLast(newItem);
        if (newItem >= max()) {// 只在最大元素到来时
            curMax.addLast(newItem);// 记录当前最大元素
        }
    }

    int pop() {
        int res = items.removeLast();
        if (res == curMax.getLast()) {// 当最大元素即将出栈时
            curMax.removeLast();// 更新到次大元素的索引
        }
        return res;
    }

    int max() {// last就是最大元素
        return curMax.isEmpty() ? 0 : curMax.getLast();
    }

    boolean isEmpty() {
        return items.isEmpty();
    }
}


class StackWithMaxAd {
    LinkedList<Integer> items;
    int curMax;

    StackWithMaxAd() {
        items = new LinkedList<>();
        curMax = 0;
    }

    void push(int newItem) {
        int tmp = newItem - curMax;// 保存元素和最大值之差
        items.addLast(tmp);
        if (tmp > 0) {// 差为正则当前元素为最大元素
            curMax = newItem;// 记录当前最大元素
        }
    }

    int pop() {
        int tmp = items.removeLast();
        int res;
        if (tmp <= 0) {// 差不为正则当前元素不为最大元素
            res = tmp + curMax;
        } else {// 差为正
            res = curMax;// 当前最大元素为结果
            curMax = res - tmp;// 更新当前最大元素
        }
        return res;
    }

    int max() {// curMax就是最大元素
        return curMax;
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    public static void main(String[] args) {
        StackWithMaxAd stack = new StackWithMaxAd();
        int[] num = new int[]{5, 4, 1, 2, 3, 10, 9, 8, 6, 7, 11};
        for (int n : num) {
            stack.push(n);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " " + stack.max());
        }
    }
}

