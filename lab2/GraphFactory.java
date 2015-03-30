import java.util.ArrayList;
import java.util.List;


public class GraphFactory {
	public static List<String>[] getNeighbours(String[] words) {
		List<char[]> sneakyCache = new ArrayList<char[]>(words.length);
		for(int i = 0; i < words.length; i++) {
			sneakyCache.add(i, words[i].toCharArray());
		}
		
		@SuppressWarnings("unchecked")
		List<String>[] neighbours = (List<String>[])new ArrayList[words.length];
		for(int i = 0; i < words.length; i++) {
			attatch(i, neighbours, words, sneakyCache);
		}
		return neighbours;
	}

	private static void attatch(int index, List<String>[] neighbours, String[] words, List<char[]> sneakyCache) {
		neighbours[index] = new ArrayList<String>();
		for(int i = 0; i < words.length; i++) {
			String possibleBuddy = words[i];
			if(possibleBuddy.equals(words[index]))
				continue;
			else if(ladderPossible(sneakyCache.get(index), sneakyCache.get(i)))
				neighbours[index].add(possibleBuddy);
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
