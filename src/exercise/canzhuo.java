package exercise;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 某餐馆有n张桌子，每张桌子有一个参数：a 可容纳的最大人数；
 * 有m批客人，每批客人有两个参数:b人数，c预计消费金额。
 * 在不允许拼桌的情况下，请实现一个算法选择其中一部分客人，使得总预计消费金额最大
 * 输入描述:
 * 输入包括m+2行。
 * 第一行两个整数n(1 <= n <= 50000),m(1 <= m <= 50000)
 * 第二行为n个参数a,即每个桌子可容纳的最大人数,以空格分隔,范围均在32位int范围内。
 * 接下来m行，每行两个参数b,c。分别表示第i批客人的人数和预计消费金额,以空格分隔,范围均在32位int范围内。
 * <p>
 * 输出描述:
 * 输出一个整数,表示最大的总预计消费金额
 * <p>
 * 输入例子:
 * 3 5
 * 2 4 2
 * 1 3
 * 3 5
 * 3 7
 * 5 9
 * 1 10
 * <p>
 * 输出例子:
 * 20
 */
public class canzhuo {
    static class Client implements Comparable {
        int num, cos;

        Client(int b, int c) {
            this.num = b;
            this.cos = c;
        }

        @Override
        public int compareTo(Object o) {
            if (this.cos == ((Client) o).cos) return this.num - ((Client) o).num;// 升序排列人数
            return ((Client) o).cos - this.cos;// 降序排列消费
        }
    }

    static long solution(int[] tables, Client[] clients) {
        int n = tables.length;
        int m = clients.length;
        boolean[] assigned = new boolean[n];
        long total = 0;
        Arrays.sort(tables);
        Arrays.sort(clients);// 贪心法，对于消费高的顾客一定要尽量安排，如果消费一样，要选择人数少的安排
        for (int i = 0; i < m; i++) {
            int k = binarySearch(tables, clients[i].num);// 对消费高的客人尽量安排餐桌，如果无法安排，k会等于n
            while (k < n && assigned[k]) ++k;// 对已经安排的餐桌应该找略大的餐桌安排
            if (k < n) {// 可以安排
                total += clients[i].cos;
                assigned[k] = true;
            }
        }
        return total;
    }

    static int binarySearch(int[] tables, int key) {
        int i = 0;
        int j = tables.length - 1;
        int mid;
        boolean hitted = false;
        while (i <= j) {
            mid = i + (j - i) / 2;
            if (tables[mid] == key) {
                i = mid;
                hitted = true;
                break;
            } else if (tables[mid] > key) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }// 退出循环时tables[i]>=key，而tables[j]<key
        while (hitted && i - 1 >= 0 && tables[i - 1] == tables[i]) --i;// 若相同则找最左边
        return i;
    }

    static int lowerBound(int[] tables, int key) {
        int i = 0, j = tables.length - 1, mid;
        while (i < j) {
            mid = i + ((j - i) >> 1);
            if (key <= tables[mid])
                j = mid;
            else
                i = mid + 1;
        }
        if (tables[i] < key) i++;
        return i;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] tables = new int[n];
        for (int i = 0; i < n; i++) {
            tables[i] = sc.nextInt();
        }
        Client[] clients = new Client[m];
        for (int i = 0; i < m; i++) {
            clients[i] = new Client(sc.nextInt(), sc.nextInt());
        }
        System.out.println(solution(tables, clients));
    }
}
