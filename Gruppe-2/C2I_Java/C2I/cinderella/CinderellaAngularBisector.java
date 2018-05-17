package cinderella;

public class CinderellaAngularBisector extends CinderellaElement {
	private CinderellaMeet meet;
	
	public CinderellaAngularBisector(String name, CinderellaMeet meet) {
		this.setName(name);
		this.setMeet(meet);
	}
	
	public void setMeet(CinderellaMeet meet) {
		this.meet = meet;
	}	
	
	public CinderellaMeet getMeet() {
		return this.meet;
	}	
}
