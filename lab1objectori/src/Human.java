import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Human {

	protected Map<Human, Integer> preferedList;
	protected String name;
	protected Human partner;

	public Human(String name) {
		preferedList = new LinkedHashMap<Human, Integer>();
		partner = null;
		this.name = name;
	}

	public void releaseToMarket() {
		partner = null;
	}

	@Override
	public String toString() {
		return name + " -- " + partner.name;
	}

	public void addDesiredPartner(Human human) {
		preferedList.put(human, preferedList.size());
	}

	public boolean reviewPropose(Human human) {
		if (partner == null) {
			partner = human;
			return true;
		} else {
			if (preferedList.get(human) < preferedList.get(partner)) {
				partner.releaseToMarket();
				partner = human;
				return true;
			} else {
				return false;
			}
		}
	}

}
