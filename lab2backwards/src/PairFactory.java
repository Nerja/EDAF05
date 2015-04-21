import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PairFactory {
	@SuppressWarnings("unchecked")
	public static Arc<String>[] readPairs(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<Arc<String>> pairs = new ArrayList<Arc<String>>();
		String line = br.readLine();
		while (line != null && !line.isEmpty()) {
			String[] parts = line.split(" ");
			pairs.add(new Arc<String>(parts[0], parts[1]));
			line = br.readLine();
		}
		br.close();
		return pairs.toArray(new Arc[0]);
	}
}
