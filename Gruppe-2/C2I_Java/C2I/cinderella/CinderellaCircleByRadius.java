package cinderella;

public class CinderellaCircleByRadius extends CinderellaElement {
	private CinderellaFreePoint centrePoint;
	private double radius;
	
	public CinderellaCircleByRadius(String name, CinderellaFreePoint centrePoint, double radius) {
		this.setName(name);
		this.setCentrePoint(centrePoint);
		this.setRadius(radius);
	}
	
	public void setCentrePoint(CinderellaFreePoint centrePoint) {
		this.centrePoint = centrePoint;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public CinderellaFreePoint getCentrePoint() {
		return this.centrePoint;
	}
	
	public double getRadius() {
		return this.radius;
	}
}