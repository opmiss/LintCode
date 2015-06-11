package hard;
import util.*; 
import java.util.*;

public class TreeIterator {
	//@param root: The root of binary tree.
    Stack<TreeNode> stack = new Stack<TreeNode>(); 
    public TreeIterator(TreeNode root) {
        // write your code here
        TreeNode cur = root; 
        while (cur!=null){
            stack.push(cur); 
            cur = cur.left; 
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        // write your code here
        return (!stack.isEmpty()); 
    }
    
    //@return: return next node
    public TreeNode next() {
        // write your code here
        TreeNode ret = stack.pop(); 
        TreeNode cur = ret.right; 
        while (cur!=null){
            stack.push(cur); 
            cur = cur.left; 
        }
        return ret; 
    }

}
