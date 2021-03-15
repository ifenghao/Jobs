package exercise.stackqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueMin {
    private int[] pq;
    private int N = 0;

    PriorityQueueMin(int size) {
        pq = new int[size + 1];
    }

    public void add(int n) {
        pq[++N] = n;
        siftUp(N);
    }

    public int poll() {
        if (isEmpty()) return Integer.MIN_VALUE;
        int res = pq[1];
        pq[1] = pq[N--];
        siftDown(1);
        return res;
    }

    public int peek() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return pq[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void siftUp(int k) {
        int cur = pq[k];
        while (k > 1 && pq[k / 2] > cur) {
            pq[k] = pq[k / 2];
            pq[k / 2] = cur;
            k /= 2;
        }
    }

    private void siftDown(int k) {
        int cur = pq[k];
        int child;
        while (2 * k < N) {
            child = pq[k * 2] < pq[k * 2 + 1] ? k * 2 : k * 2 + 1;
            if (pq[child] < cur) {
                pq[k] = pq[child];
                pq[child] = cur;
                k = child;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        PriorityQueueMin pq = new PriorityQueueMin(10);
        PriorityQueue<Integer> pqmin = new PriorityQueue<>();
        PriorityQueue<Integer> pqmax = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int[] a = new int[]{3, 2, 5, 7, 4, 8, 9, 6, 0};
        for (int i = 0; i < a.length; i++) {
            pq.add(a[i]);
            pqmin.add(a[i]);
            pqmax.add(a[i]);
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(pq.poll() + " ");
            System.out.print(pqmin.poll() + " ");
            System.out.println(pqmax.poll());
        }
    }
}
