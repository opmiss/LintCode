package hard;
import java.util.*;
/* 
 * Given an integer array, find a subarray where the sum of numbers is between two given interval. 
 * Your code should return the number of possible answer.
 */

public class SubarraySum {
	/**
     * @param A an integer array
     * @param start an integer
     * @param end an integer
     * @return the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        // Write your code here
        if (A.length<end-start) return quickSolve(A, start, end); 
        
        Set<Integer> sol = new HashSet<Integer>(); 
        int sum = 0, res=0; 
        sol.add(sum); 
        Integer c; 
        for (int i=0; i<A.length; i++){
            sum+=A[i]; 
            for (int r=start; r<=end; r++){
                if (sol.contains(sum-r)) res++; 
            }
            sol.add(sum); 
        }
        return res; 
    }
    
    public int quickSolve(int[] A, int start, int end){
        int[] P = new int[A.length+1];
        P[0] = 0; 
        int res = 0; 
        for (int i=0; i<A.length; i++){
            P[i+1] = P[i]+A[i]; 
        }
        for (int s=0; s<A.length; s++){
            for (int e=s+1; e<=A.length; e++){
                int ss = P[e]-P[s]; 
                if (ss>=start&&ss<=end) res++; 
            }
        }
        return res; 
    }

}
