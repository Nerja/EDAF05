package labb4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DnC {

	List<Node> m_nodes;
	List<Node> m_sortX;
	List<Node> m_sortY;
	BruteForce m_bf;
	public DnC(List<Node> nodes){
		m_nodes = nodes;
		m_bf = new BruteForce();
		
		m_sortX = new LinkedList<Node>(m_nodes);
		Collections.sort(m_sortX, new ComperatorX());
		m_sortY = new LinkedList<Node>(m_nodes);
		Collections.sort(m_sortY, new ComperatorY());
	}

	public double run(){
		return run(m_sortX, m_sortY);
	}
	public double run(List<Node> sortX, List<Node>sortY){
		if(sortX.size() <= 3){
			return m_bf.run(sortX);
		}
		
		int middle = sortX.size() >>> 1;
		List<Node> leftX = sortX.subList(0, middle);
		List<Node> rightX = sortX.subList(middle, sortX.size());
		
		List<Node> tempL = new LinkedList<Node>(leftX);
		sortByY(tempL);
		double shortestDistLeft = run(leftX, tempL);
		
		tempL.clear();
		tempL.addAll(rightX);
		sortByY(tempL);
		double shortestDistRight = run(rightX, tempL);
		
		double shortestDist = Math.min(shortestDistLeft, shortestDistRight);
		
		tempL.clear();
		
		double centerX = rightX.get(0).getX();
		for(Node n : sortY){
			if(Math.abs(centerX - n.getX()) < shortestDist){
				tempL.add(n);
			}
		}
		
		for(int i = 0; i < tempL.size() - 1; i++){
			Node n1 = tempL.get(i);
			for(int j = i+1; j < tempL.size(); j++){
				Node n2 = tempL.get(j);
				if((n2.getY() - n1.getY()) >= shortestDist){
					break;
				}
				double dist = n1.distBetween(n2);
				if(dist < shortestDist){
					shortestDist = dist;
				}
			}
		}
		
		
		return shortestDist;
	}
	
	private void sortByY(List<Node> list){
		Collections.sort(list, new ComperatorY());
	}
	
	
}
