package hard;
import util.*; 

public class RemoveTreeNode {
	/**
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        // write your code here
        TreeNode dummyroot = new TreeNode(Integer.MAX_VALUE);
        dummyroot.left = root; 
        TreeNode cur = root, prev = dummyroot; 
        while (cur!=null){
            if (cur.val<value) {
                prev = cur; 
                cur = cur.right; 
            }
            else if (cur.val>value) {
                prev = cur; 
                cur = cur.left;
            }
            else break; 
        }
        if (cur==null) return root;
        if (prev.left==cur){
            insertNode(cur, cur.left, cur.right, true);
            prev.left = cur.left; 
        }
        else {
            insertNode(cur, cur.right, cur.left, false); 
            prev.right = cur.right; 
        }
        return dummyroot.left; 
    }
    
    public void insertNode(TreeNode dummyroot, TreeNode root, TreeNode node, boolean left){
        if (node==null) return; 
        TreeNode prev = dummyroot, cur = root; 
        while (cur!=null){
            if (cur.val<node.val) {
                prev = cur; 
                cur = cur.right; 
                left = false; 
            }
            else {
                prev = cur; 
                cur = cur.left;
                left = true; 
            }
        }
        if (left){
            prev.left = node; 
        }
        else {
            prev.right = node; 
        }
    }
}
