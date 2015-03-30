import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WordFactory {
	public static String[] readWords(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		List<String> words = new ArrayList<String>();
		while(line != null && !line.isEmpty()) {
			words.add(line);
			line = br.readLine(); 
		}
		br.close();
		return words.toArray(new String[1]);
	}
	
	public static void main(String[] args) throws IOException {
		long t = System.currentTimeMillis();
		String[] words = readWords("files/words-5757.dat");
		System.out.println("Wordread took: " + (System.currentTimeMillis() - t) + " loaded" + words.length);
	}
}
