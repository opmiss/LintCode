package median;

public class InterleavingString {
	/**
     * Determine whether s3 is formed by interleaving of s1 and s2.
     * @param s1, s2, s3: As description.
     * @return: true or false.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        int N = s1.length(); 
        int M = s2.length(); 
        if (M+N!=s3.length()) return false; 
        boolean[][] dp = new boolean[N+1][M+1];
        dp[0][0] = true; 
        for (int i=0; i<N; i++){
            if (s1.charAt(i)==s3.charAt(i)) dp[i+1][0]=true; 
            else break; 
        }
        for (int j=0; j<M; j++){
            if (s2.charAt(j)==s3.charAt(j)) dp[0][j+1] = true; 
        }
        for (int i=1; i<=N; i++){
            for (int j=1; j<=M; j++){
                if (dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1))  
                    dp[i][j]=true; 
                else if (dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1))
                    dp[i][j]=true; 
                else 
                    dp[i][j]=false; 
            }    
        }
        return dp[N][M];
    }
}
