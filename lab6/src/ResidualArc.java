public class ResidualArc extends Arc {
	private FlowArc parent;
	private boolean forwardArc;

	public ResidualArc(int source, int target, int weight, FlowArc parent,
			boolean forwardArc) {
		super(source, target, weight);
		this.parent = parent;
		this.forwardArc = forwardArc;
	}

	public void apply(int b) {
		parent.setFlow(parent.getFlow() + (forwardArc ? b : -b));
	}
}
