package exercise.backtrace;

public class kmp {
    // 是否匹配，返回匹配位置，否则返回-1
    static int match(String src, String pat) {
        int ns = src.length(), np = pat.length();
        int[] next = nexter(pat);
        int i = 0, j = 0;
        while (i < ns && j < np) {
            if (j == -1 || src.charAt(i) == pat.charAt(j)) {// 匹配成功
                i++;
                j++;
            } else {// 匹配失败跳转到pat的下一个位置，i不回溯
                j = next[j];
            }
        }
        if (j == np) return i - j;
        return -1;
    }

    static int[] nexter(String pat) {
        int np = pat.length();
        int[] next = new int[np];
        next[0] = -1;
        int k = -1, j = 0;
        while (j < np - 1) {
            if (k == -1 || pat.charAt(j) == pat.charAt(k)) {// 后缀和前缀相同
                j++;
                k++;
                if (pat.charAt(j) != pat.charAt(k)) next[j] = k;
                else next[j] = next[k];// 递归一层
            } else {
                k = next[k];
            }
        }
        return next;
    }

    static int matchTimes(String src, String pat) {
        int ns = src.length(), np = pat.length();
        int[] next = nexter(pat);
        int i = 0, j = 0;
        int cnt = 0;
        while (i <= ns - np) {
            while (i < ns && j < np) {
                if (j == -1 || src.charAt(i) == pat.charAt(j)) {
                    i++;
                    j++;
                } else {
                    j = next[j];
                }
            }
            if (j == np) cnt++;// 匹配成功一次
            j = 0;// i在下一个位置，j归零
        }
        return cnt;
    }

    public static void main(String[] args) {
        String src = "BBC ABCDAB ABCDABCDABDE";
        String pat = "CDAB";
        System.out.println(matchTimes(src, pat));
    }
}
