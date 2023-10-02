package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */


public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    LinkedList<String> listCompletions = new LinkedList<String>();

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
    
  
    
	public boolean addWord(String word)
	{
		//System.out.println(word);
	    //TODO: Implement this method.
		//TrieNode cNode = new TrieNode();
		TrieNode pNode = new TrieNode();
		pNode = root;
		//boolean b = false;
		//while (b == false) {
		for (Character c : (word.toLowerCase()).toCharArray()) {
			if(pNode.getChild(c)==null) {
				pNode = pNode.insert(c);
			}
			
			if(pNode.getChild(c) != null) {
				pNode = pNode.getChild(c);
			}
		}
		if (pNode.endsWord()==false) {
		pNode.setEndsWord(true);
		//System.out.println("Ustawilem slowo  " + pNode.getText());
		size++;
		return true;
		}
		
		//}
		
		//root.insert('D');
		//root.insert('A');
		//root.insert('C');
		//System.out.println("pierwszy rzad" + root.getValidNextCharacters());
		//System.out.println("drugi rzad  " + root.getChild('d').getValidNextCharacters());
		//System.out.println("trzecii rzad  " + (root.getChild('d')).getChild('a').getValidNextCharacters());
		//System.out.println("Sprawdzam pusty  " + (root.getChild('e')));
		
	
			
		//System.out.println("dupa  " + (root.getChild('d')).getValidNextCharacters());
		//System.out.println(root.getText());
		//System.out.println(root.getChild('a'));
		//System.out.println(root.getChild('B'));
		//System.out.println(root.getChild('E'));
		return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode pNode = new TrieNode();
		pNode = root;
		if (s.isEmpty()) {
			return false;
		}
		for (Character c : (s.toLowerCase()).toCharArray()) {
			if(!Character.isLetter(c)) {
				return false;
			}
			if(pNode.getChild(c) == null) {
				return false;
			}
			if(pNode.getChild(c) != null) {
				pNode = pNode.getChild(c);
			}
		}
		if(pNode.endsWord()) {
 			return true;
 		}
		return false;
	
	}
	public boolean isStem(String s) 
	{
	    // TODO: Implement this method
		TrieNode pNode = new TrieNode();
		pNode = root;
		if (s.isEmpty()) {
			return false;
		}
		for (Character c : (s.toLowerCase()).toCharArray()) {
			if(!Character.isLetter(c)) {
				return false;
			}
			if(pNode.getChild(c) == null) {
				return false;
			}
			if(pNode.getChild(c) != null) {
				pNode = pNode.getChild(c);
			}
		}
		return true;
	}
	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     
     
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 //System.out.println("prefix length= " +prefix.length());
    	 //System.out.println("prefix text= " +prefix);
    	 listCompletions.clear();
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 //LinkedList<String> listCompletions = new LinkedList<String>();
    	 TrieNode pNode = new TrieNode();
    	 pNode=root;
    	 	if (isStem(prefix)==false && prefix.length()!=0) {
    	 		//System.out.println("2");
    	 		return listCompletions;
    	 	}

    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 	
    	 	if (isStem(prefix)==true) {
    	 		//System.out.println("size= " + listCompletions.size());
    	 		//((LinkedList<String>) listCompletions).addLast(prefix);
    	 		//listCompletions.addLast(prefix);
    	 		for(Character c : prefix.toCharArray()) {
    				//if(pNode.getChild(c) != null) {
    					pNode = pNode.getChild(c);
    				//}
    	 		}
    	 		if(pNode.endsWord()) {
    	 			listCompletions.addLast(pNode.getText());
    	 		}
    	 		//System.out.println("Wchodze");
    	 		if(isStem(prefix)==true) {
    	 			recuSearch(pNode, numCompletions);
    	 		}

    	 		
    				//if (pNode.endsWord()) {
					//	listCompletions.addLast(pNode.getText());
					//	System.out.println("5" );
					//}
    	 		//TrieNode curr = new TrieNode();
    	 		//for(Character c : pNode.getValidNextCharacters()) {
    	 		//	if (pNode.getChild(c).endsWord()) {
    	 		//		listCompletions.addLast((pNode.getChild(c)).getText());
    	 		//	}
    	 		//}
    	 	}
	 		if(prefix.length()==0) {
	 			//System.out.println("wysylam roota");
	 			//System.out.println("litery w roocie to" + root.getValidNextCharacters());
	 			recuSearch(root, numCompletions);
	 			
	 		}
    	 	
    	 		
    	 // Return the list of completions
    	 	//System.out.println("lista dlugosc " + listCompletions.size());
	 		//sortList();
	 		//System.out.println(listCompletions.toString());
	 		//Collections.sort(listCompletions, String);
	 		//System.out.println(listCompletions.toString());
	 		sortList();
    	 	//System.out.println(listCompletions.toString());
    	 	return listCompletions;
     }
     public void recuSearch (TrieNode pNode,int num) {
    	 //System.out.println(pNode.getText());
    	 TrieNode rNode = new TrieNode();
    	 rNode = pNode;
    	 for(Character c : pNode.getValidNextCharacters()) {
    		 //System.out.println("literka to "+ c);
	 			if (pNode.getChild(c).endsWord()) {
	 				//System.out.println("Dodaje slowo     " + (pNode.getChild(c)).getText());
	 				listCompletions.addLast((pNode.getChild(c)).getText());
	 				//System.out.println("rozmiar listy  " + listCompletions.size());
	 				clearList(num);
	 			}
	 			//else {	
	 				rNode=pNode.getChild(c);
	 				recuSearch(rNode, num);
	 				//recuSearch(rNode);
	 			//}
	 			
	 		}
     }
    public void clearList(int num) {
    	 //System.out.println("kurka wodna " + listCompletions.size());
    	 String longest = "";
    	 if (listCompletions.size()>num) {
    		 
    		 for(String s : listCompletions) {
    			 if (s.length()>longest.length()) {
    				 //System.out.println(" slowa  " + s);
    				 longest=s;
    			 }
    		 }
    		//	 System.out.println("robie remove  " + longest);
    		listCompletions.remove(longest);
    		 
    		 //if(listCompletions.getFirst().length()>listCompletions.getLast().length()) {
    		 //	 listCompletions.removeFirst(); 
    		 //}
    		 //else listCompletions.removeLast();
    		 //listCompletions.removeFirst();
    	 }
    }
    public void sortList () {
    	
    	int indexMin;
    	String temp;
    	for (int i=0; i < listCompletions.size()-1; i++) {
    		indexMin = i;
    		for (int j=i+1; j < listCompletions.size(); j++) {
    			if (listCompletions.get(j).length() < listCompletions.get(indexMin).length()) {
    				indexMin = j;
    			}
    		}
    		//swap (vals, indexMin, i);
    		temp= listCompletions.get(indexMin);
    		listCompletions.set(indexMin, listCompletions.get(i));
    		listCompletions.set(i, temp);
    	}
    	
    }
    

    
    
 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}