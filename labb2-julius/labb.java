package labb2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class labb {

	public static void main(String[] args) throws IOException{
		Parser parser = new Parser("in3.txt");
		Graph graph = new Graph();
		graph.buildGraph(parser.getWords());
		
		BufferedReader br = new BufferedReader(new FileReader("input3.txt"));
		String ind = null;


		while((ind = br.readLine()) != null){
			String[] split = ind.split(" ");
			System.out.println(graph.getDistBetween(split[0], split[1]));
			graph.resetGraph();
		}
		
	}
}
