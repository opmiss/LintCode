package hard;

public class RegularExpressionMatching {
	/**
     * @param s: A string 
     * @param p: A string includes "." and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        // write your code here
        int sl = s.length(); 
        StringBuffer pb = new StringBuffer(); 
        boolean addstar = true; 
        for (int i=0; i<p.length(); i++){
            if (p.charAt(i)=='*'){
                if (i==0) continue; 
                if (addstar){ 
                    pb.append(p.charAt(i));
                    addstar=false;
                }
                continue; 
            }
            pb.append(p.charAt(i));
            addstar=true; 
        }
        p = pb.toString(); 
        int pl = p.length(); 
        boolean[][] match = new boolean[sl+1][pl+1];
        match[0][0] = true; 
        for (int j=0; j<pl; j++){
            if (p.charAt(j)=='*'){
                match[0][j+1] = match[0][j-1]; 
            }
        }
        for (int i=0; i<sl; i++){
            for (int j=0; j<pl; j++){
                if (s.charAt(i)==p.charAt(j)||p.charAt(j)=='.'){
                    match[i+1][j+1] = match[i][j]; 
                }
                else if (p.charAt(j)=='*'){
                    if (match[i+1][j]||match[i+1][j-1]){
                        match[i+1][j+1]=true; 
                    }
                    else if (match[i][j+1]){
                        match[i+1][j+1]=(s.charAt(i)==p.charAt(j-1))||
                        (p.charAt(j-1)=='.'); 
                    }
                }
            }
        }
        return match[sl][pl]; 
    }
}
