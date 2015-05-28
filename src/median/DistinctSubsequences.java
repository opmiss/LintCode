package median;
/*
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * A subsequence of a string is a new string 
 * which is formed from the original string by deleting some (can be none) of the characters 
 * without disturbing the relative positions of the remaining characters. 
 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * Example
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 */

public class DistinctSubsequences {
	 /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        // write your code here
        int N = S.length(); 
        int M = T.length(); 
        if (N<M) return 0; 
        int[][] dp = new int[M+1][N+1];
        for (int i=0; i<N; i++) dp[0][i]=1; 
        for (int k=0; k<M; k++){
            for (int i=0; i<N; i++){
                if (S.charAt(i)==T.charAt(k))
                	dp[k+1][i+1] = dp[k+1][i]+dp[k][i]; 
                else dp[k+1][i+1] = dp[k+1][i]; 
            }
        }
        return dp[M][N]; 
    }
    public static void main(String[] args){
    	DistinctSubsequences ds = new DistinctSubsequences(); 
    	System.out.println(ds.numDistinct("rabbbit", "rabbit")); 
    }
}
