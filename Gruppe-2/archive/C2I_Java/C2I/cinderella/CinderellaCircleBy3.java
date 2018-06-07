package cinderella;

public class CinderellaCircleBy3 extends CinderellaElement {
	private CinderellaFreePoint point1;
	private CinderellaFreePoint point2;
	private CinderellaFreePoint point3;
	
	public CinderellaCircleBy3(String name, CinderellaFreePoint point1, CinderellaFreePoint point2, CinderellaFreePoint point3) {
		this.setName(name);
		this.setPoint1(point1);
		this.setPoint2(point2);
		this.setPoint3(point3);
	}
	
	public void setPoint1(CinderellaFreePoint point1) {
		this.point1 = point1;
	}
	
	public void setPoint2(CinderellaFreePoint point2) {
		this.point2 = point2;
	}	
	
	public void setPoint3(CinderellaFreePoint point3) {
		this.point3 = point3;
	}		
	
	public CinderellaFreePoint getPoint1() {
		return this.point1;
	}
	
	public CinderellaFreePoint getPoint2() {
		return this.point2;
	}	
	
	public CinderellaFreePoint getPoint3() {
		return this.point3;
	}	
}
