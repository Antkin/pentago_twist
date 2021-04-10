package student_player;
/*
 * Tree class to implement tree search functionality for MCTS
 */
public class Tree {
	TreeNode rootNode;
	
	public Tree() {
		rootNode = new TreeNode();
	}
	
	public Tree(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
	
	public TreeNode getRootNode() {
		return rootNode;
	}
	
	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
	
	public void addChild(TreeNode parentNode, TreeNode childNode) {
	}
}
