package cinderella;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Cinderella {
	public static final String TEMP_FOLDER = "temp";
	public static final String EL_NAME_REGEX = "\\(\"[A-Za-z0-9]*\"\\)";
	private List<CinderellaElement> cinderellaElements;
	
	public Cinderella() {
		cinderellaElements = new ArrayList<CinderellaElement>();
	}
	
	public void setCinderellaElements(ArrayList<CinderellaElement> cinderellaElements) {
		this.cinderellaElements = cinderellaElements;
	}
	
	public List<CinderellaElement> getCinderellaElements(){
		return this.cinderellaElements;
	}
	
	public void addElement(CinderellaElement cinderellaElement) {
		this.cinderellaElements.add(cinderellaElement);
	}
	
	public void loadFromFile(String cinderellaFilePath) throws IOException {
		//List<String> dataLines = new ArrayList<String>();
		String dataLine;
		BufferedReader bufferedReader = null;
		
		unzip(cinderellaFilePath);
		
		try {
			bufferedReader = new BufferedReader(
					new FileReader(TEMP_FOLDER + File.separator + "construction.cdy"));
			while ((dataLine = bufferedReader.readLine()) != null) {
				if (dataLine.matches(".*:=FreePoint.*")) {
					addElement(parseFreePoint(dataLine));					
				}else if (dataLine.matches(".*:=Meet.*")) {
					addElement(parseMeet(dataLine));
				}else if (dataLine.matches(".*:=Mid.*")) {
					addElement(parseMid(dataLine));
				}else if (dataLine.matches(".*:=Join.*")) {
					addElement(parseJoin(dataLine));
				}else if (dataLine.matches(".*:=Segment.*")) {
					addElement(parseSegment(dataLine));
				}else if (dataLine.matches(".*:=Through.*")) {
					addElement(parseThrough(dataLine));
				}else if (dataLine.matches(".*:=Parallel.*")) {
					addElement(parseParallel(dataLine));
				}else if (dataLine.matches(".*:=Orthogonal.*")) {
					addElement(parseOrthogonal(dataLine));
				}else if (dataLine.matches(".*:=AngularBisector.*")) {
					addElement(parseAngularBisector(dataLine));
				}
				else if (dataLine.matches(".*:=CircleMP.*")) {
					addElement(parseCircleMP(dataLine));
				}else if (dataLine.matches(".*:=CircleByRadius.*")) {
					addElement(parseCircleByRadius(dataLine));
				}else if (dataLine.matches(".*:=CircleBy3.*")) {
					addElement(parseCinderellaCircleBy3(dataLine));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CinderellaFreePoint parseFreePoint(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+17, dataLine.length()-2);
		
		String[] pCoords = dataLine.split(",");
		double x =  Double.parseDouble(pCoords[0].substring(0, pCoords[0].indexOf("+i")))/Double.parseDouble(pCoords[2].substring(0, pCoords[2].indexOf("+i")));
		double y =  Double.parseDouble(pCoords[1].substring(0, pCoords[1].indexOf("+i")))/-Double.parseDouble(pCoords[2].substring(0, pCoords[2].indexOf("+i")));
		return new CinderellaFreePoint(elementName, x, y);
	}
	
	public CinderellaMid parseMid(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+10, dataLine.length()-2);
		
		String[] points = dataLine.split(",");
		CinderellaFreePoint p1 = (CinderellaFreePoint) getElementByName(points[0].substring(1, points[0].length()-1));
		CinderellaFreePoint p2 = (CinderellaFreePoint) getElementByName(points[1].substring(1, points[1].length()-1));
		return new CinderellaMid(elementName, p1, p2);
	}	
	
	public CinderellaMeet parseMeet(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+11, dataLine.length()-2);
		
		String[] elements = dataLine.split(",");
		CinderellaElement cinderellaElement1 = getElementByName(elements[0].substring(1, elements[0].length()-1));
		CinderellaElement cinderellaElement2 = getElementByName(elements[1].substring(1, elements[1].length()-1));
				
		if (cinderellaElement1.getClass().getSimpleName().equals("CinderellaJoin")) {
			cinderellaElement1 = (CinderellaJoin) cinderellaElement1;
		}else if (cinderellaElement1.getClass().getSimpleName().equals("CinderellaSegment")) {
			cinderellaElement1 = (CinderellaSegment) cinderellaElement1;	
		}
		if (cinderellaElement2.getClass().getSimpleName().equals("CinderellaJoin")) {
			cinderellaElement2 = (CinderellaJoin) cinderellaElement2;
		}else if (cinderellaElement2.getClass().getSimpleName().equals("CinderellaSegment")) {
			cinderellaElement2 = (CinderellaSegment) cinderellaElement2;	
		}		
		return new CinderellaMeet(elementName,cinderellaElement1,cinderellaElement2);
	}		
	
	public CinderellaJoin parseJoin(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+11, dataLine.length()-2);
			
		String[] points = dataLine.split(",");
		CinderellaFreePoint p1 = (CinderellaFreePoint) getElementByName(points[0].substring(1, points[0].length()-1));
		CinderellaFreePoint p2 = (CinderellaFreePoint) getElementByName(points[1].substring(1, points[1].length()-1));
		return new CinderellaJoin(elementName, p1, p2);
	}	
	
	public CinderellaSegment parseSegment(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+14, dataLine.length()-2);
			
		String[] points = dataLine.split(",");
		CinderellaFreePoint p1 = (CinderellaFreePoint) getElementByName(points[0].substring(1, points[0].length()-1));
		CinderellaFreePoint p2 = (CinderellaFreePoint) getElementByName(points[1].substring(1, points[1].length()-1));
		return new CinderellaSegment(elementName, p1, p2);		
	}	
	
	public CinderellaThrough parseThrough(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+14, dataLine.length()-2);
		
		String[] points = dataLine.split(",");
		CinderellaFreePoint throughPoint = (CinderellaFreePoint) getElementByName(points[0].substring(1, points[0].length()-1));
		
		double x =  Double.parseDouble(points[1].substring(1, points[1].indexOf("+i"))) + throughPoint.getX();
		double y =  -Double.parseDouble(points[2].substring(0, points[2].indexOf("+i"))) + throughPoint.getY();
		CinderellaFreePoint tempPoint = new CinderellaFreePoint("tempPoint"+UUID.randomUUID().toString().replaceAll("-", ""), x, y);
		return new CinderellaThrough(elementName, throughPoint, tempPoint);
	}		
	
	public CinderellaParallel parseParallel(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+15, dataLine.length()-2);

		String[] elements = dataLine.split(",");
		CinderellaElement cinderellaElement = getElementByName(elements[0].substring(1, elements[0].length()-1));
		CinderellaFreePoint throughPoint = (CinderellaFreePoint) getElementByName(elements[1].substring(1, elements[1].length()-1));	
				
		if (cinderellaElement.getClass().getSimpleName().equals("CinderellaJoin")) {
			CinderellaJoin join = (CinderellaJoin) cinderellaElement;
			return new CinderellaParallel(elementName, join, throughPoint);
		}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaSegment")) {
			CinderellaSegment segment = (CinderellaSegment) cinderellaElement;	
			return new CinderellaParallel(elementName, segment, throughPoint);			
		}
		return null;
	}	
	
	public CinderellaOrthogonal parseOrthogonal(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+17, dataLine.length()-2);

		String[] elements = dataLine.split(",");
		CinderellaElement cinderellaElement = getElementByName(elements[0].substring(1, elements[0].length()-1));
		CinderellaFreePoint throughPoint = (CinderellaFreePoint) getElementByName(elements[1].substring(1, elements[1].length()-1));	
				
		if (cinderellaElement.getClass().getSimpleName().equals("CinderellaJoin")) {
			CinderellaJoin join = (CinderellaJoin) cinderellaElement;
			return new CinderellaOrthogonal(elementName, join, throughPoint);
		}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaSegment")) {
			CinderellaSegment segment = (CinderellaSegment) cinderellaElement;	
			return new CinderellaOrthogonal(elementName, segment, throughPoint);			
		}
		return null;
	}	
	
	public CinderellaAngularBisector parseAngularBisector(String dataLine) {
		Pattern pattern = Pattern.compile("\\{\"[A-Za-z0-9]*\",");
		Matcher matcher = pattern.matcher(dataLine);		
		String elementName = "";
		if (matcher.find()) {
			elementName =  dataLine.substring(matcher.start()+2,matcher.end()-2);
		}		
		dataLine = dataLine.substring(dataLine.indexOf("AngularBisector")+25,dataLine.length()-3);
		CinderellaElement cinderellaElement = getElementByName(dataLine);
		if (cinderellaElement.getClass().getSimpleName().equals("CinderellaMeet")) {
			return new CinderellaAngularBisector(elementName, (CinderellaMeet) cinderellaElement);
		}			
		return null;
	}		
	
	public CinderellaCircleMP parseCircleMP(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+15, dataLine.length()-2);
		
		String[] points = dataLine.split(",");
		CinderellaFreePoint p1 = (CinderellaFreePoint) getElementByName(points[0].substring(1, points[0].length()-1));
		CinderellaFreePoint p2 = (CinderellaFreePoint) getElementByName(points[1].substring(1, points[1].length()-1));
		return new CinderellaCircleMP(elementName, p1, p2);		
	}	
	
	public CinderellaCircleByRadius parseCircleByRadius(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+21, dataLine.length()-2);

		String[] cinderellaElement = dataLine.split(",");
		CinderellaFreePoint centrePoint = (CinderellaFreePoint) getElementByName(cinderellaElement[0].substring(1, cinderellaElement[0].length()-1));
		double radius =  Double.parseDouble(cinderellaElement[1].substring(0, cinderellaElement[1].indexOf("+i")));
		return new CinderellaCircleByRadius(elementName, centrePoint, radius);		
	}	
	
	public CinderellaCircleBy3 parseCinderellaCircleBy3(String dataLine) {
		String elementName = parseElementName(dataLine);
		dataLine = dataLine.substring(elementName.length()+16, dataLine.length()-2);

		String[] points = dataLine.split(",");
		CinderellaFreePoint p1 = (CinderellaFreePoint) getElementByName(points[0].substring(1, points[0].length()-1));
		CinderellaFreePoint p2 = (CinderellaFreePoint) getElementByName(points[1].substring(1, points[1].length()-1));
		CinderellaFreePoint p3 = (CinderellaFreePoint) getElementByName(points[2].substring(1, points[2].length()-1));
		
		return new CinderellaCircleBy3(elementName, p1, p2, p3);		
	}	
	
	public String parseElementName(String dataLine) {	
		Pattern pattern = Pattern.compile(EL_NAME_REGEX);
		Matcher matcher = pattern.matcher(dataLine);		
		if (matcher.find()) {
			return dataLine.substring(matcher.start()+2,matcher.end()-2);
		}	
		return null;
	}
	
	public CinderellaElement getElementByName(String name) {
		for (CinderellaElement cinderellaElement : cinderellaElements) {
			if (cinderellaElement.getName().equals(name)) {
				return cinderellaElement;
			}
		}
		return null;
	}	
	
	public void unzip(String filePath) throws IOException {
		byte[] buffer = new byte[1024];
		String outputFolder = TEMP_FOLDER;

		try {
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath));
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {
				String fileName = ze.getName();
				if (ze.isDirectory()) {
					ze = zis.getNextEntry();
					continue;
				}
				fileName = new File(fileName).getName();
				File newFile = new File(outputFolder + File.separator + fileName);

				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
