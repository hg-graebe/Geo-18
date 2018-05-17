package cinderella;

public class CinderellaThrough extends CinderellaElement {
	private CinderellaFreePoint throughPoint;
	private CinderellaFreePoint tempPoint;	

	public CinderellaThrough(String name, CinderellaFreePoint throughPoint, CinderellaFreePoint tempPoint) {
		this.setName(name);
		this.setThroughPoint(throughPoint);
		this.setTempPoint(tempPoint);
	}
	
	public void setThroughPoint(CinderellaFreePoint throughPoint) {
		this.throughPoint = throughPoint;
	}
	
	public void setTempPoint(CinderellaFreePoint tempPoint) {
		this.tempPoint = tempPoint;
	}	
	
	public CinderellaFreePoint getThroughPoint() {
		return this.throughPoint;
	}
	
	public CinderellaFreePoint getTempPoint() {
		return this.tempPoint;
	}		
}
