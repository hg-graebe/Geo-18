package gr2.cips.geoproofscheme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 * @author Duong Trung Duong
 *
 */
public class GeoProofScheme {
	final static Logger logger = Logger.getLogger(GeoProofScheme.class);
	private List<GeoProofSchemeElement> geoProofSchemeElements;
	private String title;

	public GeoProofScheme() {
		geoProofSchemeElements = new ArrayList<GeoProofSchemeElement>();
	}

	public void setGeoProofSchemeElements(List<GeoProofSchemeElement> geoProofSchemeElements) {
		this.geoProofSchemeElements = geoProofSchemeElements;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<GeoProofSchemeElement> getGeoProofSchemeElements() {
		return this.geoProofSchemeElements;
	}

	public String getTitle() {
		return this.title;
	}

	public boolean loadFromFile(String geoProofSchemeFilePath, String parameterFilePath, String variableFilePath)
			throws IOException {
		try {
			File geoProofSchemeFile = new File(geoProofSchemeFilePath);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(geoProofSchemeFile);

			document.getDocumentElement().normalize();

			if (document.getElementsByTagName("Title").getLength() == 1) {
				setTitle(document.getElementsByTagName("Title").item(0).getChildNodes().item(0).getNodeValue());
			}

			if (document.getElementsByTagName("vars").getLength() == 1) {
				List<String> variableIDs = Arrays.asList(document.getElementsByTagName("vars").item(0).getChildNodes()
						.item(0).getNodeValue().split(","));
				if (isVariableMatching(variableIDs, variableFilePath) == true) {
					parseVariables(variableIDs, variableFilePath);
				} else {
					logger.error("Variables do not match");
					return false;
				}
			}

			if (document.getElementsByTagName("parameters").getLength() == 1) {
				List<String> parameterIDs = Arrays.asList(document.getElementsByTagName("parameters").item(0)
						.getChildNodes().item(0).getNodeValue().split(","));
				if (isParameterMatching(parameterIDs, parameterFilePath) == true) {
					parseParameters(parameterIDs, parameterFilePath);
				} else {
					logger.error("Parameters do not match");
					return false;
				}
			}

			if (document.getElementsByTagName("Points").getLength() == 1) {
				NodeList pointsChildNodes = document.getElementsByTagName("Points").item(0).getChildNodes();
				for (int i = 0; i < pointsChildNodes.getLength(); i++) {
					if (pointsChildNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
						String id = pointsChildNodes.item(i).getAttributes().getNamedItem("id").getNodeValue();
						String rawData = pointsChildNodes.item(i).getFirstChild().getNodeValue();
						GeoProofSchemeFreePoint geoProofSchemeFreePoint = parseFreePoint(id, rawData);
						if (geoProofSchemeFreePoint != null) {
							addElement(geoProofSchemeFreePoint);
							logger.info("Found point: ID:" + geoProofSchemeFreePoint.getID() + ", "
									+ geoProofSchemeFreePoint.toString());
						} else {
							return false;
						}
					}
				}
			}

			if (document.getElementsByTagName("Assignments").getLength() == 1) {
				NodeList assignmentChildNodes = document.getElementsByTagName("Assignments").item(0).getChildNodes();
				for (int i = 0; i < assignmentChildNodes.getLength(); i++) {
					if (assignmentChildNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
						String id = assignmentChildNodes.item(i).getAttributes().getNamedItem("id").getNodeValue();
						String rawData = assignmentChildNodes.item(i).getFirstChild().getNodeValue();

						if (rawData.matches(".*midpoint.*")) {
							GeoProofSchemeMidPoint geoProofSchemeMidPoint = parseMidPoint(id, rawData);
							if (geoProofSchemeMidPoint != null) {
								addElement(geoProofSchemeMidPoint);
								logger.info("Found midpoint: ID:" + geoProofSchemeMidPoint.getID() + ", "
										+ geoProofSchemeMidPoint.toString());
							} else {
								return false;
							}
						} else if (rawData.matches(".*intersection_point.*")) {
							GeoProofSchemeIntersectionPoint geoProofSchemeIntersectionPoint = parseIntersectionPoint(id,
									rawData);
							if (geoProofSchemeIntersectionPoint != null) {
								addElement(geoProofSchemeIntersectionPoint);
								logger.info("Found intersection_point: ID:" + geoProofSchemeIntersectionPoint.getID()
										+ ", " + geoProofSchemeIntersectionPoint.toString());
							} else {
								return false;
							}
						} else if (rawData.matches(".*pp_line.*")) {
							GeoProofSchemePPLine geoProofSchemePPLine = parsePPLine(id, rawData);
							if (geoProofSchemePPLine != null) {
								addElement(geoProofSchemePPLine);
								logger.info("Found pp_line: ID:" + geoProofSchemePPLine.getID() + ", "
										+ geoProofSchemePPLine.toString());
							} else {
								return false;
							}
						} else if (rawData.matches(".*par_line.*")) {
							GeoProofSchemeParLine geoProofSchemeParLine = parseParLine(id, rawData);
							if (geoProofSchemeParLine != null) {
								addElement(geoProofSchemeParLine);
								logger.info("Found par_line: ID:" + geoProofSchemeParLine.getID() + ", "
										+ geoProofSchemeParLine.toString());
							} else {
								return false;
							}
						} else if (rawData.matches(".*ortho_line.*")) {
							GeoProofSchemeOrthoLine geoProofSchemeOrthoLine = parseOrthoLine(id, rawData);
							if (geoProofSchemeOrthoLine != null) {
								addElement(geoProofSchemeOrthoLine);
								logger.info("Found ortho_line: ID:" + geoProofSchemeOrthoLine.getID() + ", "
										+ geoProofSchemeOrthoLine.toString());
							} else {
								return false;
							}
						} else if (rawData.matches(".*p3_bisector.*")) {
							GeoProofSchemeP3Bisector geoProofSchemeP3Bisector = parseP3Bisector(id, rawData);
							if (geoProofSchemeP3Bisector != null) {
								addElement(geoProofSchemeP3Bisector);
								logger.info("Found p3_bisector: ID:" + geoProofSchemeP3Bisector.getID() + ", "
										+ geoProofSchemeP3Bisector.toString());
							} else {
								return false;
							}
						} else if (rawData.matches(".*p3_circle.*")) {
							GeoProofSchemeP3Circle geoProofSchemeP3Circle = parseP3Circle(id, rawData);
							if (geoProofSchemeP3Circle != null) {
								addElement(geoProofSchemeP3Circle);
								logger.info("Found p3_circle: ID:" + geoProofSchemeP3Circle.getID() + ", "
										+ geoProofSchemeP3Circle.toString());
							} else {
								return false;
							}
						} else if (rawData.matches(".*pc_circle.*")) {
							GeoProofSchemePCCircle geoProofSchemePCCircle = parsePCCircle(id, rawData);
							if (geoProofSchemePCCircle != null) {
								addElement(geoProofSchemePCCircle);
								logger.info("Found pc_circle: ID:" + geoProofSchemePCCircle.getID() + ", "
										+ geoProofSchemePCCircle.toString());
							} else {
								return false;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void parseVariables(List<String> variableIDs, String variableFilePath) {
		Map<String, Double> variablesFromFile = new HashMap<String, Double>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(variableFilePath))) {
			String tempString;
			while ((tempString = bufferedReader.readLine()) != null) {
				variablesFromFile.put(tempString.split("\t")[0], NumberUtils.toDouble(tempString.split("\t")[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String variableID : variableIDs) {
			GeoProofSchemeVariable geoProofSchemeVariable = new GeoProofSchemeVariable(variableID,
					variablesFromFile.get(variableID));
			addElement(geoProofSchemeVariable);
			logger.info("Found variable: ID:" + geoProofSchemeVariable.getID() + ", Value:"
					+ geoProofSchemeVariable.getValue());
		}
	}

	public void parseParameters(List<String> parameterIDs, String parameterFilePath) {
		Map<String, Double> parametersFromFile = new HashMap<String, Double>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(parameterFilePath))) {
			String tempString;
			while ((tempString = bufferedReader.readLine()) != null) {
				parametersFromFile.put(tempString.split("\t")[0], NumberUtils.toDouble(tempString.split("\t")[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String parameterID : parameterIDs) {
			GeoProofSchemeParameter geoProofSchemeParameter = new GeoProofSchemeParameter(parameterID,
					parametersFromFile.get(parameterID));
			addElement(geoProofSchemeParameter);
			logger.info("Found parameter: ID:" + geoProofSchemeParameter.getID() + ", Value:"
					+ geoProofSchemeParameter.getValue());
		}
	}

	public GeoProofSchemeFreePoint parseFreePoint(String id, String rawData) {
		String rawX = rawData.substring(6, rawData.length() - 1).split(",")[0].trim();
		String rawY = rawData.substring(6, rawData.length() - 1).split(",")[1].trim();
		double x = 0.0d;
		double y = 0.0d;
		if (NumberUtils.isCreatable(rawX)) {
			x = NumberUtils.toDouble(rawX);
		} else {
			GeoProofSchemeElement xElement = getElementByID(rawX);
			if (xElement != null) {
				if (xElement.isParameter()) {
					x = ((GeoProofSchemeParameter) xElement).getValue();
				} else if (xElement.isVariable()) {
					x = ((GeoProofSchemeVariable) xElement).getValue();
				} else {
					logger.error("Error while parsing FreePoint: ID:" + id);
					return null;
				}
			} else {
				logger.error("Error while parsing FreePoint: ID:" + id);
				return null;
			}
		}
		if (NumberUtils.isCreatable(rawY)) {
			y = NumberUtils.toDouble(rawY);
		} else {
			GeoProofSchemeElement yElement = getElementByID(rawY);
			if (yElement != null) {
				if (yElement.isParameter()) {
					y = ((GeoProofSchemeParameter) yElement).getValue();
				} else if (yElement.isVariable()) {
					y = ((GeoProofSchemeVariable) yElement).getValue();
				} else {
					logger.error("Error while parsing FreePoint: ID:" + id);
					return null;
				}
			} else {
				logger.error("Error while parsing FreePoint: ID:" + id);
				return null;
			}
		}
		return new GeoProofSchemeFreePoint(id, x, y);
	}

	public GeoProofSchemeMidPoint parseMidPoint(String id, String rawData) {
		String point1ID = rawData.substring(9, rawData.length() - 1).split(",")[0].trim();
		String point2ID = rawData.substring(9, rawData.length() - 1).split(",")[1].trim();
		GeoProofSchemeElement point1 = getElementByID(point1ID);
		GeoProofSchemeElement point2 = getElementByID(point2ID);
		if (point1 == null || point2 == null) {
			logger.error("Error while parsing midpoint: ID:" + id);
			return null;
		} else {
			if (!point1.isPoint() || !point2.isPoint()) {
				logger.error("Error while parsing midpoint: ID:" + id);
				return null;
			} else {
				return new GeoProofSchemeMidPoint(id, point1, point2);
			}
		}
	}

	public GeoProofSchemeIntersectionPoint parseIntersectionPoint(String id, String rawData) {
		String line1ID = rawData.substring(19, rawData.length() - 1).split(",")[0].trim();
		String line2ID = rawData.substring(19, rawData.length() - 1).split(",")[1].trim();
		GeoProofSchemeElement line1 = getElementByID(line1ID);
		GeoProofSchemeElement line2 = getElementByID(line2ID);
		if (line1 == null || line2 == null) {
			logger.error("Error while parsing intersection_point: ID:" + id);
			return null;
		} else {
			if (!line1.isLine() || !line2.isLine()) {
				logger.error("Error while parsing intersection_point: ID:" + id);
				return null;
			} else {
				return new GeoProofSchemeIntersectionPoint(id, line1, line2);
			}
		}
	}

	public GeoProofSchemePPLine parsePPLine(String id, String rawData) {
		String point1ID = rawData.substring(8, rawData.length() - 1).split(",")[0].trim();
		String point2ID = rawData.substring(8, rawData.length() - 1).split(",")[1].trim();
		GeoProofSchemeElement point1 = getElementByID(point1ID);
		GeoProofSchemeElement point2 = getElementByID(point2ID);
		if (point1 == null || point2 == null) {
			logger.error("Error while parsing pp_line: ID:" + id);
			return null;
		} else {
			if (!point1.isPoint() || !point2.isPoint()) {
				logger.error("Error while parsing pp_line: ID:" + id);
				return null;
			} else {
				return new GeoProofSchemePPLine(id, point1, point2);
			}
		}
	}

	public GeoProofSchemeParLine parseParLine(String id, String rawData) {
		String pointID = rawData.substring(9, rawData.length() - 1).split(",")[0].trim();
		String lineID = rawData.substring(9, rawData.length() - 1).split(",")[1].trim();
		GeoProofSchemeElement point = getElementByID(pointID);
		GeoProofSchemeElement line = getElementByID(lineID);
		if (point == null || line == null) {
			logger.error("Error while parsing par_line: ID:" + id);
			return null;
		} else {
			if (!point.isPoint() || !line.isLine()) {
				logger.error("Error while parsing par_line: ID:" + id);
				return null;
			} else {
				return new GeoProofSchemeParLine(id, point, line);
			}
		}
	}

	public GeoProofSchemeOrthoLine parseOrthoLine(String id, String rawData) {
		String pointID = rawData.substring(11, rawData.length() - 1).split(",")[0].trim();
		String lineID = rawData.substring(11, rawData.length() - 1).split(",")[1].trim();
		GeoProofSchemeElement point = getElementByID(pointID);
		GeoProofSchemeElement line = getElementByID(lineID);
		if (point == null || line == null) {
			logger.error("Error while parsing ortho_line: ID:" + id);
			return null;
		} else {
			if (!point.isPoint() || !line.isLine()) {
				logger.error("Error while parsing ortho_line: ID:" + id);
				return null;
			} else {
				return new GeoProofSchemeOrthoLine(id, point, line);
			}
		}
	}
	
	public GeoProofSchemeP3Bisector parseP3Bisector(String id, String rawData) {
		String point1ID = rawData.substring(12, rawData.length() - 1).split(",")[0].trim();
		String point2ID = rawData.substring(12, rawData.length() - 1).split(",")[1].trim();
		String point3ID = rawData.substring(12, rawData.length() - 1).split(",")[2].trim();
		GeoProofSchemeElement point1 = getElementByID(point1ID);
		GeoProofSchemeElement point2 = getElementByID(point2ID);
		GeoProofSchemeElement point3 = getElementByID(point3ID);
		if (point1 == null || point2 == null || point3 == null) {
			logger.error("Error while parsing p3_bisector: ID:" + id);
			return null;
		} else {
			if (!point1.isPoint() || !point2.isPoint() || !point3.isPoint()) {
				logger.error("Error while parsing p3_bisector: ID:" + id);
				return null;
			} else {
				return new GeoProofSchemeP3Bisector(id, point1, point2, point3);
			}
		}
	}

	public GeoProofSchemeP3Circle parseP3Circle(String id, String rawData) {
		String point1ID = rawData.substring(10, rawData.length() - 1).split(",")[0].trim();
		String point2ID = rawData.substring(10, rawData.length() - 1).split(",")[1].trim();
		String point3ID = rawData.substring(10, rawData.length() - 1).split(",")[2].trim();
		GeoProofSchemeElement point1 = getElementByID(point1ID);
		GeoProofSchemeElement point2 = getElementByID(point2ID);
		GeoProofSchemeElement point3 = getElementByID(point3ID);
		if (point1 == null || point2 == null || point3 == null) {
			logger.error("Error while parsing p3_circle: ID:" + id);
			return null;
		} else {
			if (!point1.isPoint() || !point2.isPoint() || !point3.isPoint()) {
				logger.error("Error while parsing p3_circle: ID:" + id);
				return null;
			} else {
				return new GeoProofSchemeP3Circle(id, point1, point2, point3);
			}
		}
	}

	public GeoProofSchemePCCircle parsePCCircle(String id, String rawData) {
		String point1ID = rawData.substring(10, rawData.length() - 1).split(",")[0].trim();
		String point2ID = rawData.substring(10, rawData.length() - 1).split(",")[1].trim();
		GeoProofSchemeElement point1 = getElementByID(point1ID);
		GeoProofSchemeElement point2 = getElementByID(point2ID);
		if (point1 == null || point2 == null) {
			logger.error("Error while parsing pc_circle: ID:" + id);
			return null;
		} else {
			if (!point1.isPoint() || !point2.isPoint()) {
				logger.error("Error while parsing pc_circle: ID:" + id);
				return null;
			} else {
				return new GeoProofSchemePCCircle(id, point1, point2);
			}
		}
	}

	public boolean isVariableMatching(List<String> variableIDs, String variableFilePath) throws IOException {
		List<String> variablesFromFile = new ArrayList<String>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(variableFilePath))) {
			String tempString;
			while ((tempString = bufferedReader.readLine()) != null) {
				variablesFromFile.add(tempString.split("\t")[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return new HashSet<>(variableIDs).equals(new HashSet<>(variablesFromFile));
	}

	public boolean isParameterMatching(List<String> parameterIDs, String parameterFilePath) throws IOException {
		List<String> parametersFromFile = new ArrayList<String>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(parameterFilePath))) {
			String tempString;
			while ((tempString = bufferedReader.readLine()) != null) {
				parametersFromFile.add(tempString.split("\t")[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return new HashSet<>(parameterIDs).equals(new HashSet<>(parametersFromFile));
	}

	public void addElement(GeoProofSchemeElement geoProofSchemeElement) {
		this.geoProofSchemeElements.add(geoProofSchemeElement);
	}

	public GeoProofSchemeElement getElementByID(String id) {
		for (GeoProofSchemeElement geoProofSchemeElement : geoProofSchemeElements) {
			if (geoProofSchemeElement.getID().equals(id)) {
				return geoProofSchemeElement;
			}
		}
		return null;
	}
}
