package student_player;

import java.util.Collections;
import java.util.Comparator;

public class UpperConfidenceTree {
	
	public static double uctCalculatedValue(int numVisitsTotal, double winScore, int numVisitsNode) {
		if (numVisitsNode == 0) {
			return Integer.MAX_VALUE;
		}
		return (winScore / (double) numVisitsNode) + Math.sqrt(2)*Math.sqrt(Math.log(numVisitsTotal) / (double) numVisitsNode);
	}
	
	public static TreeNode findBestNode(TreeNode node) {
		int numParentVisits = node.getState().getNumVisits();
		return Collections.max(node.getChildNodes(), Comparator.comparing(c -> 
				uctCalculatedValue(numParentVisits, c.getState().getWinScore(), c.getState().getNumVisits())));
	}
}
