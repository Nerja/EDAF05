package labb3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Edge  {

	private Node[] m_endPoints;
	private int m_weight;

	public Edge(Node parent, Node child, int weight) {
		m_endPoints = new Node[2];
		m_endPoints[0] = parent;
		m_endPoints[1] = child;
		m_weight = weight;
		
		parent.addEdge(this);
		child.addEdge(this);
	}

	public Node[] getNodes(){
		return m_endPoints;
	}
	public int getWeight() {
		return m_weight;
	}

	public void visit(){
		m_endPoints[0].visit();
		m_endPoints[1].visit();
	}
	public boolean isVisited(){
		return ((m_endPoints[0].isVisited())&&(m_endPoints[1].isVisited()));
	}
	@Override
	public String toString() {
		return m_endPoints[0].getName() + " <-> " + m_endPoints[1].getName() + "\n";
	}

	public List<Edge> getNeighbours(){
		List<Edge> rl = new LinkedList<Edge>();
		rl.addAll(m_endPoints[0].getEdges());
		rl.addAll(m_endPoints[1].getEdges());
		return rl;
	}


}
