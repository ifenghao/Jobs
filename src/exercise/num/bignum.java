package exercise.num;

import java.util.Arrays;

/**
 * 用String表示大数的加减乘
 * 通过转化为相应的char数组进行逐位加减，将进位/借位考虑到下一位的计算中
 * 乘法计算中，直接保存每一位整数结果，最后统一处理进位
 */

public class bignum {
    static String sum(String x, String y) {
        char[] longNum = x.length() > y.length() ? x.toCharArray() : y.toCharArray();
        char[] shortNum = x.length() > y.length() ? y.toCharArray() : x.toCharArray();
        int nl = longNum.length, ns = shortNum.length;
        char[] res = new char[nl];
        int takeOver = 0, j;
        int elementSum;
        for (int i = nl - 1; i >= 0; i--) {
            j = i - nl + ns;
            elementSum = (longNum[i] - '0') + takeOver;
            if (j >= 0) {
                elementSum += shortNum[j] - '0';
            }
            if (elementSum >= 10) {
                elementSum %= 10;
                takeOver = 1;
            } else {
                takeOver = 0;
            }
            res[i] = (char) (elementSum + '0');
        }
        String resStr = new String(res);
        if (takeOver != 0) {
            resStr = "1" + resStr;
        }
        return resStr;
    }

    static int compare(String x, String y) {
        int nx = x.length(), ny = y.length();
        if (nx != ny) return nx > ny ? 1 : -1;
        for (int i = 0; i < nx; i++) {
            if (x.charAt(i) != y.charAt(i))
                return x.charAt(i) > y.charAt(i) ? 1 : -1;
        }
        return 0;
    }

    static String substract(String x, String y) {
        int flag = compare(x, y);
        if (flag == 0) return "0";
        String sign = flag == 1 ? "" : "-";
        char[] largeNum = flag == 1 ? x.toCharArray() : y.toCharArray();
        char[] smallNum = flag == 1 ? y.toCharArray() : x.toCharArray();
        int nl = largeNum.length, ns = smallNum.length;
        char[] res = new char[nl];
        int takeOver = 0, j;
        int elementSubstract;
        for (int i = nl - 1; i >= 0; i--) {
            j = i - nl + ns;
            elementSubstract = (largeNum[i] - '0') - takeOver;
            if (j >= 0) {
                elementSubstract -= smallNum[j] - '0';
            }
            if (elementSubstract >= 0) {
                takeOver = 0;
            } else {
                elementSubstract += 10;
                takeOver = 1;
            }
            res[i] = (char) (elementSubstract + '0');
        }
        StringBuilder resStr = new StringBuilder();
        boolean isHeadZeros = true;
        for (char element: res) {
            if (element == '0' && isHeadZeros) continue;
            resStr.append(element);
            isHeadZeros = false;
        }
        return sign + resStr.toString();
    }

    static String multiply(String x, String y) {
        if (x.equals("0") || y.equals("0")) return "0";
        char[] chx = x.toCharArray();
        char[] chy = y.toCharArray();
        int lx = chx.length, ly = chy.length;
        int[] res = new int[lx + ly];
        for (int i = lx - 1; i >= 0; i--) {
            for (int j = ly - 1; j >= 0; j--) {
                res[i + j + 1] += (chx[i] - '0') * (chy[j] - '0');
            }
        }
        for (int i = lx + ly - 1; i > 0; i--) {
            res[i - 1] += res[i] / 10;
            res[i] %= 10;
        }
        StringBuilder resStr = new StringBuilder();
        boolean isHeadZeros = true;
        for (int element: res) {
            if (element == 0 && isHeadZeros) continue;
            resStr.append(element);
            isHeadZeros = false;
        }
        return resStr.toString();
    }

    public static void main(String[] args) {
        System.out.println(sum("123456", "99999"));
        System.out.println(123456 + 99999);
        System.out.println(sum("999999", "999999"));
        System.out.println(999999 + 999999);

        System.out.println(substract("123456", "999999"));
        System.out.println(123456 - 999999);
        System.out.println(substract("10000", "9999"));
        System.out.println(10000 - 9999);

        System.out.println(multiply("1234", "999999"));
        System.out.println(1234 * 999999);
        System.out.println(multiply("123", "456"));
        System.out.println(123 * 456);
    }
}
