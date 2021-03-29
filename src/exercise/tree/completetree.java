package exercise.tree;

import java.util.Deque;
import java.util.LinkedList;

public class completetree {
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

    // 只需要走向左子树就可以得到树深度
    static int getDepth(Node root) {
        int depth = 0;
        while (root != null) {
            depth += 1;
            root = root.left;
        }
        return depth;
    }

    // 循环实现
    static Node getLastNode(Node root) {
        int depth = getDepth(root); // 总树深
        int layer = 1; // 记录当前root层深
        while (root != null) {
            if (layer == depth) break;
            if (root.right != null) { // 优先检查右子树深度
                int tmpDepth = layer + getDepth(root.right);
                if (tmpDepth < depth) {
                    root = root.left; // 右子树低于总深度，最后节点一定在左子树
                } else {
                    root = root.right; // 否则就在右子树
                }
            } else {
                root = root.left; // 没有右子树，最后节点一定在左子树
            }
            layer += 1;
        }
        return root;
    }

    // 递归实现
    static Node getLastNode2(Node root) {
        int depth = getDepth(root);
        return getLastNodeCore(root, depth);
    }

    static Node getLastNodeCore(Node root, int depth) {
        if (depth == 1) return root;
        int leftDepth = depth - 1;
        int rightDepth = getDepth(root.right);
        if (rightDepth < leftDepth) {
            return getLastNodeCore(root.left, leftDepth);
        }
        return getLastNodeCore(root.right, rightDepth);
    }

    public static void main(String[] args) {
        Node root = build(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        System.out.println(getLastNode(root).val);
        System.out.println(getLastNode2(root).val);
    }
}
