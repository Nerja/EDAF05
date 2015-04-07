import java.util.LinkedList;
import java.util.List;


public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

	private List<Edge<T>> edges;
	private T nodeElement;
	
	public Node(T node) {
		edges = new LinkedList<Edge<T>>();
		this.nodeElement = node;
	}
	
	public void addEdge(Edge<T> newEdge) {
		edges.add(newEdge);
	}

	public List<Edge<T>> getEdges() {
		return edges;
	}

	@Override
	public int compareTo(Node<T> otherNode) {
		return nodeElement.compareTo(otherNode.nodeElement);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object x) {
		if(!(x instanceof Node)) 
			return false;
		return compareTo((Node<T>)x) == 0;
	}
	
	public T getNodeElement() {
		return nodeElement;
	}

}
