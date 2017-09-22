package exercise.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class streammedian {
    private static PriorityQueue<Integer> pqMax = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    private static PriorityQueue<Integer> pqMin = new PriorityQueue<>();

    static void insert(int n) {
        if (pqMax.isEmpty() || pqMax.peek() >= n) {
            pqMax.add(n);
        } else {
            pqMin.add(n);
        }
        balance();
    }

    static void balance() {// 保证偶数时pqMax.size()==pqMin.size()，奇数时pqMax.size()==pqMin.size()+1
        if (pqMax.size() - pqMin.size() == 2) {// pqMax多了一个元素
            pqMin.add(pqMax.poll());
        } else if (pqMin.size() - pqMax.size() == 1) {// pqMin多了一个元素
            pqMax.add(pqMin.poll());
        }
    }

    static int getMedian() {
        if (pqMax.size() == pqMin.size()) {
            return (pqMax.peek() + pqMin.peek()) / 2;
        } else {
            return pqMax.peek();
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 2, 6, 1, 3, 9, 0, 8, 7, 5};
        for (int i : a) {
            insert(i);
        }
        System.out.println(getMedian());
    }
}
