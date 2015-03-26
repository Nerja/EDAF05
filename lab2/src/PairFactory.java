import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PairFactory {
	public static List<Pair<String>> getPairs(String file) throws IOException {
		List<Pair<String>> pairList = new LinkedList<Pair<String>>();
		BufferedReader scan = new BufferedReader(new FileReader(file));
		String line = scan.readLine();
		while (line != null && !line.isEmpty()) {
			String[] parts = line.split(" ");
			Pair<String> p = new Pair<String>(parts[0], parts[1]);
			pairList.add(p);
			line = scan.readLine();
		}
		scan.close();
		return pairList;
	}
}
