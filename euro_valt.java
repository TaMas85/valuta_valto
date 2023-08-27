package world;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class euro_valt {

	public double mitft;
	public double mite;
	public double valt;

	public static double EurToFt(double mitft) {
		return (mitft * 400);
	}

	public static double EurToFt(double mitft, double valt) {
		return (mitft * valt);
	}

	public static double FtToEur(double mite) {
		return (mite / 400);
	}

	public static double FtToEur(double mite, double valt) {
		return (mite / valt);
	}

	public static LinkedHashMap<String, String> valtMap() {

		LinkedHashMap<String, String> penzMap = new LinkedHashMap<String, String>();

		try {
			List<String> beolvas = Files.readAllLines(Paths.get("euro.txt"));

			for (String sor : beolvas) {
				String[] split = sor.split(" ");
				String r = (split[0]);
				String v = (split[1]);
				penzMap.put(r, v);
			}
		} catch (NoSuchFileException e) {
			System.err.println("Nincs meg a fájl!");
		} catch (IOException e) {
			System.err.println("Hiba a beolvasásnál!");
		}
		return penzMap;
	}

	public static void main(String[] args) {

		LinkedHashMap<String, String> kiirMap = valtMap();
		String kiir = "";
		double arfolyamE = 0;
		double arfolyamF = 0;
		for (Map.Entry<String, String> entry : kiirMap.entrySet()) {
			String s = entry.getKey();
			String p = entry.getValue();
			
			if (p.startsWith("E") || p.startsWith("e")) {

				double v = Double.parseDouble(s);
				double eredmeny = euro_valt.EurToFt(v);
				arfolyamE = eredmeny / v;
				kiir += v + " Euro ----> " + eredmeny + " HuF\n";
				System.out.println(v + " Euro ----> " + eredmeny + " HuF");
			}else {
				
				double v = Double.parseDouble(s);
				double eredmeny = euro_valt.FtToEur(v);
				arfolyamF = v / eredmeny;
				kiir += v + " HuF ----> " + eredmeny + " Euro\n";
				System.out.println(v + " Huf ----> " + eredmeny + " Eur");
				
			}
		}
		kiir += "\n\n(Váltás: 1 Euro --> " + arfolyamE + " HuF)\n";
		kiir += "(Váltás: " + arfolyamF + " HuF --> " + "1 Euro)";
		System.out.println("\n\n(Váltás: 1 Euro --> " + arfolyamE + " HuF)");
		System.out.println("(Váltás: " + arfolyamF + " HuF --> " + "1 Euro)");

		try {
			Files.write(Paths.get("HuF.txt"), kiir.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
