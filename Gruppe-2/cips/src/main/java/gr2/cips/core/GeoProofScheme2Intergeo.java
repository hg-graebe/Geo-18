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
import gr2.cips.intergeo.Intergeo;
import gr2.cips.intergeo.IntergeoCircleByCenterAndPoint;
import gr2.cips.intergeo.IntergeoCircleByThreePoints;
import gr2.cips.intergeo.IntergeoFreePoint;
import gr2.cips.intergeo.IntergeoLineAngularBisectorOfThreePoints;
import gr2.cips.intergeo.IntergeoLineParallelToLineThroughPoint;
import gr2.cips.intergeo.IntergeoLinePerpendicularToLineThroughPoint;
import gr2.cips.intergeo.IntergeoLineThroughTwoPoints;
import gr2.cips.intergeo.IntergeoMidPointOfTwoPoints;
import gr2.cips.intergeo.IntergeoPointIntersectionOfTwoLines;

/**
 * @author Duong Trung Duong
 *
 */
public class GeoProofScheme2Intergeo {
	final static Logger logger = Logger.getLogger(GeoProofScheme2Intergeo.class);
	private GeoProofScheme geoProofScheme;

	public GeoProofScheme2Intergeo() {

	}

	public GeoProofScheme2Intergeo(GeoProofScheme geoProofScheme) {
		this.geoProofScheme = geoProofScheme;
	}

	public void setGeoProofScheme(GeoProofScheme geoProofScheme) {
		this.geoProofScheme = geoProofScheme;
	}

	public GeoProofScheme getGeoProofScheme() {
		return this.geoProofScheme;
	}

	public Intergeo convert() {
		Intergeo intergeo = new Intergeo();
		for (GeoProofSchemeElement geoProofSchemeElement : geoProofScheme.getGeoProofSchemeElements()) {
			if (geoProofSchemeElement instanceof GeoProofSchemeFreePoint) {
				intergeo.addElement(new IntergeoFreePoint(geoProofSchemeElement.getID(), geoProofSchemeElement.getX(),
						geoProofSchemeElement.getY(), 1.0));
				logger.info("point ID:" + geoProofSchemeElement.getID() + " has been converted");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeMidPoint) {
				intergeo.addElement(new IntergeoMidPointOfTwoPoints(geoProofSchemeElement.getID(),
						intergeo.getElementByID(((GeoProofSchemeMidPoint) geoProofSchemeElement).getPoint1().getID()),
						intergeo.getElementByID(((GeoProofSchemeMidPoint) geoProofSchemeElement).getPoint2().getID())));
				logger.info("midpoint ID:" + geoProofSchemeElement.getID() + " has been converted");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeIntersectionPoint) {
				intergeo.addElement(new IntergeoPointIntersectionOfTwoLines(geoProofSchemeElement.getID(),
						intergeo.getElementByID(
								((GeoProofSchemeIntersectionPoint) geoProofSchemeElement).getLine1().getID()),
						intergeo.getElementByID(
								((GeoProofSchemeIntersectionPoint) geoProofSchemeElement).getLine2().getID())));
				logger.info("intersection_point ID:" + geoProofSchemeElement.getID() + " has been converted");
			} else if (geoProofSchemeElement instanceof GeoProofSchemePPLine) {
				intergeo.addElement(new IntergeoLineThroughTwoPoints(geoProofSchemeElement.getID(),
						intergeo.getElementByID(((GeoProofSchemePPLine) geoProofSchemeElement).getPoint1().getID()),
						intergeo.getElementByID(((GeoProofSchemePPLine) geoProofSchemeElement).getPoint2().getID())));
				logger.info("pp_line ID:" + geoProofSchemeElement.getID() + " has been converted");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeParLine) {
				intergeo.addElement(new IntergeoLineParallelToLineThroughPoint(geoProofSchemeElement.getID(),
						intergeo.getElementByID(((GeoProofSchemeParLine) geoProofSchemeElement).getPoint().getID()),
						intergeo.getElementByID(((GeoProofSchemeParLine) geoProofSchemeElement).getLine().getID())));
				logger.info("par_line ID:" + geoProofSchemeElement.getID() + " has been converted");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeOrthoLine) {
				intergeo.addElement(new IntergeoLinePerpendicularToLineThroughPoint(geoProofSchemeElement.getID(),
						intergeo.getElementByID(((GeoProofSchemeOrthoLine) geoProofSchemeElement).getPoint().getID()),
						intergeo.getElementByID(((GeoProofSchemeOrthoLine) geoProofSchemeElement).getLine().getID())));
				logger.info("ortho_line ID:" + geoProofSchemeElement.getID() + " has been converted");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeP3Bisector) {
				intergeo.addElement(new IntergeoLineAngularBisectorOfThreePoints(geoProofSchemeElement.getID(),
						intergeo.getElementByID(((GeoProofSchemeP3Bisector) geoProofSchemeElement).getPoint1().getID()),
						intergeo.getElementByID(((GeoProofSchemeP3Bisector) geoProofSchemeElement).getPoint2().getID()),
						intergeo.getElementByID(((GeoProofSchemeP3Bisector) geoProofSchemeElement).getPoint3().getID())));
				logger.info("p3_bisector ID:" + geoProofSchemeElement.getID() + " has been converted");
			} else if (geoProofSchemeElement instanceof GeoProofSchemePCCircle) {
				intergeo.addElement(new IntergeoCircleByCenterAndPoint(geoProofSchemeElement.getID(),
						intergeo.getElementByID(
								((GeoProofSchemePCCircle) geoProofSchemeElement).getCenterPoint().getID()),
						intergeo.getElementByID(
								((GeoProofSchemePCCircle) geoProofSchemeElement).getThroughPoint().getID())));
				logger.info("pc_circle ID:" + geoProofSchemeElement.getID() + " has been converted");
			} else if (geoProofSchemeElement instanceof GeoProofSchemeP3Circle) {
				intergeo.addElement(new IntergeoCircleByThreePoints(geoProofSchemeElement.getID(),
						intergeo.getElementByID(((GeoProofSchemeP3Circle) geoProofSchemeElement).getPoint1().getID()),
						intergeo.getElementByID(((GeoProofSchemeP3Circle) geoProofSchemeElement).getPoint2().getID()),
						intergeo.getElementByID(((GeoProofSchemeP3Circle) geoProofSchemeElement).getPoint3().getID())));
				logger.info("p3_circle ID:" + geoProofSchemeElement.getID() + " has been converted");
			}
		}
		logger.info("Conversion successfully completed");
		return intergeo;
	}
}
