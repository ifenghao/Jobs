package exercise.twopointer;

/**
 * 1、删除有序数组中的重复项
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。nums其余元素不用管
 *
 * 2、删除有序数组中的重复项（出现两次）
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class sortarrayduplicate {
    static int removeDuplicates(int[] nums) { // 通用解法k=1
        int slow = 0; // 慢指针存放下一个元素放置的坐标
        for (int num : nums) { // 遍历的i为快指针
            if (slow < 1 || nums[slow - 1] != num) {
                nums[slow++] = num;
            }
        }
        return slow;
    }

    static int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {// 快指针在长度为1的窗口内判断
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    static int removeDuplicatesTwice(int[] nums) { // 通用解法k=2
        int slow = 0; // 慢指针存放下一个元素放置的坐标
        for (int num : nums) { // 遍历的i为快指针
            if (slow < 2 || nums[slow - 2] != num) {
                nums[slow++] = num;
            }
        }
        return slow;
    }

    static int removeDuplicatesTwice2(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {// 慢指针在长度为2的窗口内判断
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    static void printArray(int[] array) {
        for (int item :
                array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
        printArray(nums);

        int[] nums2 = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicatesTwice(nums2));
        printArray(nums2);
    }
}
