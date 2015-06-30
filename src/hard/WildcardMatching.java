package hard;

/*
 * Implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
*/

public class WildcardMatching {
	/**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        // write your code here
        int sl = s.length(); 
        int pl = p.length(); 
        boolean[][] match = new boolean[sl+1][pl+1];
        match[0][0] = true; 
        for (int j=0; j<pl; j++){
            match[0][j+1] = (p.charAt(j)=='*')?match[0][j]:false; 
        }
        for (int i=0; i<sl; i++){
            for (int j=0; j<pl; j++){
                if (s.charAt(i)==p.charAt(j)||p.charAt(j)=='?'){
                    match[i+1][j+1] = match[i][j]; 
                }
                if (p.charAt(j)=='*'){
                    if (match[i][j]||match[i+1][j]||match[i][j+1]){
                        match[i+1][j+1] = true; 
                    }
                }
            }
        }
        return match[sl][pl]; 
     }

}
