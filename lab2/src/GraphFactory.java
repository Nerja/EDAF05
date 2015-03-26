import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphFactory {
	public static Map<String, List<String>> getGraph(String file)
			throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader(file));
		Map<String, List<String>> graph = new HashMap<String, List<String>>();
		String line = scan.readLine();
		while (line != null && !line.isEmpty()) {
			List<String> neighbours = connectNeighbours(line, graph);
			graph.put(line, neighbours);
			line = scan.readLine();
		}
		scan.close();
		return graph;
	}

	private static List<String> connectNeighbours(String newBuddy,
			Map<String, List<String>> graph) {
		List<String> neighbours = new LinkedList<String>();
		for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
			String possibleBuddy = entry.getKey();
			if (transferPossible(newBuddy, possibleBuddy)) {
				neighbours.add(possibleBuddy);
			}
			if (transferPossible(possibleBuddy, newBuddy)) {
				entry.getValue().add(newBuddy);
			}
		}
		return neighbours;
	}

	private static boolean transferPossible(String source, String target) {
		char[] targetChars = target.toCharArray();
		char[] sourceChars = source.substring(1, source.length()).toCharArray();
		for (int i = 0; i < sourceChars.length; i++) {
			boolean foundChar = false;
			for (int k = 0; !foundChar && k < targetChars.length; k++) {
				if (sourceChars[i] == targetChars[k]) {
					targetChars[k] = '#';
					foundChar = true;
				}
			}
			if (!foundChar)
				return false;
		}
		return true;
	}

}
