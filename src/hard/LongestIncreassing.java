package hard;
import java.util.*;
/*
 * Give you an integer matrix (with row size n, column size m)，
 * find the longest increasing continuous subsequence in this matrix. 
 * (The definition of the longest increasing continuous subsequence here can start at any row or column 
 * and go up/down/right/left any direction).
 * Example
 * Given a matrix:
 * [
  [1 ,2 ,3 ,4 ,5],
  [16,17,24,23,6],
  [15,18,25,22,7],
  [14,19,20,21,8],
  [13,12,11,10,9]
  ]
  return 25
*/ 

public class LongestIncreassing {
	public class Vertex{
        public int val, indegrees, order; 
        ArrayList<Vertex> neighbors = null; 
        public Vertex(int v){
            this.val = v; 
            order = 1;
            indegrees = 0; 
            neighbors = new ArrayList<Vertex>(); 
        }
        public void addNeighbor(Vertex b){
            b.indegrees++; 
            this.neighbors.add(b); 
        }
    }  
    public Queue<Vertex> queue; 
    public Vertex[][] V; 
    
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        // Write your code here
        int n = A.length; 
        if (n==0) return 0; 
        int m = A[0].length; 
        if (m==0) return 0; 
        V = new Vertex[n][m]; 
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                V[i][j] = new Vertex(A[i][j]); 
            }
        }
        for (int i=0; i<n-1; i++){
            for (int j=0; j<m; j++){
                if (A[i][j]<A[i+1][j]){
                    V[i][j].addNeighbor(V[i+1][j]); 
                } 
                else if (A[i][j]>A[i+1][j]){
                    V[i+1][j].addNeighbor(V[i][j]); 
                }
            }
        }
        for (int i=0; i<n; i++){
            for (int j=0; j<m-1; j++){
                if (A[i][j]<A[i][j+1]){
                    V[i][j].addNeighbor(V[i][j+1]); 
                }
                else if (A[i][j]>A[i][j+1]){
                    V[i][j+1].addNeighbor(V[i][j]); 
                }
            }
        }
        queue = new LinkedList<Vertex>(); 
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (V[i][j].indegrees==0) queue.add(V[i][j]); 
            }
        }
        while (!queue.isEmpty()){
            Vertex v = queue.poll();
            for (Vertex b:v.neighbors){
                b.order = Math.max(b.order, v.order+1);
                if ( (--b.indegrees)==0) queue.add(b); 
            }
        }
        int maxLeng = 1; 
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                maxLeng = (maxLeng<V[i][j].order)?V[i][j].order:maxLeng; 
            }
        }
        return maxLeng; 
    }
}
