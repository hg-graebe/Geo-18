import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import cinderella.Cinderella;
import core.Cinderella2Intergeo;

public class C2I {
	public static void main(String[] args) throws IOException, ParserConfigurationException {
		if (args.length == 2) {
			Cinderella cinderella = new Cinderella();
			cinderella.loadFromFile(args[0]);
			Cinderella2Intergeo cinderella2Intergeo = new Cinderella2Intergeo(cinderella, args[1]);
			cinderella2Intergeo.convert();
		}else {
			System.out.println("Usage: java C2I <path to cinderella file> <path to save intergeo file>");
		}
	}
}
