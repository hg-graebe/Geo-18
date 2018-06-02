package gr2.cips.geoproofscheme;

/**
 * @author Duong Trung Duong
 *
 */
public abstract class GeoProofSchemeElement {
	private String id;

	public GeoProofSchemeElement() {

	}

	public void setID(String ID) {
		this.id = ID;
	}

	public String getID() {
		return id;
	}

	public boolean isParameter() {
		return this instanceof GeoProofSchemeParameter ? true : false;
	}

	public boolean isVariable() {
		return this instanceof GeoProofSchemeVariable ? true : false;
	}

	public boolean isPoint() {
		return this instanceof GeoProofSchemeFreePoint || this instanceof GeoProofSchemeMidPoint
				|| this instanceof GeoProofSchemeIntersectionPoint ? true : false;
	}

	public boolean isLine() {
		return this instanceof GeoProofSchemePPLine || this instanceof GeoProofSchemeParLine ? true : false;
	}

	public boolean isCircle() {
		return this instanceof GeoProofSchemeP3Circle || this instanceof GeoProofSchemePCCircle ? true : false;
	}

	public double getX() {
		return Double.NaN;
	}

	public double getY() {
		return Double.NaN;
	}

	public double getA() {
		return Double.NaN;
	}

	public double getB() {
		return Double.NaN;
	}

	public double getC() {
		return Double.NaN;
	}

	public String toString() {
		return null;
	}
}
