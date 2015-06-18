package hard;

public class MaximumGap {
	 /**
     * @param nums: an array of integers
     * @return: the maximum difference
     */
    class Bin{
        public int min, max;
        public Bin(int num){
            min=max=num; 
        }
        public void add(int num){
            max = (max<num)?num:max; 
            min = (min>num)?num:min; 
        }
    } 
    
    public int maximumGap(int[] nums) {
        // write your code here
        if (nums.length<2) return 0;
        float max = nums[0], min = nums[0];
        for (int i=1; i<nums.length; i++){
            max = (max<nums[i])?nums[i]:max; 
            min = (min>nums[i])?nums[i]:min; 
        }
        float mingap = (max-min)/(nums.length-1);
        Bin[] bins = new Bin[nums.length];
        for (int i=0; i<nums.length; i++){
            int bid = (int)((nums[i]-min)/mingap); 
            if (bins[bid]==null){
                bins[bid] = new Bin(nums[i]); 
            }
            else {
                bins[bid].add(nums[i]); 
            }
        }
        int prev = bins[0].max, cur, maxgap=(int)mingap, gap; 
        for (int i=1; i<nums.length; i++){
            if (bins[i]==null) continue; 
            cur = bins[i].min; 
            gap = cur-prev; 
            maxgap = (maxgap<gap)?gap:maxgap; 
            prev = bins[i].max; 
        }
        return maxgap; 
    }
}
