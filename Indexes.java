package trie;

/**
 * Indexes encapsulates the set of three indexes that 
 * represents a substring of a word in an array of strings.
 * 
 * Data Structures Fall 2020
 * 
 * @author Professor Seshadri Venugopal
 * @author Cynthia Zhu
 */
public class Indexes {
	
	/**
	 * Array index of word.
	 */
	int arrayIndex;
	
	/**
	 * Begin index of substring.
	 */
	int beginIndex;
	
	/**
	 * End index of substring.
	 */
	int endIndex;
	
	/**
	 * Initializes Indexes with arrayIndex, beginIndex, and endIndex fields.
	 * 
	 * @param arrayIndex Array index of word
	 * @param beginIndex Begin index of substring
	 * @param endIndex End index of substring
	 */
	public Indexes(int arrayIndex, int beginIndex, int endIndex) {
		this.arrayIndex = arrayIndex;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}
	
}