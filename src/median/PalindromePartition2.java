package median;

/*
 * Given a string s, cut s into some substrings such that every substring is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * Example
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

public class PalindromePartition2 {
	/**
     * @param s a string
     * @return an integer
     */
    public int minCut(String s) {
        // write your code here
        int n = s.length(); 
        int[] cut = new int[n]; 
        for (int i=0; i<n; i++) cut[i] = i; 
        for (int i=0; i<n; i++){
            for (int j=0; j<=i&&j<n-i; j++){
                if (s.charAt(i-j)!=s.charAt(i+j)) break; 
                int m = (i==j)?0:cut[i-j-1]+1; 
                int r = i+j; 
                cut[r] = (cut[r]>m)?m:cut[r]; 
            }
            for (int j=0; j<=i&&j<n-i-1; j++){
                if (s.charAt(i-j)!=s.charAt(i+j+1)) break; 
                int m = (i==j)?0:cut[i-j-1]+1;
                int r = i+j+1; 
                cut[r] = (cut[r]>m)?m:cut[r];
            }
        }
        return cut[n-1]; 
    }

}
