package exercise.tree;

/**
 * 树状数组，能在O(logn)内对数组进行单值修改和查询某区间数值和
 * 对数组中的每个数，在它之前有多少个数比它大
 */
public class treearray {
    // 单点更新，将tree中的k索引值增加val，树状数组最小索引为1
    static void add(int[] tree, int k, int val) {
        while (k <= tree.length - 1) {
            tree[k] += val;
            k += k & -k;// lowbit = k & -k
        }
    }

    // 区间求和，tree区间1~k，对应a区间0~k-1
    static int getSum(int[] tree, int k) {
        int sum = 0;
        while (k > 0) {
            sum += tree[k];
            k -= k & -k;
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 1, 4, 9, 3};// 原数组a可以不需要
        int n = a.length;
        int[] tree = new int[n + 1];// tree的首元素不用
        for (int i = 1; i <= n; i++) {// 流式向tree中增加元素
            add(tree, i, a[i - 1]);
        }
        System.out.println(getSum(tree, 3));
        add(tree, 3, 2);// 可以随时对单点值更新
        System.out.println(getSum(tree, 3));
    }
}
