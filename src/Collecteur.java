import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Collecteur {

	public void doCollector(String src, String dict, String ign, String out) throws FileNotFoundException, IOException {

		Map<String, String> dictionnaire = new HashMap<>();
		Map<String, String> ignore = new HashMap<>();

		String line;
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(out));
	   
	     
	   
	

		try (BufferedReader br2 = new BufferedReader(new FileReader(dict))) {

			while ((line = br2.readLine()) != null) {

				dictionnaire.put(line, "");

			}

		}

		try (BufferedReader br = new BufferedReader(new FileReader(ign))) {

			while ((line = br.readLine()) != null) {

				ignore.put(line, "");

			}

		}

		try (BufferedReader br3 = new BufferedReader(new FileReader(src))) {

			int id = 0;
			while ((line = br3.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] lineTab = line.split(" ");
					if (lineTab[0].equals("Id:")) {
						id = new Integer(lineTab[3]);

					}
					if (line.charAt(0) == ' ') {
						String lineBis = line.substring(2);
						String[] lineBisTab = lineBis.split(" ");

						if (lineBisTab[0].equals("title:")) {
							for (int i = 0; i < lineBisTab.length; i++) {

								Set<Entry<String, String>> setDict = dictionnaire.entrySet();
								Iterator<Entry<String, String>> itD = setDict.iterator();
								while (itD.hasNext()) {
									Entry<String, String> e = itD.next();
									if (e.getKey().equalsIgnoreCase(lineBisTab[i])) {
										boolean existI = false;
										Set<Entry<String, String>> setIgnore = ignore.entrySet();
										Iterator<Entry<String, String>> itI = setIgnore.iterator();
										while (itI.hasNext()) {
											Entry<String, String> ig = itI.next();
											if (ig.getKey().equalsIgnoreCase(lineBisTab[i])) {
												existI = true;
											}
										}
										if (existI == false) {

											e.setValue(e.getValue() + id + "-");
										}

									}
								}

							}

						}

					}

				}

			}
		}
		Set<Entry<String, String>> setDictR = dictionnaire.entrySet();
		Iterator<Entry<String, String>> itDR = setDictR.iterator();
		while (itDR.hasNext()) {
			Entry<String, String> eR = itDR.next();
			writer.write(eR.getKey() + " " + eR.getValue()+"\n");
			
		}
		 writer.close();

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Collecteur c = new Collecteur();
		
		if (args.length==0) {
			System.out.println("exécution sans arguments");
			c.doCollector("amazon-meta-bis.txt", "Nouns.txt", "ignore.txt", "out.txt");
		}
		
		else {
			System.out.println("exécution avec arguments");
			c.doCollector(args[0], args[1], args[2], "out.txt");
		}
		
		
		System.out.println("Le résultat est dans le fichier out.txt");
	}
}
