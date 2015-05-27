package median;

public class Backpack2 {
	 /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        int[] dp = new int[m+1]; 
        for (int i=0; i<A.length; i++){
            for (int size=m; size>0; size--){
                int p = size-A[i]; 
                if (p>=0){
                    dp[size]=Math.max(dp[size], dp[p]+V[i]); 
                }
            }
        }
        return dp[m]; 
    }

}
