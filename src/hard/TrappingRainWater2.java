package hard;
import java.util.*;

public class TrappingRainWater2 {
	/*
     * @param heights: a matrix of integers
     * @return: an integer
     */
    class Cell implements Comparable<Cell>{
        public int row, col, level; 
        public boolean visit; 
        public Cell(int r, int c){
            row = r; 
            col = c; 
            level = Integer.MAX_VALUE;
            visit = false; 
        }
        public int compareTo(Cell c){
            return this.level-c.level; 
        }
    } 
    
    Cell[][] cells;
    int n, m; 
    ArrayList<Cell> neighborOf(Cell cell){
        ArrayList<Cell> ret = new ArrayList<Cell>(); 
        if (cell.row+1<n && !cells[cell.row+1][cell.col].visit){
            ret.add(cells[cell.row+1][cell.col]); 
        }
        if (cell.col+1<m && !cells[cell.row][cell.col+1].visit){
            ret.add(cells[cell.row][cell.col+1]); 
        }
        if (cell.row>0 && !cells[cell.row-1][cell.col].visit){
            ret.add(cells[cell.row-1][cell.col]); 
        }
        if (cell.col>0 && !cells[cell.row][cell.col-1].visit){
            ret.add(cells[cell.row][cell.col-1]); 
        }
        return ret; 
    }
     
    public int trapRainWater(int[][] heights) {
        // write your code here
        n = heights.length; 
        m = heights[0].length; 
        Queue<Cell> queue = new PriorityQueue<Cell>(m*n); 
        cells = new Cell[n][m]; 
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                cells[i][j] = new Cell(i, j); 
                if (i==0||j==0||i==n-1||j==m-1){
                    cells[i][j].level = heights[i][j];
                    queue.add(cells[i][j]); 
                }
            }
        }
        while (!queue.isEmpty()){
            Cell cell = queue.poll();
            cell.visit = true; 
            ArrayList<Cell> nb = neighborOf(cell);
            for (Cell bc:nb){
                int newlevel = Math.max(heights[bc.row][bc.col], Math.min(cell.level, bc.level));
                if (bc.level!=newlevel){
                    bc.level = newlevel; 
                    queue.add(bc); 
                }
            }
        }
        int volume = 0; 
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                volume += cells[i][j].level-heights[i][j];  
            }
        }
        return volume; 
    }

}
