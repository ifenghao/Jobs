package beauty;

/**
 * Created by zfh on 17-5-13.
 */
public class binarytreedis {
    static class Node {
        Node left, right;
    }

    static class Memo {
        int maxDepth, maxDist;

        Memo(int depth, int dis) {
            this.maxDepth = depth;// 该节点左右子树中的最大深度
            this.maxDist = dis;// 包括该节点的树及其所有子树中的最大距离
        }
    }

    static Memo maxDist(Node root) {
        if (root == null) {
            return new Memo(-1, 0);
        }
        Memo lMemo = maxDist(root.left);
        Memo rMemo = maxDist(root.right);
        Memo curMemo = new Memo(-1, 0);
        curMemo.maxDepth = Math.max(lMemo.maxDepth, rMemo.maxDepth) + 1;// 当前最大深度是左右子树最大深度加1
        curMemo.maxDist = Math.max(lMemo.maxDist, rMemo.maxDist);// 当前最大距离存在于左右子树中
        curMemo.maxDist = Math.max(curMemo.maxDist, lMemo.maxDepth + rMemo.maxDepth + 2);// 或者跨越了根节点
        return curMemo;
    }

    static int maxDist2(Node root) {
        if (root == null) return 0;
        int maxDis = Math.max(maxDist2(root.left), maxDist2(root.right));// 当前最大距离存在于左右子树中
        maxDis = Math.max(maxDis, maxDepth(root.left) + maxDepth(root.right) + 2);// 或者跨越了根节点
        return maxDis;
    }

    static int maxDepth(Node root) {
        if (root == null) return -1;
        int lDepth = maxDepth(root.left);// 左子树最大深度
        int rDepth = maxDepth(root.right);// 右子树最大深度
        return Math.max(lDepth, rDepth) + 1;// 当前最大深度是左右子树最大深度加1
    }

    static Node binaryTree() {
        Node root = new Node();
        Node root2 = new Node();
        root.right = root2;
        Node l = new Node();
        Node r = new Node();
        root2.left = l;
        root2.right = r;
        Node ll = new Node();
        Node lr = new Node();
        l.left = ll;
        l.right = lr;
        Node rr = new Node();
        r.right = rr;
        Node lrl = new Node();
        lr.left = lrl;
        return root;
    }

    public static void main(String[] args) {
        Node root = binaryTree();
        Memo memo = maxDist(root);
        System.out.println(memo.maxDist);
//        System.out.println(maxDist2(root));
    }
}
