public class Pair<V> {
	private V p1, p2;

	public V getP1() {
		return p1;
	}

	public void setP1(V p1) {
		this.p1 = p1;
	}

	public V getP2() {
		return p2;
	}

	public void setP2(V p2) {
		this.p2 = p2;
	}

	public Pair(V p1, V p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public String toString() {
		return p1.toString() + " <=> " + p2.toString();
	}
}
