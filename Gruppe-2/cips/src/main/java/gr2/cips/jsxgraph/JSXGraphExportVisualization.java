package gr2.cips.jsxgraph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * @author Duong Trung Duong
 *
 */
public class JSXGraphExportVisualization {
	final static Logger logger = Logger.getLogger(JSXGraphExportVisualization.class);
	final static String HTML_TEMPLATE_PATH = "Visualization/VisualizationTemplate.html";
	final static String JSXGRAPH_CORE_SOURCE_PATH = "Visualization/JSXGraphCore.zip";
	private JSXGraph jSXGraph;
	private String visualizationFilePath;

	public JSXGraphExportVisualization(JSXGraph jSXGraph, String visualizationFilePath) {
		this.jSXGraph = jSXGraph;
		setVisualizationFilePath(visualizationFilePath);
	}

	public void setJSXGraph(JSXGraph jSXGraph) {
		this.jSXGraph = jSXGraph;
	}

	public void setVisualizationFilePath(String visualizationFilePath) {
		if (!FilenameUtils.getExtension(visualizationFilePath).equals("html")) {
			visualizationFilePath += ".html";
		}
		this.visualizationFilePath = visualizationFilePath;
	}

	public JSXGraph getJSXGraph() {
		return this.jSXGraph;
	}

	public String getVisualizationFilePath() {
		return this.visualizationFilePath;
	}

	public void visualize() {
		List<JSXGraphElement> jsxGraphElements = getJSXGraph().getJSXGraphElements();
		String board = "var p = [];" + System.lineSeparator();
		for (JSXGraphElement jsxGraphElement : jsxGraphElements) {
			if (jsxGraphElement instanceof JSXGraphPoint) {
				jsxGraphElement.setProperties(JSXGraphElement.INDEPENDENT_POINT_PROP);
				board += visualizeJSXGraphPoint((JSXGraphPoint) jsxGraphElement);
				logger.info("point ID:" + jsxGraphElement.getID() + " has been visualized");
			} else if (jsxGraphElement instanceof JSXGraphMidPoint) {
				jsxGraphElement.setProperties(JSXGraphElement.DEPENDENT_POINT_PROP);
				board += visualizeJSXGraphMidPoint((JSXGraphMidPoint) jsxGraphElement);
				logger.info("midpoint ID:" + jsxGraphElement.getID() + " has been visualized");
			} else if (jsxGraphElement instanceof JSXGraphIntersection) {
				jsxGraphElement.setProperties(JSXGraphElement.DEPENDENT_POINT_PROP);
				board += visualizeJSXGraphIntersection((JSXGraphIntersection) jsxGraphElement);
				logger.info("intersection ID:" + jsxGraphElement.getID() + " has been visualized");
			} else if (jsxGraphElement instanceof JSXGraphLine) {
				jsxGraphElement.setProperties(JSXGraphElement.INDEPENDENT_LINE_PROP);
				board += visualizeJSXGraphLine((JSXGraphLine) jsxGraphElement);
				logger.info("line ID:" + jsxGraphElement.getID() + " has been visualized");
			} else if (jsxGraphElement instanceof JSXGraphParallel) {
				jsxGraphElement.setProperties(JSXGraphElement.DEPENDENT_LINE_PROP);
				board += visualizeJSXGraphParallel((JSXGraphParallel) jsxGraphElement);
				logger.info("parallel ID:" + jsxGraphElement.getID() + " has been visualized");
			} else if (jsxGraphElement instanceof JSXGraphPerpendicular) {
				jsxGraphElement.setProperties(JSXGraphElement.DEPENDENT_LINE_PROP);
				board += visualizeJSXGraphPerpendicular((JSXGraphPerpendicular) jsxGraphElement);
				logger.info("perpendicular ID:" + jsxGraphElement.getID() + " has been visualized");
			} else if (jsxGraphElement instanceof JSXGraphP3Bisector) {
				jsxGraphElement.setProperties(JSXGraphElement.DEPENDENT_LINE_PROP);
				board += visualizeJSXGraphP3Bisector((JSXGraphP3Bisector) jsxGraphElement);
				logger.info("bisector ID:" + jsxGraphElement.getID() + " has been visualized");
			} else if (jsxGraphElement instanceof JSXGraphPCCircle) {
				jsxGraphElement.setProperties("");
				board += visualizeJSXGraphPCCircle((JSXGraphPCCircle) jsxGraphElement);
				logger.info("pccircle ID:" + jsxGraphElement.getID() + " has been visualized");
			} else if (jsxGraphElement instanceof JSXGraphCircumCircle) {
				jsxGraphElement.setProperties("");
				board += visualizeJSXGraphCircumCircle((JSXGraphCircumCircle) jsxGraphElement);
				logger.info("circumcircle ID:" + jsxGraphElement.getID() + " has been visualized");
			}
		}

		String htmlContent = readHTMLTemplate().replace("TITLE_PATTERN", getJSXGraph().getTitle())
				.replace("BOARD_PATTERN", board);

		if (exportVisualization(htmlContent)) {
			logger.info("Visualization successfully completed");
			logger.info("Visualization has been saved in " + getVisualizationFilePath());
		} else {
			logger.error("Visualization failed");
		}
	}

