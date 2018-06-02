package gr2.cips.cinderella;

/**
 * @author Duong Trung Duong
 *
 */
public class CinderellaOrthogonal extends CinderellaElement {
	CinderellaElement orthogonalLineOrSegment;
	CinderellaElement throughPoint;

	public CinderellaOrthogonal(String id, CinderellaElement orthogonalLineOrSegment, CinderellaElement throughPoint) {
		this.setID(id);
		this.setOrthogonalLineOrSegment(orthogonalLineOrSegment);
		this.setThroughPoint(throughPoint);
	}
	
	public void setOrthogonalLineOrSegment(CinderellaElement orthogonalLineOrSegment) {
		this.orthogonalLineOrSegment = orthogonalLineOrSegment;
	}
	
	public void setThroughPoint(CinderellaElement throughPoint) {
		this.throughPoint = throughPoint;
	}	
	
	public CinderellaElement getOrthogonalLineOrSegment() {
		return this.orthogonalLineOrSegment;
	}		
	
	public CinderellaElement getThroughPoint() {
		return this.throughPoint;
	}
	
	@Override
	public String toString() {
		return "Point:(" + throughPoint.toString() + "), " + "Line:[" + orthogonalLineOrSegment.toString() + "]";
	}
}