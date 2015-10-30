package hard;
import java.util.*;

public class DataStreamMedian {
	 /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        // write your code here
        int n = nums.length; 
        if (n==0) return new int[]{}; 
        if (n==1) return new int[]{nums[0]};
        int heapsize = (n+1)/2; 
        PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(heapsize, Collections.reverseOrder()); 
        PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(heapsize); 
        int[] res = new int[n]; 
        res[0] = nums[0]; 
        maxheap.add(nums[0]);
        
        for (int i=1; i<n; i++){
            if (nums[i] < res[i-1]){
                maxheap.add(nums[i]); 
            }
            else {
                minheap.add(nums[i]); 
            }
            if (maxheap.size()>minheap.size()+1){
                minheap.add(maxheap.poll()); 
            }
            else if (maxheap.size()<minheap.size()){
                maxheap.add(minheap.poll()); 
            }
            res[i] = maxheap.peek(); 
        }
        return res; 
    }
}
