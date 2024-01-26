package exercise.sort;

/**
 * 合并K个排序的链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 首先实现两个有序链表排序
 * 核心是归并排序，从中间分开成两部分，递归的两两合并
 */
public class mergeKsortlist {
    static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    static ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        if (start > end) return null;
        int mid = (start + end) >> 1;
        return mergeTwoLists(merge(lists, start, mid), merge(lists, mid + 1, end));
    }

    static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        ListNode head = new ListNode(0); // 伪节点初始化，head.next为真正头节点
        ListNode tail = head, cur1 = list1, cur2 = list2;
        while (cur1 != null && cur2 != null) {
            if (cur1.data < cur2.data) {
                tail.next = cur1;
                cur1 = cur1.next;
            } else {
                tail.next = cur2;
                cur2 = cur2.next;
            }
            tail = tail.next;
        }
        tail.next = (cur1 != null ? cur1 : cur2);
        return head.next;
    }
}
