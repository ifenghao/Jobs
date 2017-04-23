package beauty;

/**
 * Created by zfh on 17-4-22.
 */
public class zuidaheshuzu {
    static void solution(int[] array, int[] tail, int[] sum) {
        tail[0] = sum[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            tail[i] = Math.max(array[i], tail[i - 1] + array[i]);
            sum[i] = Math.max(sum[i - 1], tail[i]);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-2, 5, 3, -6, 4, -8, 6};
        int length = array.length;
        int[] tail = new int[length];
        int[] sum = new int[length];
        solution(array, tail, sum);
        System.out.println(sum[length - 1]);
    }
}
