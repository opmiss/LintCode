package hard;
import java.util.*;

/*
 * Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. 
 * A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 
 */

public class WordSearch {
	/**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
     class TrieNode {
        boolean endhere = false; 
        TrieNode[] child = null; 
        public TrieNode() {}
        public void insert(String w){
            if (w.length()<1) {endhere = true; return;}
            int id = (int)w.charAt(0) -'a'; 
            if (child==null) child = new TrieNode[26]; 
            if (child[id]==null) child[id] = new TrieNode(); 
            child[id].insert(w.substring(1));
        }
    }
    Set<String> sols; 
    int nrows; 
    int ncols; 
    char[][] board; 
    boolean[][] visited; 
    TrieNode root; 
    public void visit(TrieNode node, String word, int row, int col){
        if (node.endhere) {
            sols.add(word);
        }
        if (row<0||row>=nrows||col<0||col>=ncols||visited[row][col]) return; 
        if (node.child==null) return; 
        int id = (int)board[row][col]-'a'; 
        if (node.child[id]==null) return; 
        String nword = word+board[row][col]; 
        visited[row][col] = true; 
        visit(node.child[id], nword, row+1, col); 
        visit(node.child[id], nword, row, col+1);
        visit(node.child[id], nword, row-1, col); 
        visit(node.child[id], nword, row, col-1);
        visited[row][col] = false; 
    } 
     
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        // write your code here
           this.sols = new HashSet<String>(); 
        this.nrows = board.length; 
        this.ncols = board[0].length; 
        this.board = board;
        this.visited = new boolean[nrows][ncols]; 
        root = new TrieNode(); 
        for (String w:words) root.insert(w);
        for (int r = 0; r<nrows; r++){
            for (int c=0; c<ncols; c++){
                visit(root, "", r, c); 
            }
        }
        ArrayList<String> ret = new ArrayList<String>(); 
        ret.addAll(sols); 
        return ret; 
    }

}
