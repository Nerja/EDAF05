package labb2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

	List<String> m_words;

	public Parser(String fileName) {
		m_words = new ArrayList<String>();
		try {
			parse(fileName);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void parse(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = null;
		while ((line = br.readLine()) != null) {

			m_words.add(line);
		}
	}
	
	public List<String> getWords(){
		return m_words;
	}
}
