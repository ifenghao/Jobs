package beauty;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 后序递归遍历求二叉树深度和距离
 */
public class treedepthdistance {
    static class Node {
        int val;
        Node left = null, right = null;

        Node(int val) {
            this.val = val;
        }
    }

    static class Memo {
        int maxDepth, maxDist;

        Memo(int depth, int dis) {
            this.maxDepth = depth;// 该节点左右子树中的最大深度
            this.maxDist = dis;// 包括该节点的树及其所有子树中的最大距离
        }
    }

    // 求二叉树节点的最大距离
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

    // 求二叉树节点的最大距离2
    static int maxDist2(Node root) {
        if (root == null) return 0;
        int maxDis = Math.max(maxDist2(root.left), maxDist2(root.right));// 当前最大距离存在于左右子树中
        maxDis = Math.max(maxDis, maxDepth(root.left) + maxDepth(root.right) + 2);// 或者跨越了根节点
        return maxDis;
    }

    // 求二叉树节点的最大深度
    static int maxDepth(Node root) {
        if (root == null) return -1;
        int lDepth = maxDepth(root.left);// 左子树最大深度
        int rDepth = maxDepth(root.right);// 右子树最大深度
        return Math.max(lDepth, rDepth) + 1;// 当前最大深度是左右子树最大深度加1
    }

    // 判断是否平衡二叉树，即任意左右子树深度相差不超过1，可以每个节点统计maxDepth但是复杂度太高
    static boolean isBalanceTree(Node root, int[] depth) { // 用数组对象解决Java递归不可值传递问题
        if (root == null) {
            depth[0] = 0;
            return true;
        }
        int[] lDepth = new int[1], rDepth = new int[1];
        if (isBalanceTree(root.left, lDepth) && isBalanceTree(root.right, rDepth)) { // 保证左右子树自身平衡
            int diff = Math.abs(lDepth[0] - rDepth[0]); // 再对比左右子树是否平衡
            if (diff <= 1) {
                depth[0] = Math.max(lDepth[0], rDepth[0]) + 1;
                return true;
            }
        }
        return false;
    }

    static Node buildTree() {
        Node root = new Node(0);
        Node root2 = new Node(0);
        root.right = root2;
        Node l = new Node(0);
        Node r = new Node(0);
        root2.left = l;
        root2.right = r;
        Node ll = new Node(0);
        Node lr = new Node(0);
        l.left = ll;
        l.right = lr;
        Node rr = new Node(0);
        r.right = rr;
        Node lrl = new Node(0);
        lr.left = lrl;
        return root;
    }

    static Node buildTree(int[] a) {
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

    public static void main(String[] args) {
        Node root = buildTree();
        Memo memo = maxDist(root);
        System.out.println(memo.maxDist);
        System.out.println(maxDist2(root));

        int[] a = {0, 1, 2, 3, 4, -1, 6, -1, -1, 9};
        Node root2 = buildTree(a);
        System.out.println(isBalanceTree(root2, new int[1]));
    }
}
