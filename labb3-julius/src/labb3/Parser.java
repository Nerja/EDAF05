package labb3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Parser {
	List<Node> m_nodes;
	List<Edge> m_edges;

	public Parser(String fileName) throws IOException {
		m_nodes = new LinkedList<Node>();
		m_edges = new LinkedList<Edge>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;

		while ((line = br.readLine()) != null) {
			line = line.replace("\"", "");
			line = line.trim();
			if (!line.contains("[")) {
				m_nodes.add(new Node(line));
			} else {
				String[] split = line.split("(--)|(\\[)|(\\])");
				split[0] = split[0].trim();
				split[1] = split[1].trim();
				Node n1 = null;
				Node n2 = null;
				for (Node n : m_nodes) {
					if (n.getName().equals(split[0])) {
						n1 = n;
					} else if (n.getName().equals(split[1])) {
						n2 = n;
					}
					if((n1 != null) && (n2!=null)){
						break;
					}
				}
				
				if(n1 != null && n2 !=null){
					m_edges.add(new Edge(n1, n2, Integer.parseInt(split[2])));
				}
				
			}

		}
	}

	public List<Node> getNodes() {
		return m_nodes;
	}

	public List<Edge> getEdges() {
		return m_edges;
	}

}
