public class Arc {
	private int source, target, capacity;

	public Arc(int source, int target, int weight) {
		this.source = source;
		this.target = target;
		this.capacity = weight;
	}

	public int getSource() {
		return source;
	}

	public int getTarget() {
		return target;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return source + " -> " + target + " cap : " + capacity;
	}
}
