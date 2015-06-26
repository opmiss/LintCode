package median;

/*
 * Given n, how many structurally unique BSTs (binary search trees) that store values 1...n?
 * Example
 * Given n = 3, there are a total of 5 unique BST's.
*/

public class UniqueBinarySearchTrees {
	/**
     * @paramn n: An integer
     * @return: An integer
     */
	public int numTrees(int n) {
        // write your code here
        if (n<3) return n; 
        int[] dp = new int[n]; 
        dp[0] = 1; 
        dp[1] = 2; 
        return solve(n, dp); 
    }
    
    int solve(int n, int[] dp){
        if (dp[n-1]>0) return dp[n-1]; 
        int ret = 0; 
        for (int i=0; i<n; i++){
        	if (i==0||i==n-1) { 
        	    ret+=solve(n-1, dp); 
        	    continue; 
        	}
        	int left = solve(i, dp); 
        	int right = solve(n-i-1, dp); 
        	ret+=left*right; 
        }
        dp[n-1] = ret; 
        return dp[n-1]; 
    }
    
    public static void main(String[] args){
    	UniqueBinarySearchTrees ubt = new UniqueBinarySearchTrees(); 
    	System.out.println(ubt.numTrees(3)); 
    }
}
