package exercise.tree;

// 判断树B是否是树A的子结构
public class treesubstruct {
    static class TreeNode {
        int val;
        TreeNode left = null, right = null;

        TreeNode (int val) {
            this.val = val;
        }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        boolean result = false;
        if (A.val == B.val) {
            result = isSubStructureCore(A, B);
        }
        if (!result) {
            result = isSubStructure(A.left, B);
        }
        if (!result) {
            result = isSubStructure(A.right, B);
        }
        return result;
    }

    public boolean isSubStructureCore(TreeNode root1, TreeNode root2) {
        if (root2 == null) { // 先判断子结构是否null，遍历完说明匹配成功
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSubStructureCore(root1.left, root2.left) && isSubStructureCore(root1.right, root2.right);
    }
}