	private String visualizeJSXGraphPoint(JSXGraphPoint jsxGraphPoint) {
		return "\t\t\tp[" + getIndexByElement(jsxGraphPoint) + "] = board.createElement('point', ["
				+ jsxGraphPoint.getW() + "," + jsxGraphPoint.getX() + "," + jsxGraphPoint.getY() + "], {name:'"
				+ jsxGraphPoint.getID() + "'," + jsxGraphPoint.getProperties() + "})" + System.lineSeparator();
	}

	private String visualizeJSXGraphMidPoint(JSXGraphMidPoint jsxGraphMidPoint) {
		return "\t\t\tp[" + getIndexByElement(jsxGraphMidPoint) + "] = board.createElement('midpoint', [p["
				+ getIndexByElement(jsxGraphMidPoint.getPoint1()) + "],p["
				+ getIndexByElement(jsxGraphMidPoint.getPoint2()) + "]], {name:'" + jsxGraphMidPoint.getID() + "',"
				+ jsxGraphMidPoint.getProperties() + "})" + System.lineSeparator();
	}

	private String visualizeJSXGraphIntersection(JSXGraphIntersection jsxGraphIntersection) {
		return "\t\t\tp[" + getIndexByElement(jsxGraphIntersection) + "] = board.createElement('intersection', [p["
				+ getIndexByElement(jsxGraphIntersection.getLine1()) + "],p["
				+ getIndexByElement(jsxGraphIntersection.getLine2()) + "],0], {name:'" + jsxGraphIntersection.getID()
				+ "'," + jsxGraphIntersection.getProperties() + "})" + System.lineSeparator();
	}

	private String visualizeJSXGraphLine(JSXGraphLine jsxGraphLine) {
		return "\t\t\tp[" + getIndexByElement(jsxGraphLine) + "] = board.createElement('line', [p["
				+ getIndexByElement(jsxGraphLine.getPoint1()) + "],p[" + getIndexByElement(jsxGraphLine.getPoint2())
				+ "]], {name:'" + jsxGraphLine.getID() + "'," + jsxGraphLine.getProperties() + "})"
				+ System.lineSeparator();
	}

	private String visualizeJSXGraphParallel(JSXGraphParallel jsxGraphParallel) {
		return "\t\t\tp[" + getIndexByElement(jsxGraphParallel) + "] = board.createElement('parallel', [p["
				+ getIndexByElement(jsxGraphParallel.getLine()) + "],p["
				+ getIndexByElement(jsxGraphParallel.getPoint()) + "]], {name:'" + jsxGraphParallel.getID() + "',"
				+ jsxGraphParallel.getProperties() + "})" + System.lineSeparator();
	}

