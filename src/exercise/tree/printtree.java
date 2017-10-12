package exercise.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class printtree {
    static class Node {
        int val;
        Node left = null, right = null;

        Node(int val) {
            this.val = val;
        }
    }

    static Node build() {
        int[] a = new int[]{0, 1, 2, 3, -1, 5, 6, 7, -1, 11, -1, -1, 14};
        Node root = new Node(a[0]);
        Deque<Node> dq = new LinkedList<>();
        dq.addLast(root);
        int i = 0;
        Node tmpNode;
        while (!dq.isEmpty()) {
            tmpNode = dq.removeFirst();
            if (tmpNode.val >= 0) {
                if (++i < a.length) {
                    if (a[i] >= 0) {
                        tmpNode.left = new Node(a[i]);
                        dq.addLast(tmpNode.left);
                    } else {
                        dq.addLast(new Node(a[i]));
                    }
                }
                if (++i < a.length) {
                    if (a[i] >= 0) {
                        tmpNode.right = new Node(a[i]);
                        dq.addLast(tmpNode.right);
                    } else {
                        dq.addLast(new Node(a[i]));
                    }
                }
            }
        }
        return root;
    }

    static void printLayer(Node root) {
        if (root == null) return;
        Deque<Node> dq = new LinkedList<>();
        dq.addLast(root);
        int printCnt = 1, nodeCnt = 0;
        Node tmpNode;
        while (!dq.isEmpty()) {
            tmpNode = dq.removeFirst();
            System.out.print(tmpNode.val + " ");
            printCnt--;
            if (tmpNode.left != null) {
                dq.addLast(tmpNode.left);
                nodeCnt++;
            }
            if (tmpNode.right != null) {
                dq.addLast(tmpNode.right);
                nodeCnt++;
            }
            if (printCnt == 0) {
                System.out.println();
                printCnt = nodeCnt;
                nodeCnt = 0;
            }
        }
    }

    static void printCrossLayer(Node root) {
        if (root == null) return;
        Stack<Node>[] stacks = new Stack[2];
        stacks[0] = new Stack<>();
        stacks[1] = new Stack<>();
        int cur = 0;
        stacks[cur].add(root);
        Node tmpNode;
        while (!stacks[0].isEmpty() || !stacks[1].isEmpty()) {
            tmpNode = stacks[cur].pop();
            System.out.print(tmpNode.val + " ");
            if (cur == 0) {
                if (tmpNode.left != null) {
                    stacks[(cur + 1) % 2].add(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    stacks[(cur + 1) % 2].add(tmpNode.right);
                }
            } else {
                if (tmpNode.right != null) {
                    stacks[(cur + 1) % 2].add(tmpNode.right);
                }
                if (tmpNode.left != null) {
                    stacks[(cur + 1) % 2].add(tmpNode.left);
                }
            }
            if (stacks[cur].isEmpty()) {
                System.out.println();
                cur = (cur + 1) % 2;
            }
        }
    }

    static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        Node root = build();
        printCrossLayer(root);
    }
}
