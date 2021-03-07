package exercise.sort;

/**
 * 旋转数组求最小值：二分查找
 * 旋转数组是一个有序数组循环移位后得到
 */
public class rotateArrayMin {
    static int bisearch(int[] x) {
        int lo = 0, hi = x.length - 1; // 最终lo指向前半数组末尾，hi指向后半数组开始
        if (x[lo] < x[hi]) return x[lo]; // 直接为非旋转数组，返回第一个即为最小元素
        while (lo < hi - 1) { // 退出时指针相差1
            int mid = lo + (hi - lo) / 2;
            if (x[mid] > x[lo]) { // mid在前半数组，最小值在其后
                lo = mid;
            } else if (x[mid] < x[lo]) { // mid在后半数组，最小值在其前
                hi = mid;
            } else { // 如果mid和lo上元素相等则说明前半数组为同一个值，区间选择取决于hi元素
                if (x[mid] < x[hi]) { // 此分支实际不会执行
                    hi = mid;
                } else if (x[mid] > x[hi]) { // mid在前半数组，最小值在其后
                    lo = mid;
                } else {
                    return linearsearch(x, lo, hi); // 如果mid和lo、hi上元素都相等，那么只能线性判定最小值
                }
            }
        }
        return x[hi];
    }

    static int linearsearch(int[] x, int lo, int hi) {
        int min = Integer.MAX_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (x[i] < min) min = x[i];
        }
        return min;
    }

    public static void main(String[] args) {
        int[] x = {2, 3, 4, 1, 2};
        System.out.println(bisearch(x));
        int[] y = {1, 1, 1, 0, 1};
        System.out.println(bisearch(y));
    }
}
