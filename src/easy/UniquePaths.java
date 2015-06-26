package easy;

public class UniquePaths {
	 /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here 
        if (m==1||n==1) return 1; 
        int[][] numPaths = new int[m][n];
        for (int j=0; j<n; j++) numPaths[0][j]=1; 
        for (int i=1; i<m; i++){
            numPaths[i][0]=1; 
            for (int j=1; j<n; j++){
                numPaths[i][j] = numPaths[i-1][j]+numPaths[i][j-1]; 
            }
        }
        return numPaths[m-1][n-1]; 
    }

}
