import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Util {
	public static Map<Integer, List<FlowArc>> getEmptyGraph(int n) {
		Map<Integer, List<FlowArc>> graph = new HashMap<Integer, List<FlowArc>>();
		for (int i = 0; i < n; i++) {
			graph.put(i, new LinkedList<FlowArc>());
		}
		return graph;
	}

	public static Map<Integer, List<ResidualArc>> getEmptyResidualGraph(int n) {
		Map<Integer, List<ResidualArc>> graph = new HashMap<Integer, List<ResidualArc>>();
		for (int i = 0; i < n; i++) {
			graph.put(i, new LinkedList<ResidualArc>());
		}
		return graph;
	}
}