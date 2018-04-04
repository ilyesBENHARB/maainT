import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search {

	public void doSearch(String dictEx, String words, String collecteur) throws IOException {

		ArrayList<String> dictExcluList = new ArrayList<String>();
		ArrayList<String> wordsList = new ArrayList<String>();
		ArrayList<String> wordsNew = new ArrayList<String>();
		ArrayList<Integer> idsList = new ArrayList<Integer>();
		ArrayList<Integer> intersectionList = new ArrayList<Integer>();
		BufferedWriter writer = new BufferedWriter(new FileWriter("intermediaire.txt"));

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

						writer.write(line + "\n");
						for (int j = 1; j < lineSplit.length; j++) {
							idsList.add(new Integer(lineSplit[j]));
						}

					}
				}

			}
		}

		for (int i = 0; i < idsList.size(); i++) {
			boolean intersection = true;
			try (BufferedReader br3 = new BufferedReader(new FileReader("intermediaire.txt"))) {
				String line;
				while ((intersection == true) && ((line = br3.readLine()) != null)) {

					String[] lineSplit2 = line.split(" ");
					int k = 0;
					boolean exist = false;
					while ((k < lineSplit2.length) && (exist == false)) {
						if (lineSplit2[k].equals(idsList.get(i).toString())) {
							exist = true;
						}
						k++;

					}

					intersection = exist;
				}
				
			}
			if (intersection==true) {
				intersectionList.add(idsList.get(i));
			}

		}
		
		for (int i=0;i<intersectionList.size();i++) {
			System.out.println(intersectionList.get(i));
		}

	}

	public static void main(String[] args) throws IOException {

		String dictExclu = args[0];
		String words = args[1];

		Search search = new Search();
		search.doSearch(dictExclu, words, "out.txt");

	}

}
