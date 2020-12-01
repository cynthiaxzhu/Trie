package trie;

import java.util.ArrayList;

/**
 * Trie implements a trie using linked lists and generates word completion lists using recursion.
 * 
 * Data Structures Fall 2020
 * 
 * @author Professor Seshadri Venugopal
 * @author Cynthia Zhu
 */
public class Trie {
	
	/**
	 * Builds trie that contains all words in given array.
	 * 
	 * @param wordArray Array of words in lower case
	 * @return Trie that contains all words in given array (root of trie)
	 */
	public static Node buildTrie(String[] wordArray) {
		
		//creates root of trie
		Node root = new Node(null, null, null);
		
		//inserts 0th word in array into trie
		Indexes tempIndexes = new Indexes(0, 0, wordArray[0].length() - 1);
		Node tempNode = new Node(tempIndexes, null, null);
		root.firstChild = tempNode;
		
		//inserts remaining words in array into trie
		for (int currentArrayIndex = 1; currentArrayIndex < wordArray.length; currentArrayIndex++) {
			insertWord(root, wordArray, currentArrayIndex);
		}
		
		return root;
		
	}
	
	/**
	 * Inserts word at given array index into trie.
	 * 
	 * @param currentRoot Current root
	 * @param wordArray Array of words in lower case
	 * @param currentArrayIndex Array index of current word
	 */
	private static void insertWord(Node currentRoot, String[] wordArray, int currentArrayIndex) {
		
		int matchIndex = 0;
		boolean match = true;
		Node currentLastChild = new Node(null, null, null);
		
		//traverses linked list whose first element is first child of current root
		for (Node currentChild = currentRoot.firstChild; currentChild != null; currentChild = currentChild.sibling) {
			
			//traverses substring at current child
			for (matchIndex = currentChild.substr.beginIndex; matchIndex <= currentChild.substr.endIndex; matchIndex++) {
				
				//compares current character in trie word and current character in new word
				match = wordArray[currentChild.substr.arrayIndex].substring(matchIndex, matchIndex + 1).equals
					   (wordArray[currentArrayIndex]					.substring(matchIndex, matchIndex + 1));
				
				//first character does not match
				if (match == false && matchIndex == currentChild.substr.beginIndex) {
					break;
				}
				
				//non-first character does not match
				if (match == false) {
					
					int currentChildEndIndex = currentChild.substr.endIndex;
					currentChild.substr.endIndex = matchIndex - 1;
					
					//creates new first child of current child that corresponds to current child
					Indexes newChildIndexes = new Indexes(currentChild.substr.arrayIndex, matchIndex, currentChildEndIndex);
					Node newChild = new Node(newChildIndexes, currentChild.firstChild, null);
					currentChild.firstChild = newChild;
					
					//inserts new word as sibling of new child
					Indexes newSiblingIndexes = new Indexes(currentArrayIndex, matchIndex, wordArray[currentArrayIndex].length() - 1);
					Node newSibling = new Node(newSiblingIndexes, null, null);
					newChild.sibling = newSibling;
					
					return;
					
				}
				
				//all characters in substring at current child match
				if (match == true && matchIndex == currentChild.substr.endIndex) {
					insertWord(currentChild, wordArray, currentArrayIndex);
					return;
				}
				
			}
			
			//first character does not match for all children
			//creates reference to current last child
			if (currentChild.sibling == null) {
				currentLastChild = currentChild;
			}
			
		}
		
		//inserts new word as sibling of last child
		Indexes newLastChildIndexes = new Indexes(currentArrayIndex, currentLastChild.substr.beginIndex, wordArray[currentArrayIndex].length() - 1);
		Node newLastChild = new Node(newLastChildIndexes, null, null);
		currentLastChild.sibling = newLastChild;
		
	}
	
	/**
	 * Generates word completion list for given prefix.
	 * 
	 * @param root Trie that contains all words in corresponding array (root of trie)
	 * @param wordArray Array of words in lower case that correspond to trie
	 * @param prefix Prefix
	 * @return Word completion list (array list of leaf nodes) if matches found, null if matches not found
	 */
	public static ArrayList<Node> generateWordCompletionList(Node root, String[] wordArray, String prefix) {
		ArrayList<Node> matches = new ArrayList<Node>();
		if (prefix.equals("")) {
			addAll(root.firstChild, matches);
		} else {
			matches = findMatches(root, wordArray, prefix, matches);
		}
		return matches;
	}
	
	/**
	 * Generates word completion list for given prefix using recursion.
	 * 
	 * @param currentRoot Current root
	 * @param wordArray Array of words in lower case
	 * @param prefix Prefix
	 * @param matches Word completion list (array list of leaf nodes)
	 * @return Word completion list (array list of leaf nodes) if matches found, null if matches not found
	 */
	private static ArrayList<Node> findMatches(Node currentRoot, String[] wordArray, String prefix, ArrayList<Node> matches) {
		
		for (Node currentChild = currentRoot.firstChild; currentChild != null; currentChild = currentChild.sibling) {
			
			if (prefix.length() <= currentChild.substr.endIndex + 1) {
				
				if (wordArray[currentChild.substr.arrayIndex].substring(0, prefix.length()).equals
				   (prefix									.substring(0, prefix.length()))) {
					
					if (currentChild.firstChild == null) {
						matches.add(currentChild);
						return matches;
					}
					
					else {
						addAll(currentChild.firstChild, matches);
						return matches;
					}
					
				}
				
			}
			
			else {
				
				if (wordArray[currentChild.substr.arrayIndex].substring(0, currentChild.substr.endIndex + 1).equals
				   (prefix									.substring(0, currentChild.substr.endIndex + 1))) {
					matches = findMatches(currentChild, wordArray, prefix, matches);
					return matches;
				}
				
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * Adds all leaf nodes of given root to array list.
	 * 
	 * @param currentRoot Current root
	 * @param matches Word completion list (array list of leaf nodes)
	 */
	private static void addAll(Node currentRoot, ArrayList<Node> matches) {
		for (Node currentChild = currentRoot; currentChild != null; currentChild = currentChild.sibling) {
			if (currentChild.firstChild == null) {
				matches.add(currentChild);
			} else {
				addAll(currentChild.firstChild, matches);
			}
		}
	}
	
}