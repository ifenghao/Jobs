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

    public static void main(String[] args) {
        solution(10);
    }
}
