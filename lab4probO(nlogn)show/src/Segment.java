
public class Segment {
	private int p1, p2;
	private double dist;
	
	public Segment(int p1, int p2, double dist) {
		this.p1 = p1;
		this.p2 = p2;
		this.dist = dist;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}
	
	public double getDist() {
		return dist;
	}
	
	@Override
	public String toString() {
		return "P1: " + p1 + " # P2: " + p2 + " # DIST: " + dist;
	}
}
