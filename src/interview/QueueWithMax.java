package interview;

/**
 * Created by zfh on 17-4-22.
 * 使用两个stack维护一个队列，队列入队，出队，取最大值均为O(1)
 */
public class QueueWithMax {
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

