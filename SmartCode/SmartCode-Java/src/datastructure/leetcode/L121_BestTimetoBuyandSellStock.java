package datastructure.leetcode;

/**
 * link:https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 If you were only permitted to complete at most one transaction (ie, buy one and sell one
 share of the stock), design an algorithm to find the maximum profit.
 给一个数prices[]，prices[i]代表股票在第i天的售价，求出只做一次交易(一次买入和卖出)能得到的最大收益。
 Example 1:
 Input: [7, 1, 5, 3, 6, 4]
 Output: 5

 max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 Example 2:
 Input: [7, 6, 4, 3, 1]
 Output: 0

 In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 * 时间复杂度，O(n)
 * 空间复杂度，O(1)
 *
 * Created by wangdong on 17-7-5.
 */
public class L121_BestTimetoBuyandSellStock {

    /**
     * 思路：记录遇到的最小值，比较下一个值与最小值的差值and到目前为止最大差值
     *
     * 比如　[2, 4, 1, 3, 7, 4, 2, 5]
     *
     * i=1: minPrice=2,maxProfit=0;
     * i=2: minPrice=2,maxProfit=4-2=2;
     * i=3: minPrice=1,(1-2)>2? false;
     * i=4: minPrice=1, (3-1)>2? false;
     * i=5: minPrice=1, (7-1)>2? true, maxProfit=6;
     * i=6: minPrice=1, (4-1)>6? false;
     * i=7: minPrice=1, (2-1)>6? false;
     * i=8: minPrice=1, (5-1)>6? false;
     *
     * so, maxProfit=6
     *
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    /**
     * 思路：维护两个变量，一个是到目前为止最好的交易，一个是当天的最佳交易
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;

        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur + prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }

        return maxSoFar;
    }
}
