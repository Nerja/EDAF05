import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class MatchEngine {
	
	public static void main(String[] args) throws IOException {
		makeMatches(HauntersFactory.haunters(args[0]));
	}
	
	public static void makeMatches(List<Human> ini) {
		Queue<Human> haunters = new LinkedList<Human>(ini);
		findMatchesForAllMen(haunters);
		printMatches(ini);
	}

	private static void printMatches(List<Human> ini) {
		for (Human human : ini) {
			System.out.println(human);
		}
	}

	private static void findMatchesForAllMen(Queue<Human> haunters) {
		while (!haunters.isEmpty()) {
			haunters.poll().makeProposeRound(haunters);
		}
	}
	
}
