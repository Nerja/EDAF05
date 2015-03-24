import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UglyImp {
	public static void main(String[] args) {
		Scanner scan = new Scanner(args[0]);
		String line = scan.nextLine();
		while (line.charAt(0) == '#')
			line = scan.nextLine();

		int n = Integer.parseInt(line.split("=")[1]);
		Map<Integer, Integer> femalePreferences[] = new LinkedHashMap[n];
		List<Integer> malePreferences[] = new ArrayList[n];

		line = scan.nextLine();
		while (line != null) {

			line = scan.nextLine();
		}

	}
}
