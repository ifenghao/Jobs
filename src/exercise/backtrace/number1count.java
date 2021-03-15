package exercise.backtrace;

/**
 * 统计从1~n的数字中1出现的次数
 * 暴力需要O(nlogn)，回溯法只需O(logn)
 * 以21345为例，可以分解为1~1345和1346~21345两部分，而1346~21345出现1数量与1~20000相同
 * 因此只需要回溯拆分最高位即可
 */
public class number1count {
    static int countBrute(String numStr) {
        int num = Integer.valueOf(numStr);
        int count = 0;
        for (int i = 1; i <= num; i++) {
            count += countOneNum(i);
        }
        return count;
    }

    static int countOneNum(int num) {
        int count = 0, remainder;
        while (num != 0) {
            remainder = num % 10;
            if (remainder == 1)
                count++;
            num /= 10;
        }
        return count;
    }

    static int countBacktrace(String numStr, int index) {
        if (index == numStr.length() - 1) { // 达到个位数
            int last = numStr.charAt(index) - '0';
            if (last == 0) return 0;
            return 1; // 个位数非0，最多只有一个1
        }
        int highest = numStr.charAt(index) - '0'; // 最高位
        int length = numStr.length() - index; // 当前数字长度
        // 1) 计算1346~21345出现1数量，等于1~20000出现1的数量
        // 最高位2可以带来1的数量
        int highestCount;
        if (highest == 0) {
            highestCount = 0;
        } else if (highest == 1) { // 最高位为1，带来的1的数量等于最高位之后的数字大小 + 1
            String afterHighest = numStr.substring(index + 1);
            highestCount = Integer.valueOf(afterHighest) + 1;
        } else { // 最高位>1，带来的1的数量等于10000~19999为10^4
            highestCount = pow10(length - 1);
        }
        // 之后整位0000可以带来1的数量: 固定一位为1，剩余位上0~9的排列组合4*10^3，最高位决定能排列几段(0000~9999、10000~19999)
        int lowerCount = highest * (length - 1) * pow10(length - 2);

        // 2) 递归计算1~1345出现1的数量
        int totalCount = highestCount + lowerCount + countBacktrace(numStr, index + 1);
        return totalCount;
    }

    static int pow10(int units) {
        int res = 1;
        while (units-- > 0)
            res *= 10;
        return res;
    }

    public static void main(String[] args) {
        String numStr = "21345";
        System.out.println(countBrute(numStr));
        System.out.println(countBacktrace(numStr, 0));
    }
}
