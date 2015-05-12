package labb4;

public class Node {

	private double m_x;
	private double m_y;

	public Node(double x, double y){
		m_x = x;
		m_y = y;
	}
	
	public double distBetween(Node n){
		double dist = -1;
		double x = Math.pow(n.getX() - m_x, 2);
		double y = Math.pow(n.getY() - m_y, 2);
		
		
		return Math.sqrt((x+y));
	}
	
	public double getY() {
		return m_y;
	}

	public double getX() {
		return m_x;
	}

	@Override
	public String toString(){
		return ("x: " + String.valueOf(m_x) + " y: " + String.valueOf(m_y));
	}
}
