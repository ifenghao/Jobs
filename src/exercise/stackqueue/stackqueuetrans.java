package exercise.stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class stackqueuetrans {
    static class StackTransQueue {
        private Stack<Integer> stackin = new Stack<>();
        private Stack<Integer> stackout = new Stack<>();

        void enQueue(int value) {
            stackin.push(value);
        }

        int deQueue() {
            if (!stackout.isEmpty()) {
                return stackout.pop();
            } else {
                while (!stackin.isEmpty()) {
                    stackout.push(stackin.pop());
                }
                return stackout.pop();
            }
        }

        boolean isEmpty() {
            return stackin.isEmpty() && stackout.isEmpty();
        }
    }

    static class QueueTransStack {
        private Queue<Integer> queueCur = new LinkedList<>();
        private Queue<Integer> queueTmp = new LinkedList<>();

        void enStack(int value) {
            queueCur.offer(value);
        }

        int deStack() {
            int queueSize = queueCur.size();
            for (int i = 0; i < queueSize - 1; i++) {
                queueTmp.offer(queueCur.poll());
            }
            int result = queueCur.poll();
            Queue<Integer> swap = queueCur; // 交换队列
            queueCur = queueTmp;
            queueTmp = swap;
            return result;
        }

        boolean isEmpty() {
            return queueCur.isEmpty();
        }

        // 使用一个队列实现一个栈
        void enStack2(int value) {
            int queueSize = queueCur.size();
            queueCur.offer(value);
            for (int i = 0; i < queueSize; i++) { // 除最后一个元素重新入队
                queueCur.offer(queueCur.poll());
            }
        }

        int deStack2() {
            return queueCur.poll();
        }
    }

    public static void main(String[] args) {
        StackTransQueue stq = new StackTransQueue();
        stq.enQueue(1);
        stq.enQueue(2);
        stq.enQueue(4);
        stq.enQueue(6);
        System.out.println(stq.deQueue());
        System.out.println(stq.deQueue());
        stq.enQueue(8);
        stq.enQueue(9);
        while (!stq.isEmpty()) {
            System.out.println(stq.deQueue());
        }
        System.out.println("----------------");
        QueueTransStack qts = new QueueTransStack();
        qts.enStack(1);
        qts.enStack(2);
        qts.enStack(4);
        qts.enStack(6);
        System.out.println(qts.deStack());
        System.out.println(qts.deStack());
        qts.enStack(8);
        qts.enStack(9);
        while (!qts.isEmpty()) {
            System.out.println(qts.deStack());
        }
        System.out.println("----------------");
        QueueTransStack qts2 = new QueueTransStack();
        qts.enStack2(1);
        qts.enStack2(2);
        qts.enStack2(4);
        qts.enStack2(6);
        System.out.println(qts.deStack2());
        System.out.println(qts.deStack2());
        qts.enStack2(8);
        qts.enStack2(9);
        while (!qts.isEmpty()) {
            System.out.println(qts.deStack2());
        }
    }
}
