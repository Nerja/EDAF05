package labb3;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class testLabb3 {

	@Test
	public void test() throws IOException {
		String fileName = "files/USA-highway-miles.txt";
		//String fileName = "files/tiny.txt";
		Parser parser = new Parser(fileName);
		Prim prim = new Prim();
		int i = prim.buildTree(parser.getNodes());
		
		System.out.println(i);
		assertEquals(16598, i);
	}

}
