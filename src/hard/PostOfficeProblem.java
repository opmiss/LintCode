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
        Element[][] dp = new Element[n+1][k+1]; 
        for (int i=0; i<=n; i++){
            for (int j=0; j<=i&&j<=k; j++){
                if (i==0) {
                    dp[i][j] = new Element(0, -1); 
                    continue; 
                }
                if (j==0){
                    dp[i][j] = new Element(Integer.MAX_VALUE, -1); 
                    continue; 
                }
                if (i==j){
                    dp[i][j] = new Element(0, i-1);
                    continue; 
                }
                Element in = dp[i-1][j-1]; 
                Element out = dp[i-1][j];
                int dis_in, dis_out; 
                if (in.id<0){ // j==1
                    dis_in=0; 
                    for (int h=0; h<i-1; h++){
                        dis_in+=A[i-1]-A[h]; 
                    }
                }
                else {
                    dis_in = in.dis; 
                    for (int h=i-2; h>in.id; h--){
                        int right = A[i-1]-A[h]; 
                        int left = A[h]-A[in.id]; 
                        if (right > left) break; 
                        dis_in -= (left-right); 
                    }
                }
                dis_out = out.dis+A[i-1]-A[out.id];
                if (dis_in>dis_out){
                    dp[i][j] = new Element(dis_out, out.id); 
                }
                else {
                    dp[i][j] = new Element(dis_in, i-1); 
                }
            }
        }
        //print out elements 
        for (int i=0; i<=n; i++){
            for (int j=0; j<=i&&j<=k; j++){
            	System.out.print("("+dp[i][j].dis+", "+dp[i][j].id+")");
            }
            System.out.println(); 
        }
        return dp[n][k].dis; 
    }
    
    
    public static void main(String[] args){
    	int[] A = new int[]{1,3,5,7,10}; 
    	PostOfficeProblem pop = new PostOfficeProblem(); 
    	System.out.println(pop.postOffice(A, 1)); 
    }
}
