package trie;

/**
 * Node implements a trie node that contains an Indexes instance.
 * 
 * Data Structures Fall 2020
 * 
 * @author Professor Seshadri Venugopal
 * @author Cynthia Zhu
 */
public class Node {
	
	/**
	 * Substring at node.
	 */
	Indexes substr;
	
	/**
	 * First child of node.
	 */
	Node firstChild;
	
	/**
	 * Sibling of node.
	 */
	Node sibling;
	
	/**
	 * Initializes Node with substring, first child, and sibling fields.
	 * 
	 * @param substr Substring at node
	 * @param firstChild First child of node
	 * @param sibling Sibling of node
	 */
	public Node(Indexes substr, Node firstChild, Node sibling) {
		this.substr = substr;
		this.firstChild = firstChild;
		this.sibling = sibling;
	}
	
}