public class Arc<V> {
	private V source, target;
	
	public Arc(V source, V target) {
		this.source = source;
		this.target = target;
	}

	public V getSource() {
		return source;
	}

	public void setSource(V source) {
		this.source = source;
	}

	public V getTarget() {
		return target;
	}

	public void setTarget(V target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return source + " <=> " + target;
	}
}
