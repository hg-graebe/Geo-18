package cinderella;

public abstract class CinderellaElement {
	private String name;
	
	public CinderellaElement() {
		 
	}
	
	public CinderellaElement(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

