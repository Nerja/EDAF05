import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MatchEngine {
	public static void makeMatches(List<Man> haunters) {
		List<Man> ini = new LinkedList<Man>(haunters);
		findMatchesForAllMen(haunters);
		printMatches(ini);
	}

	private static void printMatches(List<Man> ini) {
		for (Human m : ini) {
			System.out.println(m);
		}
	}

	private static void findMatchesForAllMen(List<Man> haunters) {
		boolean someAction = true;
		while (!haunters.isEmpty() && someAction) {
			someAction = false;
			someAction = makeProposeRound(haunters);
		}
	}

	private static boolean makeProposeRound(List<Man> haunters) {
		List<Man> snapshot = new LinkedList<Man>(haunters);
		ListIterator<Man> itr = snapshot.listIterator();
		boolean roundGeneratedAction = false;
		while (itr.hasNext()) {
			if (itr.next().makeProposeRound()) {
				roundGeneratedAction = true;
			}
		}
		return roundGeneratedAction;
	}

	public static void main(String[] args) throws IOException {
		makeMatches(HauntersFactory.haunters(args[0]));
	}

}
