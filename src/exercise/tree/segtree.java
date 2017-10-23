package exercise.tree;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.*;

/**
 * 区间最小值线段树构造，查询区间最小值，更新区间一个元素
 * 对数组中的每个数，在它之前有多少个数比它大
 */
public class segtree {
    static int[] tree;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 区间最小值线段树构造
    static void buildMin(int root, int[] a, int nstart, int nend) {
        if (nstart == nend) {// 叶子节点
            tree[root] = a[nstart];
        } else {
            int mid = nstart + (nend - nstart) / 2;
            buildMin(root * 2 + 1, a, nstart, mid);
            buildMin(root * 2 + 2, a, mid + 1, nend);
            tree[root] = min(tree[root * 2 + 1], tree[root * 2 + 2]);// 非叶子节点为左右子树的最小值
        }
    }

    // 查询区间最小值
    static int queryMin(int root, int nstart, int nend, int qstart, int qend) {
        if (nend < qstart || nstart > qend) return Integer.MAX_VALUE;
        if (nstart >= qstart && nend <= qend) return tree[root];
        int mid = nstart + (nend - nstart) / 2;
        return min(queryMin(root * 2 + 1, nstart, mid, qstart, qend),
                queryMin(root * 2 + 2, mid + 1, nend, qstart, qend));
    }

    // 更新区间一个元素
    static void updateMin(int root, int nstart, int nend, int idx, int val) {
        if (nstart == nend) {
            if (nstart == idx) tree[root] = val;// 找到叶子节点索引后更新
        } else {
            int mid = nstart + (nend - nstart) / 2;
            if (idx <= mid) updateMin(root * 2 + 1, nstart, mid, idx, val);// 索引在左子树
            else updateMin(root * 2 + 2, mid + 1, nend, idx, val);// 索引在右子树
            tree[root] = min(tree[root * 2 + 1], tree[root * 2 + 2]);// 非叶子节点为左右子树的最小值
        }
    }

    // 之前大于值的次数，构建最小值线段树，若一个值比区间最小值小则该区间所有值都比此值大
    static int[] largeCounter(int n, int[] a) {
        int[] res = new int[n];
        res[0] = 0;
        tree = new int[(int) pow(2, ceil(log((double) n) / log(2.0)) + 1) - 1];
        buildMin(0, a, 0, n - 1);
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // 区间和线段树构造
    static void buildSum(int root, int[] a, int nstart, int nend) {
        if (nstart == nend) {// 叶子节点
            tree[root] = a[nstart];
        } else {
            int mid = nstart + (nend - nstart) / 2;
            buildSum(root * 2 + 1, a, nstart, mid);
            buildSum(root * 2 + 2, a, mid + 1, nend);
            tree[root] = tree[root * 2 + 1] + tree[root * 2 + 2];// 非叶子节点为左右子树的最小值
        }
    }

    // 查询区间和
    static int querySum(int root, int nstart, int nend, int qstart, int qend) {
        if (nend < qstart || nstart > qend) return 0;
        if (nstart >= qstart && nend <= qend) return tree[root];
        int mid = nstart + (nend - nstart) / 2;
        return querySum(root * 2 + 1, nstart, mid, qstart, qend) +
                querySum(root * 2 + 2, mid + 1, nend, qstart, qend);
    }

    // 更新区间一个元素
    static void updateSum(int root, int nstart, int nend, int idx, int val) {
        if (nstart == nend) {
            if (nstart == idx) tree[root] += val;// 找到叶子节点索引后更新
        } else {
            int mid = nstart + (nend - nstart) / 2;
            if (idx <= mid) updateSum(root * 2 + 1, nstart, mid, idx, val);// 索引在左子树
            else updateSum(root * 2 + 2, mid + 1, nend, idx, val);// 索引在右子树
            tree[root] = tree[root * 2 + 1] + tree[root * 2 + 2];// 非叶子节点为左右子树的最小值
        }
    }

    // 之前大于值的次数，使用次数统计数组
    static int[] largeCounter2(int n, int[] a) {
        int[] res = new int[n];
        a = scatter(a);
        tree = new int[(int) pow(2, ceil(log((double) n) / log(2.0)) + 1) - 1];// tree数组记录a作为索引值出现的次数
        // 线段树不用build，初始值都为0
        for (int i = 0; i < n; i++) {
            updateSum(0, 0, n - 1, a[i], 1);// a索引值出现次数加一
            res[i] = querySum(0, 0, n - 1, a[i] + 1, n - 1);// tree中a值之后数字出现次数
        }
        return res;
    }

    // 数据离散化，将a数组所有值映射到1~n之间
    static int[] scatter(int[] a) {
        int n = a.length;
        int[][] m = new int[n][2];
        for (int i = 0; i < n; i++) {
            m[i][0] = a[i];
            m[i][1] = i;
        }
        Arrays.sort(m, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            out[m[i][1]] = i;
        }
        return out;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 1, 4, 9, 3};
        int n = a.length;
//        tree = new int[(int) pow(2, ceil(log((double) n) / log(2.0)) + 1) - 1];
//        buildMin(0, a, 0, n - 1);
//        System.out.println(queryMin(0, 0, n - 1, 1, 4));
//        updateMin(0, 0, n - 1, 2, 3);
//        System.out.println(queryMin(0, 0, n - 1, 1, 4));
        for (int r : largeCounter2(n, a)) {
            System.out.print(r + " ");
        }
    }
}
