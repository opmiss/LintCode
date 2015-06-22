package median;
import util.*; 

public class SegmentTreeQuery {
	/**
     *@param root, start, end: The root of segment tree and 
     *                         an segment / interval
     *@return: The maximum number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        int ret = Integer.MIN_VALUE; 
        if (root==null||start>end||end<root.start||start>root.end) return ret; 
        if (start<=root.start&&end>=root.end) return root.max; 
        int m = (root.start+root.end)/2; 
        if (m>=end) return query(root.left, start, end); 
        if (m<start) return query(root.right, start, end);  
        return Math.max(
            query(root.left, start, m), 
            query(root.right, m+1, end)) ; 
    }

}
