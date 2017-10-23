package exercise.tree;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 树状数组，能在O(logn)内对数组进行单值修改和查询某区间数值和
 * 对数组中的每个数，在它之前有多少个数比它大
 */
public class treearray {
    // 单点更新，将tree中的k索引值增加val，树状数组最小索引为1
    static void add(int[] tree, int k, int val) {
        while (k < tree.length) {
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

    // 之前大于值的次数，使用次数统计数组
    static int[] largeCounter(int n, int[] a) {
        int[] res = new int[n];
        a = scatter(a);
        int[] tree = new int[n + 1];// tree数组记录a作为索引值出现的次数
        for (int i = 1; i <= n; i++) {
            add(tree, a[i - 1], 1);// a索引值出现次数加一
            res[i - 1] = i - getSum(tree, a[i - 1]);// tree中a值之后数字出现次数
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
            out[m[i][1]] = i + 1;// 树状数组需要最小值为1
        }
        return out;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 1, 4, 9, 3};// 原数组a可以不需要
        int n = a.length;
//        int[] tree = new int[n + 1];// tree的首元素不用
//        for (int i = 1; i <= n; i++) {// 流式向tree中增加元素
//            add(tree, i, a[i - 1]);
//        }
//        System.out.println(getSum(tree, 3));
//        add(tree, 3, 2);// 可以随时对单点值更新
//        System.out.println(getSum(tree, 3));
        for (int r : largeCounter(n, a)) {
            System.out.print(r + " ");
        }
    }
}
