package exercise.tree;

public class sorttreekth {
    static class Node {
        int val;
        Node left = null, right = null;

        Node(int val) {
            this.val = val;
        }
    }

    // 非递归构建
    static Node build(int[] a) {
        if (a == null) return null;
        Node root = null, back = null, cur;
        for (int val : a) {
            if (root == null) {
                root = new Node(val);
            } else {
                cur = root;
                while (cur != null) {
                    back = cur;
                    if (val < cur.val) {
                        cur = cur.left;
                    } else {
                        cur = cur.right;
                    }
                }
                if (val < back.val) {
                    back.left = new Node(val);
                } else {
                    back.right = new Node(val);
                }
            }
        }
        return root;
    }

    // 递归构建
    static Node build2(int[] a) {
        if (a == null) return null;
        Node root = null;
        for (int val : a) {
            if (root == null) {
                root = new Node(val);
            } else {
                insert(root, val);
            }
        }
        return root;
    }

    static void insert(Node root, int val) {
        if (root == null) return;
        if (val < root.val) {
            if (root.left != null) {
                insert(root.left, val);
            } else {
                root.left = new Node(val);
            }
        } else {
            if (root.right != null) {
                insert(root.right, val);
            } else {
                root.right = new Node(val);
            }
        }
    }

    static void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    static int cnt = 0;

    static Node kthNode(Node root, int k) {
        Node res = null;
        if (root.left != null) {
            res = kthNode(root.left, k);
        }
        cnt++;
        if (cnt == k) {
            res = root;
        }
        if (res == null && root.right != null) {
            res = kthNode(root.right, k);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 2, 6, 1, 3, 9, 0, 8, 7, 5};
        Node root = build(a);
        System.out.println(kthNode(root, 1).val);
    }
}
