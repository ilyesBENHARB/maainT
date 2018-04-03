import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search {

	public static void main(String[] args) throws IOException {

		String dictExclu = args[0];
		String words = args[1];

		ArrayList<String> dictExcluList = new ArrayList<String>();
		ArrayList<String> wordsList = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(dictExclu))) {

			String line;
			while ((line = br.readLine()) != null) {

				dictExcluList.add(line);

			}

			try (BufferedReader br1 = new BufferedReader(new FileReader(words))) {

				while ((line = br1.readLine()) != null) {

					wordsList.add(line);

				}

			}

		}

	}
}
