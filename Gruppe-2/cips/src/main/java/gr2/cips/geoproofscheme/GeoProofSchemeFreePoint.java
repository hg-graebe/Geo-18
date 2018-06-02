package gr2.cips.geoproofscheme;

/**
 * @author Duong Trung Duong
 *
 */
public class GeoProofSchemeFreePoint extends GeoProofSchemeElement {
	private double x;
	private double y;
	
	public GeoProofSchemeFreePoint(String id, double x, double y) {
		this.setID(id);
		this.setX(x);
		this.setY(y);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}	
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}	

	@Override
	public String toString() {
		return "X:" + getX() + ", Y:" + getY();
	}	
}
