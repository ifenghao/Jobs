package exercise.twopointer;

public class removetargetnum {
    static int removeElement(int[] nums, int val) {
        int slow = 0; // 慢指针存放下一个元素放置的坐标
        for (int num : nums) { // 遍历的i为快指针
            if (val != num) {
                nums[slow++] = num;
            }
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
        int[] nums = {3,2,2,3};
        System.out.println(removeElement(nums, 3));
        printArray(nums);
    }
}
