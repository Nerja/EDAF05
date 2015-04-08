import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LaunchBad {
	public static void main(String[] args) throws IOException {
		String[] words = WordFactory.readWords((args[0]));
		Map<String, List<String>> neighbours = GraphFactory
				.getNeighbours(words);
		Pair<String>[] wantedMappings = PairFactory.readPairs(args[1]);

		for (Pair<String> wantedMapping : wantedMappings) {
			System.out.println(findDistance(wantedMapping, neighbours));
		}
	}

	private static int findDistance(Pair<String> pair,
			Map<String, List<String>> graph) {
		Set<String> visitedNodes = new HashSet<String>();
		Queue<String> currentLayer = new LinkedList<String>();
		currentLayer.offer(pair.getP1());
		int layerCounter = 0;
		boolean found = false;
		while (!found && !currentLayer.isEmpty()) {
			Queue<String> nextLayer = new LinkedList<String>();
			for (String nodeInCurrentLayer : currentLayer) {
				if (nodeInCurrentLayer.equals(pair.getP2())) {
					found = true;
				} else {
					for (String neighbour : graph.get(nodeInCurrentLayer)) {
						if (visitedNodes.add(neighbour))
							nextLayer.offer(neighbour);
					}
				}
			}
			if (!found) {
				currentLayer = nextLayer;
				layerCounter++;
			}
		}
		return found ? layerCounter : -1;
	}
}
