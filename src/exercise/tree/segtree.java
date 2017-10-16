package exercise.tree;

import static java.lang.Math.*;

/**
 * 区间最小值线段树构造，查询区间最小值，更新区间一个元素
 * 对数组中的每个数，在它之前有多少个数比它大
 */
public class segtree {
    static int[] tree;

    static void build(int root, int[] a, int nstart, int nend) {
        if (nstart == nend) {// 叶子节点
            tree[root] = a[nstart];
        } else {
            int mid = nstart + (nend - nstart) / 2;
            build(root * 2 + 1, a, nstart, mid);
            build(root * 2 + 2, a, mid + 1, nend);
            tree[root] = min(tree[root * 2 + 1], tree[root * 2 + 2]);// 非叶子节点为左右子树的最小值
        }
    }

    static int query(int root, int nstart, int nend, int qstart, int qend) {
        if (nend < qstart || nstart > qend) return Integer.MAX_VALUE;
        if (nstart >= qstart && nend <= qend) return tree[root];
        int mid = nstart + (nend - nstart) / 2;
        return min(query(root * 2 + 1, nstart, mid, qstart, qend),
                query(root * 2 + 2, mid + 1, nend, qstart, qend));
    }

    static void update(int root, int nstart, int nend, int idx, int val) {
        if (nstart == nend) {
            if (nstart == idx) tree[root] = val;// 找到叶子节点索引后更新
        } else {
            int mid = nstart + (nend - nstart) / 2;
            if (idx <= mid) update(root * 2 + 1, nstart, mid, idx, val);// 索引在左子树
            else update(root * 2 + 2, mid + 1, nend, idx, val);// 索引在右子树
            tree[root] = min(tree[root * 2 + 1], tree[root * 2 + 2]);// 非叶子节点为左右子树的最小值
        }
    }

    static int[] largeCounter(int n, int[] a) {
        int[] res = new int[n];
        res[0] = 0;
        tree = new int[(int) pow(2, ceil(log((double) n) / log(2.0)) + 1) - 1];
        build(0, a, 0, n - 1);
        for (int i = 1; i < n; i++) {
            cnt = 0;
            largeQuery(0, 0, n - 1, 0, i - 1, a[i]);
            res[i] = cnt;
        }
        return res;
    }

    static int cnt;

    // 在一个区间中查询比val大的元素个数
    static void largeQuery(int root, int nstart, int nend, int qstart, int qend, int val) {
        if (nstart > nend || nend < qstart || nstart > qend) return;// 当前区间在查询区间之外
        if (root >= tree.length) return;// 根节点索引超出范围
        if (val < tree[root]) {// 对叶子或非叶子节点，val小于该区间最小值，该区间所有值都比val大
            cnt += nend - nstart + 1;
        } else {
            int mid = nstart + (nend - nstart) / 2;
            largeQuery(root * 2 + 1, nstart, mid, qstart, qend, val);
            largeQuery(root * 2 + 2, mid + 1, nend, qstart, qend, val);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 1, 4, 9, 3};
        int n = a.length;
//        tree = new int[(int) pow(2, ceil(log((double) n) / log(2.0)) + 1) - 1];
//        build(0, a, 0, n - 1);
//        System.out.println(query(0, 0, n - 1, 1, 4));
//        update(0, 0, n - 1, 2, 3);
//        System.out.println(query(0, 0, n - 1, 1, 4));
        for (int r : largeCounter(n, a)) {
            System.out.print(r + " ");
        }
    }
}
