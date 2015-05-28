package median;

/*Given a sequence of integers, find the longest increasing subsequence (LIS).
 * 
 * You code should return the length of the LIS.
 * Example
 * For [5, 4, 1, 2, 3], the LIS  is [1, 2, 3], return 3
 * For [4, 2, 4, 5, 3, 7], the LIS is [4, 4, 5, 7], return 4
 * Challenge
 * Time complexity O(n^2) or O(nlogn)*/

public class LongestIncreasingSubsequence {
	/**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
	
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
    	int N = nums.length; 
    	if (N<2) return N; 
    	int[] dp = new int[N]; 
    	int ret = 0; 
    	for (int i=0; i<N; i++) dp[i]=1; 
    	for (int i=N-2; i>=0; i--){
    		for (int j=i+1; j<N; j++){
    			if (nums[i]<=nums[j]){
    				dp[i] = Math.max(dp[i], dp[j]+1); 
    			}
    		}
    		ret = Math.max(ret, dp[i]); 
    	}
    	return ret; 
    }
	
    public static void main(String[] args){
    	int[] nums = new int[]{5, 2, 8, 4, 3, 7};
    	LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence(); 
    	System.out.println(lis.longestIncreasingSubsequence(nums)); 
    }
}
