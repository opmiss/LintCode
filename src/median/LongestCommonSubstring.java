package median;

/*
 * Given two strings, find the longest common substring.
 * Return the length of it.
 * Example
 * Given A="ABCD", B="CBCE", return 2.
 * Note
 * The characters in substring should occur continuously in original string. This is different with subsequence.
 */

public class LongestCommonSubstring {
	
	/**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
	public int longestCommonSubstring(String A, String B) {
        // write your code here
        int M = A.length(); 
        int N = B.length(); 
        int[][] dp = new int[N+1][M+1];
        int maxLeng=0; 
        for (int i=1; i<=N; i++){
            for (int j=1; j<=M; j++){ 
                if (B.charAt(i-1)==A.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                    maxLeng = Math.max(maxLeng, dp[i][j]); 
                }
                else{
                    dp[i][j] = 0; 
                } 
            }
        }
        return maxLeng; 
    }
	public static void main(String[] args){
		LongestCommonSubstring lcs = new LongestCommonSubstring(); 
		System.out.println(lcs.longestCommonSubstring("www.lintcode.com", "www.leetcode.com")); 
		System.out.println(lcs.longestCommonSubstring("abcd", "cbced")); 
	}
}
