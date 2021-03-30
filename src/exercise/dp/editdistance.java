package exercise.dp;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */

public class editdistance {
    static int solution(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int m = chars1.length, n = chars2.length;
        int[][] dp = new int[m + 1][n + 1]; // 将word2改成word1所用的最少操作
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int remove = dp[i][j - 1] + 1; // word1不包含chars2[j]，从word2删除，word2下标前移
                    int insert = dp[i - 1][j] + 1; // word1多出了chars1[i]，在word2插入，word1下标前移
                    int replace = dp[i - 1][j - 1] + 1; // word1替换word2，word1、word2统一下标前移
                    dp[i][j] = Math.min(Math.min(remove, insert), replace); // 取所有操作最小值
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "intention", word2 = "execution";
        System.out.println(solution(word1, word2));
    }
}
