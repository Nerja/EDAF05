import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PairFactory {
	@SuppressWarnings("unchecked")
	public static Pair<String>[] readPairs(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<Pair<String>> pairs = new ArrayList<Pair<String>>();
		String line = br.readLine();
		while (line != null && !line.isEmpty()) {
			String[] parts = line.split(" ");
			pairs.add(new Pair<String>(parts[0], parts[1]));
			line = br.readLine();
		}
		br.close();
		return pairs.toArray(new Pair[1]);
	}
}
