package hard;

import java.util.Arrays;
/* 
 * On one line there are n houses. 
 * Give you an array of integer means the the position of each house. 
 * Now you need to pick k position to build k post office, so that the sum distance of each house to the nearest post office is the smallest. 
 * Return the least possible sum of all distances between each village and its nearest post office.
 * Example
 * Given array a = [1,2,3,4,5], k = 2. return 3.
 * Challenge: Could you solve this problem in O(n^2) time 
*/

public class PostOfficeProblem { 
	/**
     * @param A an integer array
     * @param k an integer
     * @return an integer
     */
    class Element{
        public int dis; 
        public int id; 
        public Element(int d, int i){
            dis = d; 
            id = i; 
        }
    }
    public int postOffice(int[] A, int k) {
        // Write your code here
        int n = A.length; 
        if (n<=k) return 0; 
        if (k==0) return Integer.MAX_VALUE; 
        Arrays.sort(A); 
        Element[][] dp = new Element[k+1][n+1];
        for (int i=0; i<=k; i++){
        	for (int j=0; j<=n; j++){
        		System.out.println(i+", "+j);
        		if (i>=j) {
        			dp[i][j] = new Element(0, i-1);
        			continue; 
        		}
        		if (i==0){
        			if (j<n) {
        				int dis = dp[i][j-1].dis+(A[j]-A[j-1])*j;
        				dp[i][j] = new Element(dis, -1); 
        			}
        			else {
        				dp[i][j] = new Element(Integer.MAX_VALUE, -1);  
        			}
        			continue; 
        		}
        		int pid = dp[i][j-1].id; 
        		int dis = Integer.MAX_VALUE;
        		int last = pid; 
        		for (int start = pid; start<j; start++){ 
        			int td = dp[i-1][start].dis+calc(start, j, A); 
        			if (dis>=td) {
        				dis = td;
        				last = start; 
        			}
        		}
        		dp[i][j] = new Element(dis, last); 	
        	}
        }
        return dp[k][n].dis; 
    }
    
    public int calc(int start, int end, int[] A){
    	int dis = 0;
    	if (end == A.length){
    		for (int i=start+1; i<end; i++){
    			dis+=A[i]-A[start]; 
    		}
    		return dis; 
    	}
    	for (int i=start+1; i<end; i++ ){
    		int left = A[i]-A[start]; 
    		int right = A[end] - A[i]; 
    		dis+=(left<right)?left:right; 
    	}
    	return dis; 
    }
    
    public static void main(String[] args){
    	int[] A = new int[]{1,3,5,7,10}; 
    	PostOfficeProblem pop = new PostOfficeProblem(); 
    	System.out.println(pop.postOffice(A, 3)); 
    }
}
