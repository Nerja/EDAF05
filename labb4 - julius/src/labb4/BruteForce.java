package labb4;

import java.util.List;

public class BruteForce {
	List<Node> m_nodes;
	
	public BruteForce(List<Node> nodes){
		m_nodes = nodes;
	}
	public BruteForce(){
		
	}
	public double run(List<Node> nodes){
		double minDist = Double.MAX_VALUE;
		for(Node n : nodes){
			for(Node m : nodes){
				if(!n.equals(m)){
					if(n.distBetween(m) <= minDist){
						minDist = n.distBetween(m);
					}
				}
			}
		}
		return minDist;
	}

	
	public String run(){
		double minDist = Double.MAX_VALUE;
		for(Node n : m_nodes){
			for(Node m : m_nodes){
				if(!n.equals(m)){
					if(n.distBetween(m) <= minDist){
						minDist = n.distBetween(m);
					}
				}
			}
		}
		return String.valueOf(minDist);
	}
}
