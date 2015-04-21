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

	private static int findMstWeight(Map<String, List<Arc<String>>> graph) {
		Queue<Arc<String>> edgesList = new PriorityQueue<Arc<String>>();
		Set<String> visitedCities = new HashSet<String>();
		Entry<String, List<Arc<String>>> start = graph.entrySet().iterator()
				.next();
		edgesList.addAll(start.getValue());
		visitedCities.add(start.getKey());
		int totalMstWeight = 0;
		while (!edgesList.isEmpty() && visitedCities.size() < graph.size()) {
			Arc<String> choosenEdge = edgesList.poll();
			if (!visitedCities.contains(choosenEdge.getDest())) {
				String choosenNode = choosenEdge.getDest();
				visitedCities.add(choosenNode);
				edgesList.addAll(graph.get(choosenNode));
				totalMstWeight += choosenEdge.getWeight();
			}
		}
		return totalMstWeight;
	}

}
