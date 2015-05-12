package labb4;

import java.io.IOException;
import java.util.LinkedList;

public class lab4 {

	public static void main(String[] args) throws IOException{
		//Parser parser = new Parser("files/a280.tsp");
		//Parser parser = new Parser("files/att48.tsp");
		System.out.println(args[0]);
		Parser parser = new Parser(args[0]);
		//parser.printAll();
		LinkedList<Node> nodes = (LinkedList<Node>) parser.getNodes();
		
		DnC algo_A = new DnC(nodes);
		
		System.out.println(parser.printPrefix() + algo_A.run());
		
		//BruteForce Algo_b = new BruteForce(nodes);
		//System.out.println(parser.printPrefix() +" "+ new BruteForce().run(nodes));
		//System.out.println(parser.printPrefix() + " " + Algo_b.run());
		
	}
}
