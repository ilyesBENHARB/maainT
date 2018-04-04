import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search {

	public void doSearch(String dictEx, String words, String collecteur) throws IOException {

		ArrayList<String> dictExcluList = new ArrayList<String>();
		ArrayList<String> wordsList = new ArrayList<String>();
		ArrayList<String> wordsNew = new ArrayList<String>();
		ArrayList<Integer> idsList = new ArrayList<Integer>();

		try (BufferedReader br = new BufferedReader(new FileReader(dictEx))) {

			String line;
			while ((line = br.readLine()) != null) {

				dictExcluList.add(line);

			}
		}

		try (BufferedReader br1 = new BufferedReader(new FileReader(words))) {

			String line;
			while ((line = br1.readLine()) != null) {

				wordsList.add(line);

			}

		}

		for (int i = 0; i < wordsList.size(); i++) {
			boolean exit = false;
			int j = 0;
			while ((j < dictExcluList.size()) && (exit == false)) {

				if (wordsList.get(i).equals(dictExcluList.get(j))) {
					exit = true;
				}
				j++;

			}
			if (exit == false) {
				wordsNew.add(wordsList.get(i));
			}
		}

		try (BufferedReader br3 = new BufferedReader(new FileReader(collecteur))) {

			String line;
			String[] lineSplit;
			while ((line = br3.readLine()) != null) {

				lineSplit = line.split(" ");
				for (int i = 0; i < wordsNew.size(); i++) {
					if (lineSplit[0].equals(wordsList.get(i))) {

						for (int j = 1; j < lineSplit.length; j++) {
							idsList.add(new Integer(lineSplit[j]));
						}

					}
				}

			}
		}

		for (int i = 0; i < idsList.size(); i++) {
			System.out.println(idsList.get(i));
		}

	}

	public static void main(String[] args) throws IOException {

		String dictExclu = args[0];
		String words = args[1];

		Search search = new Search();
		search.doSearch(dictExclu, words, "out.txt");

	}

}
