package cinderella;

public class CinderellaFreePoint extends CinderellaElement {
	private double x;
	private double y;
	
	public CinderellaFreePoint(String name, double x, double y) {
		this.setName(name);
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
}
