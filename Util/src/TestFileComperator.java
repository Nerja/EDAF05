import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestFileComperator {
	
	private static final String TEST_FILE1 = "t1";
	private static final String TEST_FILE2 = "t2";
	private FileWriter bw1, bw2;
	
	@Before
	public void setUp() throws IOException {
		File f1 = new File(TEST_FILE1);
		File f2 = new File(TEST_FILE2);
		
		f1.delete();
		f2.delete();
		
		bw1 = new FileWriter(f1);
		bw2 = new FileWriter(f2);
	}
	
	@After
	public void tearDown() throws IOException {
		bw1.close();
		bw2.close();
		File f1 = new File(TEST_FILE1);
		File f2 = new File(TEST_FILE2);
		
		f1.delete();
		f2.delete();
	}

	@Test
	public void twoEmptyShouldReturnTrue() throws IOException {
		assertTrue("Två tomma är identiska", compareTestFiles());
	}
	
	@Test
	public void equalOneLetterShouldReturnTrue() throws IOException {
		bw1.write("H");
		bw2.write("H");
		assertTrue("Båda filerna har samma tecken...", compareTestFiles());
	}
	
	@Test
	public void equalOneSentenceShouldReturnTrue() throws IOException {
		bw1.write("Hej, jag heter Marcus");
		bw2.write("Hej, jag heter Marcus");
		assertTrue("Två identiska meningar", compareTestFiles());
	}
	
	@Test
	public void twoEqualOneLetterLinesShouldReturnTrue() throws IOException {
		bw1.write("H\nM");
		bw2.write("H\nM");
		assertTrue("Två tomma är identiska", compareTestFiles());
	}
	
	@Test
	public void twoEqualSentenceLinesShouldReturnTrue() throws IOException {
		bw1.write("Hejsan jag\när kung");
		bw2.write("Hejsan jag\när kung");
		assertTrue("Två identiska rader", compareTestFiles());
	}
	
	@Test
	public void nonEqualLetterShouldReturnFalse() throws IOException {
		bw1.write("H");
		bw2.write("k");
		assertFalse("Olika tecken", compareTestFiles());
	}
	
	@Test
	public void nonEqualSentenceShouldReturnFalse() throws IOException {
		bw1.write("Hej, jag heter Marcus");
		bw2.write("Detta är en annan mening");
		assertFalse("Olika rader", compareTestFiles());
	}
	
	@Test
	public void nonEqualSentenceCountShouldReturnFalse() throws IOException {
		bw1.write("Hej, jag heter\n Marcus");
		bw2.write("Hej, jag heter Marcus");
		assertFalse("Olika rader", compareTestFiles());
	}
	
	private boolean compareTestFiles() throws IOException {
		bw1.flush();
		bw2.flush();
		return FileComperator.compareFiles(TEST_FILE1, TEST_FILE2);
	}

}
