package hard;
import java.util.*;

/*
 * There is an integer matrix which has the following features:
 * The numbers in adjacent positions are different.
 * The matrix has n rows and m columns. 
 * For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
 * For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].
 * We define a position P is a peek if A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1].
 * Find a peak element in this matrix. Return the index of the peak.
 * Example
 * Given a matrix:
   [
  	[1 ,2 ,3 ,6 ,5],
  	[16,41,23,22,6],
  	[15,17,24,21,7],
  	[14,18,19,20,10],
  	[13,14,11,10,9]
   ]
   return index of 41 (which is [1,1]) or index of 24 (which is [2,2])
*/

public class FindPeakElement2 {
	/**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        // write your code here
        pos[0]=1; 
        pos[1]=1; 
        for (int i=0; i<A.length+A[0].length; i++){
            if (isPeak(A)) break; 
        }
        ArrayList<Integer> ret = new ArrayList<Integer>(); 
        ret.add(pos[0]);
        ret.add(pos[1]);
        return ret; 
    }
    int[] pos = new int[2]; 
    public boolean isPeak(int[][] A){
        boolean top = A[pos[0]][pos[1]]>A[pos[0]+1][pos[1]]; 
        boolean button = A[pos[0]][pos[1]]>A[pos[0]-1][pos[1]]; 
        boolean left = A[pos[0]][pos[1]]>A[pos[0]][pos[1]+1]; 
        boolean right = A[pos[0]][pos[1]]>A[pos[0]][pos[1]-1]; 
        if (top&&button&&left&&right) return true; 
        int max = Math.max(A[pos[0]+1][pos[1]], A[pos[0]-1][pos[1]]);
        max = Math.max(max, A[pos[0]][pos[1]+1]);
        max = Math.max(max, A[pos[0]][pos[1]-1]);
        if (max==A[pos[0]+1][pos[1]]) pos[0]++; 
        else if (max==A[pos[0]-1][pos[1]]) pos[0]--;
        else if (max==A[pos[0]][pos[1]+1]) pos[1]++; 
        else pos[1]--; 
        return false; 
    } 

}
