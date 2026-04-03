package exercise.twopointer;

// 输入一个正数target，输出所有和为target的连续正数序列，至少2个数
// 双指针维护一个连续的求和窗口，从头到尾遍历
public class lianxutargetsum {
    static void solution(int target) {
        int lo = 1, hi = 2;
        int end = (target + 1) / 2; // 只需要寻找一半
        int curSum = lo + hi;
        while (lo < end) {
            if (curSum < target) { // 窗口扩展1
                hi++;
                curSum += hi;
            } else {
                if (curSum == target) { // 找到一个解，寻找下一个解时窗口缩小1
                    for (int i = lo; i <= hi; i++) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                }
                curSum -= lo; // 窗口缩小1
                lo++;
            }
        }
    }

    // 给定一个含有 n 个正整数的数组和一个正整数 target 。
    // 找出该数组中满足其总和大于等于 target 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    // 示例 1：
    // 输入：target = 7, nums = [2,3,1,2,4,3]
    // 输出：2
    // 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
    // 示例 2：
    // 输入：target = 4, nums = [1,4,4]
    // 输出：1
    // 示例 3：
    // 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
    // 输出：0
    static int solution_nums_input(int[] nums, int target) {
        int left = 0;
        int right = 0;
        int cur_sum = 0;
        int min_len = Integer.MAX_VALUE;
        while (right < nums.length) {
            if(cur_sum < target) {
                cur_sum += nums[right];
                right++;
            } else {
                if (cur_sum >= target) {
                    min_len = Math.min(right - left, min_len);
                }
                cur_sum -= nums[left];
                left++;
            }
        }
        return min_len == Integer.MAX_VALUE ? 0 : min_len;
    }

    public static void main(String[] args) {
        solution(10);

        int[] nums = new int[]{2,3,1,2,4,3};
        System.out.println(solution_nums_input(nums, 7));
    }
}
