package interview;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并2个或多个有序链表
 */
public class mergelist {
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    static Node createList(int[] a) {
        if (a.length == 0) return null;
        Node head = new Node(a[0]);
        Node tail = head, newNode;
        for (int i = 1; i < a.length; i++) {
            newNode = new Node(a[i]);
            if (head.next == null) {
                head.next = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
        }
        return head;
    }

    static Node merge2(Node head1, Node head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        Node head = new Node(0);
        Node tail = head;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                tail = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                tail = head2;
                head2 = head2.next;
            }
        }
        if (head1 != null) tail.next = head1;
        else tail.next = head2;
        return head.next;
    }

    static Node mergek(Node[] lists) {
        int k = lists.length;
        if (k == 0) return null;
        return mergekHelper(lists, 0, k - 1);
    }

    static Node mergekHelper(Node[] lists, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            return merge2(mergekHelper(lists, lo, mid), mergekHelper(lists, mid + 1, hi));
        } else {
            return lists[lo];
        }
    }

    static Node mergekHeap(Node[] lists) {
        int k = lists.length;
        PriorityQueue<Node> pq = new PriorityQueue<>(k, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });
        for (int i = 0; i < k; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }
        Node head = new Node(0);
        Node tail = head, tmp;
        while (!pq.isEmpty()) {
            tmp = pq.poll();
            tail.next = tmp;
            tail = tmp;
            if (tmp.next != null) {
                pq.add(tmp.next);
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 11, 21, 31}, {2, 12, 13, 32},
                {3, 4, 22, 23}, {10, 24, 33, 34}};
        Node[] lists = new Node[a.length];
        for (int i = 0; i < a.length; i++) {
            lists[i] = createList(a[i]);
        }
        Node head = mergekHeap(lists);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
