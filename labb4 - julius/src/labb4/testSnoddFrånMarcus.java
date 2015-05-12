package labb4;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.Test;

public class testSnoddFrånMarcus {
	private final static String TESTDATA_DIR = "files2";
	private final static double ALLOWED_INACCURACY = 0.000000001;

	/**
	 * Method to run an actual test case.
	 * 
	 * @param testname
	 *            Name of test data to be used, e.g. "stable-marriage-friends".
	 * @throws IOException
	 */
	private void runTestCase(String testname, String expected)
			throws IOException {
		// System.out.println("Running test: " + testname);
		/*
		 * Divert stdout to buffer
		 */
		PrintStream oldOut = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);

		String[] args = new String[1];
		args[0] = testname;

		lab4.main(args);
		/*
		 * Restore stdout
		 */
		System.setOut(oldOut);
		String[] parts = expected.split(" ");
		String expectedString = parts[0] + " " + Double.parseDouble(parts[1]);
		boolean ok = expectedString.equals(baos.toString());
		if (!ok) {
			double myResp = Double.parseDouble(baos.toString().split(" ")[1]);
			double thoreResp = Double.parseDouble(parts[1]);
			System.out.format("%-30s%-30s%-30s\n", testname, myResp, thoreResp);
			ok = Math.abs(myResp - thoreResp) < ALLOWED_INACCURACY ? true
					: false;
			if (!ok) {
				System.out.println("FAILED TO DO " + testname + ": " + myResp
						+ "  with thore " + thoreResp);
			}
		}
		assertTrue(("FAILED TO DO " + testname), ok);
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
		File resultFile = new File(TESTDATA_DIR + "/closest-pair.out");
		BufferedReader br = new BufferedReader(new FileReader(resultFile));
		System.out.format("%-30s%-30s%-30s\n", "Filnamn", "Mitt svar",
				"Thores svar");
		String line = br.readLine();
		while (line != null) {
			String[] parts = line.split(":");
			runTestCase(parts[0], parts[1].trim());
			line = br.readLine();
		}
		br.close();
	}

}