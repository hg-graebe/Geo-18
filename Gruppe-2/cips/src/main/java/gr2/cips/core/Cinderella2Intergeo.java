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
import gr2.cips.intergeo.Intergeo;
import gr2.cips.intergeo.IntergeoCircleByCenterAndPoint;
import gr2.cips.intergeo.IntergeoCircleByThreePoints;
import gr2.cips.intergeo.IntergeoFreePoint;
import gr2.cips.intergeo.IntergeoLineParallelToLineThroughPoint;
import gr2.cips.intergeo.IntergeoLinePerpendicularToLineThroughPoint;
import gr2.cips.intergeo.IntergeoLineThroughTwoPoints;
import gr2.cips.intergeo.IntergeoMidPointOfTwoPoints;
import gr2.cips.intergeo.IntergeoPointIntersectionOfTwoLines;

/**
 * @author Duong Trung Duong
 *
 */
public class Cinderella2Intergeo {
	final static Logger logger = Logger.getLogger(Cinderella2Intergeo.class);
	private Cinderella cinderella;

	public Cinderella2Intergeo() {

	}

	public Cinderella2Intergeo(Cinderella cinderella) {
		this.cinderella = cinderella;
	}

	public Intergeo convert() {
		Intergeo intergeo = new Intergeo();

		for (CinderellaElement cinderellaElement : cinderella.getCinderellaElements()) {
			if (cinderellaElement instanceof CinderellaFreePoint) {
				intergeo.addElement(new IntergeoFreePoint(cinderellaElement.getID(), cinderellaElement.getX(),
						cinderellaElement.getY(), 1.0));
				logger.info("FreePoint ID:" + cinderellaElement.getID() + " has been converted");
			} else if (cinderellaElement instanceof CinderellaMeet) {
				intergeo.addElement(new IntergeoPointIntersectionOfTwoLines(cinderellaElement.getID(),
						intergeo.getElementByID(((CinderellaMeet) cinderellaElement).getLineOrSegment1().getID()),
						intergeo.getElementByID(((CinderellaMeet) cinderellaElement).getLineOrSegment2().getID())));
				logger.info("Meet ID:" + cinderellaElement.getID() + " has been converted");
			} else if (cinderellaElement instanceof CinderellaMid) {
				intergeo.addElement(new IntergeoMidPointOfTwoPoints(cinderellaElement.getID(),
						intergeo.getElementByID(((CinderellaMid) cinderellaElement).getPoint1().getID()),
						intergeo.getElementByID(((CinderellaMid) cinderellaElement).getPoint2().getID())));
				logger.info("Mid ID:" + cinderellaElement.getID() + " has been converted");
			} else if (cinderellaElement instanceof CinderellaJoin) {
				intergeo.addElement(new IntergeoLineThroughTwoPoints(cinderellaElement.getID(),
						intergeo.getElementByID(((CinderellaJoin) cinderellaElement).getPoint1().getID()),
						intergeo.getElementByID(((CinderellaJoin) cinderellaElement).getPoint2().getID())));
				logger.info("Join ID:" + cinderellaElement.getID() + " has been converted");
			} else if (cinderellaElement instanceof CinderellaParallel) {
				intergeo.addElement(new IntergeoLineParallelToLineThroughPoint(cinderellaElement.getID(),
						intergeo.getElementByID(((CinderellaParallel) cinderellaElement).getPoint().getID()),
						intergeo.getElementByID(((CinderellaParallel) cinderellaElement).getlineOrSegment().getID())));
				logger.info("Parallel ID:" + cinderellaElement.getID() + " has been converted");
			} else if (cinderellaElement instanceof CinderellaOrthogonal) {
				intergeo.addElement(new IntergeoLinePerpendicularToLineThroughPoint(cinderellaElement.getID(),
						intergeo.getElementByID(((CinderellaOrthogonal) cinderellaElement).getThroughPoint().getID()),
						intergeo.getElementByID(
								((CinderellaOrthogonal) cinderellaElement).getOrthogonalLineOrSegment().getID())));
				logger.info("Orthogonal ID:" + cinderellaElement.getID() + " has been converted");
			} else if (cinderellaElement instanceof CinderellaCircleMP) {
				intergeo.addElement(new IntergeoCircleByCenterAndPoint(cinderellaElement.getID(),
						intergeo.getElementByID(((CinderellaCircleMP) cinderellaElement).getCenterPoint().getID()),
						intergeo.getElementByID(((CinderellaCircleMP) cinderellaElement).getThroughPoint().getID())));
				logger.info("CircleMP ID:" + cinderellaElement.getID() + " has been converted");
			} else if (cinderellaElement instanceof CinderellaCircleBy3) {
				intergeo.addElement(new IntergeoCircleByThreePoints(cinderellaElement.getID(),
						intergeo.getElementByID(((CinderellaCircleBy3) cinderellaElement).getPoint1().getID()),
						intergeo.getElementByID(((CinderellaCircleBy3) cinderellaElement).getPoint2().getID()),
						intergeo.getElementByID(((CinderellaCircleBy3) cinderellaElement).getPoint3().getID())));
				logger.info("CircleBy3 ID:" + cinderellaElement.getID() + " has been converted");
			}
		}
		logger.info("Conversion successfully completed");
		return intergeo;
	}
}
