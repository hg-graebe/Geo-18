package gr2.cips.cinderella;

/**
 * @author Duong Trung Duong
 *
 */
public class CinderellaAngularBisector extends CinderellaElement {
	private CinderellaMeet meet;
	
	public CinderellaAngularBisector(String id, CinderellaMeet meet) {
		this.setID(id);
		this.setMeet(meet);
	}
	
	public void setMeet(CinderellaMeet meet) {
		this.meet = meet;
	}	
	
	public CinderellaMeet getMeet() {
		return this.meet;
	}	
}
