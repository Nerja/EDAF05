import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointFactory {
	public static Point2D[] loadPoints(String filename) throws IOException {
		Scanner scan = new Scanner(new BufferedReader(new FileReader(filename)));
		scan.skip("(([A-Z].*)\\n)*");
		List<Point2D> points = new ArrayList<Point2D>();
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (!line.equals("") && !line.equals(" ") && !line.contains("EOF")) {
				String[] parts = line.trim().split(" +");
				points.add(new Point2D.Double(Double.parseDouble(parts[1]),
						Double.parseDouble(parts[2])));
			}
		}
		scan.close();
		return points.toArray(new Point2D[0]);
	}
}
