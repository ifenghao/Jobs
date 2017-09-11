package beauty;

/**
 * Created by zfh on 17-5-14.
 * 对于条件while(lo<=hi)，退出前一定会满足lo==hi，
 * 这时只需要考虑mid=lo=hi时查询条件是否满足，
 * 不满足时尝试分别执行分支条件决定返回lo还是hi
 */
public class bisearch {
    // 返回任意一个命中元素的索引
    static int searchEqual(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key == a[mid]) {
                return mid;
            } else if (key < a[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;// 未命中，返回查询失败
//        return lo;// 未命中，返回大于key的最小索引
//        return hi;// 未命中，返回小于key的最大索引
    }

    // 返回命中元素的最小索引，最坏情况可能为O(n)
    static int searchEqualMin1(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        boolean hitted = false;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key == a[mid]) {
                hitted = true;// 命中
                hi--;// 左移寻找最小索引
            } else if (key < a[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;// lo==hi退出前的最小索引
            }
        }
        return hitted ? lo : -1;
    }

    // 返回命中元素的最小索引
    static int searchEqualMin2(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo < hi) {// 不能<=否则lo==hi死循环
            mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) {
                hi = mid;// 少减1
            } else {
                lo = mid + 1;
            }
        }
        return a[lo] == key ? lo : -1;// 退出时一定lo==hi，lo和hi都可以
    }

    // 返回命中元素的最小索引(和搜索下界步骤一样)
    static int searchEqualMin3(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo <= hi) {// 可以lo==hi
            mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) {
                hi = mid - 1;// 多减1
            } else {
                lo = mid + 1;
            }
        }
        return a[lo] == key ? lo : -1;// 只能lo
    }


    // 返回命中元素的最大索引，最坏情况可能为O(n)
    static int searchEqualMax1(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        boolean hitted = false;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key == a[mid]) {
                hitted = true;// 命中
                lo++;// 右移寻找最大索引
            } else if (key < a[mid]) {
                hi = mid - 1;// lo==hi退出前的最大索引
            } else {
                lo = mid + 1;
            }
        }
        return hitted ? hi : -1;
    }

    // 返回命中元素的最大索引
    static int searchEqualMax2(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo < hi) {// 不能<=否则lo==hi死循环
            mid = lo + (hi - lo + 1) / 2;// 多进一位防止死循环
            if (key >= a[mid]) {
                lo = mid;// 少加1
            } else {
                hi = mid - 1;
            }
        }
        return a[lo] == key ? lo : -1;
    }

    // 返回命中元素的最大索引(和搜索上界步骤一样)
    static int searchEqualMax3(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo <= hi) {// 可以lo==hi
            mid = lo + (hi - lo + 1) / 2;
            if (key >= a[mid]) {
                lo = mid + 1;// 多加1
            } else {
                hi = mid - 1;
            }
        }
        return a[hi] == key ? hi : -1;
    }

    // 返回key的下界索引，即小于key的最大索引
    static int searchLowerBound(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) {
                hi = mid - 1;// lo==hi退出前小于key元素的最大索引
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }

    // 返回key的下界索引，即小于key的最大索引
    static int searchLowerBound2(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) {
                hi = mid;// lo==hi-1退出前小于key元素的最大索引
            } else {
                lo = mid + 1;
            }
        }
        return hi - 1;
    }

    // 返回key的上界索引，即大于key的最小索引
    static int searchUpperBound(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo <= hi) {
            mid = lo + (hi - lo + 1) / 2;
            if (key >= a[mid]) {
                lo = mid + 1;// lo==hi退出前大于key元素的最小索引
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    // 返回key的上界索引，即大于key的最小索引
    static int searchUpperBound2(int[] a, int key) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo < hi) {
            mid = lo + (hi - lo + 1) / 2;
            if (key >= a[mid]) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo + 1;
    }

    public static void main(String[] args) {
        int[] a = {4, 6, 6, 6, 7, 9, 10, 12, 13};
        System.out.println(searchEqual(a, 6));
        System.out.println(searchEqual(a, 5));
        System.out.println(searchEqualMin2(a, 6));
        System.out.println(searchEqualMin2(a, 5));
        System.out.println(searchEqualMin3(a, 6));
        System.out.println(searchEqualMin3(a, 5));
        System.out.println(searchEqualMax2(a, 6));
        System.out.println(searchEqualMax2(a, 5));
        System.out.println(searchEqualMax3(a, 6));
        System.out.println(searchEqualMax3(a, 5));
        System.out.println(searchLowerBound(a, 6));
        System.out.println(searchLowerBound(a, 7));
        System.out.println(searchLowerBound2(a, 6));
        System.out.println(searchLowerBound2(a, 7));
        System.out.println(searchUpperBound(a, 6));
        System.out.println(searchUpperBound(a, 5));
        System.out.println(searchUpperBound2(a, 6));
        System.out.println(searchUpperBound2(a, 5));
    }
}
