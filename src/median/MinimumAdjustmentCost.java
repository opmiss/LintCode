package median;
import java.util.*; 

/* 
 * Given an integer array, adjust each integers so that 
 * the difference of every adjacent integers are not greater than a given number target.
 * If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|
 * Example
 * Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal.
 * Return 2.
 * Note
 * You can assume each number in the array is a positive integer and not greater than 100
*/

public class MinimumAdjustmentCost {
	/**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        // write your code here
        int n = A.size(); 
        int max = 0; 
        for (Integer a:A) max=(max<a)?a:max; 
        int[][] dp = new int[n][max+1]; 
        for (int i=0; i<n; i++){
            if (i==0){
                for (int j=0; j<=max; j++){
                    dp[i][j] = Math.abs(A.get(i)-j); 
                }
                continue; 
            }
            for (int j=0; j<=max; j++){
                int o = dp[i-1][j]; 
                for (int k=j-target; k<=j+target; k++){
                    if (k>=0&&k<=max) o=(o<dp[i-1][k])?o:dp[i-1][k]; 
                }
                dp[i][j] = Math.abs(A.get(i)-j)+o;  
            }
        }
        int cost=Integer.MAX_VALUE; 
        for (int j=0; j<=max; j++){
            cost = (cost<dp[n-1][j])?cost:dp[n-1][j];   
        }
        return cost; 
    }

}
