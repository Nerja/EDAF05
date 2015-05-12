import java.awt.geom.Point2D;

public class Segment {
	
	private Point2D a, b;
	
	public Segment(Point2D a, Point2D b) {
		this.a = a;
		this.b = b;
	}
	public Point2D getA() {
		return a;
	}

	public void setA(Point2D a) {
		this.a = a;
	}

	public Point2D getB() {
		return b;
	}

	public void setB(Point2D b) {
		this.b = b;
	}
	
	@Override
	public String toString() {
		return ("A: " + a + " B: " + b + " L: " + length());
	}
	
	public double length() {
		return a.distance(b);
	}
}
