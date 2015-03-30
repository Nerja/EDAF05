import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SearchEngine {
	public static void main(String[] args) throws IOException {
		long tt = System.currentTimeMillis();
		String[] words = WordFactory.readWords(("files/words-5757.dat"));
		List[] graphi = GraphFactory.getNeighbours(words);
		Pair[] wantedMappings = PairFactory.readPairs("files/words-5757-test.in");
		Map<String, List<String>> graph = new HashMap<String, List<String>>();
		
		for(int i = 0; i < words.length; i++) {
			graph.put(words[i], graphi[i]);
		}
	
		List<Pair<String>> pairList = new ArrayList<Pair<String>>();
		for (Pair pair : wantedMappings) {
			pairList.add(pair);
		}
	}

	private static int findDistance(Pair<String> pair,
			Map<String, List<String>> graph) {
		long p = System.currentTimeMillis();
		Set<String> visitedNodes = new HashSet<String>();
		Queue<String> currentLayer = new LinkedList<String>();
		currentLayer.offer(pair.getP1());
		int layerCounter = 0;
		boolean found = false;
		while (!found && !currentLayer.isEmpty()) {
			Queue<String> nextLayer = new LinkedList<String>();
			for (String nodeInCurrentLayer : currentLayer) {
				if (nodeInCurrentLayer.equals(pair.getP2()))
					found = true;
				for (String neighbour : graph.get(nodeInCurrentLayer)) {
					if (visitedNodes.add(neighbour))
						nextLayer.offer(neighbour);
				}
			}
			if (!found) {
				currentLayer = nextLayer;
				layerCounter++;
			}
		}
		System.out.println("this took: " + (System.currentTimeMillis() - p));
		return found ? layerCounter : -1;
	}

}
