package gr2.cips.core;

import org.apache.log4j.Logger;

import gr2.cips.geoproofscheme.GeoProofScheme;
import gr2.cips.geoproofscheme.GeoProofSchemeElement;
import gr2.cips.geoproofscheme.GeoProofSchemeFreePoint;
import gr2.cips.geoproofscheme.GeoProofSchemeIntersectionPoint;
import gr2.cips.geoproofscheme.GeoProofSchemeMidPoint;
import gr2.cips.geoproofscheme.GeoProofSchemeOrthoLine;
import gr2.cips.geoproofscheme.GeoProofSchemeP3Bisector;
import gr2.cips.geoproofscheme.GeoProofSchemeP3Circle;
import gr2.cips.geoproofscheme.GeoProofSchemePCCircle;
import gr2.cips.geoproofscheme.GeoProofSchemePPLine;
import gr2.cips.geoproofscheme.GeoProofSchemeParLine;
import gr2.cips.jsxgraph.JSXGraph;
import gr2.cips.jsxgraph.JSXGraphP3Bisector;
import gr2.cips.jsxgraph.JSXGraphCircumCircle;
import gr2.cips.jsxgraph.JSXGraphIntersection;
import gr2.cips.jsxgraph.JSXGraphLine;
import gr2.cips.jsxgraph.JSXGraphMidPoint;
import gr2.cips.jsxgraph.JSXGraphPCCircle;
import gr2.cips.jsxgraph.JSXGraphParallel;
import gr2.cips.jsxgraph.JSXGraphPerpendicular;
import gr2.cips.jsxgraph.JSXGraphPoint;

/**
 * @author Duong Trung Duong
 *
 */
public class GeoProofScheme2JSXGraph {
	final static Logger logger = Logger.getLogger(GeoProofScheme2JSXGraph.class);
	private GeoProofScheme geoProofScheme;

	public GeoProofScheme2JSXGraph() {

	}

	public GeoProofScheme2JSXGraph(GeoProofScheme geoProofScheme) {
		this.geoProofScheme = geoProofScheme;
	}

	public void setGeoProofScheme(GeoProofScheme geoProofScheme) {
		this.geoProofScheme = geoProofScheme;
	}

	public GeoProofScheme getGeoProofScheme() {
		return this.geoProofScheme;
	}

	public JSXGraph convert() {
		JSXGraph jsxGraph = new JSXGraph();
		if (getGeoProofScheme().getTitle() != null) {
			jsxGraph.setTitle(getGeoProofScheme().getTitle());
		} else {
			jsxGraph.setTitle("");
		}

		for (GeoProofSchemeElement geoProofSchemeElement : geoProofScheme.getGeoProofSchemeElements()) {
			if (geoProofSchemeElement instanceof GeoProofSchemeFreePoint) {
				jsxGraph.addElement(new JSXGraphPoint(geoProofSchemeElement.getID(), geoProofSchemeElement.getX(),
						geoProofSchemeElement.getY(), 1.0));
				logger.info("point ID:" + geoProofSchemeElement.getID() + " has been converted to JSXGraph point");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeMidPoint) {
				jsxGraph.addElement(new JSXGraphMidPoint(geoProofSchemeElement.getID(),
						jsxGraph.getElementByID(((GeoProofSchemeMidPoint) geoProofSchemeElement).getPoint1().getID()),
						jsxGraph.getElementByID(((GeoProofSchemeMidPoint) geoProofSchemeElement).getPoint2().getID())));
				logger.info(
						"midpoint ID:" + geoProofSchemeElement.getID() + " has been converted to JSXGraph midpoint");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeIntersectionPoint) {
				jsxGraph.addElement(new JSXGraphIntersection(geoProofSchemeElement.getID(),
						jsxGraph.getElementByID(
								((GeoProofSchemeIntersectionPoint) geoProofSchemeElement).getLine1().getID()),
						jsxGraph.getElementByID(
								((GeoProofSchemeIntersectionPoint) geoProofSchemeElement).getLine2().getID())));
				logger.info("intersection_point ID:" + geoProofSchemeElement.getID()
						+ " has been converted to JSXGraph intersection");
			} else if (geoProofSchemeElement instanceof GeoProofSchemePPLine) {
				jsxGraph.addElement(new JSXGraphLine(geoProofSchemeElement.getID(),
						jsxGraph.getElementByID(((GeoProofSchemePPLine) geoProofSchemeElement).getPoint1().getID()),
						jsxGraph.getElementByID(((GeoProofSchemePPLine) geoProofSchemeElement).getPoint2().getID())));
				logger.info("pp_line ID:" + geoProofSchemeElement.getID() + " has been converted to JSXGraph line");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeParLine) {
				jsxGraph.addElement(new JSXGraphParallel(geoProofSchemeElement.getID(),
						jsxGraph.getElementByID(((GeoProofSchemeParLine) geoProofSchemeElement).getPoint().getID()),
						jsxGraph.getElementByID(((GeoProofSchemeParLine) geoProofSchemeElement).getLine().getID())));
				logger.info(
						"par_line ID:" + geoProofSchemeElement.getID() + " has been converted to JSXGraph parallel");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeOrthoLine) {
				jsxGraph.addElement(new JSXGraphPerpendicular(geoProofSchemeElement.getID(),
						jsxGraph.getElementByID(((GeoProofSchemeOrthoLine) geoProofSchemeElement).getPoint().getID()),
						jsxGraph.getElementByID(((GeoProofSchemeOrthoLine) geoProofSchemeElement).getLine().getID())));
				logger.info(
						"ortho_line ID:" + geoProofSchemeElement.getID() + " has been converted to JSXGraph parallel");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeP3Bisector) {
				jsxGraph.addElement(new JSXGraphP3Bisector(geoProofSchemeElement.getID(),
						jsxGraph.getElementByID(((GeoProofSchemeP3Bisector) geoProofSchemeElement).getPoint1().getID()),
						jsxGraph.getElementByID(((GeoProofSchemeP3Bisector) geoProofSchemeElement).getPoint2().getID()),
						jsxGraph.getElementByID(((GeoProofSchemeP3Bisector) geoProofSchemeElement).getPoint3().getID())));
				logger.info("p3_bisector ID:" + geoProofSchemeElement.getID()
						+ " has been converted to JSXGraph bisector");
			} else if (geoProofSchemeElement instanceof GeoProofSchemePCCircle) {
				jsxGraph.addElement(new JSXGraphPCCircle(geoProofSchemeElement.getID(),
						jsxGraph.getElementByID(
								((GeoProofSchemePCCircle) geoProofSchemeElement).getCenterPoint().getID()),
						jsxGraph.getElementByID(
								((GeoProofSchemePCCircle) geoProofSchemeElement).getThroughPoint().getID())));
				logger.info("pc_circle ID:" + geoProofSchemeElement.getID() + " has been converted to JSXGraph circle");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeP3Circle) {
				jsxGraph.addElement(new JSXGraphCircumCircle(geoProofSchemeElement.getID(),
						jsxGraph.getElementByID(((GeoProofSchemeP3Circle) geoProofSchemeElement).getPoint1().getID()),
						jsxGraph.getElementByID(((GeoProofSchemeP3Circle) geoProofSchemeElement).getPoint2().getID()),
						jsxGraph.getElementByID(((GeoProofSchemeP3Circle) geoProofSchemeElement).getPoint3().getID())));
				logger.info("p3_circle ID:" + geoProofSchemeElement.getID()
						+ " has been converted to JSXGraph circumcircle");
			}
		}
		logger.info("Conversion successfully completed");
		return jsxGraph;
	}
}
