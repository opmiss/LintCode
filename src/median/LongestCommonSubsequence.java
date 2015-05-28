package median;

/*
 * Given two strings, find the longest comment subsequence (LCS).
 * Your code should return the length of LCS.
 * Example
 * For "ABCD" and "EDCA", the LCS is "A" (or D or C), return 1
 * For "ABCD" and "EACB", the LCS is "AC", return 2
 */

public class LongestCommonSubsequence {
	/**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        int M = A.length(); 
        int N = B.length(); 
        int[][] dp = new int[N+1][M+1];
        for (int i=1; i<=N; i++){
            for (int j=1; j<=M; j++){ 
                int l = Math.max(dp[i-1][j], dp[i][j-1]); 
                if (B.charAt(i-1)==A.charAt(j-1)){
                    dp[i][j] = Math.max(l, dp[i-1][j-1]+1); 
                }
                else dp[i][j]=l; 
            }
        }
        return dp[N][M]; 
    }

}
