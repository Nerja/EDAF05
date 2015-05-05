import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RailwayFactory {
	public static Map<Integer, List<FlowArc>> getGraph(String graphFile)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(graphFile));
		Map<Integer, List<FlowArc>> graph = new HashMap<Integer, List<FlowArc>>();
		loadNodes(br, graph);
		loadArcs(br, graph);
		br.close();
		return graph;
	}

	private static void loadArcs(BufferedReader br,
			Map<Integer, List<FlowArc>> graph) throws IOException {
		int numberOfArcs = Integer.parseInt(br.readLine());
		for (int i = 0; i < numberOfArcs; i++) {
			addUndirectedEdge(graph, br.readLine());
		}
	}

	private static void addUndirectedEdge(Map<Integer, List<FlowArc>> graph,
			String line) {
		String[] arcParts = line.split(" ");
		Integer node1 = Integer.parseInt(arcParts[0]);
		Integer node2 = Integer.parseInt(arcParts[1]);
		int capacity = Integer.parseInt(arcParts[2]);
		capacity = capacity == -1 ? Integer.MAX_VALUE : capacity; // Account for
		// infinity notation
		graph.get(node1).add(new FlowArc(node1, node2, capacity));
	}

	private static void loadNodes(BufferedReader br,
			Map<Integer, List<FlowArc>> graph) throws IOException {
		int numberOfNodes = Integer.parseInt(br.readLine());
		for (int i = 0; i < numberOfNodes; i++) {
			graph.put(i, new LinkedList<FlowArc>());
			br.readLine(); // ignore node names
		}
	}

	public static void main(String[] args) throws IOException {
		getGraph("files/rail.txt");
	}
}
