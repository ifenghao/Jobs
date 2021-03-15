package exercise.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class traverse {
    static class Node {
        int val;
        Node left = null, right = null;

        Node(int val) {
            this.val = val;
        }
    }

    static Node build() {
        int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, -1, 9, -1, 11, -1, -1, 14};
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

    static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void preOrder2(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                System.out.print(cur.val + " ");// 入栈先打印当前节点
                stack.push(cur);// 入栈所有左子节点
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;// 出栈直接找右子节点
        }
    }

    static void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    static void inOrder2(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);// 入栈所有左子节点
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.print(cur.val + " ");// 出栈即打印当前节点
            cur = cur.right;// 出栈直接找右子节点
        }
    }

    static void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    static void postOrder2(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        Node cur = root, last = null;// 用于记录上一次访问的节点
        while (cur != null) {// 入栈所有左子节点
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (cur.right == null || cur.right == last) {// 没有右子节点或已经访问过右子节点
                System.out.print(cur.val + " ");
                last = cur;// 记录上次访问节点
            } else {// 有右子节点且还没访问
                stack.push(cur);// 重新入栈根节点
                cur = cur.right;
                while (cur != null) {// 入栈所有左子节点
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
    }

    // 深度优先搜索树的路径和
    static void findPathDFS(Node root, int sum) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        findPathDFSCore(root, stack, sum);
    }

    static void findPathDFSCore(Node root, Stack<Node> stack, int sum) {
        stack.push(root); // root一定非null，且stack非空栈
        sum -= root.val; // 尝试将root添加进路径
        if (sum == 0 && root.left == null && root.right == null) { // 检查当前路径和且为叶子节点
            for (Node node : stack) {
                System.out.print(node.val + " ");
            }
            System.out.println();
        } else { // 继续扩展路径
            if (root.left != null)
                findPathDFSCore(root.left, stack, sum);
            if (root.right != null)
                findPathDFSCore(root.right, stack, sum);
        }
        stack.pop(); // 当前节点所有情况已经完成，需要退出stack
    }

    public static void main(String[] args) {
        Node root = build();
        preOrder(root);
        System.out.println();
        preOrder2(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        inOrder2(root);
        System.out.println();
        postOrder(root);
        System.out.println();
        postOrder2(root);

        findPathDFS(root, 11);
    }
}
