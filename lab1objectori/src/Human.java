import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

public class Human {

	protected Map<Human, Integer> preferedList;
	protected String name;
	protected Human partner;

	public Human(String name) {
		preferedList = new LinkedHashMap<Human, Integer>();
		partner = null;
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " -- " + partner.name;
	}

	public void addDesiredPartner(Human haunters) {
		preferedList.put(haunters, preferedList.size());
	}

	public boolean reviewPropose(Human human, Queue<Human> haunters) {
		if (partner == null) {
			partner = human;
			return true;
		} else if(preferedList.get(human) < preferedList.get(partner)) {
			partner.releaseToMarket(haunters);
			partner = human;
			return true;
		}
		return false;
	}

	public void makeProposeRound(Queue<Human> haunters) {
		if(!propose(getProposeSuggestion(), haunters))
			haunters.add(this);	
	}

	private Human getProposeSuggestion() {
		Iterator<Map.Entry<Human, Integer>> itr = preferedList.entrySet()
				.iterator();
		Human suggestion = itr.next().getKey();
		preferedList.remove(suggestion);
		return suggestion;
	}

	private boolean propose(Human theVictim, Queue<Human> haunters) {
		if (theVictim.reviewPropose(this, haunters)) {
			partner = theVictim;
			return true;
		}
		return false;
	}

	public void releaseToMarket(Queue<Human> haunters) {
		haunters.add(this);
		partner = null;
	}

}
