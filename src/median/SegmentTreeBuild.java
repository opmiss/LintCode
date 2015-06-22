package median;
import util.*; 

public class SegmentTreeBuild {
	/**
     *@param start, end: Denote an segment / interval
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int start, int end) {
        // write your code here
        if (start>end) return null;  
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start==end) return root; 
        int m = (start+end)/2; 
        root.left = build(start, m);
        root.right = build(m+1, end);
        return root; 
    }

}
