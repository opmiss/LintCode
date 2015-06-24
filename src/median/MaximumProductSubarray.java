package median;

/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * Example
 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
 */

public class MaximumProductSubarray {
	/**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
        // write your code here
        int N = nums.length; 
        if (N==0) return 0; 
        if (N==1) return nums[0]; 
        int maxProd = nums[0]; 
        int maxHere = nums[0];
        int minHere = Math.min(0, nums[0]);
        for (int i=1; i<N; i++){
            if (nums[i]>=0){
                maxHere = Math.max(maxHere*nums[i], nums[i]); 
                minHere = minHere*nums[i]; 
            }
            else {
            	int _maxHere = maxHere; 
                maxHere = minHere*nums[i]; 
                minHere = Math.min(_maxHere*nums[i], nums[i]); 
            }
            maxProd = Math.max(maxProd, maxHere); 
        }
        return maxProd; 
    }
    
    public static void main(String[] args){
    	int[] nums = new int[]{-4, -3, -2}; 
    	MaximumProductSubarray mps = new MaximumProductSubarray(); 
    	System.out.println(mps.maxProduct(nums)); 
    }

}
