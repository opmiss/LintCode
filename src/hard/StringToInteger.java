package hard;

public class StringToInteger {
	/**
     * @param str: A string
     * @return An integer
     */
    public int atoi(String str) {
        // write your code here
        int n = str.length(); 
        if (n==0) return 0; 
        int i=0; 
        boolean neg = false; 
        while (i<n && str.charAt(i)==' '){
            i++; 
        }
        if (i<n && str.charAt(i)=='-'){
            neg = true;
            i++; 
        }
        if (i<n && str.charAt(i)=='+'){
            i++; 
        }
        long num = 0; 
        while (i<n && str.charAt(i)!='.'){
            char c = str.charAt(i);
            int d = (int)c-48; 
            if (d<0||d>9) break; 
            num = num*10+d; 
            if (num>Integer.MAX_VALUE) {
                if (neg) {
                    return Integer.MIN_VALUE; 
                }
                else {
                    return Integer.MAX_VALUE; 
                }
            }
            i++; 
        }
        if (neg) num = -num; 
        return (int)num; 
    }
}
