package exercise.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长不含重复字母的连续子串
 * dp[i]保存以i结尾的最长不重复连续子串，那么dp[i]=
 * 若此前chars[i]未出现过，可以直接扩展子串到dp[i-1] + 1
 * 若此前chars[i]出现过，与当前距离为dist，那么
 *   dist > dp[i-1]时，说明chars[i]与dp[i-1]的子串不会重复，可扩展到dp[i-1] + 1
 *   dist <= dp[i-1]时，说明chars[i]与dp[i-1]的子串会重复，当前最长子串只能到dist
 */
public class maxnonduplicate {
    static int solution(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] dp = new int[n];
        int[] pos = new int[26]; // 用26维数组保存每个字母最近一次位置
        for (int i = 0; i < 26; i++) { // 初始化-1都没出现过
            pos[i] = -1;
        }
        dp[0] = 1;
        pos[chars[0] - 'a'] = 0;
        for (int i = 1; i < n; i++) {
            int lastPos = pos[chars[i] - 'a']; // 获取最近这个字母出现的位置
            if (lastPos == -1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                int dist = i - lastPos; // 当前与上次的距离可以O(1)时间计算
                if (dist > dp[i - 1]) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dist;
                }
            }
            pos[chars[i] - 'a'] = i;
        }
        int max = -1;
        for (int d : dp) {
            if (d > max)
                max = d;
        }
        return max;
    }

    // 使用滑动窗口
    static int solution2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length, maxLen = 0, count;
        int left = 0, right = 0;
        char rchar, lchar;
        Map<Character, Integer> window = new HashMap<>(); // 保存窗口内字符的出现次数
        while (right < n) {
            rchar = chars[right];
            window.put(rchar, window.getOrDefault(rchar, 0) + 1); // 更新right字符出现次数
            right++;
            while (window.get(rchar) > 1) { // 检查right字符重复次数
                lchar = chars[left];
                window.put(lchar, window.get(lchar) - 1); // 从left字符开始去除重复
                left++;
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "arabcrcfr";
        System.out.println(solution(s));
    }
}
