package hard;

/*
 * There are n coins in a line. Two players take turns to take a coin from one of the ends of the line 
 * until there are no more coins left. The player with the larger amount of money wins.
 * Could you please decide the first player will win or lose?
 * Example
 * Given array A = [3,2,2], return true.
 * Given array A = [1,2,4], return true.
 * Given array A = [1,20,4], return false.
 * Challenge
 * Follow Up Question:
 * If n is even. Is there any hacky algorithm that can decide whether first player will win or lose in O(1) memory and O(n) time?
*/ 
public class CoinsInLine {
	/**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    int[] val; 
    int[][] take; 
    int[][] sum; 
    public boolean firstWillWin(int[] values) {
        // write your code here
        val = values; 
        int n = values.length; 
        take = new int[n][n];
        sum = new int[n][n]; 
        
        for (int k=0; k<n; k++){
            for (int i=0; i<n-k; i++){
                if (k==0) {
                    sum[i][i] = val[i]; 
                    take[i][i] = val[i];
                }
                else {
                    int j = i+k; 
                    sum[i][j] = sum[i][j-1]+val[j]; 
                    take[i][j] = Math.max(
                        val[i]+sum[i+1][j]-take[i+1][j], 
                        val[j]+sum[i][j-1]-take[i][j-1]); 
                }
            }
        }
        return (take[0][n-1]>(sum[0][n-1]-take[0][n-1]));  
    }
}
