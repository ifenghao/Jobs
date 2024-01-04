package exercise.twopointer;

// 递增排序数组和为target的两个数字
// 双指针分别从头、尾进行遍历
public class sortarraytargetsum {
    static int[] solution(int[] a, int target) {
        int lo = 0;
        int hi = a.length - 1;
        int[] res = new int[2];
        while (lo < hi) {
            if (a[lo] + a[hi] == target) {
                res[0] = a[lo];
                res[1] = a[hi];
                break;
            } else if (a[lo] + a[hi] < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1,2,4,7,11,15};
        int[] res = solution(a, 15);
        System.out.println(res[0] + " " + res[1]);
    }
}
