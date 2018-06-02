package gr2.cips.core;

import org.apache.log4j.Logger;

import gr2.cips.intergeo.Intergeo;
import gr2.cips.intergeo.IntergeoCircleByCenterAndPoint;
import gr2.cips.intergeo.IntergeoCircleByThreePoints;
import gr2.cips.intergeo.IntergeoElement;
import gr2.cips.intergeo.IntergeoFreePoint;
import gr2.cips.intergeo.IntergeoLineAngularBisectorOfThreePoints;
import gr2.cips.intergeo.IntergeoLineParallelToLineThroughPoint;
import gr2.cips.intergeo.IntergeoLinePerpendicularToLineThroughPoint;
import gr2.cips.intergeo.IntergeoLineThroughTwoPoints;
import gr2.cips.intergeo.IntergeoMidPointOfTwoPoints;
import gr2.cips.intergeo.IntergeoPointIntersectionOfTwoLines;
import gr2.cips.jsxgraph.JSXGraph;
import gr2.cips.jsxgraph.JSXGraphCircumCircle;
import gr2.cips.jsxgraph.JSXGraphIntersection;
import gr2.cips.jsxgraph.JSXGraphLine;
import gr2.cips.jsxgraph.JSXGraphMidPoint;
import gr2.cips.jsxgraph.JSXGraphP3Bisector;
import gr2.cips.jsxgraph.JSXGraphPCCircle;
import gr2.cips.jsxgraph.JSXGraphParallel;
import gr2.cips.jsxgraph.JSXGraphPerpendicular;
import gr2.cips.jsxgraph.JSXGraphPoint;

/**
 * @author Duong Trung Duong
 *
 */
public class Intergeo2JSXGraph {
	final static Logger logger = Logger.getLogger(Intergeo2JSXGraph.class);
	private Intergeo intergeo;

	public Intergeo2JSXGraph() {

	}

	public Intergeo2JSXGraph(Intergeo intergeo) {
		this.intergeo = intergeo;
	}

	public void setIntergeo(Intergeo intergeo) {
		this.intergeo = intergeo;
	}

	public Intergeo getIntergeo() {
		return this.intergeo;
	}

