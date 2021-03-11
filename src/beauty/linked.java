package beauty;

/**
 * Created by fenghao on 2017/4/17.
 */
public class linked {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node buildList(int[] data) {
        Node headNode = null;
        for (int d : data) {
            if (headNode == null) {
                headNode = new Node(d);
            } else {
                Node newNode = new Node(d);
                newNode.next = headNode;
                headNode = newNode;
            }
        }
        return headNode;
    }


    static void printList(Node headNode) {
        Node curNode = headNode;
        while (curNode != null) {
            System.out.println(curNode.data);
            curNode = curNode.next;
        }
    }

    // 在O(1)时间删除链表节点
    // 因为单链表不能找到前一个节点，所以不能删除本节点
    // 用后一个节点值代替本节点的值，然后删除后一个节点
    static void deleteNode(Node curNode) {
        if (curNode.next == null)
            curNode = null;
        else {
            Node nextNode = curNode.next;
            curNode.data = nextNode.data;
            curNode.next = nextNode.next;
            nextNode = null;
        }
    }

    // 单链表的转置
    // 要求只遍历一遍
    static Node reverse(Node headNode) {
        if (headNode == null) return headNode;
        boolean isFirst = true;
        Node nextNode = headNode.next;
        Node preNode = null;
        while (nextNode != null) {
            if (isFirst) {
                headNode.next = null;// 最终的尾节点要指null否则有环
                isFirst = false;
            }
            preNode = headNode;// 保存原来头节点
            headNode = nextNode;// 头结点移动到下一点
            nextNode = nextNode.next;// 保存下一个待处理节点
            headNode.next = preNode;// 逆置
            // 此时headNode与nextNode处于断开状态
        }
        return headNode;
    }

    static Node reverse2(Node headNode) {
        if (headNode == null) return headNode;
        Node nextNode = headNode.next;
        Node preNode = null;
        while (nextNode != null) {
            headNode.next = preNode;
            preNode = headNode;
            headNode = nextNode;
            nextNode = headNode.next;
        }
        headNode.next = preNode; // 此时headNode与preNode处于断开状态
        return headNode;
    }

    // 单链表的转置
    // 递归形式
    static Node reverseRecursive(Node headNode) {
        if (headNode == null || headNode.next == null) {// 利用第二个条件返回尾节点
            return headNode;
        }
        Node newHeadNode = reverseRecursive(headNode.next);// 先纵深方向返回尾节点，此时headNode为倒数第二节点
        // 此newHeadNode就是最终的尾节点，逐层返回
        headNode.next.next = headNode;// 逆置
        headNode.next = null;
        return newHeadNode;
    }

