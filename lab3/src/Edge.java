
public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>>{
	
	private Node<T> vertex1, vertex2;
	private int weight;
	
	public Edge(Node<T> vertex1, Node<T> vertex2, int weight) {
		vertex1.addEdge(this);
		vertex2.addEdge(this);
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge<T> obj) {
		int result = weight > obj.weight ? 1 : -1;
		result = weight == obj.weight ? 0 : result;
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object x) {
		if(!(x instanceof Edge))
			return false;
		return compareTo((Edge<T>)x) == 0;
	}
	
	public Node<T> getVertex1() {
		return vertex1;
	}
	
	public Node<T> getVertex2() {
		return vertex2;
	}
	
	public int getWeight() {
		return weight;
	}
	
}
