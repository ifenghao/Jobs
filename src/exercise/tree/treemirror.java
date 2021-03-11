package exercise.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class treemirror {
    static class Node {
        int val;
        Node left = null, right = null;

        Node(int val) {
            this.val = val;
        }
    }

    static Node build(int[] a) {
        Node root = new Node(a[0]);
        Deque<Node> dq = new LinkedList<>();
        dq.addLast(root);
        int i = 0;
        Node tmpNode;
        while (!dq.isEmpty()) {
            tmpNode = dq.removeFirst();
            if (tmpNode.val < 0) continue;
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
        return root;
    }

    // 分行打印二叉树
    static void traverseBFS(Node root) {
        Deque<Node> dq = new LinkedList<>();
        dq.addFirst(root);
        int curLayer = 1, nextLayer = 0;
        Node tmpNode;
        while (!dq.isEmpty()) {
            tmpNode = dq.removeLast();
            System.out.print(tmpNode.val + " ");
            if (tmpNode.left != null) {
                dq.addFirst(tmpNode.left);
                nextLayer++;
            }
            if (tmpNode.right != null) {
                dq.addFirst(tmpNode.right);
                nextLayer++;
            }
            if (--curLayer == 0) { // 当前层全部遍历完成，输出一个换行符
                System.out.println();
                curLayer = nextLayer;
                nextLayer = 0;
            }
        }
    }

    // 二叉树镜像
    static void mirror(Node root) {
        if (root == null) return;
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        mirror(root.left);
        mirror(root.right);
    }

    // 是否中心对称二叉树
    static boolean isSymmetrical(Node root) {
        if (root == null) return true;
        return isSymmetricalCore(root.left, root.right);
    }

    static boolean isSymmetricalCore(Node left, Node right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;

        if (left.val != right.val)
            return false;
        return isSymmetricalCore(left.left, right.right) &&
                isSymmetricalCore(left.right, right.left);
    }

    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, -1, 9, -1, 11, -1, -1, 14};
        Node root = build(a);
        traverseBFS(root);
        mirror(root);
        traverseBFS(root);

        int[] b = new int[]{8, 6, 6, 5, 7, 7, 5, -1, 4, -1, -1, -1, -1, 4, -1};
        Node root2 = build(b);
        System.out.println(isSymmetrical(root2));
    }
}
