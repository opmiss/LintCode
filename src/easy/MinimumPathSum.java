package easy;

/*
 * Given a m x n grid filled with non-negative numbers, 
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Example
 * Note
 * You can only move either down or right at any point in time.
 */

public class MinimumPathSum {
	/**
     * @param grid: a list of lists of integers.
     * @return: An integer, minimizes the sum of all numbers along its path
     */
	public int minPathSum(int[][] grid) {
        // write your code here
        int n = grid.length; 
        if (n==0) return 0; 
        int m = grid[0].length; 
        if (m==0) return 0; 
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (i==0&&j==0) continue; 
                else if (i==0) grid[i][j]+=grid[i][j-1]; 
                else if (j==0) grid[i][j]+=grid[i-1][j]; 
                else
                 grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]); 
            }
        }
        return grid[n-1][m-1]; 
    }
}
