package gr2.cips.geoproofscheme;

/**
 * @author Duong Trung Duong
 *
 */
public class GeoProofSchemeVariable extends GeoProofSchemeElement {
	private double value;
	
	public GeoProofSchemeVariable(String id, double value) {
		this.setID(id);
		this.setValue(value);
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return Double.toString(getValue());
	}	
}
