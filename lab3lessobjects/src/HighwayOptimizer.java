import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class HighwayOptimizer {
	public static void main(String[] args) throws IOException {
		System.out.print(findMstWeight(GraphFactory.buildGraph(args[0])));
	}

	private static int findMstWeight(Map<String, List<Edge<String>>> graph) {
		Queue<Edge<String>> edgesList = new PriorityQueue<Edge<String>>();
		Set<String> visitedCities = new HashSet<String>();
		Entry<String, List<Edge<String>>> start = graph.entrySet().iterator()
				.next();
		edgesList.addAll(start.getValue());
		visitedCities.add(start.getKey());
		int totalMstWeight = 0;
		while (!edgesList.isEmpty() && visitedCities.size() < graph.size()) {
			Edge<String> choosenEdge = edgesList.poll();
			String choosenNode = chooseNode(choosenEdge, visitedCities);
			if (choosenNode != null) {
				visitedCities.add(choosenNode);
				edgesList.addAll(graph.get(choosenNode));
				totalMstWeight += choosenEdge.getWeight();
			}
		}
		return totalMstWeight;
	}

	private static String chooseNode(Edge<String> edge,
			Set<String> visitedCities) {
		boolean vertex1Visited = visitedCities.contains(edge.getVertex1());
		boolean vertex2Visited = visitedCities.contains(edge.getVertex2());
		if (vertex1Visited && vertex2Visited) {
			return null;
		}
		return !vertex1Visited ? edge.getVertex1() : edge.getVertex2();
	}

}
