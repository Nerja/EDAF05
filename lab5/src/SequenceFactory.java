import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SequenceFactory {
	public static Sequence[] getSequences(String sequencefile)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(sequencefile));
		List<Sequence> sequenceList = new LinkedList<Sequence>();
		boolean animalParsed = true;
		while (animalParsed)
			animalParsed = parseAnimal(br, sequenceList);
		br.close();
		return sequenceList.toArray(new Sequence[0]);
	}

	private static boolean parseAnimal(BufferedReader br,
			List<Sequence> sequenceList) throws IOException {
		String header = br.readLine();
		if (header != null && !header.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			sb.append(br.readLine());
			sb.append(br.readLine());
			sb.append(br.readLine());
			String animal = header.split(" ")[0];
			animal = animal.substring(1, animal.length());
			sequenceList.add(new Sequence(animal, sb.toString()));
		}
		return header != null && !header.isEmpty();
	}
}
