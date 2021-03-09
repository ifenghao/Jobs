package exercise.num;

/**
 * 用String表示大数的加减
 * 通过转化为相应的char数组进行逐位加减，将进位/借位考虑到下一位的计算中
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
        for (char element: res) {
            if (element != '0') resStr.append(element);
        }
        return sign + resStr.toString();
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
    }
}
