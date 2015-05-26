package hard;
/*
 * Given n distinct positive integers, integer k (k <= n) and a number target.
 * Find k numbers where sum is target. Calculate how many solutions there are?
 * Example
 * Given [1,2,3,4], k = 2, target = 5.
 * There are 2 solutions: [1,4] and [2,3].
 * Return 2.
 */
public class kSum {
	/**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        // write your code here
        int[][] dp = new int[k+1][target+1];
        dp[0][0]=1; 
        int K = k; 
        for (int a:A){
            for (int t=target; t>0; t--){
                for (k=K; k>0; k--){
                    int p = t-a; 
                    if (p<0||dp[k-1][p]==0) continue; 
                    dp[k][t] += dp[k-1][p];
                }
            }
        }
        return dp[K][target]; 
    }
}
