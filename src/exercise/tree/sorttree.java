package exercise.tree;

// 二叉排序树
public class sorttree {
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

    // 排序二叉树寻找第k大的节点，中序遍历即可从小到大找到第k个
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

    // 排序二叉树转双向链表
    static Node treeToBinaryLinked(Node root) {
        if (root == null) return root;
        treeToBinaryLinkedCore(root, new Node[1]);

        Node head = root; // 寻找构造链表的头结点
        while (head.left != null)
            head = head.left;
        return head;
    }

    // Java单值引用在递归中无法传递，因此使用一个数组来保存中序遍历左子树的最后一个节点(即最大节点)
    static void treeToBinaryLinkedCore(Node root, Node[] inOrderLast) {
        if (root == null) return;
        treeToBinaryLinkedCore(root.left, inOrderLast);

        root.left = inOrderLast[0]; // 向前链接
        if (inOrderLast[0] != null) {
            inOrderLast[0].right = root; // 向后链接
        }
        inOrderLast[0] = root; // 更新中序遍历左子树的最后一个节点

        treeToBinaryLinkedCore(root.right, inOrderLast);
    }

    static void printBinaryLinked(Node head) {
        if (head == null) return;
        System.out.print("forward: ");
        Node pre = head;
        while (head != null) {
            System.out.print(head.val + " ");
            pre = head;
            head = head.right;
        }
        head = pre;
        System.out.print("\nbackward: ");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.left;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 2, 6, 1, 3, 9, 0, 8, 7, 5};
        Node root = build(a);
        System.out.println(kthNode(root, 1).val);

        Node head = treeToBinaryLinked(root);
        printBinaryLinked(head);
    }
}
