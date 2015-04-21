package labb3;

import java.util.LinkedList;
import java.util.List;

public class Prim {

	private List<Edge> m_possibleEdges;
	private List<Node> m_visited;

	private List<Edge> m_chosenEdges;

	public Prim() {
		m_visited = new LinkedList<Node>();
		m_possibleEdges = new LinkedList<Edge>();
		m_chosenEdges = new LinkedList<Edge>();

	}

	public int buildTree(List<Node> nodes) {
		int result = 0;
		Node startN = nodes.get(0);
		m_visited.add(startN);
		m_possibleEdges.addAll(startN.getEdges());

		while (existPossibleMatch()) {

			Edge cheapest = null;
			for (Edge e : m_possibleEdges) {
				if (!e.isVisited()) {
					if (cheapest == null) {
						cheapest = e;
					} else {
						if (e.getWeight() <= cheapest.getWeight()) {
							cheapest = e;
						}
					}
				}
			}

			cheapest.visit();
			m_chosenEdges.add(cheapest);
			result += cheapest.getWeight();
			
			if (!m_visited.contains(cheapest.getNodes()[0])) {
				m_visited.add(cheapest.getNodes()[0]);
				m_possibleEdges.addAll(cheapest.getNodes()[0].getEdges());
			}
			if (!m_visited.contains(cheapest.getNodes()[1])) {
				m_visited.add(cheapest.getNodes()[1]);
				m_possibleEdges.addAll(cheapest.getNodes()[1].getEdges());
			}
		}

		return result;
	}

	private boolean existPossibleMatch() {

		boolean exist = false;

		for (Edge e : m_possibleEdges) {
			if (!e.isVisited()) {
				exist = true;
			}

		}

		return exist;
	}

	public String printEdges() {
		String s = "";

		for (Edge e : m_chosenEdges) {
			s += e.toString();
		}

		return s;
	}

}
