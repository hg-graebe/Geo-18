package core;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cinderella.Cinderella;
import cinderella.CinderellaAngularBisector;
import cinderella.CinderellaCircleBy3;
import cinderella.CinderellaCircleMP;
import cinderella.CinderellaElement;
import cinderella.CinderellaFreePoint;
import cinderella.CinderellaJoin;
import cinderella.CinderellaMeet;
import cinderella.CinderellaMid;
import cinderella.CinderellaOrthogonal;
import cinderella.CinderellaParallel;
import cinderella.CinderellaSegment;
import cinderella.CinderellaThrough;

public class Cinderella2Intergeo {
	private Cinderella cinderella;
	private String intergeoFileName;
	private Element xmlRootElement;
	private Element xmlElements;
	private Element xmlConstraints;
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;	
	
	public Cinderella2Intergeo(Cinderella cinderella, String intergeoFileName) throws ParserConfigurationException {
		this.cinderella = cinderella;
		this.intergeoFileName = intergeoFileName;
		
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			
			xmlRootElement = doc.createElement("construction");
			doc.appendChild(xmlRootElement);
			
			xmlElements = doc.createElement("elements");
			xmlRootElement.appendChild(xmlElements);
			xmlConstraints = doc.createElement("constraints");
			xmlRootElement.appendChild(xmlConstraints);	
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public void setXMlElements(Element xmlElements) {
		this.xmlElements = xmlElements;
	}
	
	public void setXMLConstraints(Element xmlConstraints) {
		this.xmlConstraints = xmlConstraints;
	}
	
	public Element getXMLElements() {
		return xmlElements;
	}
	
	public Element getXMLConstraints() {
		return xmlConstraints;
	}	
	
	public void convert() {
		try {
			for (CinderellaElement cinderellaElement : cinderella.getCinderellaElements()) {
				if (cinderellaElement.getClass().getSimpleName().equals("CinderellaFreePoint")) {
					CinderellaFreePoint freePoint = (CinderellaFreePoint) cinderellaElement;
					appendXMLFreePoint(freePoint);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaMeet")) {
					CinderellaMeet meet = (CinderellaMeet) cinderellaElement;
					appendXMLMeet(meet);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaMid")) {
					CinderellaMid mid = (CinderellaMid) cinderellaElement;
					appendXMLMid(mid);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaJoin")) {
					CinderellaJoin join = (CinderellaJoin) cinderellaElement;
					appendXMLJoin(join);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaSegment")) {
					CinderellaSegment segment = (CinderellaSegment) cinderellaElement;
					appendXMLSegment(segment);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaThrough")) {
					CinderellaThrough through = (CinderellaThrough) cinderellaElement;
					appendXMLThrough(through);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaParallel")) {
					CinderellaParallel parallel = (CinderellaParallel) cinderellaElement;
					appendXMLParallel(parallel);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaOrthogonal")) {
					CinderellaOrthogonal orthogonal = (CinderellaOrthogonal) cinderellaElement;
					appendXMLOrthogonal(orthogonal);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaAngularBisector")) {
					CinderellaAngularBisector angularBisector = (CinderellaAngularBisector) cinderellaElement;
					appendXMLAngularBisector(angularBisector);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaCircleMP")) {
					CinderellaCircleMP circleMP = (CinderellaCircleMP) cinderellaElement;
					appendXMLCircleMP(circleMP);
				}else if (cinderellaElement.getClass().getSimpleName().equals("CinderellaCircleBy3")) {
					CinderellaCircleBy3 circleBy3 = (CinderellaCircleBy3) cinderellaElement;
					appendXMLCircleBy3(circleBy3);
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(intergeoFileName));	
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void appendXMLFreePoint(CinderellaFreePoint freePoint) {
		Element xmlPointE = doc.createElement("point");
		xmlPointE.setAttribute("id", freePoint.getName());
		Element xmlHomogeneousCoordinates = doc.createElement("homogeneous_coordinates");
		Element xmlDoubleX = doc.createElement("double");
		xmlDoubleX.appendChild(doc.createTextNode(""+freePoint.getX()));
		xmlHomogeneousCoordinates.appendChild(xmlDoubleX);
		Element xmlDoubleY = doc.createElement("double");
		xmlDoubleY.appendChild(doc.createTextNode(""+freePoint.getY()));
		xmlHomogeneousCoordinates.appendChild(xmlDoubleY);
		Element xmlDoubleW = doc.createElement("double");
		xmlDoubleW.appendChild(doc.createTextNode("1"));					
		xmlHomogeneousCoordinates.appendChild(xmlDoubleW);
		xmlPointE.appendChild(xmlHomogeneousCoordinates);
		xmlElements.appendChild(xmlPointE);	
		
		Element xmlFree_Point = doc.createElement("free_point");
		Element xmlPointC = doc.createElement("point");
		xmlPointC.setAttribute("out", "true");
		xmlPointC.appendChild(doc.createTextNode(freePoint.getName()));
		xmlFree_Point.appendChild(xmlPointC);
		xmlConstraints.appendChild(xmlFree_Point);		
	}	

	public void appendXMLMeet(CinderellaMeet meet) {	
		Element xmlPointIntersectionOfTwoLines = doc.createElement("point_intersection_of_two_lines");
		Element xmlPointC = doc.createElement("point");
		xmlPointC.setAttribute("out", "true");
		xmlPointC.appendChild(doc.createTextNode(meet.getName()));
		Element xmlLine1 = doc.createElement("line");
		xmlLine1.appendChild(doc.createTextNode(meet.getLineOrSegment1().getName()));
		Element xmlLine2 = doc.createElement("line");
		xmlLine2.appendChild(doc.createTextNode(meet.getLineOrSegment2().getName()));		
		xmlPointIntersectionOfTwoLines.appendChild(xmlPointC);
		xmlPointIntersectionOfTwoLines.appendChild(xmlLine1);
		xmlPointIntersectionOfTwoLines.appendChild(xmlLine2);		
		xmlConstraints.appendChild(xmlPointIntersectionOfTwoLines);		
	}		
	
	public void appendXMLMid(CinderellaMid mid) {
		CinderellaFreePoint midPoint = new CinderellaFreePoint(mid.getName(),
				(mid.getPoint1().getX()+mid.getPoint2().getX())/2,
				(mid.getPoint1().getY()+mid.getPoint2().getY())/2);
		appendXMLFreePoint(midPoint);
		
		Element xmlMidpointOfTwoPoints = doc.createElement("midpoint_of_two_points");
		Element xmlLineC = doc.createElement("line");
		xmlLineC.setAttribute("out", "true");
		xmlLineC.appendChild(doc.createTextNode(mid.getName()));
		Element xmlPoint1 = doc.createElement("point");
		xmlPoint1.appendChild(doc.createTextNode(mid.getPoint1().getName()));
		Element xmlPoint2 = doc.createElement("point");
		xmlPoint2.appendChild(doc.createTextNode(mid.getPoint2().getName()));		
		xmlMidpointOfTwoPoints.appendChild(xmlLineC);
		xmlMidpointOfTwoPoints.appendChild(xmlPoint1);
		xmlMidpointOfTwoPoints.appendChild(xmlPoint2);		
		xmlConstraints.appendChild(xmlMidpointOfTwoPoints);		
	}	
	
	public void appendXMLJoin(CinderellaJoin join) {
		Element xmlLineThroughTwoPoints = doc.createElement("line_through_two_points");
		Element xmlLineC = doc.createElement("line");
		xmlLineC.setAttribute("out", "true");
		xmlLineC.appendChild(doc.createTextNode(join.getName()));
		Element xmlPoint1 = doc.createElement("point");
		xmlPoint1.appendChild(doc.createTextNode(join.getPoint1().getName()));
		Element xmlPoint2 = doc.createElement("point");
		xmlPoint2.appendChild(doc.createTextNode(join.getPoint2().getName()));		
		xmlLineThroughTwoPoints.appendChild(xmlLineC);
		xmlLineThroughTwoPoints.appendChild(xmlPoint1);
		xmlLineThroughTwoPoints.appendChild(xmlPoint2);		
		xmlConstraints.appendChild(xmlLineThroughTwoPoints);		
	}
	
	public void appendXMLSegment(CinderellaSegment segment) {
		Element xmlLineSegmentByPoints = doc.createElement("line_segment_by_points");
		Element xmlLineC = doc.createElement("line_segment");
		xmlLineC.setAttribute("out", "true");
		xmlLineC.appendChild(doc.createTextNode(segment.getName()));
		Element xmlPoint1 = doc.createElement("point");
		xmlPoint1.appendChild(doc.createTextNode(segment.getPoint1().getName()));
		Element xmlPoint2 = doc.createElement("point");
		xmlPoint2.appendChild(doc.createTextNode(segment.getPoint2().getName()));		
		xmlLineSegmentByPoints.appendChild(xmlLineC);
		xmlLineSegmentByPoints.appendChild(xmlPoint1);
		xmlLineSegmentByPoints.appendChild(xmlPoint2);		
		xmlConstraints.appendChild(xmlLineSegmentByPoints);		
	}	
	
	public void appendXMLThrough(CinderellaThrough through) {
		Element xmlLineE = doc.createElement("line");
		xmlLineE.setAttribute("id", through.getName());
		Element xmlHomogeneousCoordinates = doc.createElement("homogeneous_coordinates");
		double a = (through.getThroughPoint().getY()-through.getTempPoint().getY())/
				(through.getThroughPoint().getX()-through.getTempPoint().getX());
		double b = through.getTempPoint().getY()-a*through.getTempPoint().getX();		
		Element xmlDoubleA = doc.createElement("double");
		xmlDoubleA.appendChild(doc.createTextNode(""+a));
		xmlHomogeneousCoordinates.appendChild(xmlDoubleA);
		Element xmlDoubleC = doc.createElement("double");
		xmlDoubleC.appendChild(doc.createTextNode("-1"));	
		xmlHomogeneousCoordinates.appendChild(xmlDoubleC);
		Element xmlDoubleB = doc.createElement("double");
		xmlDoubleB.appendChild(doc.createTextNode(""+b));
		xmlHomogeneousCoordinates.appendChild(xmlDoubleB);
		xmlLineE.appendChild(xmlHomogeneousCoordinates);
		xmlElements.appendChild(xmlLineE);	
		
		Element xmlLineThroughTwoPoints = doc.createElement("line_through_point");
		Element xmlLineC = doc.createElement("line");
		xmlLineC.setAttribute("out", "true");
		xmlLineC.appendChild(doc.createTextNode(through.getName()));
		Element xmlThroughPoint = doc.createElement("point");
		xmlThroughPoint.appendChild(doc.createTextNode(through.getThroughPoint().getName()));
		xmlLineThroughTwoPoints.appendChild(xmlLineC);
		xmlLineThroughTwoPoints.appendChild(xmlThroughPoint);
		xmlConstraints.appendChild(xmlLineThroughTwoPoints);		
	}	
	
	public void appendXMLParallel(CinderellaParallel parallel) {
		Element xmlLineParallelToLineThroughPoint = doc.createElement("line_parallel_to_line_through_point");
		Element xmlLineC = doc.createElement("line");
		xmlLineC.setAttribute("out", "true");
		xmlLineC.appendChild(doc.createTextNode(parallel.getName()));
		Element xmlPointC = doc.createElement("point");
		xmlPointC.appendChild(doc.createTextNode(parallel.getThroughPoint().getName()));
		Element xmlLineParallelC = doc.createElement("line");
		xmlLineParallelC.appendChild(doc.createTextNode(parallel.getParallelLineOrSegment().getName()));		
		xmlLineParallelToLineThroughPoint.appendChild(xmlLineC);
		xmlLineParallelToLineThroughPoint.appendChild(xmlPointC);
		xmlLineParallelToLineThroughPoint.appendChild(xmlLineParallelC);		
		xmlConstraints.appendChild(xmlLineParallelToLineThroughPoint);		
	}		
	
	public void appendXMLOrthogonal(CinderellaOrthogonal orthogonal) {
		Element xmlLinePerpendicularToLineThroughPoint = doc.createElement("line_perpendicular_to_line_through_point");
		Element xmlLineC = doc.createElement("line");
		xmlLineC.setAttribute("out", "true");
		xmlLineC.appendChild(doc.createTextNode(orthogonal.getName()));
		Element xmlPointC = doc.createElement("point");
		xmlPointC.appendChild(doc.createTextNode(orthogonal.getThroughPoint().getName()));
		Element xmlLineOrthogonalC = doc.createElement("line");
		xmlLineOrthogonalC.appendChild(doc.createTextNode(orthogonal.getOrthogonalLineOrSegment().getName()));		
		xmlLinePerpendicularToLineThroughPoint.appendChild(xmlLineC);
		xmlLinePerpendicularToLineThroughPoint.appendChild(xmlPointC);
		xmlLinePerpendicularToLineThroughPoint.appendChild(xmlLineOrthogonalC);		
		xmlConstraints.appendChild(xmlLinePerpendicularToLineThroughPoint);		
	}	
	
	public void appendXMLAngularBisector(CinderellaAngularBisector angularBisector) {
		Element xmlLineAngularBisectorsOfTwoLines = doc.createElement("line_angular_bisectors_of_two_lines");
		Element xmlLine1C = doc.createElement("line");
		xmlLine1C.setAttribute("out", "true");
		xmlLine1C.appendChild(doc.createTextNode(angularBisector.getName()+"1"));
		Element xmlLine2C = doc.createElement("line");
		xmlLine2C.setAttribute("out", "true");
		xmlLine2C.appendChild(doc.createTextNode(angularBisector.getName()+"2"));		
		Element xmlLine3C = doc.createElement("line");
		xmlLine3C.appendChild(doc.createTextNode(angularBisector.getMeet().getLineOrSegment1().getName()));
		Element xmlLine4C = doc.createElement("line");
		xmlLine4C.appendChild(doc.createTextNode(angularBisector.getMeet().getLineOrSegment2().getName()));
		xmlLineAngularBisectorsOfTwoLines.appendChild(xmlLine1C);
		xmlLineAngularBisectorsOfTwoLines.appendChild(xmlLine2C);
		xmlLineAngularBisectorsOfTwoLines.appendChild(xmlLine3C);	
		xmlLineAngularBisectorsOfTwoLines.appendChild(xmlLine4C);		
		xmlConstraints.appendChild(xmlLineAngularBisectorsOfTwoLines);		
	}		
	
	
	public void appendXMLCircleMP(CinderellaCircleMP circleMP) {
		Element xmlCircleE = doc.createElement("circle");
		xmlCircleE.setAttribute("id", circleMP.getName());
		//TODO: calculate circle equation in matrix form
		Element xmlMatrix = doc.createElement("matrix");
		xmlCircleE.appendChild(xmlMatrix);		
		xmlElements.appendChild(xmlCircleE);
		
		Element xmlCircleByCenterAndPoint = doc.createElement("circle_by_center_and_point");
		Element xmlCircleC = doc.createElement("xmlCircleC");
		xmlCircleC.setAttribute("out", "true");
		xmlCircleC.appendChild(doc.createTextNode(circleMP.getName()));
		Element xmlPoint1 = doc.createElement("point");
		xmlPoint1.appendChild(doc.createTextNode(circleMP.getPoint1().getName()));
		Element xmlPoint2 = doc.createElement("point");
		xmlPoint2.appendChild(doc.createTextNode(circleMP.getPoint2().getName()));		
		xmlCircleByCenterAndPoint.appendChild(xmlCircleC);
		xmlCircleByCenterAndPoint.appendChild(xmlPoint1);
		xmlCircleByCenterAndPoint.appendChild(xmlPoint2);		
		xmlConstraints.appendChild(xmlCircleByCenterAndPoint);		
	}	
	
	//TODO: appendXMLCircleByRadius
	/*
	public void appendXMLCircleByRadius{
		
	}*/
	
	public void appendXMLCircleBy3(CinderellaCircleBy3 circleBy3) {
		Element xmlCircleE = doc.createElement("circle");
		xmlCircleE.setAttribute("id", circleBy3.getName());
		//TODO: calculate circle equation in matrix form
		Element xmlMatrix = doc.createElement("matrix");
		xmlCircleE.appendChild(xmlMatrix);		
		xmlElements.appendChild(xmlCircleE);
		
		Element xmlCircleByThreePoints = doc.createElement("circle_by_three_points");
		Element xmlCircleC = doc.createElement("xmlCircleC");
		xmlCircleC.setAttribute("out", "true");
		xmlCircleC.appendChild(doc.createTextNode(circleBy3.getName()));
		Element xmlPoint1 = doc.createElement("point");
		xmlPoint1.appendChild(doc.createTextNode(circleBy3.getPoint1().getName()));
		Element xmlPoint2 = doc.createElement("point");
		xmlPoint2.appendChild(doc.createTextNode(circleBy3.getPoint2().getName()));		
		Element xmlPoint3 = doc.createElement("point");
		xmlPoint3.appendChild(doc.createTextNode(circleBy3.getPoint3().getName()));			
		xmlCircleByThreePoints.appendChild(xmlCircleC);
		xmlCircleByThreePoints.appendChild(xmlPoint1);
		xmlCircleByThreePoints.appendChild(xmlPoint2);		
		xmlCircleByThreePoints.appendChild(xmlPoint3);		
		xmlConstraints.appendChild(xmlCircleByThreePoints);		
	}		
	
	/*
	public void appendXMLCircleByRadius(CinderellaCircleByRadius circleByRadius) {
		Element xmlCircleByCenterAndRadius = doc.createElement("circle_by_center_and_radius");
		Element xmlCircleC = doc.createElement("xmlCircleC");
		xmlCircleC.setAttribute("out", "true");
		xmlCircleC.appendChild(doc.createTextNode(circleByRadius.getName()));
		Element xmlPoint1 = doc.createElement("point");
		xmlPoint1.appendChild(doc.createTextNode(circleByRadius.getPoint1().getName()));
		Element xmlPoint2 = doc.createElement("point");
		xmlPoint2.appendChild(doc.createTextNode(circleByRadius.getPoint2().getName()));		
		xmlCircleByCenterAndRadius.appendChild(xmlCircleC);
		xmlCircleByCenterAndRadius.appendChild(xmlPoint1);
		xmlCircleByCenterAndRadius.appendChild(xmlPoint2);		
		xmlConstraints.appendChild(xmlCircleByCenterAndRadius);		
	}*/
}