	private String visualizeJSXGraphPerpendicular(JSXGraphPerpendicular jsxGraphPerpendicular) {
		return "\t\t\tp[" + getIndexByElement(jsxGraphPerpendicular) + "] = board.createElement('perpendicular', [p["
				+ getIndexByElement(jsxGraphPerpendicular.getLine()) + "],p["
				+ getIndexByElement(jsxGraphPerpendicular.getPoint()) + "]], {name:'" + jsxGraphPerpendicular.getID()
				+ "'," + jsxGraphPerpendicular.getProperties() + "})" + System.lineSeparator();
	}

	private String visualizeJSXGraphP3Bisector(JSXGraphP3Bisector jsxGraphP3Bisector) {
		return "\t\t\tp[" + getIndexByElement(jsxGraphP3Bisector) + "] = board.createElement('bisector', [p["
				+ getIndexByElement(jsxGraphP3Bisector.getPoint1()) + "],p["
				+ getIndexByElement(jsxGraphP3Bisector.getPoint2()) + "],p["
				+ getIndexByElement(jsxGraphP3Bisector.getPoint3()) + "]], {name:'" + jsxGraphP3Bisector.getID()
				+ "'," + jsxGraphP3Bisector.getProperties() + "})" + System.lineSeparator();
	}
	
	private String visualizeJSXGraphPCCircle(JSXGraphPCCircle jsxGraphPCCircle) {
		return "\t\t\tp[" + getIndexByElement(jsxGraphPCCircle) + "] = board.createElement('circle', [p["
				+ getIndexByElement(jsxGraphPCCircle.getCenterPoint()) + "],p["
				+ getIndexByElement(jsxGraphPCCircle.getThroughPoint()) + "]], {name:'" + jsxGraphPCCircle.getID() + "'"
				+ jsxGraphPCCircle.getProperties() + "})" + System.lineSeparator();
	}

	private String visualizeJSXGraphCircumCircle(JSXGraphCircumCircle jsxGraphCircumCircle) {
		return "\t\t\tp[" + getIndexByElement(jsxGraphCircumCircle) + "] = board.createElement('circumcircle', [p["
				+ getIndexByElement(jsxGraphCircumCircle.getPoint1()) + "],p["
				+ getIndexByElement(jsxGraphCircumCircle.getPoint2()) + "],p["
				+ getIndexByElement(jsxGraphCircumCircle.getPoint3()) + "]], {name:'" + jsxGraphCircumCircle.getID()
				+ "'," + jsxGraphCircumCircle.getProperties() + "})" + System.lineSeparator();
	}

	private int getIndexByElement(JSXGraphElement jsxGraphElement) {
		return getJSXGraph().getJSXGraphElements().indexOf(jsxGraphElement);
	}

	private String readHTMLTemplate() {
		ClassLoader classLoader = getClass().getClassLoader();
		StringBuilder contentBuilder = new StringBuilder();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(classLoader.getResourceAsStream(HTML_TEMPLATE_PATH)))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				contentBuilder.append(line).append(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}

	private boolean exportJSXGraphCore() {
		try {
			InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(JSXGRAPH_CORE_SOURCE_PATH);
			if (inputStream == null) {
				return false;
			}

			File tempJSXGraphCoreFile = File.createTempFile(String.valueOf(inputStream.hashCode()), ".tmp");
			tempJSXGraphCoreFile.deleteOnExit();

			try (FileOutputStream fileOutputStream = new FileOutputStream(tempJSXGraphCoreFile)) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, bytesRead);
				}
			}
			ZipFile zipFile = new ZipFile(tempJSXGraphCoreFile.getAbsolutePath());
			zipFile.extractAll(
					FilenameUtils.getFullPathNoEndSeparator(new File(getVisualizationFilePath()).getAbsolutePath()));
		} catch (ZipException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean exportVisualization(String htmlContent) {
		if (!exportJSXGraphCore())
			return false;
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getVisualizationFilePath()))) {
			bufferedWriter.write(htmlContent);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
