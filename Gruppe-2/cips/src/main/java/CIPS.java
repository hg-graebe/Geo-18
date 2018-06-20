import java.util.Arrays;

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

/**
 * @author Duong Trung Duong
 * @author <a href=
 *         "mailto:bss13ard@studserv.uni-leipzig.de">bss13ard@studserv.uni-leipzig.de</a>
 */
public class CIPS {
	public static void main(String argv[]) throws Exception {
		Options options = new Options();

		options.addOption(new Option("c2i", false, "cinderella to intergeo"));
		options.addOption(new Option("g2i", false, "geoproofscheme to intergeo"));
		options.addOption(new Option("vc", false, "cinderella visualisation with jsxgraph"));
		options.addOption(new Option("vi", false, "intergeo visualisation with jsxgraph"));
		options.addOption(new Option("vg", false, "geoproofscheme visualisation with jsxgraph"));

		Option oInput = new Option("i", "input", true, "input file path");
		oInput.setRequired(false);
		options.addOption(oInput);

		Option oParameter = new Option("p", "parameter", true, "default parameter file path");
		oParameter.setRequired(false);
		options.addOption(oParameter);

		Option oOutput = new Option("o", "output", true, "output file path");
		oOutput.setRequired(false);
		options.addOption(oOutput);

		Option oHelp = new Option("h", "help", false, "print this message");
		oHelp.setRequired(false);
		options.addOption(oHelp);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;
		String helpString =
				"\njava -jar cips.jar -j c2i -i <cinderella file> -o <intergeo file>\n"
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

		boolean hasRun = false;
		try {
			if( cmd.hasOption("c2i")) {
				hasRun = c2iMode(cmd);
			}
			if (cmd.hasOption("g2i")) {
				hasRun |= g2iMode(cmd);
			}
			if (cmd.hasOption("vc")) {
				hasRun |= vcMode(cmd);
			}
			if (cmd.hasOption("vi")) {
				hasRun |= viMode(cmd);
			}
			if (cmd.hasOption("vg")) {
				hasRun |= vgMode(cmd);
			}

			if (!hasRun) {
				final String inputFileExtension = getFileExtension(cmd);
				switch (inputFileExtension) {
					case "cdy":
						hasRun = c2iMode(cmd);
						hasRun |= vcMode(cmd);
						break;
					case "xml":
						hasRun = g2iMode(cmd);
						hasRun |= vgMode(cmd);
						break;
					case "i2g":
						hasRun = viMode(cmd);
						break;
					default:
						break;
				}
			}
		} catch (IllegalArgumentException e) {

		}

		if (!hasRun) {
			formatter.printHelp(helpString, options);
		}
	}

	static boolean c2iMode(CommandLine cmd) throws Exception {
		Cinderella cinderella = new Cinderella();
		String cinderellaFilePath = getInput(cmd);
		String intergeoFilePath = getOutput(cmd, "i2g");

		if (!cinderella.loadFromFile(cinderellaFilePath))
			return false;

		Cinderella2Intergeo cinderella2Intergeo = new Cinderella2Intergeo(cinderella);
		Intergeo intergeo = cinderella2Intergeo.convert();
		intergeo.exportXML(intergeoFilePath);

		return true;
	}
	static boolean g2iMode(CommandLine cmd) throws Exception {
		GeoProofScheme geoProofScheme = new GeoProofScheme();
		String geoProofSchemeFilePath = getInput(cmd);
		String intergeoFilePath = getOutput(cmd, "i2g");
		String parametersFilePath = getParameterFile(cmd);

		if (parametersFilePath.isEmpty() || !geoProofScheme.loadFromFile(geoProofSchemeFilePath, parametersFilePath))
			return false;

		GeoProofScheme2Intergeo geoProofScheme2Intergeo = new GeoProofScheme2Intergeo(geoProofScheme);
		Intergeo intergeo = geoProofScheme2Intergeo.convert();
		intergeo.exportXML(intergeoFilePath);

		return true;
	}
	static boolean vcMode(CommandLine cmd) throws Exception {
		Cinderella cinderella = new Cinderella();
		String cinderellaFilePath = getInput(cmd);
		String visualizationFilePath = getOutput(cmd, "html");

		if (!cinderella.loadFromFile(cinderellaFilePath))
			return false;

		CinderellaVisualization cinderellaVisualization = new CinderellaVisualization(cinderella,
				visualizationFilePath);
		cinderellaVisualization.visualize();

		return true;
	}
	static boolean viMode(CommandLine cmd) throws Exception {
		Intergeo intergeo = new Intergeo();
		String intergeoFilePath = getInput(cmd);
		String visualizationFilePath = getOutput(cmd, "html");

		if (!intergeo.loadFromFile(intergeoFilePath))
			return false;

		IntergeoVisualization intergeoVisualization = new IntergeoVisualization(intergeo, visualizationFilePath);
		intergeoVisualization.visualize();

		return true;
	}
	static boolean vgMode(CommandLine cmd) throws Exception {
		GeoProofScheme geoProofScheme = new GeoProofScheme();
		String geoProofSchemeFilePath = getInput(cmd);
		String visualizationFilePath = getOutput(cmd, "html");
		String parametersFilePath = getParameterFile(cmd);

		if (parametersFilePath.isEmpty() || !geoProofScheme.loadFromFile(geoProofSchemeFilePath, parametersFilePath))
			return false;

		GeoProofSchemeVisualization geoProofSchemeVisualization = new GeoProofSchemeVisualization(geoProofScheme,
				visualizationFilePath);
		geoProofSchemeVisualization.visualize();

		return true;
	}

	private static String getFileExtension(CommandLine cmd) {
		final String input = getInput(cmd);
		final String[] inputFile = input.split("\\.");
		final String inputFileExtension = inputFile[inputFile.length - 1];

		return inputFileExtension;
	}
	private static String getInput(CommandLine cmd) {
		String input = cmd.getOptionValue("input");

		if (input == null || input.isEmpty()) {
			if (cmd.getArgs().length > 0) {
				return cmd.getArgs()[0];
			} else {
				throw new IllegalArgumentException();
			}
		}

		return input;
	}

	private static String getOutput(CommandLine cmd, String extension) {
		String output = cmd.getOptionValue("output");
		if (output == null || output.isEmpty()) {
			return generateOutputName(cmd, extension);
		}
		return output;
	}
	private static String generateOutputName(CommandLine cmd, String extension) {
		String input = getInput(cmd);
		final String[] inputPaths = input.split("/");
		final String[] inputFile = inputPaths[inputPaths.length - 1].split("\\.");
		final String newName = String.join(".", Arrays.copyOf(inputFile, inputFile.length-1));
		final String newPath = String.join("/", Arrays.copyOf(inputPaths, inputPaths.length-1));

		return newPath + "/" + newName + "." + extension;
	}

	private static String getParameterFile(CommandLine cmd) {
		final String parameter = cmd.getOptionValue("parameter");
		if (parameter == null || parameter.isEmpty()) {
			return generateOutputName(cmd, "parameter");
		}
		return parameter;
	}
}
