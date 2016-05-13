package lesson160504;

public class Insurance {

	private final String name;

	public Insurance(String name) {
		this.name = name;
	}
	
	// "name" can't be null -> no Optional
	// "name" must be initialized

	public String getName() {
		return name;
	}
	
}
