package exercise.backtrace;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 示例 1：
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 */
public class regexmatch {
    static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean firstMatch = s.length() > 0 &&
                (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'); // 第1个字符是否匹配（p.charAt(0)出现1次）
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || // p.charAt(0)出现0次，即跳过p前2字符后与s匹配
                    (firstMatch && isMatch(s.substring(1), p)); // p.charAt(0)出现>1次，即p继续和s第1字符之后的匹配
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1)); // 后续字符是否匹配
        }
    }

    static boolean isMatchDP(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();
        boolean[][] dp = new boolean[len1 + 1][len2 + 1]; // s的前i个与p的前j个是否匹配
        dp[0][0] = true;
        if (len1 == 0 && len2 == 0) return true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (p.charAt(j - 1) != '*') { // 只出现一次匹配
                    if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'))
                        dp[i][j] = dp[i - 1][j - 1]; // 当前字符匹配，取前i-1,j-1结果
                } else { // 出现0次或多次匹配
                    dp[i][j] = dp[i][j - 2]; // 出现0次，即可将j-1和j直接去掉
                    if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'))
                        dp[i][j] = dp[i][j] || dp[i - 1][j]; // 前1字符匹配，还要考虑前i-1,j出现>1次结果
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s = "aab", p = "c*a*b";
        System.out.println(isMatch(s, p));
        System.out.println(isMatchDP(s, p));
    }
}
