import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PairFactory {
	public static Pair[] readPairs(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<Pair> pairs = new ArrayList<Pair>();
		String line = br.readLine();
		while(line != null && !line.isEmpty()) {
			String[] parts = line.split(" ");
			pairs.add(new Pair(parts[0], parts[1]));
			line = br.readLine();
		}
		br.close();
		return pairs.toArray(new Pair[1]);
	}
	
	public static void main(String[] args) throws IOException {
		long m = System.currentTimeMillis();
		readPairs("files/words-5757-test.in");
		System.out.println("Pairload is : " + (System.currentTimeMillis() - m));
	}
}
