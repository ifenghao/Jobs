package exercise.num;

/**
 * 众数：给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 摩尔投票法： 遍历过程中记众数的票数为 +1，非众数的票数为 −1，最终因为众数的存在一定会让票数 > 0
 * 当投票累计求和为0时，说明前面假设的众数错误，应该从头记录众数
 */
public class majoritynum {
    static int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }
}
