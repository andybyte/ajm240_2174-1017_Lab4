package pitt.edu.ajm240_Lab4;

import java.util.LinkedList;
import java.util.Queue;

import edu.pitt.infsci1017_tree.Node;
import edu.pitt.infsci1017_tree.Tree;
import edu.pitt.infsci1017_tree.TreeFactory;
// Main 
public class Main {
	
	public static void main(String[] args) {
		int numberOfNodes;
		int depthOfTree;
		int thisDepth = 0;
		int recursiveCore = 0;
		String binaryTree = "Yes";
		Boolean binary = true;	
		
		Tree tree = TreeFactory.getTree();
		
		// Identify the number of nodes.
		numberOfNodes = numberOfNodes(tree.getRoot());
		System.out.println("1. The number of nodes in the tree is " + numberOfNodes + ".");
		
		// Calculate it's depth.
		depthOfTree = getDepth(tree.getRoot(), thisDepth, recursiveCore);
		System.out.println("2. The depth of the tree is " + depthOfTree + ".");
		
		// Is it a binary Tree?
		if(!childrenPerNode(tree.getRoot(), binary)) {
			binaryTree = "No";
		};
		System.out.println("3. Is it a binary tree? " + binaryTree + ".");
		
		// Reconstruct the tree
		System.out.println("4. Recontruct the tree:");
		reconstruct(tree.getRoot());
	}
	
	// Task 1. Identify the number of nodes.
	public static int numberOfNodes(Node n) {
		int nodeCount = 1;
		
		if (n.getChildren().size() > 0) {
			for (Node child : n.getChildren()) {
				nodeCount += numberOfNodes(child);
			}
		}
		
		return nodeCount;
	}
	
	// Task 2. Calculate it's depth.
	public static int getDepth(Node n, int thisDepth, int recursiveCore) {
			// Add depth before drilling down.
			thisDepth++;
			
			// Capture depth if it is deeper.
			if (thisDepth > recursiveCore) {
				recursiveCore = thisDepth;
			}
			
			// Drill down.
			if (n.getChildren().size() > 0) {
				for (Node child : n.getChildren()) {
					recursiveCore = getDepth(child, thisDepth, recursiveCore);
				}
			}
			
			// Remove extra depth buildup.
			thisDepth--;
		
		return recursiveCore;
	}
	
	// Task 3. Is it a binary Tree?
	
	public static Boolean childrenPerNode(Node n, boolean binary) {
		int childSize = n.getChildren().size();
		
		// Are there children?
		if (childSize > 0) {
			
			// Binary trees can only have two children.
			if (childSize > 2) {
				// System.out.println("Found " + childSize + " children on one node.");
				binary = false;
			}
			
			// Drill down.
			for (Node child : n.getChildren()) {
				
				// One node with at least 3 children will trigger the Boolean to ultimately return false.
				return (binary & childrenPerNode(child, binary));
			}
		}
		return binary;
	}
	
	// Task 4. Reconstruct the tree.
	
	public static void reconstruct(Node n) {
		// Create Queue with LinkList
		Queue<Node> q = new LinkedList<Node>();
		
		// Add root and delimiter
		q.add(n);
		q.add(null);
		
		
		while(!q.isEmpty()) {
			// Take top of queue
			n = q.poll();
			
			if (n != null) {
				
				// If node is a parent we will print it with /#\ to signify it has children
				if (!n.isLeaf()) System.out.print("/" + n.getContent() + "\\" + " ");
				else System.out.print(n.getContent() + " ");
				
				// Add children to queue
				for (Node child : n.getChildren()) {
					q.add(child);
				}
				
			} else {
				
				// Reached end of level so we will print a newline statement.
				if (!q.isEmpty()) {
					System.out.println();
					q.add(null);
				}
			}
		}
	}
}