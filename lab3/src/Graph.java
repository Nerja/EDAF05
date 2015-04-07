import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T extends Comparable<T>> {
	private Map<T, Node<T>> nodes;
	private Set<Edge<T>> edges;
	private int totalWeight;

	public Graph() {
		nodes = new HashMap<T, Node<T>>();
		edges = new HashSet<Edge<T>>();
		totalWeight = 0;
	}

	public void addEdge(Edge<T> newEdge) {
		edges.add(newEdge);
		totalWeight += newEdge.getWeight();
	}
	
	public int getTotalWeight() {
		return totalWeight;
	}
	
	public void addNode(T nodeElement) {
		nodes.put(nodeElement, new Node<T>(nodeElement));
	}
	
	public boolean containsNode(T nodeElement) {
		return nodes.containsKey(nodeElement);
	}

	public int numberOfNodes() {
		return nodes.size();
	}
	
	public int numberOfEdges() {
		return edges.size();
	}

	public Node<T> getRandomNode() {
		return nodes.values().iterator().next();
	}
	
	public Node<T> getNode(T nodeElement) {
		return nodes.get(nodeElement);
	}
	
}
