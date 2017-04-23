package exercise;

/**
 * Created by zfh on 17-3-29.
 */
public class Sort {
    public static void selectSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            int tmp = array[i];
            array[i] = array[minIdx];
            array[minIdx] = tmp;
        }
    }

    public static void bubbleSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array[i] > array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public static void insertSort(int[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] < array[j]) {
                    int tmp = array[i];
                    for (int k = i; k > j; k--) {
                        array[k] = array[k - 1];
                    }
                    array[j] = tmp;
                    break;
                }
            }
        }
    }

    public static void shellSort(int[] array) {
        int length = array.length;
        int[] steps = new int[]{5, 3, 1};
        for (int step : steps) {
            for (int start = 0; start < step; start++) {
                for (int i = start; i < length; i += step) {
                    for (int j = start; j < i; j += step) {
                        if (array[i] < array[j]) {
                            int tmp = array[i];
                            for (int k = i; k > j; k -= step) {
                                array[k] = array[k - step];
                            }
                            array[j] = tmp;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void merge(int[] array, int lo, int mid, int hi) {
        int i = lo, j = mid;
        int[] aux = new int[array.length];
        for (int k = lo; k < hi; k++) {
            aux[k] = array[k];
        }
        for (int k = lo; k < hi; k++) {
            if (i >= mid) {
                array[k] = aux[j++];
            } else if (j >= hi) {
                array[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                array[k] = aux[i++];
            } else {
                array[k] = aux[j++];
            }
        }
    }

    public static void mergeSort(int[] array, int lo, int hi) {
        if (lo >= hi - 1) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(array, lo, mid);
        mergeSort(array, mid, hi);
        merge(array, lo, mid, hi);
    }

    public static int partation(int[] array, int lo, int hi) {
        int i = lo + 1, j = hi;
        int point = array[lo];
        while (true) {
            while (array[i] < point)
                if (i >= hi) break;
                else i++;
            while (array[j] > point)
                if (j <= lo) break;
                else j--;
            if (i >= j) break;
            int tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
        System.out.println(i + " " + j);
        int tmp = array[j];
        array[j] = array[lo];
        array[lo] = tmp;
        return j;
    }

    public static void quickSort(int[] array, int lo, int hi) {
        if (lo >= hi) return;
        int j = partation(array, lo, hi);
        quickSort(array, lo, j - 1);
        quickSort(array, j + 1, hi);
    }

    public static int selectMedium(int[] array, int k, int lo, int hi) {
        int j = partation(array, lo, hi);
        if (j == k) {
            return array[j];
        } else if (j > k) {
            return selectMedium(array, k, lo, j - 1);
        } else {
            return selectMedium(array, k, j + 1, hi);
        }
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + ", ");
        }
    }

    public static void main(String[] args) {
        int[] testArray = new int[]{10, 33, 4, 5, 62, 325, 5, 6, 1, 9, 6};
        quickSort(testArray, 0, testArray.length - 1);
        printArray(testArray);
    }
}
