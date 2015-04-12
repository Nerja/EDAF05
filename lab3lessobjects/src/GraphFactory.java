import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphFactory {

	public static Map<String, List<Edge<String>>> buildGraph(String fileName)
			throws IOException {
		Map<String, List<Edge<String>>> graph = new HashMap<String, List<Edge<String>>>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while (line != null && !line.isEmpty()) {
			parseLine(line, graph);
			line = br.readLine();
		}
		br.close();
		return graph;

	}

	private static void parseLine(String line,
			Map<String, List<Edge<String>>> graph) {
		if (!line.contains("["))
			saveCityName(line, graph);
		else
			saveCityConnection(line, graph);
	}

	private static void saveCityConnection(String line,
			Map<String, List<Edge<String>>> graph) {
		String[] parts = line.split("(--)|(\\[)|(\\])");
		String firstCity = parts[0].trim();
		String secondCity = parts[1].trim();
		int roadWeight = Integer.parseInt(parts[2].trim());
		Edge<String> edge = new Edge<String>(firstCity, secondCity, roadWeight);
		graph.get(firstCity).add(edge);
		graph.get(secondCity).add(edge);
	}

	private static void saveCityName(String line,
			Map<String, List<Edge<String>>> graph) {
		graph.put(line.trim(), new LinkedList<Edge<String>>());
	}
}
