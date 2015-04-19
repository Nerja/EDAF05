import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPointFinder {
	public static void main(String[] args) throws IOException {
		Point2D[] xPoints = PointFactory.loadPoints(args[0]);
		Point2D[] yPoints = xPoints.clone();
		Arrays.sort(xPoints, new XComperator());
		Arrays.sort(yPoints, new YComperator());
		System.out.print(" " + compute(xPoints, yPoints, 0, xPoints.length));
	}

	private static double compute(Point2D[] xPoints, Point2D[] yPoints,
			int xlb, int xrb) {
		if ((xrb - xlb) <= 1) {
			return bruteForce(xPoints, xrb, xlb);
		}
		int middX = (xrb + xlb) / 2;
		double middXCord = xPoints[middX].getX();
		List<Point2D> pointsyl = new ArrayList<Point2D>();
		List<Point2D> pointsyr = new ArrayList<Point2D>();
		for (int i = 0; i < yPoints.length; i++) {
			if (yPoints[i].getX() < middXCord)
				pointsyl.add(yPoints[i]);
			else
				pointsyr.add(yPoints[i]);
		}

		double dl = compute(xPoints, pointsyl.toArray(new Point2D[0]), xlb,
				middX);
		double dr = compute(xPoints, pointsyr.toArray(new Point2D[0]),
				middX + 1, xrb);
		double dlr = Math.min(dl, dr);

		List<Point2D> pointsToConsider = new ArrayList<Point2D>();
		for (Point2D point : yPoints) {
			if (Math.abs(point.getX() - middXCord) < dlr)
				pointsToConsider.add(point);
		}

		double minNeighbours = bruteForce(
				pointsToConsider.toArray(new Point2D[0]), 0,
				pointsToConsider.size() - 1);

		return dlr < minNeighbours ? dlr : minNeighbours;
	}

	private static double bruteForce(Point2D[] points, int lb, int rb) {
		double min = Double.MAX_VALUE;
		for (int i = lb; i <= rb; i++) {
			for (int j = i + 1; j <= rb; j++) {
				double distanceIToJ = Math.abs(points[i].distance(points[j]));
				if (distanceIToJ < min)
					min = distanceIToJ;
			}
		}
		return min;
	}

	private static class YComperator implements Comparator<Point2D> {
		@Override
		public int compare(Point2D point, Point2D otherPoint) {
			return (new Double(point.getY())).compareTo(otherPoint.getY());
		}
	}

	private static class XComperator implements Comparator<Point2D> {
		@Override
		public int compare(Point2D point, Point2D otherPoint) {
			return (new Double(point.getX())).compareTo(otherPoint.getX());
		}
	}
}
