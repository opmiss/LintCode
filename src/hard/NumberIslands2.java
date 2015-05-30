package hard;
import java.util.*;
/* Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). 
 * Originally, the 2D matrix is all 0 which means there is only sea in the matrix. 
 * The list pair has k operator and each operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] from sea to island. Return how many island are there in the matrix after each operator.
 * Example
 * Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].
 * return [1,1,2,2].
 * Note
 * 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. 
 * We only consider up/down/left/right adjacent.
*/
public class NumberIslands2 {
	class Point {
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { x = a; y = b; }
	}
	/**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    int[][] map; 
    int n, m; 
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Write your code here 
        List<Integer> res = new ArrayList<Integer>(); 
        if (n==0||m==0||operators==null || operators.length==0) return res; 
        this.n = n; 
        this.m = m;
        map = new int[n][m];
        int numIsland = 0; 
        List<Point> list = new ArrayList<Point>();
        Set<Integer> set = new HashSet<Integer>(); 
        for (int id = 0; id<operators.length; id++){
            Point p = operators[id]; 
            if (map[p.x][p.y]>0) {
                res.add(numIsland); 
                continue; 
            }
            list.clear(); set.clear(); 
            if (p.x>0 && map[p.x-1][p.y]>0) {
                set.add(map[p.x-1][p.y]); 
                list.add(new Point(p.x-1, p.y)); 
            }
            if (p.x<n-1 && map[p.x+1][p.y]>0){
                if (!set.contains(map[p.x+1][p.y]))
                    list.add(new Point(p.x+1, p.y)); 
            }
            if (p.y>0 && map[p.x][p.y-1]>0){
                if (!set.contains(map[p.x][p.y-1]))
                    list.add(new Point(p.x, p.y-1)); 
            }
            if (p.y<m-1 && map[p.x][p.y+1]>0){
                if (!set.contains(map[p.x][p.y+1]))
                    list.add(new Point(p.x, p.y+1)); 
            }
            if (list.isEmpty()) {
                map[p.x][p.y] = id+1; 
                res.add(++numIsland); 
                continue; 
            }
            Point o = list.get(0); 
            map[p.x][p.y] = map[o.x][o.y]; 
            for (int i=1; i<list.size(); i++){
                if (merge(o, list.get(i))) numIsland--; 
            }
            res.add(numIsland); 
        }
        return res; 
    }
    public boolean merge(Point o, Point m){
        if (map[o.x][o.y]==map[m.x][m.y]) return false;
        set(m.x, m.y, map[m.x][m.y], map[o.x][o.y]); 
        return true; 
    }
    public void set(int i, int j, int old, int val){
        if (i<0||j<0||i>=n||j>=m||map[i][j]!=old){
            return; 
        }
        map[i][j]=val; 
        set(i-1, j, old, val); 
        set(i, j-1, old, val);
        set(i+1, j, old, val);
        set(i, j+1, old, val); 
    }
    public static void main(String[] args){
    	NumberIslands2 ni = new NumberIslands2(); 
    	Point[] points = new Point[]{ni.new Point(1, 1), ni.new Point(0, 1), ni.new Point(3, 3), ni.new Point(3, 4)}; 
    	System.out.println(ni.numIslands2(4, 5, points)); 
    }
}