    // 求链表倒数第k个节点
    // 设置两个指针之间相隔k个节点
    static Node backCount(Node headNode, int k) {
        Node fast = headNode;
        while (k-- >= 0 && fast != null) {
            fast = fast.next;
        }
        Node slow = headNode;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 合并两个有序链表
    static Node mergeSortedLinked(Node p1, Node p2) {
        if (p1 == null) return p2;
        else if (p2 == null) return p1;

        Node head = null, pre = null, cur;
        while (p1 != null && p2 != null) {
            if (p1.data < p2.data) {
                cur = p1;
                p1 = p1.next;
            } else {
                cur = p2;
                p2 = p2.next;
            }
            if (head == null) {
                head = cur;
            } else {
                pre.next = cur;
            }
            pre = cur;
        }
        if (p1 != null) pre.next = p1;
        else pre.next = p2;
        return head;
    }

    // 判断单链表是否有环
    // 设置两个指针一个一次一步，一个一次两步，若有环则两个指针必然相遇
    static boolean hasCircle(Node headNode) {
        Node fast = headNode;
        Node slow = headNode;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // 寻找环的入口
    // 环入口距离头节点为a，距离二指针相遇节点为b，环周长为L
    // 在相遇时fast=a+b+iL，slow=a+b+jL，且fast=2*slow
    // 解得a+b=(i-2*j)L，为周长的整数倍，可令a=L-b
    // 这说明如果slow继续向前走，或slow从头节点向前走，经过a步后必然都能到达环入口
    static Node circleEntrance(Node headNode) {
        Node fast = headNode;
        Node slow = headNode;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        fast = headNode;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    // 判断两个无环单链表是否相交
    // 1 将一链表接在另一链表后，若原链表相交，则相接链表有环
    // 2 若两链表相交，则二者尾节点必然相同
    static boolean isIntersected(Node headNode1, Node headNode2) {
        Node tailNode1 = headNode1;
        while (tailNode1.next != null) {
            tailNode1 = tailNode1.next;
        }
        Node tailNode2 = headNode2;
        while (tailNode2.next != null) {
            tailNode2 = tailNode2.next;
        }
        return tailNode1 == tailNode2;
    }

    // 判断两个有环单链表是否相交
    // 如果二者相交且一个有环，则另一个也有环且二者共用一个环
    // 一个链表上fast和slow相遇点必然在另一个链表上
    static boolean isCircleIntersected(Node headNode1, Node headNode2) {
        Node fast1 = headNode1;
        Node slow1 = headNode1;
        while (fast1 != null && fast1.next != null) {
            slow1 = slow1.next;
            fast1 = fast1.next.next;
            if (slow1 == fast1) {
                break;
            }
        }
        Node circleNode1 = slow1;// 1环上一点
        Node fast2 = headNode2;
        Node slow2 = headNode2;
        while (fast2 != null && fast2.next != null) {
            slow2 = slow2.next;
            fast2 = fast2.next.next;
            if (slow2 == fast2) {
                break;
            }
        }
        Node circleNode2 = slow2;// 2环上一点
        // 如果相交，则这两点在同一个环上
        Node tmpNode = circleNode1.next;
        while (tmpNode != circleNode1) {// 在环上遍历一圈
            if (tmpNode == circleNode2) {
                return true;
            }
        }
        return false;
    }

    static int lengthOf(Node headNode) {
        int len = 0;
        Node tmpNode = headNode;
        while (tmpNode != null) {
            tmpNode = tmpNode.next;
            ++len;
        }
        return len;
    }

    // 两个无环单链表相交的第一个公共节点
    // 将不相交的链表部分截取为相同长度，则两个指针遍历的相遇点就是第一个公共节点
    static Node fistIntersectedNode(Node headNode1, Node headNode2) {
        if (!isIntersected(headNode1, headNode2)) return null;
        int len1 = lengthOf(headNode1);
        int len2 = lengthOf(headNode2);
        int k = len1 - len2;
        Node curNode1 = headNode1;
        Node curNode2 = headNode2;
        if (k > 0) {
            while (k-- > 0) {
                curNode1 = curNode1.next;
            }
        } else {
            while (k++ < 0) {
                curNode2 = curNode2.next;
            }
        }
        while (curNode1 != curNode2) {
            curNode1 = curNode1.next;
            curNode2 = curNode2.next;
        }
        return curNode1;
    }

    public static void main(String[] args) {
        Node p1 = buildList(new int[]{0, 2, 4, 6});
        Node p2 = buildList(new int[]{1, 3, 5, 7, 8});
        printList(mergeSortedLinked(reverse2(p1), reverse2(p2)));

        Node headNode = buildList(new int[]{0, 1, 2, 3, 4, 5, 6});
        makeCircle(headNode, 3);
        System.out.println(circleEntrance(headNode).data);

        Node headNode1 = buildList(new int[]{0, 1, 2, 3, 4, 5, 6});
        Node headNode2 = buildList(new int[]{9, 10, 11, 12});
        makeIntersected(headNode1, headNode2, 3);
        System.out.println(fistIntersectedNode(headNode1, headNode2).data);
    }

    static void makeCircle(Node headNode, int k) {
        Node tailNode = headNode;
        while (tailNode.next != null) {
            tailNode = tailNode.next;
        }
        Node entrance = headNode;
        while (k-- > 0) {
            entrance = entrance.next;
        }
        tailNode.next = entrance;
    }

    static void makeIntersected(Node headNode1, Node headNode2, int k) {
        Node tailNode = headNode2;
        while (tailNode.next != null) {
            tailNode = tailNode.next;
        }
        Node intersectedNode = headNode1;
        while (k-- > 0) {
            intersectedNode = intersectedNode.next;
        }
        tailNode.next = intersectedNode;
    }
}
