package labb2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

	List<Node> m_graph;

	public Graph() {
		m_graph = new LinkedList<Node>();

	}

	public void buildGraph(List<String> words) {
		for (String s : words) {
			m_graph.add(new Node(s));
		}

		for(Node n : m_graph){
			for(Node c : m_graph){
				if(!n.getWord().equals(c.getWord())){
					n.isChild(c);
				}
			}
		}
	}

	public int getDistBetween(String n1, String n2) {
		
		boolean foundChild = false;
		int dist = 0;
		
		LinkedList<Node> tempG = new LinkedList<Node>(m_graph);
		Node startNode = null;
		for(Node n : tempG){
			if(n.getWord().equals(n1)){
				startNode = n;
			}
		}
		
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(startNode);
		
		while(!queue.isEmpty() && !foundChild){
			LinkedList<Node> tempQ = new LinkedList<Node>();
			for(Node n : queue){
				if(!n.getVisited()){
					n.setVisit(true);
					if (n.getWord().equals(n2)) {
						dist -= 1; //Will add an extra 1 at the end of loop,, so get rid of that one
						foundChild = true;
						break;
					} else {
						for(Node c : n.getChildren()){
							if(!c.getVisited()){
								tempQ.add(c);
							}
						}
					}
				}
			}
			
			queue = tempQ;
			dist++;
		}

		return foundChild ? dist : -1;
	}

	public void resetGraph(){
		for(Node n : m_graph){
			n.setVisit(false);
		}
	}
}
