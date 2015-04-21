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
		String graphResultFile = TESTDATA_DIR + SC + testname + ".out";
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

		try {
			StringBuilder sb = new StringBuilder();
			FileInputStream is = new FileInputStream(new File(graphResultFile));
			byte buffer[] = new byte[1024];

			while (is.available() != 0) {
				int i = is.read(buffer);
				sb.append(new String(buffer, 0, i).trim());
			}
			is.close();
			assertEquals(sb.toString(), baos.toString());
		} catch (FileNotFoundException e) {
			fail("File " + graphResultFile + " not found.");
		} catch (IOException e) {
			fail("Error reading " + graphResultFile);
		}

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