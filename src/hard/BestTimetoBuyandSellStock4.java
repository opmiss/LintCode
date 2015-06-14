package hard;

/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Example
 * Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.
*/

public class BestTimetoBuyandSellStock4 {
	/**
     * @param k: An integer
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int k, int[] prices) {
        // write your code here
        if (k==0) return 0; 
        if (k>=prices.length){
            return quickSolve(prices); 
        }
        int[] maxtillbuy = new int[k];
        int[] maxtillsell = new int[k];
        for (int j=0; j<k; j++) maxtillbuy[j]=Integer.MIN_VALUE; 
        for (int i=0; i<prices.length; i++){
            for (int j=k-1; j>0; j--){
                maxtillsell[j] = Math.max(maxtillbuy[j]+prices[i], maxtillsell[j]); 
                maxtillbuy[j] = Math.max(maxtillsell[j-1]-prices[i], maxtillbuy[j]); 
            }
            maxtillsell[0] = Math.max(maxtillbuy[0]+prices[i], maxtillsell[0]); 
            maxtillbuy[0] = Math.max(-prices[i], maxtillbuy[0]); 
        }
        return maxtillsell[k-1]; 
    }
    public int quickSolve(int[] prices){
        int ret = 0; 
        for (int i=1; i<prices.length; i++){
            if (prices[i]>prices[i-1]){
                ret+=prices[i]-prices[i-1]; 
            }
        }
        return ret; 
    }

}
