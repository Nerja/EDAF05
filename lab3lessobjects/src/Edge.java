public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {

	private T vertex1, vertex2;
	private int weight;

	public Edge(T vertex1, T vertex2, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	public T getVertex1() {
		return vertex1;
	}

	public T getVertex2() {
		return vertex2;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Edge<T> otherEdge) {
		return ((Integer) weight).compareTo((Integer) otherEdge.weight);
	}
}
