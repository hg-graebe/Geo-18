package gr2.cips.jsxgraph;

/**
 * @author Duong Trung Duong
 *
 */
public abstract class JSXGraphElement {
	public static String INDEPENDENT_POINT_PROP = "strokecolor:'red',fillColor:'red'";
	public static String DEPENDENT_POINT_PROP = "strokecolor:'green',fillColor:'green'";
	public static String INDEPENDENT_LINE_PROP = "strokecolor:'blue',fillColor:'blue'";
	public static String DEPENDENT_LINE_PROP = "strokecolor:'black',fillColor:'black'";

	private String id;
	private String properties;
	
	public JSXGraphElement() {
		
	}
	
	public JSXGraphElement(String id, String properties) {
		this.id = id;
		this.properties = properties;
	}

	public void setID(String id) {
		this.id = id;
	}
	
	public void setProperties(String properties) {
		this.properties = properties;
	}	
	
	public String getID() {
		return this.id;
	}
	
	public String getProperties() {
		return this.properties;
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
