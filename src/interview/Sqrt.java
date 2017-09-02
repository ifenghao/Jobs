package interview;

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

    public static void main(String[] args) {
        System.out.println(sqrt1(2, 10));
        System.out.println(sqrt2(2, 10));
    }
}
