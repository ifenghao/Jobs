package exercise.num;

public class bitoperation {
    // 获取最低位的1，其余位都为0
    static int getLowestBit(int x) {
        return x & (-x);
    }

    // 去除最低位的1，其余位保持不变
    static int removeLowestBit(int x) {
        return x & (x - 1);
    }

    // 求二进制表示中1的数量
    static int binary1Count(int x) {
        int count = 0;
        while (x != 0) {
            x = removeLowestBit(x);
            count++;
        }
        return count;
    }

    // 数组有一个数字出现了一次，其他数字出现了两次，求这个数字
    static int num1Appear1Other2(int[] a) {
        int num = 0;
        for (int i = 0; i < a.length; i++) {
            num ^= a[i];
        }
        return num;
    }

    static void binaryBitAdd(int[] sum, int x) {
        int i = sum.length - 1;
        while (x != 0) {
            int lowestBit = x & 1;
            sum[i] += lowestBit;
            x = x >> 1;
            i--;
        }
    }

    // 数组有一个数字出现了一次，其他数字出现了三次，求这个数字
    static int num1Appear1Other3(int[] a) {
        int[] bitSum = new int[32];
        for (int i = 0; i < a.length; i++) {
            binaryBitAdd(bitSum, a[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if (bitSum[i] % 3 == 0) {
                sb.append(0);
            } else {
                sb.append(1);
            }
        }
        String num = sb.toString();
        return Integer.parseInt(num, 2);
    }

    // 数组有两个不同数字出现了一次，其他数字出现了两次，求这两个数字
    static int[] num2Appear1Other2(int[] a) {
        int num2xor = 0;
        for (int i = 0; i < a.length; i++) {
            num2xor ^= a[i];
        }
        // 用某个为1的位一定可将两个不同数字分开到两个子数组里，而出现两次的数字一定都在同一个子数组里
        int lowestBit = getLowestBit(num2xor); // 这里选择最低位
        int num1 = 0, num2 = 0;
        for (int i = 0; i < a.length; i++) {
            int splitFlag = a[i] & lowestBit; // 分成两个子数组标志
            if (splitFlag == 0) {
                num1 ^= a[i];
            } else {
                num2 ^= a[i];
            }
        }
        return new int[]{num1, num2};
    }

    // 数组有三个不同数字出现了一次，其他数字出现了两次，求这三个数字
    static int[] num3Appear1Other2(int[] a) {
        int num3xor = 0;
        for (int i = 0; i < a.length; i++) {
            num3xor ^= a[i];
        }
        // 三个数互不相等，那么三者异或结果某一位一定为1，且两个数在此位相同另一个数不同，先找出不同的数
        int lowestBit = getLowestBit(num3xor);
        int num1 = 0, num23xor = 0;
        for (int i = 0; i < a.length; i++) {
            int splitFlag = a[i] & lowestBit;
            if (splitFlag == 1) { // 此位为1的一定是那个不同的数
                num1 ^= a[i];
            } else {
                num23xor ^= a[i];
            }
        }
        // 排除这个已经找到的数，剩下的两个数可以用num2Appear1Other2解决
        int lowestBit2 = getLowestBit(num23xor);
        int num2 = 0, num3 = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == num1) continue;
            int splitFlag = a[i] & lowestBit2;
            if (splitFlag == 0) {
                num2 ^= a[i];
            } else {
                num3 ^= a[i];
            }
        }
        return new int[]{num1, num2, num3};
    }

    public static void main(String[] args) {
        int[] a = {2, 4, 3, 6, 3, 2, 5, 6, 5};
        System.out.println(num1Appear1Other2(a)); // 4

        a = new int[]{2, 4, 3, 2, 3, 2, 5, 3, 5, 5};
        System.out.println(num1Appear1Other3(a)); // 4

        a = new int[]{2, 4, 3, 6, 3, 2, 5, 5};
        int[] nums = num2Appear1Other2(a);
        System.out.println(nums[0] + " " + nums[1]); // 4 6

        a = new int[]{1, 2, 4, 3, 6, 3, 2, 5, 5};
        nums = num3Appear1Other2(a);
        System.out.println(nums[0] + " " + nums[1] + " " + nums[2]); // 1 4 6
    }
}
