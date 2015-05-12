import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PointFactory {
	public static Point2D[] loadPoints(String filename) throws IOException {
		Scanner scan = new Scanner(new BufferedReader(new FileReader(filename)));
		scan.skip("([A-Z](_| |[A-Z])*(( )?:( )?([A-Z]|[a-z]|.|[0-9])*)?\\n)*");
		List<Point2D> points = new LinkedList<Point2D>();
		while (scan.hasNextDouble()) {
			String[] parts = scan.nextLine().trim().split("( )( )*");
			points.add(new Point2D.Double(Double.parseDouble(parts[1]), Double
					.parseDouble(parts[2])));
		}
		scan.close();
		return points.toArray(new Point2D[0]);
	}
}
