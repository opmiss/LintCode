package util;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTreeNode {
	public String symbol;
	public ExpressionTreeNode left, right;
	public ExpressionTreeNode(String symbol) {
		this.symbol = symbol;
		this.left = this.right = null;
	}
	
	public static List<List<String>> levelOrder(ExpressionTreeNode node){
		List<List<String>> ret = new ArrayList<List<String>>(); 
		List<String> list = new ArrayList<String>(); 
		if (node==null) {
			list.add("#"); 
			ret.add(list); 
			return ret; 
		}
		list.add(node.symbol); 
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
	
	public static void printTree(ExpressionTreeNode root){
		List<List<String>> levels = levelOrder(root);
	    System.out.println(levels); 
	}
	
	public static String toString(ExpressionTreeNode root){
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
}
