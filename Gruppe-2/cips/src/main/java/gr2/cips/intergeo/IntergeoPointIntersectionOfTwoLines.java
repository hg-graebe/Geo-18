package gr2.cips.intergeo;

/**
 * @author Duong Trung Duong
 *
 */
public class IntergeoPointIntersectionOfTwoLines extends IntergeoElement {
	private IntergeoElement line1;
	private IntergeoElement line2;
	
	public IntergeoPointIntersectionOfTwoLines(String id, IntergeoElement line1, IntergeoElement line2) {
		this.setID(id);
		this.setLine1(line1);
		this.setLine2(line2);
	}
	
	public void setLine1(IntergeoElement Line1) {
		this.line1 = Line1;
	}
	
	public void setLine2(IntergeoElement Line2) {
		this.line2 = Line2;
	}	
	
	public IntergeoElement getLine1() {
		return this.line1;
	}
	
	public IntergeoElement getLine2() {
		return this.line2;
	}	
	
	public double getX() {
		double a1 = this.getLine1().getA();
		double b1 = this.getLine1().getB();
		double c1 = this.getLine1().getC();
		return (-c1-b1*this.getY())/a1;
	}
	
	public double getY() {
		double a1 = this.getLine1().getA();
		double a2 = this.getLine2().getA();
		double b1 = this.getLine1().getB();
		double b2 = this.getLine2().getB();
		double c1 = this.getLine1().getC();
		double c2 = this.getLine2().getC();	
		return ((a2 * c1) / a1 - c2) / (b2 - (a2 * b1) / a1);
	}		
	
	public double getW() {
		return 1.0;
	}	
	
	@Override
	public String toString() {
		return "Line1:[" + line1.toString() + "], " + "Line2:[" + line2.toString()+"]";
	}	
}
