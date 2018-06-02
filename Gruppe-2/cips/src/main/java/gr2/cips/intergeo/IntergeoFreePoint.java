package gr2.cips.intergeo;

/**
 * @author Duong Trung Duong
 *
 */
public class IntergeoFreePoint extends IntergeoElement {
	private double x;
	private double y;
	private double w;
	
	public IntergeoFreePoint(String id, double x, double y, double w) {
		this.setID(id);
		this.setX(x);
		this.setY(y);
		this.setW(w);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}	
	
	public void setW(double w) {
		this.w = w;
	}		
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}	

	public double getW() {
		return this.w;
	}	
	
	@Override
	public String toString() {
		return "X:" + getX()/getW() + ", Y:" + getY()/getW();
	}	
}
