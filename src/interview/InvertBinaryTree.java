package interview;

/**
 * Created by zfh on 17-4-26.
 */
public class InvertBinaryTree {
    static class Node {
        int x;
        Node left, right;

        Node(int x) {
            this.x = x;
        }
    }

    static Node invert(Node root) {
        if (root == null) return null;
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invert(root.left);
        invert(root.right);
        return root;
    }
}
