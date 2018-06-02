package cinderella;

public class CinderellaMeet extends CinderellaElement {
	private CinderellaElement lineOrSegment1;
	private CinderellaElement lineOrSegment2;
	
	public CinderellaMeet(String name, CinderellaElement lineOrSegment1, CinderellaElement lineOrSegment2) {
		this.setName(name);
		this.setLineOrSegment1(lineOrSegment1);
		this.setLineOrSegment2(lineOrSegment2);
	}
	
	public void setLineOrSegment1(CinderellaElement lineOrSegment1) {
		this.lineOrSegment1 = lineOrSegment1;
	}
	
	public void setLineOrSegment2(CinderellaElement lineOrSegment2) {
		this.lineOrSegment2 = lineOrSegment2;
	}	
	
	public CinderellaElement getLineOrSegment1() {
		return this.lineOrSegment1;
	}
	
	public CinderellaElement getLineOrSegment2() {
		return this.lineOrSegment2;
	}	
}
