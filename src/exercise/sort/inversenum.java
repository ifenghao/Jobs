package exercise.sort;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.*;

/**
 * 数组逆序数
 * 1 插入排序法 O(n2)
 * 2 归并排序法 O(nlogn)
 * 3 树状数组法 O(nlogn)
 * 4 线段树法 O(nlogn)
 */
public class inversenum {
    static void insertCount(int[] a) {
        int n = a.length;
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i]) cnt++;
            }
        }
        System.out.println(cnt);
    }

    static void mergeCount(int[] a) {
        aux = new int[a.length];
        mergeHelper(a, a.length, 0, a.length - 1);
        System.out.println(cnt);
    }

    static int[] aux;
    static int cnt = 0;

    static void mergeHelper(int[] a, int n, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeHelper(a, n, lo, mid);
        mergeHelper(a, n, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    static void merge(int[] a, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
                cnt += mid - i + 1;
            } else a[k] = aux[i++];
        }
    }


    static void treearrayCount(int[] a) {
        int n = a.length;
        a = scatter(a);
        int[] tree = new int[n + 1];
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            add(tree, a[i - 1], 1);
            cnt += i - getSum(tree, a[i - 1]);
        }
        System.out.println(cnt);
    }

    static void add(int[] tree, int k, int val) {
        while (k <= tree.length - 1) {
            tree[k] += val;
            k += k & -k;
        }
    }

    static int getSum(int[] tree, int k) {
        int sum = 0;
        while (k > 0) {
            sum += tree[k];
            k -= k & -k;
        }
        return sum;
    }

    static void segtreeCount(int[] a) {
        int n = a.length;
        a = scatter(a);
        tree = new int[(int) pow(2, ceil(log((double) n) / log(2.0)) + 1) - 1];
        build(0, a, 0, n - 1);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            update(0, 0, n - 1, a[i], 1);
            cnt += query(0, 0, n - 1, a[i] + 1, tree.length - 1);
        }
    }

    static int[] tree;

    static void build(int root, int[] a, int nstart, int nend) {
        if (nstart == nend) {
            tree[root] = a[nstart];
        } else {
            int mid = nstart + (nend - nstart) / 2;
            build(root * 2 + 1, a, nstart, mid);
            build(root * 2 + 2, a, mid + 1, nend);
            tree[root] = tree[root * 2 + 1] + tree[root * 2 + 2];
        }
    }

    static int query(int root, int nstart, int nend, int qstart, int qend) {
        if (nend < qstart || nstart > qend) return 0;
        if (nstart >= qstart && nend <= qend) return tree[root];
        int mid = nstart + (nend - nstart) / 2;
        return query(root * 2 + 1, nstart, mid, qstart, qend) +
                query(root * 2 + 2, mid + 1, nend, qstart, qend);
    }

    static void update(int root, int nstart, int nend, int idx, int val) {
        if (nstart == nend) {
            if (nstart == idx) tree[root] += val;
        } else {
            int mid = nstart + (nend - nstart) / 2;
            if (idx <= mid) update(root * 2 + 1, nstart, mid, idx, val);
            else update(root * 2 + 2, mid + 1, nend, idx, val);
            tree[root] = tree[root * 2 + 1] + tree[root * 2 + 2];
        }
    }

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
            out[m[i][1]] = i + 1;// 树状数组需要最小值为1
        }
        return out;
    }

    public static void main(String[] args) {
        int[] a = new int[]{5, 3, 4, 2, 1};
//        insertCount(a);
//        mergeCount(a);
        treearrayCount(a);
    }
}
