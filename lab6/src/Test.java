import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Test {
	private final static String TESTDATA_DIR = "files";

	private void runTestCase(String testFile, int source, int terminal,
			String expectedResult) throws IOException {
		PrintStream oldOut = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);
		String[] args = new String[3];
		args[0] = testFile;
		args[1] = source + "";
		args[2] = terminal + "";

		MaxFlowFinder.main(args);

		/*
		 * Restore stdout
		 */
		System.setOut(oldOut);
		System.out.print("GOT : " + baos.toString() + "  EXPECTED  "
				+ expectedResult + "\t");
		boolean passed = baos.toString().equals(expectedResult);
		assertTrue("Testcase : " + testFile + " did not pass!", passed);
		System.out.println(passed ? "OK!" : "EJ OK!");
	}

	@org.junit.Test
	public void testAll() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(TESTDATA_DIR
				+ "/result.txt"));
		String test = br.readLine();
		while (test != null && !test.isEmpty()) {
			String[] testParts = test.split(" ");
			String testFile = TESTDATA_DIR + "/" + testParts[0];
			int testSource = Integer.parseInt(testParts[1]);
			int testTerminal = Integer.parseInt(testParts[2]);
			String testExpectedResult = testParts[3];
			runTestCase(testFile, testSource, testTerminal, testExpectedResult);
			test = br.readLine();
		}
		br.close();
	}

}
