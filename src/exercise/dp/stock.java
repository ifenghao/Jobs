package exercise.dp;

/**
 * ref  http://blog.csdn.net/dr_unknown/article/details/51939121
 * 一次交易
 * 无穷次交易
 * 两次交易
 * k次交易
 */
public class stock {
    static void maxProfit1(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            System.out.println(0);
            return;
        }
        int profit = 0, min = prices[0], tmp;
        for (int i = 1; i < n; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                tmp = prices[i] - min;
                if (tmp > profit) {
                    profit = tmp;
                }
            }
        }
        System.out.println(profit);
    }

    static void maxProfitInf(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            System.out.println(0);
            return;
        }
        int profit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        System.out.println(profit);
    }

    static void maxProfit2(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            System.out.println(0);
            return;
        }
        int[] profitPre = new int[n];
        int[] profitPos = new int[n];
        int min = prices[0];// 从左向右取得i前最大利润
        for (int i = 1; i < n; i++) {
            if (prices[i] < min) min = prices[i];
            profitPre[i] = Math.max(profitPre[i - 1], prices[i] - min);
        }
        int max = prices[n - 1];// 从右向左取得i后最大利润
        for (int i = n - 2; i >= 0; i--) {
            if (prices[i] > max) max = prices[i];
            profitPos[i] = Math.max(profitPos[i + 1], max - prices[i]);
        }
        int profit = 0, tmp;
        for (int i = 0; i < n; i++) {
            tmp = profitPre[i] + profitPos[i];
            if (tmp > profit) profit = tmp;
        }
        System.out.println(profit);
    }

    static void maxProfitK(int[] prices, int k) {
        int n = prices.length;
        if (n <= 1) {
            System.out.println(0);
            return;
        }
        int[][] loc = new int[n][k + 1];
        int[][] glb = new int[n][k + 1];
        int tmp;
        for (int i = 1; i < n; i++) {
            tmp = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                loc[i][j] = Math.max(glb[i - 1][j - 1], loc[i - 1][j] + tmp);
                glb[i][j] = Math.max(loc[i][j], glb[i - 1][j]);
            }
        }
        System.out.println(glb[n - 1][k]);
    }

    static void maxProfitKImproved(int[] prices, int k) {
        int n = prices.length;
        if (n <= 1) {
            System.out.println(0);
            return;
        }
        if (k >= n) {
            maxProfitInf(prices);
            return;
        }
        int[] loc = new int[k + 1];
        int[] glb = new int[k + 1];
        int tmp;
        for (int i = 1; i < n; i++) {
            tmp = prices[i] - prices[i - 1];
            for (int j = k; j >= 1; j--) {
                loc[j] = Math.max(glb[j - 1], loc[j] + tmp);
                glb[j] = Math.max(loc[j], glb[j]);
            }
        }
        System.out.println(glb[k]);
    }

    static void maxProfitInfCool(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            System.out.println(0);
            return;
        }
        int buy = Integer.MIN_VALUE, pre_buy = 0, sell = 0, pre_sell = 0;
        for (int i = 0; i < n; i++) {
            pre_buy = buy;
            buy = Math.max(pre_sell - prices[i], pre_buy);
            pre_sell = sell;
            sell = Math.max(pre_buy + prices[i], pre_sell);
        }
        System.out.println(sell);
    }


    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        maxProfitInfCool(prices);
    }
}
