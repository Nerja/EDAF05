import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Test {
	private final static String TESTDATA_DIR = "files";
	
	private void runTestCase(String testname, String expected) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(expected));
		Set<String> expectedLines = new HashSet<String>();
		String line = br.readLine();
		while(line != null && !line.isEmpty()) {
		//	System.out.println("EXPECTING: " + "#" + line + "#");
			expectedLines.add(line);
			line = br.readLine();
		}
		br.close();
		PrintStream oldOut = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);
		String[] args = new String[2];
		args[0] = "files/BLOSUM62.txt";
		args[1] = testname;
		SequenceMatching.main(args);
		/*
		 * Restore stdout
		 */
		System.setOut(oldOut);
		String[] lines = baos.toString().split("\n");
		boolean allMatched = true;
		for(int i = 0; allMatched && i < lines.length; i++) {
			String outputLine = lines[i];
			allMatched = expectedLines.contains(outputLine);
			if(!allMatched) {
				String[] parts = outputLine.split("--");
				String[] newParts = parts[1].split(":");
				String otherPossibility = newParts[0] + "--" + parts[0] + ":"+  newParts[1];
				allMatched = expectedLines.contains(otherPossibility);
			}
			System.out.format("%40s%10s\n", outputLine, (allMatched ? " OK!" : " ERROR!"));
		}
		assertTrue(allMatched);
	}
	
	@org.junit.Test
	public void testAll() throws IOException {
		File[] filesInDir = (new File(TESTDATA_DIR)).listFiles();
		for(File file : filesInDir) {
			String name = file.getPath();
			if(name.contains("-in")) {
				if(name.contains("Toy")) {
					continue;
				}
				String expected = name.split("-")[0] + "-out.txt";
				runTestCase(name, expected);
			}
		}
	}

}
