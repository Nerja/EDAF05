package labb4;

import java.util.Comparator;

public class ComperatorX implements Comparator<Node>{

	@Override
	public int compare(Node arg0, Node arg1) {
		if(arg0.getX() > arg1.getX()){
			return 1;
		} else if(arg0.getX() < arg1.getX()){
			return -1;
		}
		
		return 0;
	}
	

}
