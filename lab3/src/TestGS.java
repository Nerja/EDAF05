import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class TestGS {
	private final static String TESTDATA_DIR = "files";
	private final static char SC = File.separatorChar;

	/**
	 * Method to run an actual test case.
	 * 
	 * @param testname
	 *            Name of test data to be used, e.g. "stable-marriage-friends".
	 * @throws IOException
	 */
	private void runTestCase(String testname) throws IOException {
		System.out.println("Running test: " + testname);
		String graphFile = TESTDATA_DIR + SC + testname + ".in";
		System.out.println(graphFile);
		/*
		 * Divert stdout to buffer
		 */
		PrintStream oldOut = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);

		String[] args = new String[1];
		args[0] = graphFile;
		HighwayOptimizer.main(args); // FIXME: Change GS to your own class!

		/*
		 * Restore stdout
		 */
		System.setOut(oldOut);

		assertEquals("16598", baos.toString());
	}

	/**
	 * Test case that will use all test data it can find in TESTDATA_DIR.
	 * 
	 * You may want to comment this out until you think your program works, as
	 * this test can take some time to execute.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testAll() throws IOException {
		File dir = new File(TESTDATA_DIR);

		for (File f : dir.listFiles()) {
			if (f.isFile() && f.toString().endsWith(".in")) {
				String s = f.toString();
				s = s.substring(s.lastIndexOf(SC) + 1);
				s = s.substring(0, s.lastIndexOf(".in"));

				runTestCase(s);
			}
		}
	}

}