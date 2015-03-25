import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HauntersFactory {
	public static List<Human> haunters(String file) throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader(file));
		Integer p = Integer.parseInt(skipComments(scan).split("=")[1]);
		List<Human> ravers = new ArrayList<Human>(p / 2);
		Human[] humans = new Human[p * 2];
		loadHumans(scan, humans, ravers);
		loadDesiredPartners(scan, humans);
		scan.close();
		return ravers;
	}

	private static void loadDesiredPartners(BufferedReader scan, Human[] humans)
			throws IOException {
		String line = scan.readLine();
		while (line != null && !line.isEmpty()) {
			StringTokenizer st = new StringTokenizer(line, ": ");
			Integer myId = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				Human desiredPartner = humans[Integer.parseInt(st.nextToken()) - 1];
				humans[myId - 1].addDesiredPartner(desiredPartner);
			}
			line = scan.readLine();
		}
	}

	private static void loadHumans(BufferedReader scan, Human[] humans,
			List<Human> ravers) throws IOException {
		String line;
		line = scan.readLine();
		int index = 1;
		while (line != null && !line.isEmpty()) {
			String[] parts = line.split(" ");
			Human human = new Human(parts[1]);
			humans[index - 1] = human;
			if (index % 2 == 1)
				ravers.add(human);

			index++;
			line = scan.readLine();
		}
	}

	private static String skipComments(BufferedReader scan) throws IOException {
		String line = scan.readLine();
		while (line.charAt(0) == '#') {
			line = scan.readLine();
		}
		return line;
	}
}