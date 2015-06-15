package hard;
import util.*; 

/*
 * Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):
 * For query(start, end), return the sum from index start to index end in the given array.
 * For modify(index, value), modify the number in the given index to value
 * Have you met this question in a real interview? Yes
 * Example
 * Given array A = [1,2,7,8,5].
 * query(0, 2), return 10.
 * modify(0, 4), change A[0] from 1 to 4.
 * query(0, 1), return 6.
 * modify(2, 1), change A[2] from 7 to 1.
 * query(2, 4), return 14.
 * Note
 * We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.
 * Challenge
 * O(logN) time for query and modify.
*/

public class IntervalSum {
	/* you may need to use some attributes here */
    public SegmentTreeNode buildTree(int[] A, int start, int end){
        if (start>end) return null; 
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start==end){
            root.sum = A[start]; 
            return root; 
        }
        int mid = (start+end)/2; 
        root.left = buildTree(A, start, mid); 
        root.right = buildTree(A, mid+1, end); 
        root.sum = root.left.sum+root.right.sum; 
        return root; 
    }
    
    public void updateTree(SegmentTreeNode root, int id, int inc){
        if (root.start==root.end){
            if (root.start==id) root.sum+=inc; 
            return; 
        }
        root.sum+=inc; 
        int mid = (root.start+root.end)/2; 
        if (id<=mid) updateTree(root.left, id, inc); 
        else updateTree(root.right, id, inc); 
    }
    
    public long queryTree(SegmentTreeNode node, int start, int end){
        if (start<=node.start&&end>=node.end) return node.sum;
        if (start>end) return (long)0;
        int mid = (node.start+node.end)/2; 
        if (end<=mid) return queryTree(node.left, start, end); 
        if (start>mid) return queryTree(node.right, start, end);
        return queryTree(node.left, start, mid)+queryTree(node.right, mid+1, end); 
    }

    /**
     * @param A: An integer array
     */
    SegmentTreeNode root = null; 
    int[] array = null; 
    public 	IntervalSum(int[] A) {
        // write your code here
        root = buildTree(A, 0, A.length-1);
        array = A; 
    }
    
    /**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        // write your code here
        return queryTree(root, start, end); 
    }
    
    /**
     * @param index, value: modify A[index] to value.
     */
    public void modify(int index, int value) {
        // write your code here
        int inc = value-array[index]; 
        array[index] = value; 
        updateTree(root, index, inc); 
    }
}
