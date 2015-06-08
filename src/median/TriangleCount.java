package median;

import java.util.Arrays;
/* 
 * Given an array of integers, how many three numbers can be found in the array, 
 * so that we can build an triangle whose three edges length is the three numbers that we find?
 */

public class TriangleCount {
	/**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int S[]) {
        // write your code here
        if (S.length<3) return 0; 
        if (S.length<4) return 1; 
        Arrays.sort(S); 
        int n = S.length; 
        int count = 0; 
        for (int i=0; i<n-2; i++){
            int k=i+2, sum; 
            for (int j=i+1; j<n-1; j++){
                if (k<j+1) k=j+1; 
                sum = S[i]+S[j]; 
                while(k<n&&S[k]<sum) k++; 
                count+=k-j-1; 
            }
        }
        return count; 
    }

}
