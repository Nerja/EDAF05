package labb4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parser {
	
	List<Node> m_nodes;
	String m_fileName;
	
	public Parser(String fileName) throws IOException{
		m_fileName = fileName;
		m_nodes = new LinkedList<Node>();
		Scanner scan = new Scanner( new BufferedReader(new FileReader(fileName)));
		scan.skip("([A-Z](_| |[A-Z])*(( )?:( )?([A-Z]|[a-z]|.|[0-9])*)?\\n)*");
		
		while(scan.hasNextDouble()){
			String[] split = scan.nextLine().trim().split("( )( )*");
			m_nodes.add(new Node(Double.parseDouble(split[1]), Double.parseDouble(split[2])));
		}
		scan.close();
	}
	

	public void printAll() {
		int i = 1;
		for(Node n : m_nodes){
			System.out.println(i + " " + n.toString());
			i++;
		}
		
	}


	public String printPrefix() {
		//return (m_fileName+": "+m_nodes.size());
		return (m_fileName+": ");
	}


	public List<Node> getNodes() {
		return m_nodes;
	}

}
