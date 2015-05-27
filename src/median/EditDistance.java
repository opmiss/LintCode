package median;
/*
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
 * (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 * Example
 * Given word1 = "mart" and word2 = "karma", return 3.
*/

public class EditDistance {
	/**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        int N = word1.length(); 
        int M = word2.length(); 
        if (N==0) return M; 
        if (M==0) return N; 
        int[][] dp = new int[N+1][M+1];
        dp[0][0] = 0;     
        for (int i=1; i<=N; i++) dp[i][0] = i; 
        for (int j=1; j<=M; j++) dp[0][j] = j; 
        for (int i=1; i<=N; i++){
            for (int j=1; j<=M; j++){
                int sol1 = Math.min(dp[i-1][j], dp[i][j-1])+1; 
                int sol2 = (word1.charAt(i-1)==word2.charAt(j-1))?dp[i-1][j-1]:(dp[i-1][j-1]+1); 
                dp[i][j] = Math.min(sol1, sol2); 
            }
        }
        return dp[N][M]; 
    }
}
