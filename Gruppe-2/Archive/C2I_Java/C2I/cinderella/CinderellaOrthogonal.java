package cinderella;

public class CinderellaOrthogonal extends CinderellaElement {
	CinderellaElement orthogonalLineOrSegment;
	CinderellaFreePoint throughPoint;

	public CinderellaOrthogonal(String name, CinderellaElement orthogonalLineOrSegment, CinderellaFreePoint throughPoint) {
		this.setName(name);
		this.setOrthogonalLineOrSegment(orthogonalLineOrSegment);
		this.setThroughPoint(throughPoint);
	}
	
	public void setOrthogonalLineOrSegment(CinderellaElement orthogonalLineOrSegment) {
		this.orthogonalLineOrSegment = orthogonalLineOrSegment;
	}
	
	public void setThroughPoint(CinderellaFreePoint throughPoint) {
		this.throughPoint = throughPoint;
	}	
	
	public CinderellaElement getOrthogonalLineOrSegment() {
		return this.orthogonalLineOrSegment;
	}		
	
	public CinderellaFreePoint getThroughPoint() {
		return this.throughPoint;
	}
	
}