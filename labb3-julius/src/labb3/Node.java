package labb3;

import java.util.LinkedList;
import java.util.List;

public class Node {
	
	private String m_name;
	private List<Edge> m_edges;
	private boolean m_visited;
	
	
	public Node(String name){
		m_name = name;
		m_edges = new LinkedList<Edge>();
		m_visited = false;
	}
	
	public void visit(){
		m_visited = true;
	}
	public boolean isVisited(){
		return m_visited;
	}
	public String getName(){
		return m_name;
	}
	public List<Edge> getEdges(){
		return m_edges;
	}
	
	public void addEdge(Edge edge){
		m_edges.add(edge);
	}

}
