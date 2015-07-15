package hard;

public class CopyBooks {
    /**
     * @param pages: an array of integers
     * @param k: an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here
        int n = pages.length;
        k = (k<n)?k:n; 
        sum = new int[n+1];
        for (int i=0; i<n; i++){
            sum[i+1] = sum[i]+pages[i]; 
        }
        sol = new int[n][k]; 
        return copyBooks(pages, n, k); 
    }
    
    int[] sum; 
    int[][] sol; 
    
    public int copyBooks(int[] pages, int end, int k){
        if (sol[end-1][k-1]>0) return sol[end-1][k-1]; 
        if (k==1){
            return sum[end]; 
        }
        int time = Integer.MAX_VALUE; 
        for (int e = end-1; e >= k-1; e--){
            int t1 = copyBooks(pages, e, k-1);
            int t2 = sum[end]-sum[e]; 
            int t = Math.max(t1, t2); 
            time = (time>t)?t:time; 
            if (t2>t1) break; 
        }
        sol[end-1][k-1] = time; 
        return time; 
    }
}
