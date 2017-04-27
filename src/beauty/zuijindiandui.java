package beauty;

import java.util.*;

/**
 * Created by zfh on 17-4-26.
 */
public class zuijindiandui {
    static double dist(double[] a, double[] b) {
        double difx = a[0] - b[0];
        double dify = a[1] - b[1];
        return Math.sqrt(difx * difx + dify * dify);
    }

    static double distMin3(double[] a, double[] b, double[] c) {
        double distab = dist(a, b);
        double distac = dist(a, c);
        double distbc = dist(b, c);
        return Math.min(distab, Math.min(distac, distbc));
    }

    static double[][] getLeft(double[][] points, double bound, int mid, int lo) {
        int left = mid;
        double leftMin = points[mid][0] - bound;// 横坐标的左边界
        while (left - 1 >= lo && points[left - 1][0] > leftMin) --left;
        int len = mid - left;// 不包含mid点
        double[][] leftPoints = new double[len][2];
        System.arraycopy(points, left, leftPoints, 0, len);
        return leftPoints;
    }

    static double[][] getRight(double[][] points, double curMin, int mid, int hi) {
        int right = mid;
        double rightMax = points[mid][0] + curMin;// 横坐标的右边界
        while (right + 1 <= hi && points[right + 1][0] < rightMax) ++right;
        int len = right - mid + 1;// 包含mid点
        double[][] rightPoints = new double[len][2];
        System.arraycopy(points, mid, rightPoints, 0, len);
        return rightPoints;
    }

    static double minPair(double[][] points, int lo, int hi) {
        if (lo == hi - 1)// 递归到只有两个点时直接计算距离
            return dist(points[lo], points[hi]);
        if (lo == hi - 2)// 递归到只有三个点时不能再分，也直接计算距离
            return distMin3(points[lo], points[lo + 1], points[hi]);
        int mid = lo + (hi - lo) / 2;
        double curMin = Math.min(minPair(points, lo, mid), minPair(points, mid, hi));
        double[][] left = getLeft(points, curMin, mid, lo);// 找和mid点横坐标距离小于curMin的左边点
        double[][] right = getRight(points, curMin, mid, hi);// 找和mid点横坐标距离小于curMin的右边点
        Arrays.sort(right, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return (int) (o1[1] - o2[1]);// 按照纵坐标升序排列
            }
        });
        for (double[] lp : left) {// 每个左边点在右边点中比较
            int rmid = binarySearch(right, lp[1]);// 找到纵坐标距离最近的右边点
            int i = rmid - 1;
            double downMin = lp[1] - curMin;// 纵坐标的下边界
            while (i >= 0 && i < right.length && right[i][1] > downMin) {
                double tmpDist = dist(right[i], lp);
                if (tmpDist < curMin) curMin = tmpDist;
                --i;
            }
            int j = rmid;
            double upMax = lp[1] + curMin;// 纵坐标的上边界
            while (j >= 0 && j < right.length && right[j][1] < upMax) {
                double tmpDist = dist(right[j], lp);
                if (tmpDist < curMin) curMin = tmpDist;
                ++j;
            }
        }
        return curMin;
    }

    static int binarySearch(double[][] points, double key) {
        int i = 0;
        int j = points.length - 1;
        int mid;
        while (i <= j) {
            mid = i + (j - i) / 2;
            if (key == points[mid][1]) {
                return mid;
            } else if (key < points[mid][1]) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[][] points = new double[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextDouble();
            points[i][1] = sc.nextDouble();
        }
        Arrays.sort(points, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return (int) (o1[0] - o2[0]);// 按照横坐标升序排列
            }
        });
        System.out.println(minPair(points, 0, n - 1));
    }
}
