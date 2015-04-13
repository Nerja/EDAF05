package labb2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {

	private String m_word;
	private List<Node> m_children;
	private boolean m_hasVisited;

	public Node(String word) {
		this.m_word = word;
		m_children = new ArrayList<Node>();
		m_hasVisited = false;
	}
	public void setVisit(boolean visit){
		m_hasVisited = visit;
	}

	public boolean getVisited(){
		return m_hasVisited;
	}
	private void addChild(Node c) {
		m_children.add(c);
	}

	public List<Node> getChildren() {
		return m_children;
	}

	public String getWord() {
		return m_word;
	}

	public boolean isChild(Node n) {

		StringBuilder sb = new StringBuilder(n.getWord());
		
		LinkedList<Character> lastFour = new LinkedList<Character>();
		
		for(int i = m_word.length() - 1; i > m_word.length() - 5; i--){
			lastFour.add(m_word.charAt(i));
		}
		
		for(Character c : lastFour){
			if(sb.indexOf(c.toString()) != -1){
				sb.deleteCharAt(sb.indexOf(c.toString()));
			}
		}
		
		if (sb.length() == 1) {
			addChild(n);
			return true;
		} else {
			return false;
		}

	}
	
	@Override
	public String toString() {
		String rs = m_word;

		return rs;
	}

}
