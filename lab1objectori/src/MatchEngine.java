import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MatchEngine {
	public static void makeMatches(List<Human> haunters) {
		List<Human> ini = new LinkedList<Human>(haunters);
		findMatchesForAllMen(haunters);
		printMatches(ini);
	}

	private static void printMatches(List<Human> ini) {
		for (Human human : ini) {
			System.out.println(human);
		}
	}

	private static void findMatchesForAllMen(List<Human> haunters) {
		boolean someAction = true;
		while (!haunters.isEmpty() && someAction) {
			someAction = makeProposeRound(haunters);
		}
	}

	private static boolean makeProposeRound(List<Human> haunters) {
		ListIterator<Human> itr = haunters.listIterator();
		boolean roundGeneratedAction = false;
		while (itr.hasNext()) {
			if (itr.next().makeProposeRound(itr)) {
				roundGeneratedAction = true;
			}
		}
		return roundGeneratedAction;
	}

	public static void main(String[] args) throws IOException {
		List<Human> rav = HauntersFactory.haunters(args[0]);
		makeMatches(rav);
	}

}
