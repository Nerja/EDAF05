import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPointFinder {
	
	private static Segment closestSegment;
	
	public static void main(String[] args) throws IOException {
		Point2D[] xPoints = PointFactory.loadPoints(args[0]);
		Arrays.sort(xPoints, new XComperator());
		Point2D[] yPoints = xPoints.clone();
		System.out.print(" "
				+ compute(xPoints, yPoints, 0, xPoints.length - 1,
						new Point2D[xPoints.length],  Double.MAX_VALUE));
		System.out.println(closestSegment);
	}

	private static double compute(Point2D[] xPoints, Point2D[] yPoints,
			int xlb, int xrb, Point2D[] aux, double currentMin) {
		if ((xrb - xlb) == 1) {
			if (yPoints[xlb].getY() > yPoints[xrb].getY())
				swapElements(yPoints, xlb, xrb);
			double dd = yPoints[xrb].distance(yPoints[xlb]);
			if(dd < currentMin)
				closestSegment = new Segment(xrb, xlb, dd);
			return dd;
		} else if (xrb - xlb < 1) {
			return Double.MAX_VALUE;
		}
		int middle = (xlb + xrb) / 2;
		double middleXLine = xPoints[middle].getX();
		double dminl = compute(xPoints, yPoints, xlb, middle, aux, currentMin);
		currentMin = Math.min(currentMin, dminl);
		double dminr = compute(xPoints, yPoints, middle + 1, xrb, aux, currentMin);
		double dminlr = Math.min(dminl, dminr);
		currentMin = Math.min(currentMin, dminlr);
		merge(yPoints, xlb, middle, xrb, aux);
		int ptcIndex = 0;
		for (int i = xlb; i <= xrb; i++) {
			if (Math.abs(yPoints[i].getX() - middleXLine) < dminlr)
				aux[ptcIndex++] = yPoints[i];
		}
		return Math.min(dminlr, bruteForce(aux, 0, ptcIndex - 1, currentMin));
	}

	private static void swapElements(Point2D[] yPoints, int xlb, int xrb) {
		Point2D temp = yPoints[xlb];
		yPoints[xlb] = yPoints[xrb];
		yPoints[xrb] = temp;
	}

	private static void merge(Point2D[] array, int lb, int middle, int hb,
			Point2D[] aux) {
		int lx = lb;
		int rx = middle + 1;
		int auxIndex = lb;
		while (lx <= middle && rx <= hb) {
			if (array[lx].getY() < array[rx].getY())
				aux[auxIndex++] = array[lx++];
			else
				aux[auxIndex++] = array[rx++];
		}
		while (lx <= middle)
			aux[auxIndex++] = array[lx++];
		while (rx <= hb)
			aux[auxIndex++] = array[rx++];
		for (int i = lb; i <= hb; i++)
			array[i] = aux[i];
	}
	
	private static double bruteForce(Point2D[] points, int lb, int rb, double currentMin) {
		double min = Double.MAX_VALUE;
		for (int i = lb; i <= rb; i++) {
			for (int j = i + 1; j <= rb && j < i + 16; j++) {
				double distanceIToJ = Math.abs(points[i].distance(points[j]));
				if (distanceIToJ < min) {
					min = distanceIToJ;
					if(min < currentMin) {
						closestSegment = new Segment(i, j, min);
					}
				}
			}
		}
		return min;
	}

}
