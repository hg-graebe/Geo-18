package gr2.cips.core;

import org.apache.log4j.Logger;

import gr2.cips.cinderella.Cinderella;
import gr2.cips.cinderella.CinderellaCircleBy3;
import gr2.cips.cinderella.CinderellaCircleMP;
import gr2.cips.cinderella.CinderellaElement;
import gr2.cips.cinderella.CinderellaFreePoint;
import gr2.cips.cinderella.CinderellaJoin;
import gr2.cips.cinderella.CinderellaMeet;
import gr2.cips.cinderella.CinderellaMid;
import gr2.cips.cinderella.CinderellaOrthogonal;
import gr2.cips.cinderella.CinderellaParallel;
import gr2.cips.jsxgraph.JSXGraph;
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
public class Cinderella2JSXGraph {
	final static Logger logger = Logger.getLogger(Cinderella2JSXGraph.class);
	private Cinderella cinderella;

	public Cinderella2JSXGraph() {

	}

	public Cinderella2JSXGraph(Cinderella cinderella) {
		this.cinderella = cinderella;
	}

	public void setCinderella(Cinderella cinderella) {
		this.cinderella = cinderella;
	}

	public Cinderella getCinderella() {
		return this.cinderella;
	}

	public JSXGraph convert() {
		JSXGraph jsxGraph = new JSXGraph();
		
		if (getCinderella().getTitle() != null) {
			jsxGraph.setTitle(getCinderella().getTitle());
		}
		
		for (CinderellaElement cinderellaElement : cinderella.getCinderellaElements()) {
			if (cinderellaElement instanceof CinderellaFreePoint) {
				jsxGraph.addElement(new JSXGraphPoint(cinderellaElement.getID(), cinderellaElement.getX(),
						cinderellaElement.getY(), 1.0));
				logger.info("FreePoint ID:" + cinderellaElement.getID() + " has been converted to JSXGraph point");
			} else if (cinderellaElement instanceof CinderellaMid) {
				jsxGraph.addElement(new JSXGraphMidPoint(cinderellaElement.getID(),
						jsxGraph.getElementByID(((CinderellaMid) cinderellaElement).getPoint1().getID()),
						jsxGraph.getElementByID(((CinderellaMid) cinderellaElement).getPoint2().getID())));
				logger.info("Mid ID:" + cinderellaElement.getID() + " has been converted to JSXGraph midpoint");
			} else if (cinderellaElement instanceof CinderellaMeet) {
				jsxGraph.addElement(new JSXGraphIntersection(cinderellaElement.getID(),
						jsxGraph.getElementByID(((CinderellaMeet) cinderellaElement).getLineOrSegment1().getID()),
						jsxGraph.getElementByID(((CinderellaMeet) cinderellaElement).getLineOrSegment2().getID())));
				logger.info("Meet ID:" + cinderellaElement.getID() + " has been converted to JSXGraph intersection");
			} else if (cinderellaElement instanceof CinderellaJoin) {
				jsxGraph.addElement(new JSXGraphLine(cinderellaElement.getID(),
						jsxGraph.getElementByID(((CinderellaJoin) cinderellaElement).getPoint1().getID()),
						jsxGraph.getElementByID(((CinderellaJoin) cinderellaElement).getPoint2().getID())));
				logger.info("Join ID:" + cinderellaElement.getID() + " has been converted to JSXGraph line");
			} else if (cinderellaElement instanceof CinderellaParallel) {
				jsxGraph.addElement(new JSXGraphParallel(cinderellaElement.getID(),
						jsxGraph.getElementByID(((CinderellaParallel) cinderellaElement).getPoint().getID()),
						jsxGraph.getElementByID(((CinderellaParallel) cinderellaElement).getlineOrSegment().getID())));
				logger.info("Parallel ID:" + cinderellaElement.getID() + " has been converted to JSXGraph parallel");
			} else if (cinderellaElement instanceof CinderellaOrthogonal) {
				jsxGraph.addElement(new JSXGraphPerpendicular(cinderellaElement.getID(),
						jsxGraph.getElementByID(((CinderellaOrthogonal) cinderellaElement).getThroughPoint().getID()),
						jsxGraph.getElementByID(((CinderellaOrthogonal) cinderellaElement).getOrthogonalLineOrSegment().getID())));
				logger.info("Orthogonal ID:" + cinderellaElement.getID() + " has been converted to JSXGraph perpendicular");
			} 		else if (cinderellaElement instanceof CinderellaCircleMP) {
				jsxGraph.addElement(new JSXGraphPCCircle(cinderellaElement.getID(),
						jsxGraph.getElementByID(((CinderellaCircleMP) cinderellaElement).getCenterPoint().getID()),
						jsxGraph.getElementByID(((CinderellaCircleMP) cinderellaElement).getThroughPoint().getID())));
				logger.info("CircleMP ID:" + cinderellaElement.getID() + " has been converted to JSXGraph circle");
			} else if (cinderellaElement instanceof CinderellaCircleBy3) {
				jsxGraph.addElement(new JSXGraphCircumCircle(cinderellaElement.getID(),
						jsxGraph.getElementByID(((CinderellaCircleBy3) cinderellaElement).getPoint1().getID()),
						jsxGraph.getElementByID(((CinderellaCircleBy3) cinderellaElement).getPoint2().getID()),
						jsxGraph.getElementByID(((CinderellaCircleBy3) cinderellaElement).getPoint3().getID())));
				logger.info(
						"CircleBy3 ID:" + cinderellaElement.getID() + " has been converted to JSXGraph circumcircle");
			}
		}
		logger.info("Conversion successfully completed");
		return jsxGraph;
	}
}
