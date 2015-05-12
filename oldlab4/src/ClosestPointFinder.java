import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Arrays;

public class ClosestPointFinder {
	public static void main(String[] args) throws IOException {
		System.out.print(" " + compute(PointFactory.loadPoints(args[0])));
	}

	private static double compute(Point2D[] points) {
		Point2D[] xPoints = points;
		Arrays.sort(xPoints, new XComperator());
		Point2D[] yPoints = xPoints.clone();
		return compute(xPoints, yPoints, 0, xPoints.length - 1,
				new Point2D[xPoints.length]);
	}

	private static double compute(Point2D[] xPoints, Point2D[] yPoints,
			int xlb, int xrb, Point2D[] aux) {
		if ((xrb - xlb) == 1) {
			if (yPoints[xlb].getY() > yPoints[xrb].getY())
				swapElements(yPoints, xlb, xrb);
			return yPoints[xrb].distance(yPoints[xlb]);
		} else if (xrb - xlb < 1)
			return Double.MAX_VALUE;
		int middle = (xlb + xrb) / 2;
		double middleXLine = xPoints[middle].getX();
		double dminl = compute(xPoints, yPoints, xlb, middle, aux);
		double dminr = compute(xPoints, yPoints, middle + 1, xrb, aux);
		double dminlr = Math.min(dminl, dminr);
		merge(yPoints, xlb, middle, xrb, aux);
		int ptcIndex = 0;
		for (int i = xlb; i <= xrb; i++)
			if (Math.abs(yPoints[i].getX() - middleXLine) < dminlr)
				aux[ptcIndex++] = yPoints[i];
		return Math.min(dminlr, bruteForce(aux, 0, ptcIndex - 1));
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

	private static double bruteForce(Point2D[] points, int lb, int rb) {
		double min = Double.MAX_VALUE;
		for (int i = lb; i <= rb; i++)
			for (int j = i + 1; j <= rb && j < i + 16; j++)
				min = Math.min(min, points[i].distance(points[j]));
		return min;
	}

	private static void swapElements(Point2D[] yPoints, int a, int b) {
		Point2D temp = yPoints[a];
		yPoints[a] = yPoints[b];
		yPoints[b] = temp;
	}
}