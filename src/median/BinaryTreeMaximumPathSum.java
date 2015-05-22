package median;
import util.TreeNode;

/*
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 * Example
 * Given the below binary tree:
  1
 / \
2   3
return 6.
*/

public class BinaryTreeMaximumPathSum {
	/**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxPathSum(TreeNode root) {
        // write your code here
        maxSum = root.val; 
        sideSum(root); 
        return maxSum; 
    }
    public int maxSum; 
    
    public int sideSum(TreeNode root){
        if (root==null) return 0; 
        int lsum = sideSum(root.left); 
        int rsum = sideSum(root.right);
        int ret = Math.max(lsum, rsum)+root.val; 
        ret = Math.max(ret, root.val); 
        maxSum = Math.max(maxSum, ret); 
        maxSum = Math.max(maxSum, lsum+rsum+root.val); 
        return ret; 
    }

}
