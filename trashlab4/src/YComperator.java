import java.awt.geom.Point2D;
import java.util.Comparator;

public class YComperator implements Comparator<Point2D> {
	@Override
	public int compare(Point2D point, Point2D otherPoint) {
		return (new Double(point.getY())).compareTo(otherPoint.getY());
	}
}