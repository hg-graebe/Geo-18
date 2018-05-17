package cinderella;

public class CinderellaParallel extends CinderellaElement {
	CinderellaElement parallelLineOrSegment;
	CinderellaFreePoint throughPoint;

	public CinderellaParallel(String name, CinderellaElement parallelLineOrSegment, CinderellaFreePoint throughPoint) {
		this.setName(name);
		this.setParallelLineOrSegment(parallelLineOrSegment);
		this.setThroughPoint(throughPoint);
	}
	
	public void setParallelLineOrSegment(CinderellaElement parallelLineOrSegment) {
		this.parallelLineOrSegment = parallelLineOrSegment;
	}
	
	public void setThroughPoint(CinderellaFreePoint throughPoint) {
		this.throughPoint = throughPoint;
	}	
	
	public CinderellaElement getParallelLineOrSegment() {
		return this.parallelLineOrSegment;
	}		
	
	public CinderellaFreePoint getThroughPoint() {
		return this.throughPoint;
	}
	
}
