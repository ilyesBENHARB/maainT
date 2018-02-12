import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PageRank {

	private ArrayList rows = new ArrayList();
	private ArrayList c = new ArrayList();
	private ArrayList l = new ArrayList();
	private ArrayList i = new ArrayList();
	private ArrayList pr = new ArrayList();
	private int nbNodes;
	private double d=0.1;

	public ArrayList getC() {
		return c;
	}

	public void setC(ArrayList c) {
		this.c = c;
	}

	public ArrayList getL() {
		return l;
	}

	public void setL(ArrayList l) {
		this.l = l;
	}

	public ArrayList getI() {
		return i;
	}

	public void setI(ArrayList i) {
		this.i = i;
	}

	public int getNbNodes() {
		return nbNodes;
	}

	public void setNbNodes(int nbNodes) {
		this.nbNodes = nbNodes;
	}

	public void countNodes(String fileName) throws FileNotFoundException, IOException {
		int count = 1;
		int lastValue = 0;
		int value;

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {

				if (line.charAt(0) != '#') {
					value = (Integer.parseInt(String.valueOf(line.charAt(0))));
					if (value != lastValue) {
						lastValue = value;
						count++;
					}

				}
			}
			this.nbNodes = count;
		}
	}

	public void cliExtract(String fileName) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {

				int occurency = 0;
				if (line.charAt(0) != '#') {
					this.rows.add(Integer.parseInt(String.valueOf(line.charAt(0))));

					int temp = Integer.parseInt(String.valueOf(line.charAt(2)));
					this.i.add(temp);

					try (BufferedReader br2 = new BufferedReader(new FileReader(fileName))) {
						String line2;
						while ((line2 = br2.readLine()) != null) {
							if (line.charAt(0) == line2.charAt(0)) {
								occurency++;
							}

						}
						double temp2 = (double) 1 / occurency;
						this.c.add(temp2);
					}

				}
			}
		}

		this.l.add(0);

		for (int j = 1; j < this.rows.size(); j++) {
			if ((int) this.rows.get(j - 1) != (int) this.rows.get(j)) {
				this.l.add(j);
			}

		}
		this.l.add(this.rows.size());

	}

	public void doPageRank() {
		
		 ArrayList prNew  = new ArrayList();
		
		double init = 1 / this.nbNodes;
		for (int i = 0; i < this.nbNodes; i++) {
			this.pr.add(init);
		}

		for (int i = 0; i < this.nbNodes; i++) {
			for (int j =(int) this.l.get(i); j < (int) this.l.get(i+1); j++) {
				double element=(this.d/this.nbNodes)+(1/this.d)*(int)this.c.get(j) * (int)this.pr.get(i);
				prNew.add((int)this.i.get(j), element) ;
			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		PageRank p = new PageRank();
		p.cliExtract("file.txt");
		p.countNodes("file.txt");
		ArrayList c = p.getC();
		ArrayList l = p.getL();
		ArrayList i = p.getI();
		int nb = p.getNbNodes();

		for (int j = 0; j < l.size(); j++) {
			System.out.print(l.get(j) + "|");

		}
		System.out.println("");

		for (int j = 0; j < c.size(); j++) {
			System.out.print(c.get(j) + "|");

		}
		System.out.println("");

		for (int j = 0; j < i.size(); j++) {
			System.out.print(i.get(j) + "|");

		}
		System.out.println("");
		System.out.println("nb = " + nb);
	}

}
