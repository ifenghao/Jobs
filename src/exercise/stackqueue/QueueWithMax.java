package exercise.stackqueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by zfh on 17-4-22.
 * 方法1，使用两个stack维护一个队列，队列入队，出队，取最大值均为O(1)
 * 方法2，和最大栈相同的思路，用额外的deque来保存最大值
 */
class QueueWithMax {
    StackWithMaxAd stackIn;
    StackWithMaxAd stackOut;

    QueueWithMax() {
        stackIn = new StackWithMaxAd();
        stackOut = new StackWithMaxAd();
    }

    void enQueue(int newItem) {
        stackIn.push(newItem);
    }

    int deQueue() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    int max() {
        return Math.max(stackIn.max(), stackOut.max());
    }
}

class QueueWithMaxAd {
    LinkedList<Integer> items;
    Deque<Integer> curMax;

    QueueWithMaxAd() {
        items = new LinkedList<>();
        curMax = new LinkedList<>();
    }

    void enQueue(int newItem) {
        items.addLast(newItem);
        while (!curMax.isEmpty() && curMax.getLast() < newItem) {
            curMax.removeLast(); // 上一个最大值之后且小于newItem的值不可能是当前的最大值，全部出队
        }
        curMax.addLast(newItem); // curMax只保存上一个最大值后次大的值
    }

    int deQueue() {
        int res = items.removeFirst();
        if (res == max()) { // 只有在当前最大值出队，curMax需要更新到次大值
            curMax.removeFirst();
        }
        return res;
    }

    int max() {// last就是最大元素
        return curMax.isEmpty() ? 0 : curMax.getFirst();
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    public static void main(String[] args) {
        QueueWithMaxAd queue = new QueueWithMaxAd();
        int[] num = new int[]{8, 6, 7, 5, 4, 1, 2, 3, 1, 2};
        for (int n : num) {
            queue.enQueue(n);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue() + " " + queue.max());
        }
    }
}

