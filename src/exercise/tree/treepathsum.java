package exercise.tree;

import java.util.*;

// 二叉树中和为某个数值的路径
public class treepathsum {
    static class TreeNode {
        int val;
        TreeNode left = null, right = null;

        TreeNode (int val) {
            this.val = val;
        }
    }
    
    static TreeNode build(int[] a) {
        TreeNode root = new TreeNode(a[0]);
        Deque<TreeNode> dq = new LinkedList<>();
        dq.addLast(root);
        int i = 0;
        TreeNode tmpNode;
        while (!dq.isEmpty()) {
            tmpNode = dq.removeFirst();
            if (tmpNode.val < 0) continue;
            if (++i < a.length) {
                if (a[i] >= 0) {
                    tmpNode.left = new TreeNode(a[i]);
                    dq.addLast(tmpNode.left);
                } else {
                    dq.addLast(new TreeNode(a[i]));
                }
            }
            if (++i < a.length) {
                if (a[i] >= 0) {
                    tmpNode.right = new TreeNode(a[i]);
                    dq.addLast(tmpNode.right);
                } else {
                    dq.addLast(new TreeNode(a[i]));
                }
            }
        }
        return root;
    }

    // 是否存在和为target的路径
    static boolean existPathSum(TreeNode root, int target) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == target) { //先序遍历递归到叶子结点，且剩余target恰好为叶子节点数值
            return true;
        }
        return existPathSum(root.left, target - root.val) || existPathSum(root.right, target - root.val);
    }

    // 求所有路径结果
    static List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null){
            pathSumCore(result, new ArrayList<>(), root, target);
        }
        return result;
    }

    static void pathSumCore(List<List<Integer>> res, List<Integer> curPath, TreeNode root, int target){
        if (root.left == null && root.right == null && root.val == target){ //先序遍历递归到叶子结点，且剩余target恰好为叶子节点数值
            curPath.add(root.val);
            res.add(curPath);
            return;
        }

        curPath.add(root.val);
        if (root.left != null){
            List<Integer> left_children_path = new ArrayList<>(curPath);  //每开启一个新的递归，都要复制一份新的curPath
            pathSumCore(res, left_children_path, root.left, target - root.val);
        }
        if (root.right != null){
            List<Integer> right_children_path = new ArrayList<>(curPath);  //每开启一个新的递归，都要复制一份新的curPath
            pathSumCore(res, right_children_path, root.right, target - root.val);
        }
    }

    public static void main(String[] args) {
        TreeNode root = build(new int[]{5,4,8,11,-1,13,4,7,2,-1,-1,5,1});
        System.out.println(existPathSum(root, 22));
        System.out.println(pathSum(root, 22));
    }
}
