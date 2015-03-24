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
		for (Human m : ini) {
			System.out.println(m);
		}
	}

	private static void findMatchesForAllMen(List<Human> haunters) {
		boolean someAction = true;
		while (!haunters.isEmpty() && someAction) {
			someAction = false;
			someAction = makeProposeRound(haunters);
		}
	}

	private static boolean makeProposeRound(List<Human> haunters) {
		List<Human> snapshot = new LinkedList<Human>(haunters);
		ListIterator<Human> itr = snapshot.listIterator();
		boolean roundGeneratedAction = false;
		while (itr.hasNext()) {
			if (itr.next().makeProposeRound()) {
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
