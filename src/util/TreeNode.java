package util;
import java.util.*; 

public class TreeNode {
	public int val;
	public TreeNode left, right;

	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
	
	public static List<List<String>> levelOrder(TreeNode node){
		List<List<String>> ret = new ArrayList<List<String>>(); 
		List<String> list = new ArrayList<String>(); 
		if (node==null) {
			list.add("#"); 
			ret.add(list); 
			return ret; 
		}
		list.add(Integer.toString(node.val)); 
		ret.add(list); 
		if (node.left==null&&node.right==null) {
			return ret; 
		}
		List<List<String>> left = levelOrder(node.left); 
		List<List<String>> right = levelOrder(node.right); 
		int i=0;  
		while (i<left.size()&&i<right.size()){
			List<String> level = new ArrayList<String>();
			level.addAll(left.get(i)); 
			level.addAll(right.get(i)); 
			ret.add(level); 
			i++; 
		}
		while(i<left.size()){
			List<String> level = new ArrayList<String>(); 
			level.addAll(left.get(i)); 
			if (i==right.size()){
				List<String> lastright = right.get(i-1);
				for (String w:lastright){
					if (!w.equals("#")) {
						level.add("#");
						level.add("#"); 
					}
				}
			}
			ret.add(level);
			i++; 
		}
		while(i<right.size()){
			List<String> level = new ArrayList<String>(); 
			if (i==left.size()){
				List<String> lastleft = left.get(i-1);
				for (String w:lastleft){
					if (!w.equals("#")) {
						level.add("#");
						level.add("#"); 
					}
				}
			}
			level.addAll(right.get(i)); 
			ret.add(level);
			i++; 
			
		}
		return ret; 
	}
	
	public static void printTree(TreeNode root){
		List<List<String>> levels = levelOrder(root);
	    System.out.println(levels); 
	}
	
	public static String toString(TreeNode root){
		StringBuffer sb = new StringBuffer();
		sb.append("{"); 
		List<List<String>> levels = levelOrder(root);
		for (List<String> level:levels){
			for (String w:level){
				sb.append(w+",");  
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("}");
		return sb.toString(); 
	}
	
	public static void main(String[] args){
		TreeNode node = new TreeNode(1); 
		node.right = new TreeNode(2); 
		node.left = new TreeNode(0); 
		node.right.right = new TreeNode(3); 
		printTree(node); 
		System.out.println(toString(node)); 
	}

}
