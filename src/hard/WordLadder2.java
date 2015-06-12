package hard;
import java.util.*;

public class WordLadder2 {
	/**
     * @param start, a string
     * @param end, a string
     * @param dict, a set of string
     * @return a list of lists of string
     */
	  class Node{
	        public String word; 
	        public ArrayList<Node> prev; 
	        public boolean[] from; 
	        public boolean visited; 
	        public int dis; 
	        public Node(String w){
	            word = w; 
	            from = new boolean[leng]; 
	            prev = new ArrayList<Node>();
	            visited = false;
	            dis = Integer.MAX_VALUE; 
	        } 
	    }  
	    
	    Map<String, ArrayList<Node>> processDict(Set<String> dict){
	        Map<String, ArrayList<Node>> map = new HashMap<String, ArrayList<Node>>(); 
	        for (String word:dict){
	            StringBuffer sb = new StringBuffer(word); 
	            Node node = new Node(word); 
	            for (int i=0; i<word.length(); i++){
	                sb.setCharAt(i, '?');
	                ArrayList<Node> list = map.get(sb.toString());
	                if (list==null) {
	                    list = new ArrayList<Node>(); 
	                    map.put(sb.toString(), list);
	                }
	                list.add(node);
	                sb.setCharAt(i, word.charAt(i)); 
	            }
	        }
	        return map; 
	    }
	    int leng; 
	    int pathLeng = -1;
	    public List<List<String>> generatePaths(Node node){
	        List<List<String>> res = new ArrayList<List<String>>(); 
	        if (node.prev.isEmpty()){
	            List<String> list = new ArrayList<String>(); 
	            list.add(node.word); 
	            res.add(list); 
	            return res; 
	        } 
	        for (Node prev:node.prev){
	            List<List<String>> paths = generatePaths(prev);
	            for (List<String> path:paths){
	                path.add(node.word); 
	            }
	            res.addAll(paths); 
	        }
	        return res; 
	    }
	    
	    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
	        // write your code here
	        leng = start.length(); 
	        Map<String, ArrayList<Node>> map = processDict(dict); 
	        Queue<Node> queue = new LinkedList<Node>(); 
	        Node node = new Node(start); 
	        node.dis = 0; 
	        queue.add(node); 
	        while (!queue.isEmpty()){
	            node = queue.poll();
	            node.visited = true; 
	            if (node.word.equals(end)){
	                return generatePaths(node); 
	            }
	            StringBuffer key = new StringBuffer(node.word); 
	            for (int i=0; i<leng; i++){
	                if (node.from[i]) continue; 
	                key.setCharAt(i, '?'); 
	                ArrayList<Node> list = map.get(key.toString());
	                key.setCharAt(i, node.word.charAt(i)); 
	                for (Node b:list){
	                    if (b.visited || b.word.equals(start)) continue; 
	                    if (b.dis>=node.dis+1){
	                        if (b.prev.isEmpty()) queue.add(b); 
	                        b.from[i] = true; 
	                        b.prev.add(node); 
	                        b.dis = node.dis+1; 
	                    }
	                }
	            }
	        }
	        return new ArrayList<List<String>>();
	    }

}
