package gr2.cips.intergeo;

/**
 * @author Duong Trung Duong
 *
 */
public class IntergeoLineThroughTwoPoints extends IntergeoElement {
	private IntergeoElement point1;
	private IntergeoElement point2;
	
	public IntergeoLineThroughTwoPoints(String id, IntergeoElement point1, IntergeoElement point2) {
		this.setID(id);
		this.setPoint1(point1);
		this.setPoint2(point2);
	}
	
	public void setPoint1(IntergeoElement point1) {
		this.point1 = point1;
	}
	
	public void setPoint2(IntergeoElement point2) {
		this.point2 = point2;
	}	
	
	public IntergeoElement getPoint1() {
		return this.point1;
	}
	
	public IntergeoElement getPoint2() {
		return this.point2;
	}		
	
	public double getA() {
		return this.getPoint2().getY() - this.getPoint1().getY();
	}
	
	public double getB() {
		return this.getPoint1().getX() - this.getPoint2().getX();
	}	
	
	public double getC() {
		return this.getPoint2().getX()*this.getPoint1().getY() - this.getPoint1().getX()*this.getPoint2().getY();	
	}	
	
	@Override
	public String toString() {
		return "Point1:(" + point1.toString() + "), Point2:(" +  point2.toString() + ")";
	}	
}
