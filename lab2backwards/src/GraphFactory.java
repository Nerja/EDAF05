import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphFactory {
	public static Map<String, List<String>> getNeighbours(String[] words) {
		List<char[]> sneakyCache = new ArrayList<char[]>(words.length);
		for (int i = 0; i < words.length; i++) {
			sneakyCache.add(i, words[i].toCharArray());
		}

		Map<String, List<String>> myGraph = new HashMap<String, List<String>>();
		for (int i = 0; i < words.length; i++) {
			attatch(i, myGraph, words, sneakyCache);
		}

		return myGraph;
	}

	private static void attatch(int index, Map<String, List<String>> myGraph,
			String[] words, List<char[]> sneakyCache) {
		myGraph.put(words[index], new LinkedList<String>());
		for (int i = 0; i < words.length; i++) {
			String possibleBuddy = words[i];
			if (possibleBuddy.equals(words[index]))
				continue;
			else if (ladderPossible(sneakyCache.get(index), sneakyCache.get(i)))
				myGraph.get(words[index]).add(possibleBuddy);
		}
	}

	private static boolean ladderPossible(char[] sourceChars, char[] targetChars) {
		boolean[] taken = new boolean[5];
		for (int i = 1; i < sourceChars.length; i++) {
			boolean foundChar = false;
			for (int k = 0; !foundChar && k < targetChars.length; k++) {
				if (sourceChars[i] == targetChars[k] && !taken[k]) {
					taken[k] = true;
					foundChar = true;
				}
			}
			if (!foundChar)
				return false;
		}
		return true;
	}
}
