package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class reorderlinked {
    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>(); // 需要O(n)的存储空间
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int start = 0, end = list.size() - 1;
        while (start < end) { // 首尾节点交替链接
            list.get(start).next = list.get(end);
            start++;
            list.get(end).next = list.get(start);
            end--;
        }
        list.get(start).next = null;
    }

    static void reorderList2(ListNode head) { // 分成拆分+逆置+合并，不需要额外空间
        // 先找到链表中点
        ListNode midNode = findMidNode(head);
        // 将后半链表逆置
        ListNode reverseHead = reverse(midNode);
        // 将前半和后半链表交替合并
        merge(head, reverseHead);
    }

    static ListNode findMidNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    static void merge(ListNode head1, ListNode head2) {
        ListNode cur1, cur2;
        while (head1 != null && head2 != null) {
            cur1 = head1.next;
            cur2 = head2.next;

            head1.next = head2;
            head1 = cur1;
            head2.next = head1;
            head2 = cur2;
        }
    }

    static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    static ListNode buildList() {
        int[] nums = {1,2,3,4,5,6,};
        ListNode node = new ListNode();
        ListNode head = node;
        for (int n : nums) {
            node.next = new ListNode(n);
            node = node.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode head = buildList();
        printList(head);
        reorderList2(head);
        printList(head);
    }
}
