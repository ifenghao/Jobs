package exercise.sort;

/**
 * 数组循环右移k位，可以将0~k-1位翻转，k~n-1位翻转，最后所有位0~n-1翻转
 */
public class rotateK {
    static void rotK(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        k = n - k; // 原始k是循环左移，用n-k是循环右移
        rotate(nums, 0, k - 1);
        rotate(nums, k, n - 1);
        rotate(nums, 0, n - 1);
    }

    static void rotate(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6,7};
        rotK(a, 3);
        printArray(a);
    }
}
