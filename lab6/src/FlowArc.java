public class FlowArc extends Arc {
	private int flow;

	public FlowArc(int source, int target, int weight) {
		this(source, target, weight, 0);
	}

	public FlowArc(int source, int target, int weight, int flow) {
		super(source, target, weight);
		this.flow = flow;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return super.toString() + " flow: " + flow;
	}
}
