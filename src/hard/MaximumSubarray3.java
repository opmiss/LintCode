package hard;
import java.util.*; 
/*
 * Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.
 * The number in each subarray should be contiguous.
 * Return the largest sum.
 * Example
 * Given [-1,4,-2,3,-2,3], k=2, return 8
 * Note
 * The subarray should contain at least one number
 */

public class MaximumSubarray3 {
	/**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(List<Integer> nums, int k) {
        // write your code
        int n = nums.size(); 
        if (n==0||k==0) return 0; 
        int[][] dp1 = new int[n][n];
        for (int s=0; s<n; s++){
            int endhere = nums.get(s);
            dp1[s][s] = endhere; 
            for (int e=s+1; e<n; e++){
                endhere = Math.max(endhere+nums.get(e), nums.get(e)); 
                dp1[s][e] = Math.max(endhere, dp1[s][e-1]); 
            }
        }
        int[][] dp2 = new int[n][k];
        for (int i=0; i<n; i++){
            for (int j=0; j<=i && j<k; j++){
                if (j==0){
                    dp2[i][0] = dp1[0][i];
                    continue; 
                }
                else if (i==j){
                    for (int e=0; e<=i; e++)
                        dp2[i][j] +=nums.get(e);
                    continue; 
                }
                else {
                    int m = Integer.MIN_VALUE;  
                    for (int x = j-1; x<i; x++){
                        m = Math.max(m, dp2[x][j-1]+dp1[x+1][i]);
                    }
                    dp2[i][j] = m; 
                }
            }
        }
        return dp2[n-1][k-1]; 
    }
	
    public static void main(String[] args){
    	MaximumSubarray3 ms = new MaximumSubarray3(); 
    	Integer[] nums = new Integer[]{-3, 5, -6, 2, -1, 2}; 
    	System.out.println(ms.maxSubArray(Arrays.asList(nums), 2)); 
    }
   
}
