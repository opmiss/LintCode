package median;
import java.util.*; 

/*
 * Given an array of integers, find two non-overlapping subarrays which have the largest sum.
 * The number in each subarray should be contiguous.
 * Return the largest sum.
 * Example
 * For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], 
 * they both have the largest sum 7.
 * Note
 * The subarray should contain at least one number
*/

public class MaximumSubarray2 {
	/**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        int n=nums.size(); 
        if (n==0) return 0; 
        if (n==1) return nums.get(0); 
        if (n==2) return nums.get(0)+nums.get(1); 
        int[] maxfrom = new int[n]; 
        int maxhere = nums.get(n-1);
        maxfrom[n-1] = maxhere; 
        for (int i=n-2; i>=0; i--){
            maxhere = Math.max(maxhere+nums.get(i), nums.get(i)); 
            maxfrom[i] = Math.max(maxfrom[i+1], maxhere); 
        }
        maxhere = nums.get(0); 
        int maxtill = nums.get(0); 
        int maxsum = maxtill+maxfrom[1]; 
        for (int i=1; i<n-1; i++){
            maxhere = Math.max(maxhere+nums.get(i), nums.get(i)); 
            maxtill = Math.max(maxhere, maxtill); 
            maxsum = Math.max(maxsum, maxtill+maxfrom[i+1]); 
        }
        return maxsum; 
    }

}