	public JSXGraph convert() {
		JSXGraph jsxGraph = new JSXGraph();
		if (getIntergeo().getTitle() != null) {
			jsxGraph.setTitle(getIntergeo().getTitle());
		}
		for (IntergeoElement intergeoElement : intergeo.getIntegeoElements()) {
			if (intergeoElement instanceof IntergeoFreePoint) {
				jsxGraph.addElement(new JSXGraphPoint(intergeoElement.getID(), intergeoElement.getX(),
						intergeoElement.getY(), 1.0));
				logger.info("free_point ID:" + intergeoElement.getID() + " has been converted to JSXGraph point");
			} else if (intergeoElement instanceof IntergeoMidPointOfTwoPoints) {
				jsxGraph.addElement(new JSXGraphMidPoint(intergeoElement.getID(),
						jsxGraph.getElementByID(((IntergeoMidPointOfTwoPoints) intergeoElement).getPoint1().getID()),
						jsxGraph.getElementByID(((IntergeoMidPointOfTwoPoints) intergeoElement).getPoint2().getID())));
				logger.info("midpoint_of_two_points\n" + " ID:" + intergeoElement.getID()
						+ " has been converted to JSXGraph midpoint");
			} else if (intergeoElement instanceof IntergeoPointIntersectionOfTwoLines) {
				jsxGraph.addElement(new JSXGraphIntersection(intergeoElement.getID(),
						jsxGraph.getElementByID(
								((IntergeoPointIntersectionOfTwoLines) intergeoElement).getLine1().getID()),
						jsxGraph.getElementByID(
								((IntergeoPointIntersectionOfTwoLines) intergeoElement).getLine2().getID())));
				logger.info("point_intersection_of_two_lines\n" + " ID:" + intergeoElement.getID()
						+ " has been converted to JSXGraph intersection");
			} else if (intergeoElement instanceof IntergeoLineThroughTwoPoints) {
				jsxGraph.addElement(new JSXGraphLine(intergeoElement.getID(),
						jsxGraph.getElementByID(((IntergeoLineThroughTwoPoints) intergeoElement).getPoint1().getID()),
						jsxGraph.getElementByID(((IntergeoLineThroughTwoPoints) intergeoElement).getPoint2().getID())));
				logger.info("line_through_two_points\n" + " ID:" + intergeoElement.getID()
						+ " has been converted to JSXGraph line");
			} else if (intergeoElement instanceof IntergeoLineParallelToLineThroughPoint) {
				jsxGraph.addElement(new JSXGraphParallel(intergeoElement.getID(),
						jsxGraph.getElementByID(
								((IntergeoLineParallelToLineThroughPoint) intergeoElement).getPoint().getID()),
						jsxGraph.getElementByID(
								((IntergeoLineParallelToLineThroughPoint) intergeoElement).getLine().getID())));
				logger.info("line_parallel_to_line_through_point\n" + " ID:" + intergeoElement.getID()
						+ " has been converted to JSXGraph parallel");
			} else if (intergeoElement instanceof IntergeoLinePerpendicularToLineThroughPoint) {
				jsxGraph.addElement(new JSXGraphPerpendicular(intergeoElement.getID(),
						jsxGraph.getElementByID(
								((IntergeoLinePerpendicularToLineThroughPoint) intergeoElement).getPoint().getID()),
						jsxGraph.getElementByID(
								((IntergeoLinePerpendicularToLineThroughPoint) intergeoElement).getLine().getID())));
				logger.info("line_perpendicular_to_line_through_point\n" + " ID:" + intergeoElement.getID()
						+ " has been converted to JSXGraph perpendicular");
			} else if (intergeoElement instanceof IntergeoLineAngularBisectorOfThreePoints) {
				jsxGraph.addElement(new JSXGraphP3Bisector(intergeoElement.getID(),
						jsxGraph.getElementByID(((IntergeoLineAngularBisectorOfThreePoints) intergeoElement).getPoint1().getID()),
						jsxGraph.getElementByID(((IntergeoLineAngularBisectorOfThreePoints) intergeoElement).getPoint2().getID()),
						jsxGraph.getElementByID(((IntergeoLineAngularBisectorOfThreePoints) intergeoElement).getPoint3().getID())));
				logger.info("line_angular_bisector_of_three_points\n" + 
						"\n" + " ID:" + intergeoElement.getID()
						+ " has been converted to JSXGraph bisector");
			} else if (intergeoElement instanceof IntergeoCircleByCenterAndPoint) {
				jsxGraph.addElement(new JSXGraphPCCircle(intergeoElement.getID(),
						jsxGraph.getElementByID(
								((IntergeoCircleByCenterAndPoint) intergeoElement).getCenterPoint().getID()),
						jsxGraph.getElementByID(
								((IntergeoCircleByCenterAndPoint) intergeoElement).getThroughPoint().getID())));
				logger.info("circle_by_center_and_point\n" + " ID:" + intergeoElement.getID()
						+ " has been converted to JSXGraph circle");
			} else if (intergeoElement instanceof IntergeoCircleByThreePoints) {
				jsxGraph.addElement(new JSXGraphCircumCircle(intergeoElement.getID(),
						jsxGraph.getElementByID(((IntergeoCircleByThreePoints) intergeoElement).getPoint1().getID()),
						jsxGraph.getElementByID(((IntergeoCircleByThreePoints) intergeoElement).getPoint2().getID()),
						jsxGraph.getElementByID(((IntergeoCircleByThreePoints) intergeoElement).getPoint3().getID())));
				logger.info("circle_by_three_points\n" + " ID:" + intergeoElement.getID()
						+ " has been converted to JSXGraph circumcircle");
			}
		}
		logger.info("Conversion successfully completed");
		return jsxGraph;
	}
}