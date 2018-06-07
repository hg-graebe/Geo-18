import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import gr2.cips.cinderella.Cinderella;
import gr2.cips.core.Cinderella2Intergeo;
import gr2.cips.core.CinderellaVisualization;
import gr2.cips.core.GeoProofScheme2Intergeo;
import gr2.cips.core.GeoProofSchemeVisualization;
import gr2.cips.core.IntergeoVisualization;
import gr2.cips.geoproofscheme.GeoProofScheme;
import gr2.cips.intergeo.Intergeo;
import net.lingala.zip4j.exception.ZipException;

/**
 * @author Duong Trung Duong
 * @author <a href=
 *         "mailto:bss13ard@studserv.uni-leipzig.de">bss13ard@studserv.uni-leipzig.de</a>
 */
public class CIPS {
	public static void main(String argv[]) throws IOException, ParseException, TransformerException, ZipException {
		Options options = new Options();
		Option oJobType = new Option("j", "job-type", true,
				"\"c2i\": cinderella to intergeo,\n" + "\"g2i\": geoproofscheme to intergeo,\n"
						+ "\"vc\":  cinderella visualisation with jsxgraph,\n"
						+ "\"vi\":  intergeo visualisation with jsxgraph,\n"
						+ "\"vg\":  geoproofscheme visualisation with jsxgraph");
		oJobType.setRequired(true);
		options.addOption(oJobType);

		Option oInput = new Option("i", "input", true, "input file path");
		oInput.setRequired(true);
		options.addOption(oInput);

		Option oParameter = new Option("p", "parameter", true, "default parameter file path");
		oParameter.setRequired(false);
		options.addOption(oParameter);

		Option oOutput = new Option("o", "output", true, "output file path");
		oOutput.setRequired(true);
		options.addOption(oOutput);

		Option oHelp = new Option("h", "help", false, "print this message");
		oHelp.setRequired(false);
		options.addOption(oHelp);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;
		String helpString = "\njava -jar cips.jar -j c2i -i <cinderella file> -o <intergeo file>\n"
				+ "java -jar cips.jar -j g2i -i <geoproofscheme file> -o <intergeo file> -p [default parameter file]\n"
				+ "java -jar cips.jar -j vc -i <cinderella file> -o <visualization file>\n"
				+ "java -jar cips.jar -j vi -i <intergeo file> -o <visualization file>\n"
				+ "java -jar cips.jar -j vg -i <geoproofscheme file> -o <visualization file> -p [default parameter file]\n\n";

		try {
			cmd = parser.parse(options, argv);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp(helpString, options);
			System.exit(1);
			return;
		}

		if (cmd.hasOption("help")) {
			formatter.printHelp(helpString, options);
			return;
		}

		if (cmd.getOptionValue("job-type").equals("c2i")) {
			Cinderella cinderella = new Cinderella();
			String cinderellaFilePath = cmd.getOptionValue("input");
			String intergeoFilePath = cmd.getOptionValue("output");

			if (!cinderella.loadFromFile(cinderellaFilePath))
				return;

			Cinderella2Intergeo cinderella2Intergeo = new Cinderella2Intergeo(cinderella);
			Intergeo intergeo = cinderella2Intergeo.convert();
			intergeo.exportXML(intergeoFilePath);
		} else if (cmd.getOptionValue("job-type").equals("g2i")) {
			GeoProofScheme geoProofScheme = new GeoProofScheme();
			String geoProofSchemeFilePath = cmd.getOptionValue("input");
			String intergeoFilePath = cmd.getOptionValue("output");
			String parametersFilePath = cmd.getOptionValue("parameter");

			if (!geoProofScheme.loadFromFile(geoProofSchemeFilePath, parametersFilePath))
				return;

			GeoProofScheme2Intergeo geoProofScheme2Intergeo = new GeoProofScheme2Intergeo(geoProofScheme);
			Intergeo intergeo = geoProofScheme2Intergeo.convert();
			intergeo.exportXML(intergeoFilePath);
		} else if (cmd.getOptionValue("job-type").equals("vc")) {
			Cinderella cinderella = new Cinderella();
			String cinderellaFilePath = cmd.getOptionValue("input");
			String visualizationFilePath = cmd.getOptionValue("output");

			if (!cinderella.loadFromFile(cinderellaFilePath))
				return;

			CinderellaVisualization cinderellaVisualization = new CinderellaVisualization(cinderella,
					visualizationFilePath);
			cinderellaVisualization.visualize();
		} else if (cmd.getOptionValue("job-type").equals("vi")) {
			Intergeo intergeo = new Intergeo();
			String intergeoFilePath = cmd.getOptionValue("input");
			String visualizationFilePath = cmd.getOptionValue("output");

			if (!intergeo.loadFromFile(intergeoFilePath))
				return;

			IntergeoVisualization intergeoVisualization = new IntergeoVisualization(intergeo, visualizationFilePath);
			intergeoVisualization.visualize();
		} else if (cmd.getOptionValue("job-type").equals("vg")) {
			GeoProofScheme geoProofScheme = new GeoProofScheme();
			String geoProofSchemeFilePath = cmd.getOptionValue("input");
			String visualizationFilePath = cmd.getOptionValue("output");
			String parametersFilePath = cmd.getOptionValue("parameter");

			if (!geoProofScheme.loadFromFile(geoProofSchemeFilePath, parametersFilePath))
				return;

			GeoProofSchemeVisualization geoProofSchemeVisualization = new GeoProofSchemeVisualization(geoProofScheme,
					visualizationFilePath);
			geoProofSchemeVisualization.visualize();
		} else {
			formatter.printHelp(helpString, options);
		}
	}
}
