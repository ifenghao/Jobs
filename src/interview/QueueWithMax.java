package interview;

/**
 * Created by zfh on 17-4-22.
 * 使用两个stack维护一个队列，队列入队，出队，取最大值均为O(1)
 */
public class QueueWithMax {
    StackWithMax stackIn;
    StackWithMax stackOut;

    QueueWithMax(int capacity) {
        stackIn = new StackWithMax(capacity);
        stackOut = new StackWithMax(capacity);
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

class StackWithMax {
    int[] items;
    int[] nextMax;
    int top = -1;
    int maxItemIndex = -1;

    StackWithMax(int capacity) {
        items = new int[capacity];
        nextMax = new int[capacity];
    }

    void push(int newItem) {
        ++top;
        if (top == items.length) {
            System.out.println("full");
            return;
        }
        if (newItem > max()) {// 只在最大元素到来时
            nextMax[top] = maxItemIndex;// 记录上一个最大元素的索引
            maxItemIndex = top;// 现在最大元素在栈顶
        }
        items[top] = newItem;
    }

    int pop() {
        if (top < 0) {
            System.out.println("empty");
            return Integer.MIN_VALUE;
        }
        if (maxItemIndex == top) {// 当最大元素即将出栈时
            maxItemIndex = nextMax[top];// 更新到次大元素的索引
        }
        return items[top--];
    }

    int max() {// maxItemIndex就指向最大元素
        return maxItemIndex >= 0 ? items[maxItemIndex] : Integer.MIN_VALUE;
    }

    boolean isEmpty() {
        return top < 0;
    }
}