package hard;
import java.util.*;

public class ScrambleString {
	/**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        // Write your code here
        if (s1.length()!=s2.length()) return false; 
        if (s1.equals(s2)) return true; 
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i=0; i<s1.length(); i++){
            Integer c = map.get(s1.charAt(i)); 
            if (c==null) c = 0; 
            map.put(s1.charAt(i), ++c); 
        }
        for (int i=0; i<s2.length(); i++){
            Integer c = map.get(s2.charAt(i)); 
            if (c==null) return false; 
            if (--c<0) return false; 
            map.put(s2.charAt(i), c); 
        }
        if (s1.length()<4) return true; 
        int n = s2.length(); 
        for (int d=1; d<=s1.length(); d++) {
            String left1 = s1.substring(0, d);
            String right1 = s1.substring(d); 
            if (isScramble(left1, s2.substring(n-d)) 
            && isScramble(right1, s2.substring(0, n-d))) return true; 
        }
        return false; 
    }

}
