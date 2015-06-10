package hard;
import java.util.*;

public class LargestRectangle {
	/**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] height) {
        // write your code here
        int n = height.length; 
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i=0; i<=n; i++){
            int cur = (i==n)?0:height[i]; 
            if (stack.isEmpty() || cur>height[stack.peek()]){
                stack.push(i); 
            }
            else {
                int pid = stack.pop();
                int width = stack.isEmpty()?i:(i-stack.peek()-1);
                maxArea = Math.max(maxArea, height[pid]*width); 
                i--; 
            }
        }
        return maxArea; 
    }
}
