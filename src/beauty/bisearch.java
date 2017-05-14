package beauty;

/**
 * Created by zfh on 17-5-14.
 */
public class bisearch {
    // 返回任意一个命中元素的索引
    static int searchEqual(int[] array, int key) {
        int lo = 0, hi = array.length - 1, mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key == array[mid]) {
                return mid;
            } else if (key < array[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;// 未命中，返回查询失败
//        return lo;// 未命中，返回大于key的最小索引
//        return hi;// 未命中，返回小于key的最大索引
    }

    // 返回命中元素的最小索引
    static int searchEqualMin(int[] array, int key) {
        int lo = 0, hi = array.length - 1, mid;
        boolean hitted = false;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key == array[mid]) {
                hitted = true;// 命中
                hi--;// 左移寻找最小索引
            } else if (key < array[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;// lo==hi退出前的最小索引
            }
        }
        return hitted ? lo : -1;
    }

    // 返回命中元素的最大索引
    static int searchEqualMax(int[] array, int key) {
        int lo = 0, hi = array.length - 1, mid;
        boolean hitted = false;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key == array[mid]) {
                hitted = true;// 命中
                lo++;// 右移寻找最大索引
            } else if (key < array[mid]) {
                hi = mid - 1;// lo==hi退出前的最大索引
            } else {
                lo = mid + 1;
            }
        }
        return hitted ? hi : -1;
    }

    // 返回key的下界索引，即小于key的最大索引
    static int searchLowerBound(int[] array, int key) {
        int lo = 0, hi = array.length - 1, mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key <= array[mid]) {
                hi = mid - 1;// lo==hi退出前小于key元素的最大索引
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }

    // 返回key的上界索引，即大于key的最小索引
    static int searchUpperBound(int[] array, int key) {
        int lo = 0, hi = array.length - 1, mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key >= array[mid]) {
                lo = mid + 1;// lo==hi退出前大于key元素的最小索引
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 6, 6, 6, 7, 9, 10};
        System.out.println(searchEqual(array, 6));
        System.out.println(searchEqual(array, 5));
        System.out.println(searchEqualMin(array, 6));
        System.out.println(searchEqualMin(array, 5));
        System.out.println(searchEqualMax(array, 6));
        System.out.println(searchEqualMax(array, 5));
        System.out.println(searchLowerBound(array, 6));
        System.out.println(searchLowerBound(array, 5));
        System.out.println(searchUpperBound(array, 6));
        System.out.println(searchUpperBound(array, 5));
    }
}
