package hard;
import java.util.*;
/*
 * Given an integer matrix, find a submatrix where the sum of numbers is zero. 
 * Your code should return the coordinate of the left-up and right-down number. 
 * Example
 * Given matrix
[
  [1 ,5 ,7],
  [3 ,7 ,-8],
  [4 ,-8 ,9],
]
return [(1,1), (2,2)]
	Challenge
	O(n3) time.
*/
public class SubmatrixSum {
	public int[][] submatrixSum(int[][] matrix) {
        // Write your code here
        int n = matrix.length; 
        int m = matrix[0].length; 
        int[][] res = new int[2][2];
        int[][] sum = new int[n+1][m+1]; 
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                sum[i+1][j+1] = matrix[i][j]+sum[i][j+1]; 
            }
        }
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                sum[i+1][j+1] += sum[i+1][j]; 
                if (sum[i+1][j+1] == 0){
                    res[0][0] = 0; 
                    res[0][1] = 0;
                    res[1][0] = i; 
                    res[1][1] = j;
                    return res; 
                }
            }
        }
        class Pair{
            public int s, e; 
            public Pair(int i, int j){
                s=i; e=j; 
            }
        }
        Map<Pair, Map<Integer, Integer>> map = new HashMap<Pair, Map<Integer, Integer>>(); 
        for (int i=0; i<n; i++){
            for (int j=i+1; j<=n; j++){
                Pair p = new Pair(i, j); 
                for (int k=1; k<=m; k++){
                    int sub = sum[j][k]-sum[i][k]; 
                    if (sub==0){
                        res[0][0] = i; 
                        res[0][1] = 0; 
                        res[1][0] = j-1; 
                        res[1][1] = k-1; 
                        return res; 
                    }
                    Map<Integer, Integer> smap = map.get(p); 
                    if (smap==null) smap = new HashMap<Integer, Integer>(); 
                    if (smap.containsKey(sub)){
                        int pk = smap.get(sub); 
                        res[0][0] = i; 
                        res[0][1] = pk; 
                        res[1][0] = j-1; 
                        res[1][1] = k-1;
                        return res; 
                    }
                    smap.put(sub, k); 
                    map.put(p, smap); 
                }
            }
        }
        return res; 
    }
	
	public static void main(String[] args){
		int[][] matrix = new int[][]{{2, -2}, {-4, 4}}; 
		SubmatrixSum ss = new SubmatrixSum(); 
		int[][] res = ss.submatrixSum(matrix); 
		System.out.println(res[0][0]+", "+res[0][1]+", "+res[1][0]+", "+res[1][1]); 
	}

}
