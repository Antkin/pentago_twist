package student_player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Implements Node functionality for the Tree. This class will hold the Nodes state, nodes parent, and nodes children
 */
public class TreeNode {
	State state;
	TreeNode parentNode;
	List<TreeNode> childNodes;
	
	public TreeNode() {
		childNodes = new ArrayList<>();
	}
	
	public TreeNode(State state) {
		this.state = state;
		childNodes = new ArrayList<>();
	}
	
	public TreeNode(State state, TreeNode parentNode, List<TreeNode> childNodes) {
		this.state = state;
		this.parentNode = parentNode;
		this.childNodes = childNodes;
	}
	
	//Used to copy a node structure
	public TreeNode(TreeNode node) {
		this.childNodes = new ArrayList<>();
		this.state = new State(node.getState());
		if (node.getParentNode() != null) {
			this.parentNode = node.getParentNode();
		}
		
		List<TreeNode> tempNodeChildNodes = node.getChildNodes();
		for(TreeNode childNode : tempNodeChildNodes) {
			this.childNodes.add(new TreeNode(childNode));
		}
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public TreeNode getParentNode() {
		return parentNode;
	}
	
	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
	}
	
	public List<TreeNode> getChildNodes(){
		return childNodes;
	}
	
	public void setChildNodes(List<TreeNode> childNodes) {
		this.childNodes = childNodes;
	}
	
	public TreeNode getRandomChildNode() {
		int numChildNodes = this.childNodes.size();
		int randNum = (int) (Math.random() * numChildNodes);
		return childNodes.get(randNum);
	}
	
	public TreeNode getMaxChild() {
		return Collections.max(this.childNodes, Comparator.comparing(c -> {
			return c.getState().getNumVisits();
		}));
	}
}
