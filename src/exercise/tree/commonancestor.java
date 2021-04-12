package exercise.tree;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 */
public class commonancestor {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static TreeNode solution(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) { // 节点可以是它自己的祖先
            return root;
        }
        TreeNode left = solution(root.left, p, q);
        TreeNode right = solution(root.right, p, q);
        if (left != null && right != null) { // p、q分别在左右子树，当前root就是公共祖先，后序遍历可以保证公共祖先最低
            return root;
        } else if (right == null) { // p、q都不在右子树，当前left就是公共祖先
            return left;
        } else { // p、q都不在左子树，当前right就是公共祖先
            return right;
        }
    }
}
