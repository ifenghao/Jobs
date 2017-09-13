package exercise.sort;

/**
 * 数组循环右移k位，可以将0~k-1位翻转，k~n-1位翻转，最后所有位0~n-1翻转
 */
public class rotate {
    static void rotK(int[] array, int k) {
        int n = array.length;
        k = k % n;
        int tmp;
        for (int i = 0; i < k / 2; i++) {
            tmp = array[i];
            array[i] = array[k - 1 - i];
            array[k - 1 - i] = tmp;
        }
        for (int i = 0; i < (n - k) / 2; i++) {
            tmp = array[i + k];
            array[i + k] = array[n - 1 - i];
            array[n - 1 - i] = tmp;
        }
        for (int i = 0; i < n / 2; i++) {
            tmp = array[i];
            array[i] = array[n - 1 - i];
            array[n - 1 - i] = tmp;
        }
    }

    static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + ", ");
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{10, 33, 4, 5, 62, 325, 5, 6, 1, 9, 6};
        rotK(a, 13);
        printArray(a);
    }
}
