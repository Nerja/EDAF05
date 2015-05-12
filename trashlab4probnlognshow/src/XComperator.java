import java.awt.geom.Point2D;
import java.util.Comparator;

public class XComperator implements Comparator<Point2D> {
	@Override
	public int compare(Point2D point, Point2D otherPoint) {
		return (new Double(point.getX())).compareTo(otherPoint.getX());
	}
}