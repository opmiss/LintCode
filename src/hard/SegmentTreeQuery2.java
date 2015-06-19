package hard;
import util.*; 

/* For an array, we can build a SegmentTree for it, 
 * each node stores an extra attribute count to denote the number of elements in the the array which value is between interval start and end. 
 * (The array may not fully filled by elements)
 * Design a query method with three parameters root, start and end, 
 * find the number of elements in the in array's interval [start, end] by the given root of value SegmentTree.
 */

public class SegmentTreeQuery2 {
	/**
     *@param root, start, end: The root of segment tree and 
     *                         an segment / interval
     *@return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if (root==null || start>end || start > root.end || end < root.start||root.count==0) 
            return 0; 
        if (start<=root.start && end >=root.end) return root.count;
        int mid = (root.start+root.end)/2; 
        if (mid>end)
            return query(root.left, start, end); 
        if (mid<start)
            return query(root.right, start, end); 
        return query(root.left, start, mid) + query(root.right, mid+1, end); 
    }

}
