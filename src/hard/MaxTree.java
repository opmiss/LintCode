package hard;
import util.*; 
import java.util.*;
/*
 * Given an integer array with no duplicates. A max tree building on this array is defined as follow:

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.
Example
Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:

    6
   / \
  5   3
 /   / \
2   0   1
Challenge
O(n) time and memory.
 */

public class MaxTree {
	public TreeNode maxTree(int[] A) {
        // write your code here
        if (A.length==0) return null; 
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(new TreeNode(A[0])); 
        for (int i=1; i<A.length; i++){
            if (A[i]<stack.peek().val){
                stack.push(new TreeNode(A[i])); 
            }
            else {
                TreeNode top = stack.pop(); 
                while (!stack.isEmpty() && stack.peek().val<A[i]){
                    stack.peek().right = top; 
                    top = stack.pop(); 
                }
                TreeNode cur = new TreeNode(A[i]); 
                cur.left = top; 
                stack.push(cur); 
            }
        }
        TreeNode root = stack.pop(); 
        while (!stack.isEmpty()){
            stack.peek().right = root; 
            root = stack.pop(); 
        }
        return root; 
    }

}
