package exercise.greed;

/**
 * 1、跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 * 对于每一个可以到达的位置 x，它使得 x+1,x+2,⋯,x+nums[x] 这些连续的位置都可以到达。
 * 贪心的遍历数组中的每一个位置，并实时维护 最远可以到达的位置。
 *
 * 2、跳跃游戏
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]
 *
 * 动态规划：dp[i]表示到达i的最小次数，dp[i] = min{dp[j]} + 1 其中 nums[j] + j >= i，j < i
 * 贪心：「贪心」地进行正向查找，每次找到可到达的最远位置
 */
public class jumpgame {
    static boolean canJump(int[] nums) {
        int n = nums.length - 1, maxjump = 0; // 最远到达位置
        if (n <= 0) return true;
        for (int i = 0; i < n; i++) {
            if (i <= maxjump) {
                maxjump = Math.max(maxjump, i + nums[i]); // 更新最远到达位置
                if (maxjump >= n) {
                    return true;
                }
            }
        }
        return false;
    }

    static int jumpMin(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            int minjump = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) { // 寻找上一步最小的跳跃次数
                if (nums[j] + j >= i && dp[j] < minjump) {
                    minjump = dp[j];
                }
            }
            dp[i] = minjump + 1;
        }
        return dp[n - 1];
    }

    static int jumpMin2(int[] nums) {
        int n = nums.length;
        int end = 0; // 记录上一次能到达的最远位置
        int maxPosition = 0; // 记录当前能到达最远位置
        int steps = 0;
        for (int i = 0; i < n - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) { // 到达边界时，更新边界并将跳跃次数增加 1
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        System.out.println(canJump(nums));

        int[] nums2 = {2,3,1,1,4};
        System.out.println(jumpMin2(nums2));
    }
}
