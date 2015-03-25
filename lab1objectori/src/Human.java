import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

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

	public void addDesiredPartner(Human human) {
		preferedList.put(human, preferedList.size());
	}

	public boolean reviewPropose(Human human, ListIterator<Human> itr) {
		boolean accepted = true;
		if (partner == null) {
			partner = human;
		} else {
			accepted = preferedList.get(human) < preferedList.get(partner);
			if (accepted) {
				partner.releaseToMarket(itr);
				partner = human;
			}
		}
		return accepted;
	}

	public boolean makeProposeRound(ListIterator<Human> itr) {
		boolean someoneToProposeTo = !preferedList.isEmpty() && partner == null;
		if (someoneToProposeTo) {
			propose(getProposeSuggestion(), itr);
		}
		return someoneToProposeTo;
	}

	private Human getProposeSuggestion() {
		Iterator<Map.Entry<Human, Integer>> itr = preferedList.entrySet()
				.iterator();
		Human suggestion = itr.next().getKey();
		preferedList.remove(suggestion);
		return suggestion;
	}

	private void propose(Human theVictim, ListIterator<Human> itr) {
		if (theVictim.reviewPropose(this, itr)) {
			partner = theVictim;
		}
	}

	public void releaseToMarket(ListIterator<Human> itr) {
		itr.remove();
		itr.add(this);
		partner = null;
	}

}
