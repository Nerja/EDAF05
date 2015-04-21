package labb3;

import java.io.IOException;

public class labb3 {

	
	public static void main(String[] args){
		
		//String fileName = "files/tinyEWG-alpha.txt";
		//String fileName = "files/test.txt";
		//String fileName = "files/USA-highway-miles.txt";
		//String fileName = args[0];
		String fileName = "files/tiny.txt";
		try {
			Parser parser = new Parser(fileName);
			Prim prim = new Prim();

			System.out.println(prim.buildTree(parser.getNodes()));
			System.out.println(prim.printEdges());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
