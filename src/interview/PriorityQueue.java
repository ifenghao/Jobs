package interview;

public class PriorityQueue {
    private int[] pq;
    private int N = 0;

    PriorityQueue(int size) {
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
        PriorityQueue pq = new PriorityQueue(10);
        int[] a = new int[]{3, 2, 5, 7, 4, 8, 9, 6, 0};
        for (int i = 0; i < a.length; i++) {
            pq.add(a[i]);
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(pq.poll());
        }
    }
}
