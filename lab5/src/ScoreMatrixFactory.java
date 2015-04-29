import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreMatrixFactory {
	public static Map<String, Integer> getScoreMatrix(String scorematrixfile)
			throws IOException {
		Map<String, Integer> matrix = new HashMap<String, Integer>();
		BufferedReader scan = new BufferedReader(new FileReader(
				scorematrixfile));
		String[] headers = getHeaders(scan);
		String line = scan.readLine();
		while(line != null && !line.isEmpty()) {
			parseLine(line, matrix, headers);
			line = scan.readLine();
		}
		scan.close();
		return matrix;
	}

	private static void parseLine(String line, Map<String, Integer> matrix, String[] headers) {
		String[] rowElements = getElements(line);
		Character rowIndex = rowElements[0].charAt(0);
		for(int i = 1; i < rowElements.length; i++) {
			Character collIndex = headers[i-1].charAt(0);
			matrix.put(""+rowIndex + collIndex, new Integer(Integer.parseInt(rowElements[i])));
		}
	}

	private static String[] getHeaders(BufferedReader scan) throws IOException {
		String line = scan.readLine();
		while(line.charAt(0) == '#')
			line = scan.readLine();
		return getElements(line);
	}
	
	public static String[] getElements(String line) {
		List<String> headers = new ArrayList<String>();
		String[] vector = line.split(" ");
		for(int i = 0; i < vector.length; i++) {
			if(!vector[i].isEmpty() && vector[i].charAt(0) != ' ')
				headers.add(vector[i]);
		}
		return headers.toArray(new String[0]);
	}
}
