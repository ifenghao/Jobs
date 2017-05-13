package beauty;

/**
 * Created by zfh on 17-5-13.
 */
public class reconsbinary {
    static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    static Node reconstruct(int[] pre, int preHead, int preTail, int[] in, int inHead, int inTail) {
        if (preHead > preTail || inHead > inTail) return null;
        int rootValue = pre[preHead];
        Node root = new Node(rootValue);// 先序序列的第一个节点一定是根节点
        for (int i = inHead; i <= inTail; i++) {
            if (in[i] == rootValue) {// 在中序序列里寻找对应根节点的位置
                int leftLen = i - inHead;// 左子树的长度
                root.left = reconstruct(pre, preHead + 1, preHead + leftLen, in, inHead, i - 1);// 重构左子树
                root.right = reconstruct(pre, preHead + leftLen + 1, preTail, in, i + 1, inTail);// 重构右子树
            }
        }
        return root;
    }

    static void preTraverse(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preTraverse(root.left);
            preTraverse(root.right);
        }
    }

    static void inTraverse(Node root) {
        if (root != null) {
            inTraverse(root.left);
            System.out.print(root.value + " ");
            inTraverse(root.right);
        }
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        int n = pre.length;
        Node root = reconstruct(pre, 0, n - 1, in, 0, n - 1);
        preTraverse(root);
        System.out.println();
        inTraverse(root);
    }
}
