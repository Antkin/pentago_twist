package student_player;

import java.util.List;

public class MyTools {
    public static double getSomething() {
        return Math.random();
    }
    
    /*
     * Select a good node by using the Upper Confidence Tree heuristic learned in class
     */
    public TreeNode selection(TreeNode parentNode) {
    	TreeNode node = parentNode;
    	
    	//Iterate till we reach the leaf node by going through the most promising path
    	while (node.getChildNodes().size() != 0) {
    		node = UpperConfidenceTree.findBestNode(node);
    	}
    	
    	return node;
    }
    
    /*
     * Expand each node with the possible next states and build the tree
     */
    public void expansion(TreeNode node) {
    	List<State> possibleStates = node.getState().getAllPossibleStates();
    	possibleStates.forEach(possibleState -> {
    		TreeNode tempNode = new TreeNode(possibleState);
    		tempNode.setParentNode(node);
    		node.getChildNodes().add(tempNode);
    	});
    }
    
    /*
     * Runs random simulations for now, this is our default policy
     */
    public int simulation(TreeNode startNode) {
    	TreeNode tempNode = new TreeNode(startNode);
    	State tempState = tempNode.getState();
    	
    	//Lets make sure this is an ongoing state as opposed to a finished state
    	if(tempState.getBoard().gameOver() == true) {
    		//If the game is finished then this must have been an opponents win
    		//because our win would be caught in the while loop below
    		tempNode.getParentNode().getState().setWinScore(0);
    		return tempState.getBoard().getTurnPlayer();
    	}
    	//If ongoing, keep going until winner
    	while(tempState.getBoard().gameOver() == false) {
    		tempState.randomMove();
    	}
    	
    	return tempState.getBoard().getTurnPlayer();
    }
    
    /*
     * Once we reach a terminal node, go back up the tree and update the visit counts for the states we selected/expanded
     * Also update score of visited nodes, attached currPlayer is the winning player
     */
    public void backpropagation(TreeNode node, int winningPlayer) {
    	TreeNode tempNode = node;
    	while(tempNode != null) {
    		tempNode.getState().incrementNumVisits();
    		if(tempNode.getState().getBoard().getTurnPlayer() == winningPlayer) {
    			tempNode.getState().addScore(1);
    		}
    		tempNode = tempNode.getParentNode();
    	}
    }
}