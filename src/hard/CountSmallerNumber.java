package hard;
import java.util.*;

public class CountSmallerNumber {
	class SegmentTreeNode{
        public int start, end;
        public int count; 
        public SegmentTreeNode left, right; 
        public SegmentTreeNode(int start, int end){
            this.start = start; 
            this.end = end; 
            count =0; 
        }
    }
    public SegmentTreeNode buildTree(int start, int end){
        if (start>end) return null; 
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start==end) return root; 
        int mid = (start+end)/2; 
        root.left = buildTree(start, mid); 
        root.right = buildTree(mid+1, end); 
        return root; 
    }
    
    public int insertQueryTree(SegmentTreeNode root, int num){
        if (root.start==root.end){
            if (num==root.start) ++root.count; 
            return 0; 
        }
        if ( num > root.end) return root.count;
        if ( num < root.start) return 0; 
        ++root.count; 
        int mid = (root.start+root.end)/2; 
        if (num<=mid) return insertQueryTree(root.left, num); 
        else return root.left.count+insertQueryTree(root.right, num); 
    }
     
    SegmentTreeNode root = null; 
   /**
     * @param A: An integer array
     * @return: Count the number of element before this element 'ai' is 
     *          smaller than it and return count number array
     */ 
    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        root = buildTree(0, 10000); 
        ArrayList<Integer> ret = new ArrayList<Integer>(); 
        for (int a:A){
            ret.add(insertQueryTree(root, a)); 
        }
        return ret; 
    }
}
