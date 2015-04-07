import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphFactory {

	public static Graph<String> buildGraph(String fileName) throws IOException {
		Graph<String> graph = new Graph<String>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while (line != null && !line.isEmpty()) {
			parseLine(line, graph);
			line = br.readLine();
		}
		br.close();
		return graph;

	}

	private static void parseLine(String line, Graph<String> graph) {
		if (!line.contains("["))
			saveCityName(line, graph);
		else
			saveCityConnection(line, graph);
	}

	private static void saveCityName(String line, Graph<String> graph) {
		graph.addNode(line.trim());
	}

	private static void saveCityConnection(String line, Graph<String> graph) {
		String[] parts = line.split("(--)|(\\[)|(\\])");
		Node<String> node1 = graph.getNode(parts[0].trim());
		Node<String> node2 = graph.getNode(parts[1].trim());
		int weight = Integer.parseInt(parts[2].trim());
		graph.addEdge(new Edge<String>(node1, node2, weight));
	}

}
