package exercise.dp;

public class stock {
    static void maxProfit1(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            System.out.println(0);
            return;
        }
        int profit = 0, buy = prices[0], tmp;
        for (int i = 1; i < n; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                tmp = prices[i] - buy;
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

    static void maxProfitK(int[] prices) {
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        maxProfitInf(prices);
    }
}
