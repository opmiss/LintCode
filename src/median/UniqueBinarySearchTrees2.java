package median;
import java.util.*; 
import util.TreeNode;

public class UniqueBinarySearchTrees2 {
	 /**
     * @paramn n: An integer
     * @return: A list of root
     */
	List<List<TreeNode>> dp = new ArrayList<List<TreeNode>>();

	public List<TreeNode> generateTrees(int n) {
		// write your code here
		List<TreeNode> list = new ArrayList<TreeNode>();
		list.add(null);
		dp.add(list);
		list = new ArrayList<TreeNode>();
		list.add(new TreeNode(1));
		dp.add(list);
		return generate(n);
	}

	public List<TreeNode> generate(int n) {
		// write your code here
		if (n < dp.size())
			return dp.get(n);
		List<TreeNode> ret = new ArrayList<TreeNode>();
		for (int i = 0; i < n; i++) {
			List<TreeNode> left = generate(i);
			List<TreeNode> right = generate(n - i - 1);
			for (TreeNode l : left) {
				for (TreeNode r : right) {
					TreeNode root = new TreeNode(i + 1);
					root.left = l;
					root.right = offset(r, i + 1);
					ret.add(root);
				}
			}
		}
		dp.add(ret);
		return ret;
	}

	public TreeNode offset(TreeNode node, int oft) {
		if (node == null)
			return null;
		TreeNode newNode = new TreeNode(node.val + oft);
		newNode.left = offset(node.left, oft);
		newNode.right = offset(node.right, oft);
		return newNode;
	}
	
	public static void main(String[] args){
		UniqueBinarySearchTrees2 ubs2 = new UniqueBinarySearchTrees2(); 
		ubs2.generateTrees(3); 
		System.out.println(ubs2.dp.size()); 
		for (List<TreeNode> trees:ubs2.dp){
			ArrayList<String> treelist = new ArrayList<String>(); 
			for (TreeNode tree:trees){
				treelist.add(TreeNode.toString(tree)); 
			}
			System.out.println(treelist); 
		}
	}

}
