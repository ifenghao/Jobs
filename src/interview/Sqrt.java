package interview;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by zfh on 2017/8/29.
 */
public class Sqrt {
    static double sqrt1(double a, int p) {
        double k = 1.0, epsilon = 0.1;
        while (p-- > 0) epsilon *= 0.1;
        while (Math.abs(k * k - a) > epsilon) {
            k = (k + a / k) / 2;
        }
        return k;
    }

    static double sqrt2(double a, int p) {
        double left = 0.0, right = a, epsilon = 0.1;
        double mid, tmp;
        while (p-- > 0) epsilon *= 0.1;
        while (true) {
            mid = left + (right - left) / 2;
            tmp = mid * mid - a;
            if (Math.abs(tmp) <= epsilon) break;
            if (tmp > 0) right = mid;
            else if (tmp < 0) left = mid;
            else return mid;
        }
        return mid;
    }

    static double rootn(double a, int r, int p) {
        double k = 1.0, epsilon = 0.1, tmp;
        while (p-- > 0) epsilon *= 0.1;
        while (true) {
            tmp = Math.pow(k, r - 1);
            if (Math.abs(tmp * k - a) < epsilon) break;
            k = ((r - 1) * tmp * k + a) / (r * tmp);
        }
        return k;
    }

    static String rootnBig(String as, int r, int p) {
        BigDecimal a = new BigDecimal(as), tmp;
        BigDecimal k = new BigDecimal("1.0"), e = new BigDecimal("0.1");
        for (int i = 1; i < p; i++)
            e = e.divide(BigDecimal.TEN, i + 1, BigDecimal.ROUND_HALF_EVEN);
        while (true) {
            tmp = k.pow(r - 1);
            if (tmp.multiply(k).subtract(a).abs().compareTo(e) < 0) break;
            k = k.add(a.subtract(tmp.multiply(k)).divide(tmp.multiply(new BigDecimal(r)), p, BigDecimal.ROUND_HALF_EVEN));
        }
        return k.toString();
    }

    public static void main(String[] args) {
        System.out.println(sqrt1(2, 10));
        System.out.println(sqrt2(2, 10));
        System.out.println(rootn(2, 3, 10));
        System.out.println(rootnBig(2 + "", 3, 10));
    }
}
