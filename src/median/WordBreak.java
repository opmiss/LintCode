package median;
import java.util.*; 
/*
 * Given a string s and a dictionary of words dict, 
 * determine if s can be break into a space-separated sequence of one or more dictionary words.
 * Example
 * Given s = "lintcode", dict = ["lint", "code"].
 * Return true because "lintcode" can be break as "lint code"
*/

public class WordBreak {
	public boolean wordSegmentation(String s, Set<String> dict) {
        // write your code here   
        int[] minmax = getMinMax(dict);
        int n = s.length(); 
        boolean[] dp = new boolean[n+1]; 
        dp[0] = true; 
        for (int i=minmax[0]; i<=n; i++){
            for (int j=minmax[0]; j<=minmax[1]&&j<=i; j++){
                if (!dp[i-j]) continue; 
                if (dict.contains(s.substring(i-j, i))) {
                    dp[i] = true;
                    break; 
                }
            }
        }
        return dp[n]; 
    }
    public int[] getMinMax(Set<String> dict){
        int[] minmax = new int[2]; 
        minmax[0] = Integer.MAX_VALUE; 
        minmax[1] = Integer.MIN_VALUE; 
        for (String w:dict){
            minmax[0] = (minmax[0]<w.length())?minmax[0]:w.length(); 
            minmax[1] = (minmax[1]>w.length())?minmax[1]:w.length(); 
        }
        return minmax; 
    }

}
