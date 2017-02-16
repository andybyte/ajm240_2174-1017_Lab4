package pitt.edu.ajm240_Lab4;

import edu.pitt.infsci1017_tree.Node;
import edu.pitt.infsci1017_tree.Tree;
import edu.pitt.infsci1017_tree.TreeFactory;
// Main (fix git)
public class Main {
	
	public static void main(String[] args) {
		int numberOfNodes;
		int depthOfTree;
		
		Tree tree = TreeFactory.getTree();
		
		// Identify the number of nodes.
		numberOfNodes = numberOfNodes(tree.getRoot());
		System.out.println("The number of nodes in the tree is " + numberOfNodes + ".");
		
		// Calculate it's depth.
		depthOfTree = getDepth(tree.getRoot());
		System.out.println("The depth of the tree is " + depthOfTree + ".");
	}
	
	private static int getDepth(Node n) {
		int depthCount = 0;
		int recursiveCore = 0;
		
		for (Node child : n.getChildren()) {
			if (n.isLeaf()) {
				return Math.max(depthCount, recursiveCore);
			} else {
				depthCount += 1;
				recursiveCore = getDepth(child);
			}
		}
		
		return recursiveCore;
	}

	public static int numberOfNodes(Node n) {
		int nodeCount = 1;
		
		if (n.getChildren().size() > 0) {
			for (Node child : n.getChildren()) {
				nodeCount += numberOfNodes(child);
			}
		}
		
		return nodeCount;
	}

}


