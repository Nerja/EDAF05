public class Arc<T extends Comparable<T>> implements Comparable<Arc<T>> {

	private T source, dest;
	private int weight;

	public Arc(T vertex1, T vertex2, int weight) {
		this.source = vertex1;
		this.dest = vertex2;
		this.weight = weight;
	}

	public T getSource() {
		return source;
	}

	public T getDest() {
		return dest;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Arc<T> otherEdge) {
		return ((Integer) weight).compareTo((Integer) otherEdge.weight);
	}
}
