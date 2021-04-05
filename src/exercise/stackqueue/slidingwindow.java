package exercise.stackqueue;

import java.util.HashMap;
import java.util.Map;

/**
 * 1、最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * 输入：s = "a", t = "aa"
 * 输出：""
 *
 * 2、字符串排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串 。
 * 示例 1：
 * 输入: s1 = "ab", s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入: s1= "ab", s2 = "eidboaoo"
 * 输出: False
 *
 * 3、最长无重复子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 4、长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。找出该数组中满足其和 ≥ target 的长度最小的连续子数组 
 * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 */

public class slidingwindow {
    // 1、最小覆盖子串
    static String minWindow(String s, String t) {
        char[] schars = s.toCharArray();
        int start = 0, minLen = Integer.MAX_VALUE, n = s.length(), validKeys = 0, count;
        int left = 0, right = 0;
        Map<Character, Integer> window = new HashMap<>(); // 保存窗口内对目标t字符的覆盖次数
        Map<Character, Integer> tmap = new HashMap<>(); // 目标t字符的出现次数
        for (char c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }
        while (right < n) { // 寻找可行解
            if (tmap.containsKey(schars[right])) { // 发现目标t字符
                count = window.getOrDefault(schars[right], 0) + 1;
                window.put(schars[right], count);
                if (count == tmap.get(schars[right])) validKeys++; // 完成一个字符的覆盖
            }
            right++;
            while (validKeys == tmap.size()) { // 缩小子串确定最小
                if (right - left < minLen) { // 记录最优解
                    start = left;
                    minLen = right - left;
                }
                if (tmap.containsKey(schars[left])) { // 移除目标t字符
                    count = window.getOrDefault(schars[left], 1) - 1;
                    window.put(schars[left], count);
                    if (count < tmap.get(schars[left])) validKeys--; // 此字符覆盖不了
                }
                left++;
            }
        }
        if (minLen == Integer.MAX_VALUE) return "";
        return s.substring(start, start + minLen);
    }

    // 2、字符串排列（固定长度窗口）
    static boolean checkInclusion(String s1, String s2) {
        char[] s2chars = s2.toCharArray();
        int n = s2chars.length, validKeys = 0, count;
        int left = 0, right = 0;
        Map<Character, Integer> window = new HashMap<>(); // 保存窗口内对目标s1字符的覆盖次数
        Map<Character, Integer> map = new HashMap<>(); // 目标s1字符的出现次数
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        while (right < n) { // 寻找可行解
            if (map.containsKey(s2chars[right])) { // 发现目标s1字符
                count = window.getOrDefault(s2chars[right], 0) + 1;
                window.put(s2chars[right], count);
            }
            right++;
            while (right - left >= s1.length()) { // 保持窗口大小
                if (map.equals(window)) return true; // 无法优化比较复杂度
                if (map.containsKey(s2chars[left])) { // 移除目标s1字符
                    count = window.getOrDefault(s2chars[left], 1) - 1;
                    window.put(s2chars[left], count);
                }
                left++;
            }
        }
        return false;
    }

    // 3、最长无重复子串
    static int lengthOfLongestSubstring(String s) {
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

    // 4、长度最小的子数组
    static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length, len, minLen = n + 1, winSum = 0;
        int left = 0, right = 0;
        while (right < n) {
            winSum += nums[right];
            right++;
            while (winSum >= target) {
                len = right - left;
                if (len < minLen) minLen = len;
                winSum -= nums[left];
                left++;
            }
        }
        return minLen > n ? 0 : minLen;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));

        String s1 = "trinit", s2 = "dinitylhydrazinetrinit";
        System.out.println(checkInclusion(s1, s2));

        String ss = "pwwkew";
        System.out.println(lengthOfLongestSubstring(ss));

        int[] nums = new int[]{2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));
    }
}
