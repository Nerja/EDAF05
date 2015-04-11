import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphFactory {
	public static Map<String, List<String>> getNeighbours(String[] words) {
		Map<String, List<String>> myGraph = new HashMap<String, List<String>>();
		for (int i = 0; i < words.length; i++) {
			attatch(i, myGraph, words);
		}

		return myGraph;
	}

	private static void attatch(int index, Map<String, List<String>> myGraph,
			String[] words) {
		myGraph.put(words[index], new LinkedList<String>());
		for (int i = 0; i < words.length; i++) {
			String possibleBuddy = words[i];
			if (possibleBuddy.equals(words[index]))
				continue;
			else if (ladderPossible(words[index], words[i]))
				myGraph.get(words[index]).add(possibleBuddy);
		}
	}
	
	private static boolean ladderPossible(String source, String target) {
		List<Character> targetChars = new LinkedList<Character>();
		for(Character c : target.toCharArray()) {
			targetChars.add(c);
		}
		for(int i = 1; i < source.length(); i++) {
			Character currentDesiredChar = source.charAt(i);
			if(targetChars.contains(currentDesiredChar))
				targetChars.remove(currentDesiredChar);
			else
				return false;
		}
		return true;
	}
}
