package cinderella;

public class CinderellaMid extends CinderellaElement {
	CinderellaFreePoint point1;
	CinderellaFreePoint point2;
	
	public CinderellaMid(String name, CinderellaFreePoint point1, CinderellaFreePoint point2) {
		this.setName(name);
		this.setPoint1(point1);
		this.setPoint2(point2);
	}
	
	public void setPoint1(CinderellaFreePoint point1) {
		this.point1 = point1;
	}
	
	public void setPoint2(CinderellaFreePoint point2) {
		this.point2 = point2;
	}	
	
	public CinderellaFreePoint getPoint1() {
		return this.point1;
	}
	
	public CinderellaFreePoint getPoint2() {
		return this.point2;
	}	
}
