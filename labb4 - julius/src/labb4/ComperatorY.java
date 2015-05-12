package labb4;

import java.util.Comparator;

public class ComperatorY implements Comparator<Node>{

	@Override
	public int compare(Node arg0, Node arg1) {
		if(arg0.getY() > arg1.getY()){
			return 1;
		} else if(arg0.getY() < arg1.getY()){
			return -1;
		}
		
		return 0;
	}
	

}
